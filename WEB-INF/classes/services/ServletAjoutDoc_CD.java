package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2022.*;

/**
 * Servlet d'ajout de document
 */
public class ServletAjoutDoc_CD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAjoutDoc_CD() {
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
		String nbMorceaux = (String)request.getParameter("nbMorceaux");
		
		String type = (String)request.getParameter("type");
		int typeInt = Integer.parseInt(type);	
		
		// vérifications de saisies
		if(incomplet(titre)) {
			msg += "Le titre est obligatoire <br>";
		}
		if(incomplet(de)) {
			msg += "L'artiste est obligatoire<br>"; 
		}
		if(incomplet(anneeParution)|| !isNumeric(anneeParution)) {
			msg += "L'année est obligatoire. Ce doit être un numérique<br>"; 
		}
		if(incomplet(nbMorceaux)|| !isNumeric(nbMorceaux)) {
			msg += "Le nombre de morceaux est obligatoire. Ce doit être un numérique<br>"; 
		}
		// si un message est disponible
		if(!msg.equals("")){
			request.setAttribute("titre", titre);
			request.setAttribute("de", de);
			request.setAttribute("categorie", categorie);
			request.setAttribute("anneeParution", anneeParution);
			request.setAttribute("nbMorceaux", nbMorceaux);
			
			request.setAttribute("msg", msg);
			
			request.getRequestDispatcher("ajoutDoc_CD.jsp").forward(request, response);
		}
		
		try  {
			
			Mediatheque.getInstance().ajoutDocument(typeInt, titre, de, categorie, anneeParution, nbMorceaux);
			
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

}
