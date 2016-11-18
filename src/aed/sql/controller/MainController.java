package aed.sql.controller;

import java.sql.SQLException;

import aed.sql.model.Conexion;
import aed.sql.view.MainView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainController {

	private Conexion conexion;
	private ListaLibrosController librosController;
	private MainView view;
	private Stage app;

	public MainController(Stage primaryStage) {

		view = new MainView();

		app = primaryStage;
		librosController = new ListaLibrosController();
		conexion = new Conexion();

		view.getConectarButton().setOnAction(e -> conectar());

	}

	private void conectar() {

		conexion.setRuta(view.getRutaBox().getValue());
		conexion.setHost(view.getHostText().getText());
		conexion.setPuerto(Integer.parseInt(view.getPuertoText().getText()));
		conexion.setDb(view.getDbText().getText());
		conexion.setUser(view.getUserText().getText());
		conexion.setPassword(view.getPasswordField().getText());

		conexion.conectar();

		librosController = new ListaLibrosController();
		librosController.setConexion(conexion);

		view.getLibrosTab().setContent(librosController.getView());
		view.getActualizarButton().setOnAction(e -> librosController.cargarLibros());

		try {

			if (conexion.isConnected()) {

				app.setTitle("Conectado a: MySQL");
				librosController.cargarLibros();
				view.getCir().setImage(new Image("resources/green.png"));

			} else {

				Alert errorConnect = new Alert(AlertType.ERROR);
				errorConnect.setHeaderText(null);
				errorConnect.setContentText("Error al conectar con la base de datos: " + view.getDbText().getText());
				errorConnect.show();

				librosController.getListaLibros().getLibros().clear();
				app.setTitle("-------");
				view.getCir().setImage(new Image("resources/red.png"));

			}

		} catch (NumberFormatException | NullPointerException e) {
			e.printStackTrace();
			Alert errorFormat = new Alert(AlertType.ERROR);
			errorFormat.setTitle("Conexi�n SQL");
			errorFormat.setHeaderText("Error al conectar");
			errorFormat.setContentText("Complete los campos correctamente.");
			errorFormat.show();
			app.setTitle("-------");
			view.getCir().setImage(new Image("resources/red.png"));
			try {
				conexion.getConexion().close();
			} catch (SQLException err) {
				System.out.println("HEREE");
			}
		}

	}

	public MainView getView() {
		return view;
	}

	public Conexion getConexion() {
		return conexion;
	}
}
