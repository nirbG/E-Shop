package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Basket;
import model.ConnexionBDD;
import model.Product;
import model.User;

public class BasketDao {
	
	public static int newPanier() throws SQLException {
		int newId = 0;
        ResultSet resultat = null;
		Connection connexion = ConnexionBDD.getInstance().getCnx(); 
         /* Cr�ation de l'objet g�rant les requ�tes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT max(idpanier)+1 AS nid FROM panier;" );
		 
		 while ( resultat.next() ) {
        	 newId = resultat.getInt( "nid" );
        }

		return newId;
	}

	public static ArrayList<Basket> findAll() throws SQLException {
		ArrayList<Basket> listP=new ArrayList<Basket>();
        ResultSet resultat = null;
		Connection connexion = ConnexionBDD.getInstance().getCnx(); 
         /* Cr�ation de l'objet g�rant les requ�tes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT idpanier, iduser, idproduit, quantite FROM panier ;" );
		 
		 while ( resultat.next() ) {

        	 int idPa = resultat.getInt( "idpanier" );
			 int idU = resultat.getInt( "iduser" );
             int idPr = resultat.getInt( "idproduit" );
             int qte = resultat.getInt( "quantite" );
             listP.add(new Basket(idPa, idU, idPr, qte));
             /* Formatage des donn�es pour affichage dans la JSP finale. */
        }

		return listP;
		
	}	
	
	public static ArrayList<Basket> findById(int idPanier) throws SQLException {
		ArrayList<Basket> listeB = new ArrayList<Basket>();
        ResultSet resultat = null;
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Cr�ation de l'objet g�rant les requ�tes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT iduser, idproduit, quantite FROM panier Where idpanier="+idPanier+";" );
		 
		 while ( resultat.next() ) {
			 
        	 int idU = resultat.getInt( "iduser" );
             int idPr = resultat.getInt( "idproduit" );
             int qte = resultat.getInt( "quantite" );
             listeB.add(new Basket(idPanier, idU, idPr, qte));
             /* Formatage des donn�es pour affichage dans la JSP finale. */
        }

		return listeB;
	}

	public static ArrayList<Basket> findByUser(int idUser) throws SQLException {
		ArrayList<Basket> listeB = new ArrayList<Basket>();
        ResultSet resultat = null;
		Connection connexion = ConnexionBDD.getInstance().getCnx(); 
         /* Cr�ation de l'objet g�rant les requ�tes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT idpanier, idproduit, quantite FROM panier Where iduser="+idUser+";" );
		 
		 while ( resultat.next() ) {
			 
        	 int idPa = resultat.getInt( "idpanier" );
             int idPr = resultat.getInt( "idproduit" );
             int qte = resultat.getInt( "quantite" );
             listeB.add(new Basket(idPa, idUser, idPr, qte));
             /* Formatage des donn�es pour affichage dans la JSP finale. */
        }

		return listeB;
	}
	
	public static ArrayList<Basket> findByProduct(int idProduit) throws SQLException {
		ArrayList<Basket> listeB = new ArrayList<Basket>();
        ResultSet resultat = null;
		Connection connexion = ConnexionBDD.getInstance().getCnx(); 
         /* Cr�ation de l'objet g�rant les requ�tes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT idpanier, iduser, quantite FROM panier Where idproduit="+idProduit+";" );
		 
		 while ( resultat.next() ) {
			 
        	 int idPa = resultat.getInt( "idpanier" );
             int idU = resultat.getInt( "iduser" );
             int qte = resultat.getInt( "quantite" );
             listeB.add(new Basket(idPa, idU, idProduit, qte));
             /* Formatage des donn�es pour affichage dans la JSP finale. */
        }

		return listeB;
	}
	
	public static ArrayList<Basket> findByProductUser(int idUser, int idProduit) throws SQLException {
		ArrayList<Basket> listeB = new ArrayList<Basket>();
        ResultSet resultat = null;
		Connection connexion = ConnexionBDD.getInstance().getCnx(); 
         /* Cr�ation de l'objet g�rant les requ�tes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT idpanier, iduser, quantite FROM panier Where idproduit="+idProduit+" and iduser=" + idUser + ";" );
		 
		 while ( resultat.next() ) {
			 
        	 int idPa = resultat.getInt( "idpanier" );
             int qte = resultat.getInt( "quantite" );
             listeB.add(new Basket(idPa, idUser, idProduit, qte));
             /* Formatage des donn�es pour affichage dans la JSP finale. */
        }

		return listeB;
	}
	
	/*
	 * insert un produit
	 * 
	 * @return produit
	 */
	public static void insert(int idpa, int idu, int idpr, int qte) throws SQLException {
		ArrayList<User> listU=new ArrayList<User>();
        ResultSet resultat = null;
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Cr�ation de l'objet g�rant les requ�tes */
		String insertTableSQL = "INSERT INTO panier"
				+ "(idpanier,iduser,idproduit,quantite) VALUES"
				+ "(?,?,?,?)";
		PreparedStatement preparedStatement = connexion.prepareStatement(insertTableSQL);
		preparedStatement.setInt(1, idpa);
		preparedStatement.setInt(2, idu);
		preparedStatement.setInt(3, idpr);
		preparedStatement.setInt(4, qte);
		// execute insert SQL stetement
		preparedStatement.executeUpdate();
		
	}
	/*
	 * modifier un unser
	 * 
	 * @return User
	 */
	public static void update(int idpa, int idu, int idpr, int qte) throws SQLException {
		
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Cr�ation de l'objet g�rant les requ�tes */
		String insertTableSQL = "update panier set quantite = ? where iduser = ? and idproduit = ? and idpanier = ?";
		PreparedStatement preparedStatement = connexion.prepareStatement(insertTableSQL);
		preparedStatement.setInt(4, idpa);
		preparedStatement.setInt(2, idu);
		preparedStatement.setInt(3, idpr);
		preparedStatement.setInt(1, qte);
		// execute insert SQL stetement
		preparedStatement.executeUpdate();
	}
	
	/*
	 * supprime un User
	 */
	public static void supp(int id) throws SQLException {
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Cr�ation de l'objet g�rant les requ�tes */		
		String insertTableSQL = "delete from panier where id=?";
		PreparedStatement preparedStatement = connexion.prepareStatement(insertTableSQL);
		preparedStatement.setInt(1, id);
		// execute insert SQL stetement
		preparedStatement.executeUpdate();
		
	}
}