package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
	
	private static DbConnector instancia;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String host = "localhost";
	private String port = "3306";
	private String user= "java";
	private String password = "12345";
	private String db= "ligafut";
	private int conectados = 0;
	private Connection conn=null;
	
	private DbConnector() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}
	
	public static DbConnector getInstancia() {
		   if(instancia==null) {
			   instancia=new DbConnector();
		   }
		   return instancia;
	}
		
	public Connection getConn() {
		try {
				if(conn==null || conn.isClosed()) {
					conn=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db, user, password);
					conectados=0;
				}
		} catch (SQLException e) {
			System.out.println("Error en la conexi�n");
			e.printStackTrace();
		}
		conectados++;
		return conn;
	}
		
	public void releaseConn() {
		conectados--;
		try {
			if(conectados<=0) {
				conn.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}