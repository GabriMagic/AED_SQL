package aed.sql.controller;

import java.sql.Connection;

import aed.sql.model.Conexion;
import aed.sql.view.MainView;

public class MainController {

	private Conexion conexion;
	private ListaLibrosController librosController;
	private MainView view;

	public MainController() {

		librosController = new ListaLibrosController();

		conexion = new Conexion();

		view = new MainView();

		// conectar();

		view.getConectarButton().setOnAction(e -> conectar());

		view.getLibrosTab().setContent(librosController.getView());
	}

	private void conectar() {
		conexion.setRuta(view.getRutaBox().getValue());
		conexion.setHost(view.getHostText().getText());
		conexion.setPuerto(Integer.parseInt(view.getPuertoText().getText()));
		conexion.setDb(view.getDbText().getText());
		conexion.setUser(view.getUserText().getText());
		conexion.setPassword(view.getPasswordField().getText());

		librosController.cargarLibros();

	}

	public MainView getView() {
		return view;
	}

	public Connection getConexion() {
		return conexion.getCon();
	}
}
