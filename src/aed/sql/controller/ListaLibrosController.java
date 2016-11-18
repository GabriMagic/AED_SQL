package aed.sql.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aed.sql.model.Conexion;
import aed.sql.model.Libro;
import aed.sql.model.ListaLibros;
import aed.sql.view.ListaLibrosView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
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

	@FXML
	private GridPane addAutorView;

	@FXML
	private ComboBox<String> autoresCombo;

	@FXML
	private Label labelText;

	@FXML
	private Button addAutorButton;

	@FXML
	private Button cancelAutorButton;

	private ListaLibrosView view;
	private ListaLibros listaLibros;
	private Conexion conexion;
	private Stage insertStage;

	public ListaLibrosController(Conexion conexion) {

		this.conexion = conexion;

		FXMLLoader loaderLibros = new FXMLLoader(getClass().getResource("/aed/sql/view/insertLibroView.fxml"));
		loaderLibros.setController(this);
		try {
			insertLibroView = loaderLibros.load();
		} catch (IOException e1) {
		}

		FXMLLoader loaderAutores = new FXMLLoader(getClass().getResource("/aed/sql/view/AddAutorView.fxml"));
		loaderAutores.setController(this);
		try {
			addAutorView = loaderAutores.load();
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

		view.getNombreLibroColumn().setCellFactory(TextFieldTableCell.forTableColumn());
		view.getIsbnLibroColumn().setCellFactory(TextFieldTableCell.forTableColumn());

		view.getNombreLibroColumn().setOnEditCommit(e -> updateNombreLibro(e));
		view.getIsbnLibroColumn().setOnEditCommit(e -> updateIsbnLibro(e));

		view.getEliminarMenu().setOnAction(e -> onEliminarButtonAction(e));
		view.getAddLibroMenu().setOnAction(e -> onAddButtonAction(e));
		view.getAddAutor().setOnAction(e -> onAddAutor(e));
		
		addAutorButton.setOnAction(e -> onConfirmAddAutor(e));

	}

	private void onConfirmAddAutor(ActionEvent e) {
		
		
		
	}

	private void onAddAutor(ActionEvent e) {

		ObservableList<String> autores = FXCollections.observableArrayList();

		try {
			PreparedStatement sql = conexion.getConexion().prepareStatement("SELECT * FROM autores");
			ResultSet result = sql.executeQuery();

			while (result.next()) {
				autores.add(result.getString("nombreAutor"));
			}

			autoresCombo.setItems(autores);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		labelText.setText(
				"A�adir autor al libro: " + view.getLibrosTable().getSelectionModel().getSelectedItem().getNombre());
		insertStage.getScene().setRoot(addAutorView);
		insertStage.setTitle("A�adir autor");
		insertStage.show();

	}

	private void updateIsbnLibro(CellEditEvent<Libro, String> e) {
		Pattern pattern = Pattern.compile("[0-9][0-9]-[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[a-zA-Z]");
		Matcher mat = pattern.matcher(e.getNewValue());
		if (mat.matches()) {
			try {
				PreparedStatement updateNombreLibro = conexion.getConexion()
						.prepareStatement("UPDATE libros SET ISBN = ? WHERE codLibro = ?");

				updateNombreLibro.setInt(2, view.getLibrosTable().getSelectionModel().getSelectedItem().getCodLibro());
				updateNombreLibro.setString(1, e.getNewValue());
				updateNombreLibro.executeUpdate();
				cargarLibros();
			} catch (SQLException e1) {
			}
		} else {
			Alert unvalidISBN = new Alert(AlertType.ERROR);
			unvalidISBN.setTitle("Error al modificar el ISBN");
			unvalidISBN.setHeaderText(null);
			unvalidISBN.setContentText("No es un ISBN v�lido.");
			unvalidISBN.show();
			view.getIsbnLibroColumn().setVisible(false);
			view.getIsbnLibroColumn().setVisible(true);
		}
	}

	private void updateNombreLibro(CellEditEvent<Libro, String> e) {
		try {
			PreparedStatement updateNombreLibro = conexion.getConexion()
					.prepareStatement("UPDATE libros SET nombreLibro = ? WHERE codLibro = ?");

			updateNombreLibro.setInt(2, view.getLibrosTable().getSelectionModel().getSelectedItem().getCodLibro());
			updateNombreLibro.setString(1, e.getNewValue());
			updateNombreLibro.executeUpdate();
			cargarLibros();
		} catch (SQLException e1) {
		}
	}

	private void onAddButtonAction(ActionEvent e) {
		if (conexion.isConnected()) {
			insertStage.show();
		} else {
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
				Alert eliminarConfirm = new Alert(AlertType.CONFIRMATION);
				eliminarConfirm.setTitle("Atenci�n!");
				eliminarConfirm.setHeaderText("Esta accci�n no se puede deshacer");
				eliminarConfirm.setContentText("�Desea eliminar el libro de todas formas?");
				Optional<ButtonType> answer = eliminarConfirm.showAndWait();
				if (answer.get() == ButtonType.OK) {
					PreparedStatement query = conexion.getConexion()
							.prepareStatement("DELETE FROM libros WHERE codLibro = ?");
					query.setInt(1, aux);
					query.execute();
					cargarLibros();
				}
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

				String query = "SELECT lb.codLibro, nombreLibro, ISBN, fechaIntro, codEjemplar, importe, nombreAutor, au.codAutor FROM libros as lb "
						+ "left join ejemplares as ej on ej.codLibro = lb.codLibro "
						+ "left join librosautores as lau on lau.codLibro = lb.codLibro "
						+ "left join autores as au on au.codAutor = lau.codAutor";

				PreparedStatement sql = conexion.getConexion().prepareStatement(query);

				ResultSet resultado = sql.executeQuery();

				while (resultado.next()) {

					listaLibros.getLibros()
							.add(new Libro(resultado.getInt("codLibro"), resultado.getString("nombreLibro"),
									resultado.getString("isbn"), resultado.getDate("fechaIntro").toLocalDate(),
									resultado.getInt("codEjemplar"), resultado.getDouble("importe"),
									resultado.getString("nombreAutor"), resultado.getString("codAutor")));
				}
			} catch (SQLException e) {
				System.err.println(e.getLocalizedMessage());
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
