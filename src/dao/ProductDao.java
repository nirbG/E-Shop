package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.ConnexionBDD;
import model.Product;
import model.User;


public class ProductDao {
	public static ArrayList<Product> findAll() throws SQLException {
		ArrayList<Product> listP=new ArrayList<Product>();
        ResultSet resultat = null;
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT id, nom, description, prix FROM produits;" );
		 
		 while ( resultat.next() ) {

        	 int idP = resultat.getInt( "id" );
             String nameP = resultat.getString( "nom" );
             String descriptionP = resultat.getString( "description" );
             float userNameUtilisateur = resultat.getFloat( "prix" );
             listP.add(new Product(idP, nameP, descriptionP, userNameUtilisateur));
             /* Formatage des données pour affichage dans la JSP finale. */
        }

		return listP;
		
	}
	
	public static ArrayList<Product> findsome(int nbP) throws SQLException {
		ArrayList<Product> listP=new ArrayList<Product>();
        ResultSet resultat = null;
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT id, nom, description, prix FROM produits limit "+nbP+";" );
		 
		 while ( resultat.next() ) {

        	 int idP = resultat.getInt( "id" );
             String nameP = resultat.getString( "nom" );
             String descriptionP = resultat.getString( "description" );
             float userNameUtilisateur = resultat.getFloat( "prix" );
             listP.add(new Product(idP, nameP, descriptionP, userNameUtilisateur));
             /* Formatage des données pour affichage dans la JSP finale. */
        }

		return listP;
		
	}
	
	
	public static Product find(int id) throws SQLException {
		Product p=null;
        ResultSet resultat = null;
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT id, nom, description, prix FROM produits Where id="+id+";" );
		 
		 while ( resultat.next() ) {

        	 int idP = resultat.getInt( "id" );
             String nameP = resultat.getString( "nom" );
             String descriptionP = resultat.getString( "description" );
             float userNameUtilisateur = resultat.getFloat( "prix" );
             p=new Product(idP, nameP, descriptionP, userNameUtilisateur);
             /* Formatage des données pour affichage dans la JSP finale. */
        }

		return p;
		
	}
	
	/*
	 * insert un produit
	 * 
	 * @return produit
	 */
	public static void insert(String n,String d,float p) throws SQLException {
		ArrayList<User> listU=new ArrayList<User>();
        ResultSet resultat = null;
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		String insertTableSQL = "INSERT INTO produits"
				+ "(nom,description,prix) VALUES"
				+ "(?,?,?)";
		PreparedStatement preparedStatement = connexion.prepareStatement(insertTableSQL);
		preparedStatement.setString(1, n);
		preparedStatement.setString(2, d);
		preparedStatement.setFloat(3,p );
		// execute insert SQL stetement
		preparedStatement.executeUpdate();
		
	}
	/*
	 * modifier un unser
	 * 
	 * @return User
	 */
	public static void update(int id,String n,String d,Float p) throws SQLException {
		
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		String insertTableSQL = "update produits set nom = ?, description=?, prix=? where id = ?";
		PreparedStatement preparedStatement = connexion.prepareStatement(insertTableSQL);
		preparedStatement.setString(1, n);
		preparedStatement.setString(2, d);
		preparedStatement.setFloat(3, p);
		preparedStatement.setInt(4, id);
		// execute insert SQL stetement
		preparedStatement.executeUpdate();
	}
	
	/*
	 * supprime un User
	 */
	public static void supp(int id) throws SQLException {
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */		
		String insertTableSQL = "delete from produits where id=?";
		PreparedStatement preparedStatement = connexion.prepareStatement(insertTableSQL);
		preparedStatement.setInt(1, id);
		// execute insert SQL stetement
		preparedStatement.executeUpdate();
		
	}
	
	
}
