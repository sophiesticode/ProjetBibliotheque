package services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2022.*;

/**
 * Servlet d'emprunt de document
 */
public class ServletEmpruntDoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEmpruntDoc() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// le numéro est dans un champ caché du formulaire donc pas d'erreurs de saisie possibles
		int num = Integer.parseInt(request.getParameter("numDoc"));
		
		// ressource critique : Mediatheque.instance
		synchronized(Mediatheque.getInstance()) {
			Document doc = Mediatheque.getInstance().tousLesDocumentsDisponibles().get(num);
		
			HttpSession session = request.getSession(true);
			Utilisateur user = (Utilisateur) session.getAttribute("user");
			
			try {
				Mediatheque.getInstance().emprunt(doc, user);
			} catch (Exception e) {
				request.setAttribute("msg", e.getMessage());
			}
			
			request.setAttribute("doc", doc);
		}

		request.getRequestDispatcher("resultatEmpruntDoc.jsp").forward(request, response);
	}
	
}