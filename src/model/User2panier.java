package model;

public class User2panier {
	private int idpanier;
	private int iduser;
	private int valider;
	
	public User2panier(int idpanier, int iduser, int val) {
		this.idpanier = idpanier;
		this.iduser = iduser;
		this.valider = val;
	}

	public int getIdpanier() {
		return idpanier;
	}

	public int getIduser() {
		return iduser;
	}

	public int getValider() {
		return valider;
	}
}
