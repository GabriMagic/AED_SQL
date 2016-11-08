package aed.sql.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Conexion {
	private Connection conexion;

	private String ruta, host, db, user, password, link = "";
	private int puerto;

	// private StringProperty ruta, host, db, user, password, link;
	// private IntegerProperty puerto;

	// public Conexion() {
	//
	// ruta = new SimpleStringProperty(this, "ruta", "jdbc:mysql:");
	// host = new SimpleStringProperty(this, "host", "localhsot");
	// puerto = new SimpleIntegerProperty(this, "puerto", 3306);
	// db = new SimpleStringProperty(this, "db", "bdbiblioteca");
	// user = new SimpleStringProperty(this, "user", "root");
	// password = new SimpleStringProperty(this, "password", "");
	// }

	public Conexion(String ruta, String host, int puerto, String db, String user, String password) {

		this.ruta = ruta;
		this.host = host;
		this.db = db;
		this.user = user;
		this.password = password;

	}

	public boolean conectar() {
		try {

			// link = new SimpleStringProperty(this, "link ", ruta + "//" + host
			// + ":" + puerto + "/" + db);
			System.out.println("THIS "+link);
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
