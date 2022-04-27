package persistance;

/* Un bibliothécaire de la médiathèque */
public class Bibliothecaire extends UtilisateurBibli {
	
	public Bibliothecaire(int id, String nom) {
		super(id, nom);
	}

	public boolean isBibliothecaire() {
		return true;
	}
}
