package aed.sql.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import aed.sql.model.Conexion;
import aed.sql.model.Libro;
import aed.sql.model.ListaLibros;
import aed.sql.view.ListaLibrosView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListaLibrosController {

	@FXML
	private GridPane insertLibroView;

	@FXML
	private TextField nombreText;

	@FXML
	private TextField isbnText;

	@FXML
	private Button addLibroButton;

	@FXML
	private Button cancelButton;

	private ListaLibrosView view;
	private ListaLibros listaLibros;
	private Conexion conexion;
	private Stage insertStage;

	public ListaLibrosController(Conexion conexion) {

		this.conexion = conexion;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/aed/sql/view/insertLibroView.fxml"));

		loader.setController(this);
		try {
			insertLibroView = loader.load();
		} catch (IOException e1) {
		}

		view = new ListaLibrosView();

		conexion.conectar();

		listaLibros = new ListaLibros();

		insertStage = new Stage();
		insertStage.setTitle("A�adir Libro");
		insertStage.setResizable(false);
		insertStage.initModality(Modality.APPLICATION_MODAL);
		insertStage.setScene(new Scene(insertLibroView));

		view.getAddLibroButton().setOnAction(e -> onAddButtonAction(e));
		view.getEliminarLibroButton().setOnAction(e -> onEliminarButtonAction(e));

	}

	private void onAddButtonAction(ActionEvent e) {
		if (conexion.isConnected())
			insertStage.show();
		else {
			Alert errorCon = new Alert(AlertType.ERROR);
			errorCon.setTitle("Error de conexi�n");
			errorCon.setHeaderText("Error al conectar");
			errorCon.setContentText("La conexi�n no se ha establecido");
			errorCon.show();
		}
	}

	private void onEliminarButtonAction(ActionEvent e) {

		int aux = view.getLibrosTable().getSelectionModel().getSelectedItem().getCodLibro();

		if (conexion.conectar()) {
			try {
				PreparedStatement query = conexion.getConexion()
						.prepareStatement("DELETE FROM libros WHERE codLibro = ?");

				query.setInt(1, aux);
				query.execute();

				cargarLibros();
			} catch (SQLException e1) {
				Alert eliminarConfirm = new Alert(AlertType.CONFIRMATION);
				eliminarConfirm.setTitle("Error al borrar");
				eliminarConfirm.setHeaderText("El libro que desea borrar tiene m�s de un ejemplar.");
				eliminarConfirm.setContentText("�Desea eliminar todos sus ejemplares?");

				Optional<ButtonType> answer = eliminarConfirm.showAndWait();

				if (answer.get() == ButtonType.OK) {
					try {
						PreparedStatement query2 = conexion.getConexion()
								.prepareStatement("DELETE FROM librosautores WHERE codLibro = ?");
						System.out.println(aux);
						query2.setInt(1, aux);
						
						query2.execute();
						cargarLibros();

					} catch (SQLException e2) {
						System.out.println(e2.getLocalizedMessage());
					}

				} else {

				}

			}

		}

	}

	// Cannot delete or update a parent row: a foreign key constraint fails
	// (`bdbiblioteca`.`librosautores`, CONSTRAINT `FK_libro` FOREIGN KEY
	// (`codLibro`) REFERENCES `libros` (`codLibro`))

	@FXML
	void onAddLibroButton(ActionEvent event) {
		if (conexion.conectar())
			try {

				String sql = "INSERT INTO libros (nombreLibro, ISBN) VALUES (?,?)";
				PreparedStatement query = conexion.getConexion().prepareStatement(sql);

				query.setString(1, nombreText.getText());
				query.setString(2, isbnText.getText());

				query.execute();

				cargarLibros();
			} catch (SQLException e1) {
				System.out.println("ERRO SQL");
				System.err.println(e1.getLocalizedMessage());
			} catch (NullPointerException e2) {
				Alert errorCon = new Alert(AlertType.ERROR);
				errorCon.setTitle("Error de conexi�n");
				errorCon.setHeaderText("Error al conectar");
				errorCon.setContentText("La conexi�n no se ha establecido");
				errorCon.show();
			} finally {
				nombreText.setText("");
				isbnText.setText("");
				insertStage.close();
			}
	}

	@FXML
	void onCancelButton(ActionEvent event) {
		insertStage.close();
	}

	public void cargarLibros() {

		listaLibros.librosProperty().clear();
		view.getLibrosTable().setItems(listaLibros.librosProperty());

		if (conexion.conectar()) {
			try {

				String query = "SELECT lb.codLibro, nombreLibro, ISBN, fechaIntro, codEjemplar, importe, nombreAutor FROM libros as lb "
						+ "left join ejemplares as ej on ej.codLibro = lb.codLibro "
						+ "left join librosautores as lau on lau.codLibro = lb.codLibro "
						+ "left join autores as au on au.codAutor = lau.codAutor";

				PreparedStatement sql = conexion.getConexion().prepareStatement(query);

				ResultSet resultado = sql.executeQuery();

				while (resultado.next()) {

					listaLibros.getLibros()
							.add(new Libro(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
									resultado.getDate(4).toLocalDate(), resultado.getInt(5), resultado.getDouble(6),
									resultado.getString(7)));
				}
			} catch (SQLException e) {
				System.err.println("SQL EXCEPTION");
			}
		}
	}

	public ListaLibrosView getView() {
		return view;
	}

	public ListaLibros getListaLibros() {
		return listaLibros;
	}
}
