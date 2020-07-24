package jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import jdbc.entities.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {

	public List<Fournisseur> extraire() {

		Connection connection = null;
		List<Fournisseur> listFournisseur = new ArrayList<Fournisseur>();

		try {

			// Jeton de connexion et d'acces a la BDD
			connection = getConnection();

			// Tuyau de comunication pour échanger avec la BDD et faire des requête
			Statement canal = connection.createStatement();

			// Resultat retour de la requete
			ResultSet resultat = canal.executeQuery("SELECT * FROM fournisseur");

			while (resultat.next()) {

				listFournisseur.add(new Fournisseur(resultat.getInt("id"), resultat.getString("nom")));

			}

			resultat.close();
			canal.close();

		} catch (Exception e) {

			System.err.println("Erreur d'execution : " + e.getMessage());

		} finally {

			try {

				if (connection != null) {

					connection.close();

				}

			} catch (SQLException e2) {

				System.err.println("Problème de connection close : " + e2.getMessage());

			}

		}

		return listFournisseur;

	}

	public void insert(Fournisseur fournisseur) {

		Connection connection = null;

		try {

			connection = getConnection();

			Statement canal = connection.createStatement();

			int result = canal.executeUpdate("INSERT INTO fournisseur (id, nom)" + "VALUES ('" + fournisseur.getId()
					+ "', '" + fournisseur.getNom() + "')");

			if (result == 1) {

				System.out.println("(Le fournisseur à bien été ajouté a la base !)\r");

			}

			canal.close();

		} catch (Exception e) {

			System.err.println("Erreur d'execution : " + e.getMessage());

		} finally {

			try {

				if (connection != null) {

					connection.close();

				}

			} catch (SQLException e2) {

				System.err.println("Problème de connection close : " + e2.getMessage());

			}
		}

	}

	public int update(Fournisseur fournisseur) {

		Connection connection = null;
		int result = 0;
		
		try {

			connection = getConnection();

			Statement canal = connection.createStatement();

			 result = canal.executeUpdate("UPDATE fournisseur SET nom = '" + fournisseur.getNom()
			 												+ "' WHERE id = " + fournisseur.getId());
			canal.close();
			
			if (result == 1) {
				
				System.out.println("(Modification du fournisseur avec l'id " + fournisseur.getId() + " réussie !)\r");
				
			} else {
				
				System.err.println("Echec de modification ..");
				
			}

		} catch (Exception e) {

			System.err.println("Erreur d'execution : " + e.getMessage());

		} finally {

			try {

				if (connection != null) {

					connection.close();

				}

			} catch (SQLException e2) {

				System.err.println("Problème de connection close : " + e2.getMessage());

			}
		}
		
		return result;

	}

	public boolean delete(Fournisseur fournisseur) {

		Connection connection = null;
		boolean result = false;
		
		try {

			connection = getConnection();

			Statement canal = connection.createStatement();

			 result = canal.executeUpdate("DELETE FROM fournisseur WHERE id = " + fournisseur.getId()) == 1;
			canal.close();
			
			if (result) {
				
				System.out.println("(Le fournisseur " + fournisseur.getNom() + " à bien été supprimé !)\r");
				
			} else {
				
				System.err.println("Echec de la suppression ..");
				
			}

		} catch (Exception e) {

			System.err.println("Erreur d'execution : " + e.getMessage());

		} finally {

			try {

				if (connection != null) {

					connection.close();

				}

			} catch (SQLException e2) {

				System.err.println("Problème de connection close : " + e2.getMessage());

			}
		}
		
		return result;

	}

	public Connection getConnection() {

		ResourceBundle db = ResourceBundle.getBundle("database");

		try {

			Class.forName(db.getString("db.driver"));

			Connection connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
					db.getString("db.pass"));

			return connection;

		} catch (SQLException | ClassNotFoundException e) {

			System.err.println("Echec de la connection a la base .. " + e.getMessage());
			return null;

		}

	}

}
