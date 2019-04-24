package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.ConnexionBDD;
import model.User;

public class UserDao {
	
	/*
	 * trouve tous les Users
	 * 
	 * @return une list d'utilisateur
	 */
	public static ArrayList<User> findAll() throws SQLException {
		ArrayList<User> listU=new ArrayList<User>();
        ResultSet resultat = null;
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
        /* Création de l'objet gérant les requêtes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT id, nom, prenom, password, email, type FROM utilisateur ;" );
		 while ( resultat.next() ) {
        	 int idUtilisateur = resultat.getInt( "id" );
             String nomUtilisateur = resultat.getString( "nom" );
             String prenomUtilisateur = resultat.getString( "prenom" );
             String pwd = resultat.getString( "password" );
             String emailUtilisateur = resultat.getString( "email" );
             int typeUtilisateur = resultat.getInt( "type" );
             listU.add(new User(idUtilisateur, nomUtilisateur, prenomUtilisateur, emailUtilisateur, pwd,typeUtilisateur));
             /* Formatage des données pour affichage dans la JSP finale. */
        }
		return listU;
		
	}
	
	/*
	 * trouve un User selon son email qui est unique
	 * 
	 * @return User
	 */
	public static User findByEmail(String e) throws SQLException {
		User u=null;
        ResultSet resultat = null;
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT id, nom, prenom, password, email,type FROM utilisateur where email='"+e+"' ;" );
		 while ( resultat.next() ) {
        	 int idUtilisateur = resultat.getInt( "id" );
             String nomUtilisateur = resultat.getString( "nom" );
             String prenomUtilisateur = resultat.getString( "prenom" );
             String pwd = resultat.getString( "password" );
             String emailUtilisateur = resultat.getString( "email" );
             int typeUtilisateur = resultat.getInt( "type" );
             u=new User(idUtilisateur, nomUtilisateur, prenomUtilisateur, emailUtilisateur, pwd,typeUtilisateur);
             /* Formatage des données pour affichage dans la JSP finale. */
        }
		return u;
		
	}
	
	/*
	 * insert un User
	 * 
	 * @return User
	 */
	public static User insert(String n,String p,String pwd,String e) throws SQLException {
		ArrayList<User> listU=new ArrayList<User>();
        ResultSet resultat = null;
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		String insertTableSQL = "INSERT INTO utilisateur"
				+ "(nom,prenom,password,email) VALUES"
				+ "(?,?,?,?)";
		PreparedStatement preparedStatement = connexion.prepareStatement(insertTableSQL);
		preparedStatement.setString(1, n);
		preparedStatement.setString(2, p);
		preparedStatement.setString(3,pwd );
		preparedStatement.setString(4, e);
		// execute insert SQL stetement
		preparedStatement.executeUpdate();
		return findByEmail(e);
		
	}
	
	/*
	 * modifier un unser
	 * 
	 * @return User
	 */
	public static User update(int id,String n,String p,String e) throws SQLException {
		
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		String insertTableSQL = "update utilisateur set nom = ?, prenom=?, email=? where id = ?";
		PreparedStatement preparedStatement = connexion.prepareStatement(insertTableSQL);
		preparedStatement.setString(1, n);
		preparedStatement.setString(2, p);
		preparedStatement.setString(3, e);
		preparedStatement.setInt(4, id);
		// execute insert SQL stetement
		preparedStatement.executeUpdate();
		return findByEmail(e);
	}
	
	/*
	 * supprime un User
	 */
	public static void supp(int id) throws SQLException {
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */		
		String insertTableSQL = "delete from utilisateur where id=?";
		PreparedStatement preparedStatement = connexion.prepareStatement(insertTableSQL);
		preparedStatement.setInt(1, id);
		// execute insert SQL stetement
		preparedStatement.executeUpdate();
		
	}
	


}
