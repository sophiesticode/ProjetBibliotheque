package persistance;

/* Un abonné de la médiathèque */
public class Abonne extends UtilisateurBibli {
	private int age;
	private boolean abDesactive;
	
	public Abonne(int id, String nom, int age, int abDesactive) {
		super(id, nom);
		this.age = age;
		this.abDesactive = (abDesactive == 0 ? true : false);
	}

	/*
	 * @return data[0] : id / data[1] : age / data[2] : abDesactive
	 */
	@Override
	public Object[] data() {
		Object[] donnees = new Object[3];
		donnees[0] = super.data()[0];
		donnees[1] = this.age;
		donnees[2] = this.abDesactive;
		return donnees;
	}
}
