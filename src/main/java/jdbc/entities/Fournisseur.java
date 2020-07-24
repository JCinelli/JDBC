package jdbc.entities;

public class Fournisseur {

	private int id;
	private String nom;

//	CONSTRUCTOR
	public Fournisseur(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

//	METHODS
	@Override
	public String toString() {
		return "id : " + id + "\rNom : " + nom;
	}

	// GETTERS & SETTERS
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

}
