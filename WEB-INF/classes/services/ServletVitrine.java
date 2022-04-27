package services;

import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2022.*;

/**
 * Servlet d'ajout d'affichage de tous les documents disponible pour l'emprunt
 */
public class ServletVitrine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVitrine() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// ressource critique : Mediatheque.instance
		synchronized(Mediatheque.getInstance()) {
			List<Document> docs = Mediatheque.getInstance().tousLesDocumentsDisponibles();
			request.setAttribute("listeDocs", docs);
		}
		request.getRequestDispatcher("emprunt.jsp").forward(request, response);
	}

}
