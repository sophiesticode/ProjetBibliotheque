package persistance;

/* Un CD de la médiathèque */
public class Document_CD extends DocumentBibli {

	private String compositeur;
	private int nbMorceaux;

	public Document_CD(int num, String titre, String anneeParution, int id_u, String categorie, String compositeur, int nbMorceaux) {
		super(num, titre, anneeParution, id_u, categorie);
		this.compositeur = compositeur;
		this.nbMorceaux = nbMorceaux;
	}

	@Override
	public String toString() {
		return "(CD)\t"+ ":" + super.toString() + "\tcompositeur : " + this.compositeur + " /\t nbMorceaux : " + nbMorceaux;
	}
	
}
