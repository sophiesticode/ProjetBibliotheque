package services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2022.*;

/**
 * Servlet de retour de document
 */
public class ServletRetourDoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRetourDoc() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// vérifications de saisies
		if(incomplet(request.getParameter("numDoc")) || !isNumeric(request.getParameter("numDoc"))){ 
			String msg = "Vous devez renseigner le numéro du document";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("retourDoc.jsp").forward(request, response);
		}
		int num = Integer.parseInt(request.getParameter("numDoc"));

		// ressource critique : Mediatheque.instance
		synchronized(Mediatheque.getInstance()) {
			Document doc = Mediatheque.getInstance().getDocument(num);
		
			if(doc == null) {
				String msg = "Ce document n'existe pas !";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("retourDoc.jsp").forward(request, response);
			}
		
			if(doc.disponible()) {
				String msg = "Ce document n'est pas à rendre, il est disponible !";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("retourDoc.jsp").forward(request, response);
			}
		
			HttpSession session = request.getSession(true);
			Utilisateur user = (Utilisateur) session.getAttribute("user");
		
			try {
				Mediatheque.getInstance().retour(doc, user);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		
			request.setAttribute("doc", doc);
		}

		request.getRequestDispatcher("resultatRetourDoc.jsp").forward(request, response);
	}
	
	/*
	 * @return true si champ non rempli
	 */
	private boolean incomplet(String champ) {
		return champ.equals("null") || champ.equals("");
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
