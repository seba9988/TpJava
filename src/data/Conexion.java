package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/ligafut";
	private static final String USUARIO = "java";
	private static final String CLAVE = "12345";

	static {
		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}
	
	public Connection conectar() {
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection(URL,USUARIO,CLAVE);
			System.out.println("Conexi�n OK");

		} catch (SQLException e) {
			System.out.println("Error en la conexi�n");
			e.printStackTrace();
		}
		
		return conexion;
	}
}