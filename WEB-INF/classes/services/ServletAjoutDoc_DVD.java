package services;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2022.*;

/**
 * Servlet d'ajout de document
 */
public class ServletAjoutDoc_DVD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAjoutDoc_DVD() {
        super();
    }

    // sunchronized object ou connection avant executeQuery
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String msg = "";
		
		// champs obligatoires
		String titre = (String)request.getParameter("titre");
		String de = (String)request.getParameter("de");
		String categorie = (String)request.getParameter("categorie");
		String anneeParution = (String)request.getParameter("anneeParution");
		String duree = (String)request.getParameter("duree");
		String ageLimite = (String)request.getParameter("ageLimite");
		
		String type = (String)request.getParameter("type");
		int typeInt = Integer.parseInt(type);
		
		// formatage pour les requêtes
		titre = formaterStringUrl(titre);
		de = formaterStringUrl(de);
		categorie = formaterStringUrl(categorie);
		anneeParution = formaterStringUrl(anneeParution);		
		
		// vérifications de saisies
		if(incomplet(titre)) {
			msg += "Le titre est obligatoire <br>";
		}
		if(incomplet(de)) {
			msg += "Le réalisateur est obligatoire<br>"; 
		}
		if(incomplet(anneeParution)|| !isNumeric(anneeParution)) {
			msg += "L'année est obligatoire. Ce doit être un numérique<br>"; 
		}
		if(incomplet(ageLimite)|| !isNumeric(ageLimite)) {
			msg += "L'âge est obligatoire. Ce doit être un numérique<br>"; 
		}
		// si un message est disponible
		if(!msg.equals("")){
			request.setAttribute("titre", titre);
			request.setAttribute("de", de);
			request.setAttribute("categorie", categorie);
			request.setAttribute("anneeParution", anneeParution);
			request.setAttribute("duree", duree);
			request.setAttribute("ageLimite", ageLimite);
			
			request.setAttribute("msg", msg);
			
			request.getRequestDispatcher("ajoutDoc_DVD.jsp").forward(request, response);
		}
		
		try  {
			
			Mediatheque.getInstance().ajoutDocument(typeInt, titre, de, categorie, anneeParution, duree, ageLimite);
			
			request.setAttribute("msg", "Document ajouté !");
		} 
		catch (Exception e) {
			request.setAttribute("msg", "Une erreur s'est produite : " + e);
		}

		request.getRequestDispatcher("resultatAjoutDoc.jsp").forward(request, response);
		
	}
	
	/*
	 * @return true si champ non rempli
	 */
	private boolean incomplet(String champ) {
		return champ==null || champ.equals("null") || champ.equals("");
	}
	
	/*
	 * @return true si champ numérique
	 */
	private static boolean isNumeric(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/*
	 * @returnstring formatée afin d'éviter des erreurs dans la requête sql qui suivra
	 */
	public static String remplacerString(String url, String avant, String apres) {
		Pattern p = Pattern.compile(avant);  
		Matcher m = p.matcher(url) ;  
		  
		return m.replaceAll(apres) ; 
	}
	
	/*
	 * @returnstring formatée de tous les caractères pouvant poser problème
	 */
	public String formaterStringUrl(String url) {
		url = remplacerString(url, ";", (char)59 + "");
		url = remplacerString(url, ":", (char)58 + "");
		url = remplacerString(url, ('"' + ""), ("'"));
		url = remplacerString(url, ("'"), (char)39 + "");
		return url;
	}
	
}
