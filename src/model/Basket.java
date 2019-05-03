package model;

public class Basket {
	private int idpanier;
	private int iduser;
	private int idproduit;
	private int quantite;
	
	public Basket(int idpanier, int iduser, int idproduit, int quantite) {
		super();
		this.idpanier = idpanier;
		this.iduser = iduser;
		this.idproduit = idproduit;
		this.quantite = quantite;
	}

	public int getIdpanier() {
		return idpanier;
	}

	public void setIdpanier(int idpanier) {
		this.idpanier = idpanier;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public int getIdproduit() {
		return idproduit;
	}

	public void setIdproduit(int idproduit) {
		this.idproduit = idproduit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	
}
