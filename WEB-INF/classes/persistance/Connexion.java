package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	private static Connection c;
	
	public static Connection getC() {
		return c;
	}

	// ouvre la connexion
	public static Connection ouvrirConnection () {
		try {
			// Chargement du driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Connexion à la base MySQL
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediawebProjet", "root", "Hermione1+");
			return c;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// ferme la connexion
	public static void fermerConnection() {
		try {
			c.close();
			System.out.println("connection fermée");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
