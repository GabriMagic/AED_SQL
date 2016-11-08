package aed.sql.controller;

import aed.sql.view.MainView;

public class MainController {

	// Conexion conexion;
	ListaLibrosController aedSqlController;
	MainView view;

	public MainController() {

		aedSqlController = new ListaLibrosController();
		view = new MainView();
		// conexion = new Conexion();

		
		
		view.getLibrosTab().setContent(aedSqlController.getView());
	}

	public ListaLibrosController getAedSqlController() {
		return aedSqlController;
	}

	public MainView getView() {
		return view;
	}
}
