package model;

public class User {

	//id user
	private int id;
	//nom user
	private String nom;
	//prenom user
	private String prenom;
	//email user
	private String email;
	//password user
	private String mdp;
	//type of user
	private int type;
	//basket du user
	private Basket panier;
	private int idpanier;
	
	
	public User(int i,String n,String p,String e,String pass,int t, int idp) {
		this.id=i;
		this.nom=n;
		this.prenom=p;
		this.email=e;
		this.mdp=pass;
		this.panier=null;
		this.type=t;
		this.idpanier=idp;
	}


	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getMdp() {
		return mdp;
	}

	public int getType() {
		return type;
	}

	public Basket getBasket() {
		return panier;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}


	public int getIdpanier() {
		return idpanier;
	}


	public void setIdpanier(int idpanier) {
		this.idpanier = idpanier;
	}
	
	public void setMdp(String pass) {
		this.mdp = pass;
	}


	
}
