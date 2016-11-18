package aed.sql.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private Connection conexion;

	private String ruta, host, db, user, password, link;
	private int puerto;
	private boolean connected;

	public Conexion() {

	}

	public boolean conectar() {
		System.out.println(link);

		switch (ruta) {
		case "jdbc:mysql:":
			try {
				link = ruta + "//" + host + ":" + puerto + "/" + db;
				this.conexion = DriverManager.getConnection(link, user, password);
				connected = true;
			} catch (SQLException e) {
				connected = false;
			}
			break;

		case "jdbc:sqlserver:":
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				link = ruta + "//" + host + ";" + "DataBaseName=" + db;
				this.conexion = DriverManager.getConnection(link, user, password);
				connected = true;
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				connected = false;
			}
			break;
		case "jdbc:ucanaccess:":
			try {
				Class.forName("org.hsqldb.jdbcDriver");
				this.conexion = DriverManager.getConnection(ruta + "//" + host);
				connected = true;
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				connected = false;
			}
			break;
		}
		System.out.println(connected);
		return connected;
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
