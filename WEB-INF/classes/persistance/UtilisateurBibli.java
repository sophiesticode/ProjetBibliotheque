package persistance;

import mediatek2022.Utilisateur;


/* Classe généraliste pour les utilisateurs */
public abstract class UtilisateurBibli implements Utilisateur {
	private String login;
	private int id;
	// on ne stocke le mot de passe que dans la BD
	
	public UtilisateurBibli(int id, String login) {
		this.id = id;
		this.login = login;
	}
	
	/*
	 * return true si est bibliothecaire
	 */
	public boolean isBibliothecaire() {
		return false;
	}
	
	@Override
	public String name() {
		return login;
	}
	
	// données supplémentaires spécifiques au type d'utilisateur
	/*
	 * @return data[0] : id
	 */
	public Object[] data() {
		Object[] donnees = new Object[1];
		donnees[0] = this.id;
		return donnees;
	}

}
