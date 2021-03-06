package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2022.*;

/**
 * Servlet de connexion
 */
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnexion() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		String login = request.getParameter("login");
		String mdp = request.getParameter("mdp");
		
		// vérifications de saisies
		if(incomplet(login) || incomplet(mdp)) {
			request.setAttribute("msg", "veuillez remplir tous les champs"); 
			request.getRequestDispatcher("connexion.jsp").forward(request, response);
		}
		
		// ressource critique : Mediatheque.instance
		synchronized(Mediatheque.getInstance()) {
			Utilisateur user = Mediatheque.getInstance().getUser(login, mdp);
			session.setAttribute("user", user); 
		}
		
		request.getRequestDispatcher("resultatConnexion.jsp").forward(request, response);
	}
	
	/*
	 * @return true si champ non rempli
	 */
	private boolean incomplet(String champ) {
		return champ.equals("null") || champ.equals("");
	}

}
