package aed.sql.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private Connection conexion;

	private String ruta, host, db, user, password, link;
	private int puerto;
	private int connected;

	public Conexion() {

	}

	public int conectar() {

		switch (ruta) {
		case "jdbc:mysql:":
			try {
				link = ruta + "//" + host + ":" + puerto + "/" + db;
				this.conexion = DriverManager.getConnection(link, user, password);
				connected = 1;
			} catch (SQLException e) {
				connected = 0;
			}
			break;

		case "jdbc:sqlserver:":
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				link = ruta + "//" + host + ";" + "DataBaseName=" + db;
				this.conexion = DriverManager.getConnection(link, user, password);
				connected = 2;
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				connected = 0;
			}
			break;
		case "jdbc:ucanaccess:":
			try {
				Class.forName("org.hsqldb.jdbcDriver");
				this.conexion = DriverManager.getConnection(ruta + "//" + host);
				connected = 3;
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				connected = 0;
			}
			break;
		}
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

	public int isConnected() {
		return connected;
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
