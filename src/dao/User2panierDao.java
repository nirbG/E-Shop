package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.ConnexionBDD;
import model.User;
import model.User2panier;

public class User2panierDao {
	
	public static int newPanier(int userid) throws SQLException {
		int newId = 0;
        ResultSet resultat = null;
		Connection connexion = ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		String insertTableSQL = "INSERT INTO user2panier"
				+ "(iduser) VALUES"
				+ "(?)";
		PreparedStatement preparedStatement = connexion.prepareStatement(insertTableSQL);
		preparedStatement.setInt(1, userid);
		// execute insert SQL stetement
		preparedStatement.executeUpdate();
		Statement statement2 = connexion.createStatement();
		
		resultat = statement2.executeQuery( "SELECT idpanier AS nid FROM user2panier where iduser="+userid+" and valider=0;" );
		 
		 while ( resultat.next() ) {
        	 newId = resultat.getInt( "nid" );
        }
		return newId;
	}
	
	
	public static ArrayList<User2panier> findAll() throws SQLException {
		ArrayList<User2panier> listP=new ArrayList<User2panier>();
        ResultSet resultat = null;
		Connection connexion = ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT idpanier, iduser, valider FROM user2panier ;" );
		 
		 while ( resultat.next() ) {

        	 int idPa = resultat.getInt( "idpanier" );
			 int idU = resultat.getInt( "iduser" );
             int valider = resultat.getInt( "valider" );
             listP.add(new User2panier(idPa, idU, valider));
             /* Formatage des données pour affichage dans la JSP finale. */
        }

		return listP;
		
	}
	
	public static User2panier findByIdpanier(int idPanier) throws SQLException {
		User2panier basket = null;
        ResultSet resultat = null;
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT iduser, valider FROM user2panier Where idpanier="+idPanier+";" );
		 
		 while ( resultat.next() ) {
        	 int idU = resultat.getInt( "iduser" );
             int valider = resultat.getInt( "valider" );
             basket=new User2panier(idPanier, idU, valider);
             /* Formatage des données pour affichage dans la JSP finale. */
        }

		return basket;
	}
	
	public static User2panier findByIduser(int iduser) throws SQLException {
		User2panier basket = null;
        ResultSet resultat = null;
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT idpanier, valider FROM user2panier Where iduser="+iduser+";" );
		 
		 while ( resultat.next() ) {
        	 int idpanier = resultat.getInt( "idpanier" );
             int valider = resultat.getInt( "valider" );
             basket=new User2panier(idpanier, iduser, valider);
             /* Formatage des données pour affichage dans la JSP finale. */
        }

		return basket;
	}
	
	public static ArrayList<User2panier> findsByIduser(int iduser) throws SQLException {
		User2panier basket = null;
        ResultSet resultat = null;
        ArrayList<User2panier> list=new ArrayList<User2panier>();
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */
		Statement statement = connexion.createStatement();
		resultat = statement.executeQuery( "SELECT idpanier, valider FROM user2panier Where iduser="+iduser+";" );
		 
		 while ( resultat.next() ) {
        	 int idpanier = resultat.getInt( "idpanier" );
             int valider = resultat.getInt( "valider" );
             list.add(new User2panier(idpanier, iduser, valider));
             /* Formatage des données pour affichage dans la JSP finale. */
        }

		return list;
	}

	public static int valider(int iduser) throws SQLException {

		Connection connexion =ConnexionBDD.getInstance().getCnx();
		String insertTableSQL = "update  user2panier set valider = 1 Where iduser="+iduser+";";
		PreparedStatement preparedStatement = connexion.prepareStatement(insertTableSQL);
		preparedStatement.executeUpdate();
		return User2panierDao.newPanier(iduser);
		

	}
	
	/*
	 * supprime un User
	 */
	public static void supp(int id) throws SQLException {
		Connection connexion =ConnexionBDD.getInstance().getCnx(); 
         /* Création de l'objet gérant les requêtes */		
		
		for (User2panier iterable_element : findsByIduser(id)) {
			BasketDao.supp(iterable_element.getIdpanier());
		}
		
		String insertTableSQL = "delete from user2panier where iduser=?";
		PreparedStatement preparedStatement = connexion.prepareStatement(insertTableSQL);
		preparedStatement.setInt(1, id);
		// execute insert SQL stetement
		preparedStatement.executeUpdate();
		
	}
	
}
