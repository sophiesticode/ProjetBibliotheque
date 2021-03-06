package persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mediatek2022.*;

// classe mono-instance  dont l'unique instance est connue de la médiatheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-François Brette 01/01/2018
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
	}

	private MediathequeData() {	
	}
	
	// renvoie la liste de tous les documents disponibles de la médiathèque
	@Override
	public List<Document> tousLesDocumentsDisponibles() {
		
		synchronized(Connexion.ouvrirConnection()) {
			
			List<Document> docs = new ArrayList<>();
		
			try {
			
				Statement requeteStatique = Connexion.getC().createStatement();
				ResultSet tableResultat = requeteStatique.executeQuery
					("SELECT * FROM Document WHERE idAbonne is null;");
				if (!tableResultat.next()) {
					return null; //Aucun document dans la BD de la médiathèque
				} 
				else do {
					
					int type = tableResultat.getInt("type");
					int num = tableResultat.getInt("num");
					String titre = tableResultat.getString("titre");
					String de = tableResultat.getString("de");
					String categorie = tableResultat.getString("categorie");
					String anneeParution = tableResultat.getString("anneeParution");
					
					int idAbonne = tableResultat.getInt("idAbonne");
					
					requeteStatique = Connexion.getC().createStatement();
					ResultSet tableResultatSpe = null;
					
					switch(type) {
					case 1: //CD
						tableResultatSpe = requeteStatique.executeQuery
							("SELECT * FROM Document, CD WHERE " + num + " = CD.numDoc;");
						if (tableResultatSpe.next()) {
							int nbMorceaux = tableResultatSpe.getInt("nbMorceaux");
							docs.add(new Document_CD(num, titre, anneeParution, idAbonne, categorie, de, nbMorceaux)); break;
						}
					case 2: //DVD
						tableResultatSpe = requeteStatique.executeQuery
							("SELECT * FROM Document, DVD WHERE " + num + " = DVD.numDoc;");
						if (tableResultatSpe.next()) {
							String duree = tableResultatSpe.getString("duree");
							int ageLimite = tableResultatSpe.getInt("ageLimite");
							docs.add(new Document_DVD(num, titre, anneeParution, idAbonne, categorie, de, duree, ageLimite)); break;
						}
					case 3: //Livre
						tableResultatSpe = requeteStatique.executeQuery
							("SELECT * FROM Document, Livre WHERE " + num + " = Livre.numDoc;");
						if (tableResultatSpe.next()) {
							int nbPages = tableResultatSpe.getInt("nbPages");
							docs.add(new Document_Livre(num, titre, anneeParution, idAbonne, categorie, de, nbPages)); break;
						}
					}
	
				} while (tableResultat.next());
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Connexion.fermerConnection();
			return docs;
		}
		
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		synchronized(Connexion.ouvrirConnection()) {
			
			Statement requeteStatique;
			ResultSet tableResultat = null;

			try {
				requeteStatique = Connexion.getC().createStatement();
				// recherche parmi les abonnés
				tableResultat = requeteStatique.executeQuery
						("SELECT * FROM Utilisateur WHERE Utilisateur.login = '" + login +"' and Utilisateur.mdp = '" + password + "'");
				if (tableResultat.next()) {
					int id = tableResultat.getInt("id");
					
					requeteStatique = Connexion.getC().createStatement();
					ResultSet tableResultatAbonne = requeteStatique.executeQuery
						("SELECT * FROM Abonne, Utilisateur WHERE " + id + " = Abonne.id;");
					
					
					if (tableResultatAbonne.next()) {
						int age = tableResultatAbonne.getInt("age");
						int abDesactive = tableResultatAbonne.getInt("abDesactive");
						Connexion.fermerConnection();	
						return new Abonne(id, login, age, abDesactive);
					}
					
					Connexion.fermerConnection();	
					return new Bibliothecaire(id, login);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			Connexion.fermerConnection();
			return null;
		}
		
	}
	
	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		synchronized(Connexion.ouvrirConnection()) {
			
			try {
				Statement requeteStatique = Connexion.getC().createStatement();
				ResultSet tableResultat = requeteStatique.executeQuery
					("SELECT * FROM Document WHERE num = " + numDocument + ";");
				if (!tableResultat.next()) {
					return null; //Aucun document dans la BD de la médiathèque
				} 
				else do {
					int type = tableResultat.getInt("type");
					int num = tableResultat.getInt("num");
					String titre = tableResultat.getString("titre");
					String de = tableResultat.getString("de");
					String categorie = tableResultat.getString("categorie");
					String anneeParution = tableResultat.getString("anneeParution");
					
					int idAbonne = tableResultat.getInt("idAbonne");
					
					requeteStatique = Connexion.getC().createStatement();
					ResultSet tableResultatSpe = null;
					
					switch(type) {
					case 1: //CD
						tableResultatSpe = requeteStatique.executeQuery
							("SELECT * FROM Document, CD WHERE " + num + " = CD.numDoc;");
						if (tableResultatSpe.next()) {
							int nbMorceaux = tableResultatSpe.getInt("nbMorceaux");
							return new Document_CD(num, titre, anneeParution, idAbonne, categorie, de, nbMorceaux);
						}
					case 2: //DVD
						tableResultatSpe = requeteStatique.executeQuery
							("SELECT * FROM Document, DVD WHERE " + num + " = DVD.numDoc;");
						if (tableResultatSpe.next()) {
							String duree = tableResultatSpe.getString("duree");
							int ageLimite = tableResultatSpe.getInt("ageLimite");
							return new Document_DVD(num, titre, anneeParution, idAbonne, categorie, de, duree, ageLimite);
						}
					case 3: //Livre
						tableResultatSpe = requeteStatique.executeQuery
							("SELECT * FROM Document, Livre WHERE " + num + " = Livre.numDoc;");
						if (tableResultatSpe.next()) {
							int nbPages = tableResultatSpe.getInt("nbPages");
							System.out.println(nbPages);
							return new Document_Livre(num, titre, anneeParution, idAbonne, categorie, de, nbPages);
						}
					}

				} while (tableResultat.next());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			Connexion.fermerConnection();
			return null;
		}
		
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc... variable suivant le type de document
		synchronized(Connexion.ouvrirConnection()) {
			try {
			Statement requeteStatique = Connexion.getC().createStatement();
			
			// formatage
			args[0] = remplacerString((String)args[0], "'", "`");
			args[1] = remplacerString((String)args[1], "'", "`");
			args[2] = remplacerString((String)args[2], "'", "`");
			
			String requeteInsertDoc = "INSERT INTO Document (type, titre, de, categorie, anneeParution) VALUES (" 
					+ type + ", "
					+ (char)39 + args[0] + (char)39 + ", "
					+ (char)39 + args[1] + (char)39 + ", "
					+ (char)39 + args[2] + (char)39 + ", "
					+ (char)39 + args[3] + (char)39 + ");";
			requeteStatique.executeUpdate(requeteInsertDoc);
			
			requeteStatique = Connexion.getC().createStatement();
			String requeteSelectDoc = "SELECT num FROM Document WHERE titre LIKE "+ (char)39 + args[0] + (char)39 + ";";
			ResultSet tableResultat = requeteStatique.executeQuery(requeteSelectDoc);
			int numDoc = 0;
			if (tableResultat.next()) {
				numDoc = tableResultat.getInt("num");
			
				String requeteInsertSpe = null;
				requeteStatique = Connexion.getC().createStatement();
				switch(type) {
				case 1: requeteInsertSpe="INSERT INTO CD (numDoc, nbMorceaux) VALUES (" + numDoc + ", " + args[4] + ");"; break;
				case 2: requeteInsertSpe="INSERT INTO DVD (numDoc, duree, ageLimite) VALUES (" + numDoc + ", " + (char)39 + args[4] + (char)39 + ", " + args[5] + ");";break;
				case 3: requeteInsertSpe="INSERT INTO Livre (numDoc, nbPages) VALUES (" + numDoc + ", " + args[4] + ");";break;
				}
				requeteStatique.executeUpdate(requeteInsertSpe);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			Connexion.fermerConnection();
		}
	}
		
	/*
	 * @returnstring formatée afin d'éviter des erreurs dans la requête sql qui suivra
	 */
	public static String remplacerString(String url, String avant, String apres) {
		Pattern p = Pattern.compile(avant);  
		Matcher m = p.matcher(url) ;  
		  
		return m.replaceAll(apres) ; 
	}
	

}
