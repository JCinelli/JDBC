package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestConnectionJdbc2Sitemarchand {

	public static void main(String[] args) {

		// recupere le fichier properties
        ResourceBundle db = ResourceBundle.getBundle("database2");

    	Connection connection = null;
        
        try {
			
        	// enregistre le pilote
        	Class.forName(db.getString("db.driver"));
        		
        	// Créer le connection a la base (sitemarchand)
        	connection = DriverManager.getConnection(db.getString("db.url"),
        														db.getString("db.user"),
        														db.getString("db.pass"));
        	
        	// Affiche l'etat de la connection
        	boolean valid = connection.isValid(500);
        	
        	// Si la connection est valide
        	if (valid) {
        		
        		System.out.println("La connection a la base 'sitemarchand' est ok !");
				
			} else {
				
				System.err.println("Il y a une erreur de connection..");
				
			}
        	
        	
        	
		} catch (SQLException | ClassNotFoundException e) {
			
            // Handle errors for JDBC
            System.err.println("Erreur de communication avec la base 'sitemarchand' --> " + e.getMessage());
            
        } finally {
			
        	try {
			
        		if (connection != null) {
        			
        			// Déco de la base
            		connection.close();
            		System.out.println("Base 'sitemarchand' déconnecté !");
				}
        		
        		
			} catch (SQLException e2) {
				
				System.err.println("Erreur de communication avec la base 'sitemarchand' --> " + e2.getMessage());
				
			}
		}
	} 

}
