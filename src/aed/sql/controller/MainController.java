package aed.sql.controller;

import aed.sql.model.Conexion;
import aed.sql.view.MainView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

public class MainController {

	private Conexion conexion;
	private ListaLibrosController librosController;
	private MainView view;

	public MainController() {

		view = new MainView();

		conexion = new Conexion(view.getRutaBox().getValue(), view.getHostText().getText(),
				Integer.parseInt(view.getPuertoText().getText()), view.getDbText().getText(),
				view.getUserText().getText(), view.getPasswordField().getText());

		conexion.conectar();

		librosController = new ListaLibrosController(conexion);

		bindVistas();

		view.getConectarButton().setOnAction(e -> conectar());
	}

	private void bindVistas() {
		view.getLibrosTab().setContent(librosController.getView());

	}

	private void conectar() {

		conexion.setRuta(view.getRutaBox().getValue());
		conexion.setHost(view.getHostText().getText());
		conexion.setPuerto(Integer.parseInt(view.getPuertoText().getText()));
		conexion.setDb(view.getDbText().getText());
		conexion.setUser(view.getUserText().getText());
		conexion.setPassword(view.getPasswordField().getText());

		try {

			if (conexion.conectar()) {

				librosController.getListaLibros().librosProperty().clear();
				librosController.cargarLibros();
				view.getCir().setImage(new Image("resources/green.png"));

			} else {
				Alert errorConnect = new Alert(AlertType.ERROR);
				errorConnect.setHeaderText(null);
				errorConnect.setContentText("Error al conectar con la base de datos: " + view.getDbText().getText());
				errorConnect.show();
				view.getCir().setImage(new Image("resources/red.png"));
				librosController.getView().getLibrosTable().setItems(null);
			}

		} catch (NumberFormatException | NullPointerException e) {
			Alert errorFormat = new Alert(AlertType.ERROR);
			errorFormat.setTitle("Conexión SQL");
			errorFormat.setHeaderText("Error al conectar");
			errorFormat.setContentText("Complete los campos correctamente.");
			errorFormat.show();
			view.getCir().setImage(new Image("resources/red.png"));
		}

	}

	public MainView getView() {
		return view;
	}

	public Conexion getConexion() {
		return conexion;
	}
}
