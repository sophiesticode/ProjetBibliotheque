package services;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Servlet d'initialisation : chargement de persistance.MediathequeData et persistance.Connexion
 */
@WebListener
public class ServletInit implements ServletContextListener {
	
	// Au démarrage de l'appli
	@Override
	public void contextInitialized(ServletContextEvent event){
		
		try {
			Class.forName("persistance.MediathequeData");
			Class.forName("persistance.Connexion");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

}
