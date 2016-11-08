package aed.sql.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import aed.sql.model.Conexion;
import aed.sql.model.Libro;
import aed.sql.model.ListaLibros;
import aed.sql.view.MainView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

public class MainController {

	private ListaLibros listaLibros;
	private Conexion conexion;
	private ListaLibrosController librosController;
	private MainView view;

	public MainController() {

		view = new MainView();
		librosController = new ListaLibrosController();
		listaLibros = new ListaLibros();

		conexion = new Conexion();

		bindVistas();

		librosController.getView().getLibrosTable().setItems(listaLibros.librosProperty());

		view.getConectarButton().setOnAction(e -> conectar());
	}

	private void bindVistas() {
		view.getLibrosTab().setContent(librosController.getView());

	}

	public void cargarLibros() {

		try {

			PreparedStatement sql = conexion.getConexion().prepareStatement("SELECT * FROM libros");
			ResultSet resultado = sql.executeQuery();

			while (resultado.next()) {
				listaLibros.getLibros().add(new Libro(resultado.getInt(1), resultado.getString(2),
						resultado.getString(3), resultado.getDate(4).toLocalDate()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		listaLibros = new ListaLibros();
	}

	private void vaciarTabla() {
		for (int i = 0; i < librosController.getView().getLibrosTable().getItems().size(); i++) {
			librosController.getView().getLibrosTable().getItems().clear();
		}
	}

	private void conectar() {

		try {
			conexion.setRuta(view.getRutaBox().getValue());
			conexion.setHost(view.getHostText().getText());
			conexion.setPuerto(Integer.parseInt(view.getPuertoText().getText()));
			conexion.setDb(view.getDbText().getText());
			conexion.setUser(view.getUserText().getText());
			conexion.setPassword(view.getPasswordField().getText());

			if (conexion.conectar()) {
				cargarLibros();
				view.getCir().setImage(new Image("resources/green.png"));

			} else {
				Alert errorConnect = new Alert(AlertType.ERROR);
				errorConnect.setHeaderText(null);
				errorConnect.setContentText("Error al conectar con la base de datos: " + view.getDbText().getText());
				errorConnect.show();
				view.getCir().setImage(new Image("resources/red.png"));
				vaciarTabla();
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
