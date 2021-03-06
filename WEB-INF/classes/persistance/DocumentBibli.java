package persistance;

import java.sql.SQLException;
import java.sql.Statement;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

/* Classe généraliste pour les documents */
public class DocumentBibli implements Document {
	
	private int num;
	private String titre;
	private String anneeParution;
	private String categorie;

	private int id_u;

	public DocumentBibli(int num, String titre, String anneeParution, int id_u, String categorie) {
		this.num = num;
		this.titre = titre;
		this.anneeParution = anneeParution;
		this.id_u = id_u;
		this.categorie = categorie;
	}
	
	@Override
	public boolean disponible() {
		return (id_u == 0 ? true : false);
	}

	@Override
	public void retour() {
		this.id_u = 0;
		
		synchronized(Connexion.ouvrirConnection()) {
			try {
				Statement requeteStatique = Connexion.getC().createStatement();
				requeteStatique.executeUpdate
				  ("UPDATE Document SET idAbonne=null WHERE num=" + this.num + ";");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Connexion.fermerConnection();
		}
		
	}
	
	@Override
	public void emprunt(Utilisateur u) throws Exception {
		if(!disponible()) throw new Exception("Document non disponible");
		if(!(boolean) u.data()[2]) throw new Exception("Votre abonnement est désactivé, vous ne pouvez pas emprunter");
		this.id_u = (int) u.data()[0];
		
		synchronized(Connexion.ouvrirConnection()) {
			try {
				Statement requeteStatique = Connexion.getC().createStatement();
				requeteStatique.executeUpdate
				  ("UPDATE Document SET idAbonne = " + this.id_u + " WHERE num = " + this.num +";");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Connexion.fermerConnection();
		}
		
		
	}
	
	@Override
	public String toString() {
		return " n°" + this.num + " '" + this.titre + "' [" + this.anneeParution + "] de " 
				+ " \t-*-* catégorie : " + this.categorie + " *-*-\t";
				
	}

}
