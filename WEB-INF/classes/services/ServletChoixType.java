package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet d'ajout d'affichage de tous les documents disponible pour l'emprunt
 */
public class ServletChoixType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletChoixType() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String type = request.getParameter("type");
		int typeInt = Integer.parseInt(type);
		
		switch(typeInt) {
		case 1: //CD
			request.getRequestDispatcher("ajoutDoc_CD.jsp").forward(request, response);
		case 2: //DVD
			request.getRequestDispatcher("ajoutDoc_DVD.jsp").forward(request, response);
		case 3: //Livre
			request.getRequestDispatcher("ajoutDoc_Livre.jsp").forward(request, response);
		}
		
	}

}
