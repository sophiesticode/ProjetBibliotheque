package persistance;

/* Un Livre de la médiathèque */
public class Document_Livre extends DocumentBibli {
	private String auteur;
	private int nbPages;

	public Document_Livre(int num, String titre, String anneeParution, int id_u, String categorie, String auteur, int nbPages) {
		super(num, titre, anneeParution, id_u, categorie);
		this.auteur = auteur;
		this.nbPages = nbPages;
	}

	@Override
	public String toString() {
		return "(Livre)\t"+ ":" + super.toString() + "\t auteur : " + this.auteur + " /\t nbPages : " + nbPages;
	}
}
