package aed.sql.controller;

import aed.sql.model.Conexion;

public class ConexionController {

	Conexion conexion;

	public ConexionController() {
//		conexion = new Conexion();
		conexion.conectar();
	}

	public Conexion getConexion() {
		return conexion;
	}

}
