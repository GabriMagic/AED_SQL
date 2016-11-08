package aed.sql.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	Connection con;

	public Conexion() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdbiblioteca", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Conexion(String ruta, String host, int puerto, String db, String user, String password) {
		try {
			// jdbc:mysql:
			con = DriverManager.getConnection(ruta + "//" + host + ":" + puerto + "/" + db, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		return con;
	}
}
