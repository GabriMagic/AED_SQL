package aed.sql.controller;

import aed.sql.model.Conexion;
import aed.sql.view.MainView;

public class MainController {

	Conexion conexion;
	ListaLibrosController aedSqlController;
	// ConexionController conexionController;
	MainView view;

	public MainController() {

		aedSqlController = new ListaLibrosController();

		conexion = new Conexion();

		view = new MainView();

		conectar();

		view.getConectarButton().setOnAction(e -> conectar());

		view.getLibrosTab().setContent(aedSqlController.getView());
	}

	private void conectar() {
		conexion.setRuta(view.getRutaBox().getValue());
		conexion.setHost(view.getHostText().getText());
		conexion.setPuerto(Integer.parseInt(view.getPuertoText().getText()));
		conexion.setDb(view.getDbText().getText());
		conexion.setUser(view.getUserText().getText());
		conexion.setPassword(view.getPasswordField().getText());

	}

	public MainView getView() {
		return view;
	}
}
