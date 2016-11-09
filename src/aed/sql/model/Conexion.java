package aed.sql.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private Connection conexion;

	private String ruta, host, db, user, password, link = "";
	private int puerto;
	private boolean connected;

	public Conexion(String ruta, String host, int puerto, String db, String user, String password) {

		this.ruta = ruta;
		this.host = host;
		this.puerto = puerto;
		this.db = db;
		this.user = user;
		this.password = password;
		connected = true;

	}

	public boolean conectar() {
		try {

			link = ruta + "//" + host + ":" + puerto + "/" + db;
			conexion = DriverManager.getConnection(link, user, password);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public Connection getConexion() {
		return conexion;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

}
