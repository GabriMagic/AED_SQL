package aed.sql.controller;

import javax.swing.text.View;

import aed.sql.model.Conexion;
import aed.sql.view.MainView;

public class ConexionController {

	Conexion conexion;
	MainView mainView;

	String ruta;
	String host;
	int puerto;
	String db;
	String user;
	String password;

	public ConexionController() {

	}

	public ConexionController(String ruta, String host, int puerto, String db, String user, String password) {

		
		
		// this.ruta = ruta;
		// this.host = host;
		// this.puerto = puerto;
		// this.db = db;
		// this.user = user;
		// this.password = password;

		conexion = new Conexion(ruta, host, puerto, db, user, password);
	}

	public Conexion getConexion() {
		return conexion;
	}

}
