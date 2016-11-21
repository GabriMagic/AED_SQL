package aed.sql.controller;

import java.sql.SQLException;

import aed.sql.model.Conexion;
import aed.sql.view.MainView;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainController {

	private Conexion conexion;
	private ListaLibrosController controller;
	private MainView view;
	private Stage app;

	public MainController(Stage primaryStage) {

		view = new MainView();

		app = primaryStage;
		controller = new ListaLibrosController();
		conexion = new Conexion();

		view.getConDisconButton().setOnAction(e -> inicializarConexion(e));
		view.getActualizarButton().setOnAction(e -> inicializarConexion(e));

	}

	private void inicializarConexion(ActionEvent e2) {

		if (view.getConDisconButton().isSelected()) {

			view.getCir().setImage(new Image("resources/green.png"));
			view.getActualizarButton().setDisable(false);

			conexion.setRuta(view.getRutaBox().getValue());
			conexion.setHost(view.getHostText().getText());
			conexion.setPuerto(Integer.parseInt(view.getPuertoText().getText()));
			conexion.setDb(view.getDbText().getText());
			conexion.setUser(view.getUserText().getText());
			conexion.setPassword(view.getPasswordField().getText());

			conexion.conectar();

			controller = new ListaLibrosController();
			controller.setConexion(conexion);

			view.setCenter(controller.getView());

			try {

				switch (conexion.isConnected()) {
				case 1:
					app.setTitle("Conectado a: MySQL");
					controller.cargarLibros();
					controller.getView().getAddAutor().setDisable(false);
					controller.getView().getpListaEjemplares().setDisable(false);
					controller.getView().getFnumAutorLibro().setDisable(false);
					controller.getView().getpCantidadEjemplares().setDisable(false);
					controller.getFechaLibro().setDisable(true);
					view.getCir().setImage(new Image("resources/green.png"));
					break;
				case 2:
					app.setTitle("Conectado a: ACCESS");
					controller.cargarLibros();
					controller.getView().getAddAutor().setDisable(true);
					controller.getView().getpListaEjemplares().setDisable(true);
					controller.getView().getpCantidadEjemplares().setDisable(true);
					controller.getView().getFnumAutorLibro().setDisable(true);
					controller.getFechaLibro().setDisable(false);
					view.getCir().setImage(new Image("resources/green.png"));
					break;
				case 3:
					app.setTitle("Conectado a: SQL Server");
					controller.cargarLibros();
					controller.getView().getAddAutor().setDisable(false);
					controller.getView().getpListaEjemplares().setDisable(false);
					controller.getView().getpCantidadEjemplares().setDisable(false);
					controller.getView().getFnumAutorLibro().setDisable(false);
					controller.getFechaLibro().setDisable(true);
					view.getCir().setImage(new Image("resources/green.png"));
					break;
				case 0:
					Alert errorConnect = new Alert(AlertType.ERROR);
					errorConnect.setHeaderText(null);
					errorConnect
							.setContentText("Error al conectar con la base de datos: " + view.getDbText().getText());
					errorConnect.show();
					controller.getListaLibros().getLibros().clear();
					app.setTitle("-------");
					view.getCir().setImage(new Image("resources/red.png"));
					break;
				}

			} catch (NumberFormatException | NullPointerException e) {
				e.printStackTrace();
				Alert errorFormat = new Alert(AlertType.ERROR);
				errorFormat.setTitle("Conexión SQL");
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
		} else {
			try {
				conexion.getConexion().close();
				view.getCir().setImage(new Image("resources/red.png"));
				view.getActualizarButton().setDisable(true);
				view.setCenter(null);
			} catch (SQLException e) {
				e.printStackTrace();
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
