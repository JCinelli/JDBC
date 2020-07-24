package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestConnectionJdbcCompta {

	public static void main(String[] args) {

		// Recupere le fichier properties
        ResourceBundle db = ResourceBundle.getBundle("database");

    	Connection connection = null;
        
        try {
			
        	// Enregistre le pilote
        	Class.forName(db.getString("db.driver"));
        		
        	// Créer la connection a la base (compta)
        	connection = DriverManager.getConnection(db.getString("db.url"),
        														db.getString("db.user"),
        														db.getString("db.pass"));
        	
        	
        	// Affiche l'etat de la connection
        	boolean valid = connection.isValid(500);
        	
        	// Si la connection est valide
        	if (valid) {
        		
        		System.out.println("La connection a la base 'compta' est ok !");
				
			} else {
				
				System.err.println("Il y a une erreur de connection..");
				
			}
        	
        	
        	
		} catch (SQLException | ClassNotFoundException e) {
            // Handle errors for JDBC
            System.err.println("Erreur de communication avec la base 'compta' --> " + e.getMessage());
            
        } finally {
			
        	try {
			
        		if (connection != null) {
        			
        			// Déco de la base
            		connection.close();
            		System.out.println("Base 'compta' déconnecté !");
				}
        		
        		
			} catch (SQLException e2) {
				
				System.err.println("Erreur de communication avec la base 'compta' --> " + e2.getMessage());
				
			}
		}
	} 

}
