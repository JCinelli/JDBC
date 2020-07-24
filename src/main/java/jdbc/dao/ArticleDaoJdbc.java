package jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import jdbc.entities.Article;

public class ArticleDaoJdbc implements ArticleDao {

	@Override
	public List<Article> extraire() {
	
		Connection connection = null;
		List<Article> listArt = new ArrayList<Article>();

		try {

			//Jeton de connexion et d'acces a la BDD
			connection = getConnection();
			
			//Tuyau de comunication pour échanger avec la BDD et faire des requête
			Statement canal = connection.createStatement();
			
			//Resultat retour de la requete
			ResultSet resultat = canal.executeQuery("SELECT * FROM articles");
			
			while (resultat.next()) {
				
				listArt.add(new Article(resultat.getInt("id"),
										resultat.getString("ref"), 
										resultat.getString("designation"),
										resultat.getDouble("prix"),
										resultat.getInt("id_fou")));
				
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

		return listArt;
		
	}
	
	public List<Article> extraireByFou(int id_fou) {
		
		Connection connection = null;
		List<Article> listArt = new ArrayList<Article>();

		try {

			//Jeton de connexion et d'acces a la BDD
			connection = getConnection();
			
			//Tuyau de comunication pour échanger avec la BDD et faire des requête
			Statement canal = connection.createStatement();
			
			//Resultat retour de la requete
			ResultSet resultat = canal.executeQuery("SELECT * FROM articles WHERE id_fou = " + id_fou);
			
			while (resultat.next()) {
				
				listArt.add(new Article(resultat.getInt("id"),
										resultat.getString("ref"), 
										resultat.getString("designation"),
										resultat.getDouble("prix"),
										resultat.getInt("id_fou")));
				
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

		return listArt;
		
	}


	@Override
	public void insert(Article article) {

		Connection connection = null;

		try {

			connection = getConnection();

			Statement canal = connection.createStatement();

			int result = canal.executeUpdate("INSERT INTO articles (id, ref, designation, prix, id_fou)" 
										   + "VALUES (" + article.getId()	+ ", '" 
										   				 + article.getRef() + "', '"
										   				 + article.getDesignation() + "', "
										   				 + article.getPrix() + ", "
										   				 + article.getIdFou() + ")");

			if (result == 1) {

				System.out.println("(L' article à bien été ajouté à la base !)\r");

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

	@Override
	public int update(Article articleModifie) {

		Connection connection = null;
		int result = 0;
		
		try {

			connection = getConnection();

			Statement canal = connection.createStatement();

			 result = canal.executeUpdate("UPDATE articles SET ref = '" + articleModifie.getRef()
			 												+ "', designation = '" + articleModifie.getDesignation()
			 												+ "', prix = " + articleModifie.getPrix()
			 												+ ", id_fou = " + articleModifie.getIdFou()
			 												+ " WHERE id = " + articleModifie.getId());
			canal.close();
			
			if (result == 1) {
				
				System.out.println("(Modification de l'article avec l'id " + articleModifie.getId() + " réussie !)\r");
				
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

	@Override
	public boolean delete(Article article) {

		Connection connection = null;
		boolean result = false;
		
		try {

			connection = getConnection();

			Statement canal = connection.createStatement();

			result = canal.executeUpdate("DELETE FROM articles WHERE id = " + article.getId()) == 1;
			 
			canal.close();
			
			if (result) {
				
				System.out.println("(L' article avec l'id " + article.getId() + " à bien été supprimé !)\r");
				
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
