package model;

public class Product {
	
	//id du produit
	private int id;
	//nom du produit
	private String nom;
	//description du produit
	private String description;
	//prix du produit
	private float prix;
	
	public Product(int i,String n,String d,float p) {
		this.id=i;
		this.nom=n;
		this.description=d;
		this.prix=p;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getDescription() {
		return description;
	}

	public float getPrix() {
		return prix;
	}


}
