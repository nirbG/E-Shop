package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public final class ConnexionBDD {

	private static volatile ConnexionBDD instance;
	private Connection cnx; 
	
	private ConnexionBDD() {
		try {
			
			Properties p = new Properties();
			p.load(Thread.currentThread().getContextClassLoader().
						getResourceAsStream("confBDD.properties"));
			
			
				
			// chargement du driver
			Class.forName(p.getProperty("driver"));
			cnx = DriverManager.getConnection(p.getProperty("url"),
					p.getProperty("user"), p.getProperty("pwd"));
//			Class.forName("com.mysql.jdbc.Driver");  
//			cnx=DriverManager.getConnection("jdbc:mysql://localhost:3306/formation","root",""); 			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public static synchronized ConnexionBDD getInstance() {
		if(instance==null)
			instance = new ConnexionBDD();
		
		return instance;
	}

	public Connection getCnx() {
		return cnx;
	}

	public void closeCnx() throws SQLException{
		if(cnx!=null){
			cnx.close();
			instance=null;
		}
	}
}