package aed.sql.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	Connection conexion;

	String ruta;
	String host;
	int puerto;
	String db;
	String user;
	String password;

	public Conexion() {
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdbiblioteca", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Conexion(String ruta, String host, int puerto, String db, String user, String password) {
		try {

			System.out.println(ruta + "//" + host + ":" + puerto + "/" + db);
			conexion = DriverManager.getConnection(ruta + "//" + host + ":" + puerto + "/" + db, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		return conexion;
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
