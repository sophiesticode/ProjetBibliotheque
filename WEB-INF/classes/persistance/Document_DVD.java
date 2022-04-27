package persistance;

import mediatek2022.Utilisateur;

/* Un DVD de la médiathèque */
public class Document_DVD extends DocumentBibli {

	private int ageLimite;
	private String realisateur;
	private String duree;
	
	public Document_DVD(int num, String titre, String anneeParution, int id_u, String categorie, String realisateur, String duree, int ageLimite) {
		super(num, titre, anneeParution, id_u, categorie);
		this.realisateur = realisateur;
		this.duree = duree;
		this.ageLimite = ageLimite;
	}
	
	@Override
	public void emprunt(Utilisateur u) throws Exception {
		if((this.ageLimite != 0) && ((int) u.data()[1] < this.ageLimite)) throw new Exception("Vous n'avez pas l'âge requis");
		super.emprunt(u);
	}
	
	@Override
	public String toString() {
		return "(DVD)\t"+ ":" + super.toString() + "\t réalisateur : " + this.realisateur 
				+ ((this.ageLimite != 0)?(" |||⚠️ age limite : " + this.ageLimite + "|||") : "") + " /\t durée : " + this.duree;
	}

}
