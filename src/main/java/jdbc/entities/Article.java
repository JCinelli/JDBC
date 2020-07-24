package jdbc.entities;

public class Article {

	private int id;
	private String ref;
	private String designation;
	private double prix;
	private int idFou;
	
//	CONSTRUCTOR
	public Article(int id, String ref, String designation, double prix, int idFou) {
		super();
		this.id = id;
		this.ref = ref;
		this.designation = designation;
		this.prix = prix;
		this.idFou = idFou;
	}
	
//	METHODS
	@Override
	public String toString() {
		return "id : " + id + "\rReference : " + ref + "\rDesignation : " + designation + "\rPrix : " + prix + "\rId du fournisseur : " + idFou;
	}
	
//	GETTERS & SETTERS
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
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}
	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}
	/**
	 * @param prix the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}
	/**
	 * @return the idFou
	 */
	public int getIdFou() {
		return idFou;
	}
	/**
	 * @param idFou the idFou to set
	 */
	public void setIdFou(int idFou) {
		this.idFou = idFou;
	}
	
	
	
}
