package jdbc.dao;

import java.util.List;

import jdbc.entities.Fournisseur;

public interface FournisseurDao {

	List<Fournisseur> extraire();
	
	void insert(Fournisseur fournisseur);
	
	int update(Fournisseur fournisseur);
	
	boolean delete(Fournisseur fournisseur);
	
}
