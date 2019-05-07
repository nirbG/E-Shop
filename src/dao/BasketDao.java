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


	public static ArrayList<Basket> findAll() throws SQLException {
		ArrayList<Basket> listP=new ArrayList<Basket>();
        ResultSet resultat = null;
		Connection connexion = ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT idpanier, idproduit, quantite FROM panier ;" );
		 
		 while ( resultat.next() ) {

        	 int idPa = resultat.getInt( "idpanier" );
             int idPr = resultat.getInt( "idproduit" );
             int qte = resultat.getInt( "quantite" );
             listP.add(new Basket(idPa, idPr, qte));
             /* Formatage des données pour affichage dans la JSP finale. */
        }

		return listP;
		
	}	
	
	public static ArrayList<Basket> findById(int idPanier) throws SQLException {
		ArrayList<Basket> listeB = new ArrayList<Basket>();
        ResultSet resultat = null;
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT  idproduit, quantite FROM panier Where idpanier="+idPanier+";" );
		 
		 while ( resultat.next() ) {
             int idPr = resultat.getInt( "idproduit" );
             int qte = resultat.getInt( "quantite" );
             listeB.add(new Basket(idPanier,  idPr, qte));
             /* Formatage des données pour affichage dans la JSP finale. */
        }

		return listeB;
	}
	
	public static float getPrice(int idPanier) throws SQLException {
		ArrayList<Basket> listeB = new ArrayList<Basket>();
		float res=0;
        ResultSet resultat = null;
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT  idproduit, quantite, prix FROM panier inner join produits  "
				+ "where panier.idproduit=produits.id  and idpanier="+idPanier+";" );
		 
		 while ( resultat.next() ) {
             float prix = resultat.getFloat( "prix" );
             int qte = resultat.getInt( "quantite" );
             res+=qte*prix;
             /* Formatage des données pour affichage dans la JSP finale. */
        }

		return res;
	}

	
	public static ArrayList<Basket> findByProduct(int idProduit) throws SQLException {
		ArrayList<Basket> listeB = new ArrayList<Basket>();
        ResultSet resultat = null;
		Connection connexion = ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT idpanier, quantite FROM panier Where idproduit="+idProduit+";" );
		 
		 while ( resultat.next() ) {
			 
        	 int idPa = resultat.getInt( "idpanier" );
             int idU = resultat.getInt( "iduser" );
             int qte = resultat.getInt( "quantite" );
             listeB.add(new Basket(idPa, idProduit, qte));
             /* Formatage des données pour affichage dans la JSP finale. */
        }

		return listeB;
	}
	
	
	public static ArrayList<Basket> findByProduct(int idPanier, int idProduit) throws SQLException {
		ArrayList<Basket> listeB = new ArrayList<Basket>();
        ResultSet resultat = null;
		Connection connexion = ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT idpanier, quantite FROM panier Where idproduit="+idProduit+" and idpanier=" + idPanier + ";" );
		 
		 while ( resultat.next() ) {
             int qte = resultat.getInt( "quantite" );
             listeB.add(new Basket(idPanier, idProduit, qte));
             /* Formatage des données pour affichage dans la JSP finale. */
        }

		return listeB;
	}
	
	/*
	 * insert un produit
	 * 
	 * @return produit
	 */
	public static void insert(int idpa, int idpr, int qte) throws SQLException {
		ArrayList<User> listU=new ArrayList<User>();
        ResultSet resultat = null;
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		String insertTableSQL = "INSERT INTO panier"
				+ "(idpanier,idproduit,quantite) VALUES"
				+ "(?,?,?)";
		PreparedStatement preparedStatement = connexion.prepareStatement(insertTableSQL);
		preparedStatement.setInt(1, idpa);
		preparedStatement.setInt(2, idpr);
		preparedStatement.setInt(3, qte);
		// execute insert SQL stetement
		preparedStatement.executeUpdate();
		
	}
	/*
	 * modifier un unser
	 * 
	 * @return User
	 */
	public static void update(int idpa, int idpr, int qte) throws SQLException {
		
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		String insertTableSQL = "update panier set quantite = ? where  idproduit = ? and idpanier = ?";
		PreparedStatement preparedStatement = connexion.prepareStatement(insertTableSQL);
		preparedStatement.setInt(3, idpa);
		preparedStatement.setInt(2, idpr);
		preparedStatement.setInt(1, qte);
		// execute insert SQL stetement
		preparedStatement.executeUpdate();
	}
	
	/*
	 * supprime un User
	 */
	public static void supp(int id) throws SQLException {
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */		
		String insertTableSQL = "delete from panier where idpanier=?";
		PreparedStatement preparedStatement = connexion.prepareStatement(insertTableSQL);
		preparedStatement.setInt(1, id);
		// execute insert SQL stetement
		preparedStatement.executeUpdate();
		
	}
	
	public static void suppProduct(int idpanier, int idproduct) throws SQLException {
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */		
		String insertTableSQL = "delete from panier where idpanier=? and idproduit = ?";
		PreparedStatement preparedStatement = connexion.prepareStatement(insertTableSQL);
		preparedStatement.setInt(1, idpanier);
		preparedStatement.setInt(2, idproduct);
		// execute insert SQL stetement
		preparedStatement.executeUpdate();
		
	}
}
