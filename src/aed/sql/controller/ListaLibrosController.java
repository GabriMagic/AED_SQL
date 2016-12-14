package aed.sql.controller;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aed.sql.model.Autor;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
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
	private ComboBox<Autor> autoresCombo;
	@FXML
	private Label labelText;
	@FXML
	private Button addAutorButton;
	@FXML
	private Button cancelAutorButton;
	// pListaEjemplares
	@FXML
	private BorderPane pLEView;
	@FXML
	private ComboBox<Autor> pLECombo;
	@FXML
	private TableView<Libro> pLETable;
	@FXML
	private Button pLEMostrar;
	@FXML
	private TableColumn<Libro, Integer> pLECodLibro;
	@FXML
	private TableColumn<Libro, String> pLENombre;
	@FXML
	private TableColumn<Libro, String> pLEAutor;
	@FXML
	private TableColumn<Libro, String> pLEIsbn;
	@FXML
	private TableColumn<Libro, Date> pLEFechaIntro;
	@FXML
	private Label varLabel;
	// pCantidadEjemplares
	@FXML
	private BorderPane pCEView;
	@FXML
	private Button pCEMostrar;
	@FXML
	private ComboBox<String> pCECombo;
	@FXML
	private Label pCELabel;
	// fNumLibros
	@FXML
	private BorderPane fNLView;
	@FXML
	private ComboBox<String> fNLCombo;
	@FXML
	private Button fNLMostrar;
	@FXML
	private Label fNLLabel;

	private ListaLibrosView view;
	private ListaLibros listaLibros;
	private Conexion conexion;
	private Stage secondaryStage;
	private Pattern pattern;
	private Alert alertMessage;

	public ListaLibrosController() {

		alertMessage = new Alert(AlertType.ERROR);
		view = new ListaLibrosView();
		listaLibros = new ListaLibros();
		pattern = Pattern.compile("[0-9][0-9]-[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[a-zA-Z]");

		FXMLloaders();

		// Tabla PLE
		pLECodLibro.setCellValueFactory(new PropertyValueFactory<>("codLibro"));
		pLENombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		pLEAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		pLEIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		pLEFechaIntro.setCellValueFactory(new PropertyValueFactory<>("fechaIntro"));

		secondaryStage = new Stage();
		secondaryStage.setTitle("Añadir Libro");
		secondaryStage.setResizable(false);
		secondaryStage.initModality(Modality.APPLICATION_MODAL);
		secondaryStage.setScene(new Scene(insertLibroView));

		view.getNombreLibroColumn().setCellFactory(TextFieldTableCell.forTableColumn());
		view.getIsbnLibroColumn().setCellFactory(TextFieldTableCell.forTableColumn());

		view.getNombreLibroColumn().setOnEditCommit(e -> updateNombreLibro(e));
		view.getIsbnLibroColumn().setOnEditCommit(e -> updateIsbnLibro(e));

		view.getEliminarMenu().setOnAction(e -> onEliminarButtonAction(e));
		view.getAddLibroMenu().setOnAction(e -> onAddButtonAction(e));
		view.getAddAutor().setOnAction(e -> onAddAutor(e));
		view.getpListaEjemplares().setOnAction(e -> pListaEjemplares(e));
		view.getpCantidadEjemplares().setOnAction(e -> pCantidadEjemplares(e));
		view.getFnumAutorLibro().setOnAction(e -> fnumAutorLibro(e));

		addAutorButton.setOnAction(e -> onConfirmAddAutor(e));
		cancelAutorButton.setOnAction(e -> secondaryStage.close());
		pLEMostrar.setOnAction(e -> MpLE(e));
		pCEMostrar.setOnAction(e -> MpCE(e));
		fNLMostrar.setOnAction(e -> MfNL(e));

	}

	private void fnumAutorLibro(ActionEvent e) {

		fNLCombo.setValue(null);
		fNLLabel.setText("");
		ObservableList<String> autores = FXCollections.observableArrayList();
		PreparedStatement sql;
		try {
			sql = conexion.getConexion().prepareStatement("SELECT * FROM autores");
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				autores.add(result.getString(2));
			}
			fNLCombo.setItems(autores);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		secondaryStage.setTitle("fnumAutorLibro");
		secondaryStage.getScene().setRoot(fNLView);
		secondaryStage.show();

	}

	private void MfNL(ActionEvent e) {

		try {
			PreparedStatement sql = null;
			if (conexion.getConnected() == 1) {
				sql = conexion.getConexion().prepareStatement("SELECT fnumAutorLibro(?) as fnumAutorLibro");
			} else if (conexion.getConnected() == 3) {
				sql = conexion.getConexion().prepareStatement("SELECT dbo.fnumAutorLibro(?) as fnumAutorLibro");
			}
			sql.setString(1, fNLCombo.getValue());
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				fNLLabel.setText("Número de libros: " + result.getInt("fnumAutorLibro"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		pCELabel.setText("Cantidad de libros: ");
	}

	private void pCantidadEjemplares(ActionEvent e) {
		try {
			ObservableList<String> ISBNs = FXCollections.observableArrayList();
			PreparedStatement sql = conexion.getConexion().prepareStatement("SELECT * FROM libros");
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				ISBNs.add(result.getString("ISBN"));
			}
			pCECombo.setItems(ISBNs);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		pCELabel.setText("");
		pCECombo.setValue(null);
		secondaryStage.setTitle("pCantidadEjemplares");
		secondaryStage.getScene().setRoot(pCEView);
		secondaryStage.show();
	}

	private void MpCE(ActionEvent e) {
		try {
			CallableStatement pCantidadEjemplares = conexion.getConexion()
					.prepareCall("{CALL pCantidadEjemplares(?,?,?)}");
			pCantidadEjemplares.setString(1, pCECombo.getValue());
			pCantidadEjemplares.registerOutParameter(2, Types.INTEGER);
			pCantidadEjemplares.registerOutParameter(3, Types.DATE);

			pCantidadEjemplares.execute();

			pCELabel.setText("Cantidad de ejemplares: " + pCantidadEjemplares.getInt(2));

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	private void pListaEjemplares(ActionEvent e) {
		pLETable.getItems().clear();
		ObservableList<Autor> autores = FXCollections.observableArrayList();
		try {
			PreparedStatement sql = conexion.getConexion().prepareStatement("SELECT * FROM autores");
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				autores.add(new Autor(result.getString("codAutor"), result.getString("nombreAutor")));
			}
			pLECombo.setItems(autores);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		secondaryStage.setTitle("pListaEjemplares");
		secondaryStage.getScene().setRoot(pLEView);
		secondaryStage.show();
	}

	private void MpLE(ActionEvent e) {

		try {
			ObservableList<Libro> pLELibros = FXCollections.observableArrayList();
			CallableStatement pListaEjemplares = conexion.getConexion().prepareCall("{CALL pListaEjemplares(?)}");
			pListaEjemplares.setString(1, pLECombo.getValue().getNombreAutor());
			ResultSet resultado = pListaEjemplares.executeQuery();
			while (resultado.next()) {
				Libro l1 = new Libro();
				l1.setCodLibro(resultado.getInt("codLibro"));
				l1.setNombre(resultado.getString("nombreLibro"));
				l1.setAutor(resultado.getString("nombreAutor"));
				l1.setIsbn(resultado.getString("ISBN"));
				l1.setFechaIntro(resultado.getDate("fechaIntro"));
				pLELibros.add(l1);
			}
			pLETable.setItems(pLELibros);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void onConfirmAddAutor(ActionEvent e) {
		try {
			if (autoresCombo.getValue() != null) {
				CallableStatement pLibrosAutores = conexion.getConexion().prepareCall("{CALL pLibrosAutores(?,?,?)}");
				pLibrosAutores.setString(1, view.getLibrosTable().getSelectionModel().getSelectedItem().getIsbn());
				pLibrosAutores.setString(2, autoresCombo.getValue().getCodAutor());
				pLibrosAutores.registerOutParameter(3, Types.INTEGER);
				pLibrosAutores.execute();
			} else {

			}
			cargarLibros();
			secondaryStage.close();
		} catch (SQLException e1) {
			System.out.println(e1.getLocalizedMessage());
			alertMessage.setAlertType(AlertType.ERROR);
			alertMessage.setTitle("Error al añadir el autor");
			alertMessage.setHeaderText(null);
			alertMessage.setContentText("Ese libro ya tiene ese autor.");
			alertMessage.show();
		}
	}

	private void onAddAutor(ActionEvent e) {
		ObservableList<Autor> autores = FXCollections.observableArrayList();
		try {
			PreparedStatement sql = conexion.getConexion().prepareStatement("SELECT * FROM autores");
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				autores.add(new Autor(result.getString("codAutor"), result.getString("nombreAutor")));
			}
			autoresCombo.setItems(autores);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		labelText.setText(
				"Añadir autor al libro: " + view.getLibrosTable().getSelectionModel().getSelectedItem().getNombre());
		secondaryStage.getScene().setRoot(addAutorView);
		secondaryStage.setTitle("Añadir autor");
		secondaryStage.show();
	}

	private void updateIsbnLibro(CellEditEvent<Libro, String> e) {
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
			alertMessage.setAlertType(AlertType.ERROR);
			alertMessage.setTitle("Error al modificar el ISBN");
			alertMessage.setHeaderText(null);
			alertMessage.setContentText("No es un ISBN válido.");
			alertMessage.show();
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
		if (conexion.getConnected() > 0) {
			secondaryStage.getScene().setRoot(insertLibroView);
			secondaryStage.show();
		} else {
			alertMessage.setAlertType(AlertType.ERROR);
			alertMessage.setTitle("Error de conexión");
			alertMessage.setHeaderText("Error al conectar");
			alertMessage.setContentText("La conexión no se ha establecido");
			alertMessage.show();
		}
	}

	private void onEliminarButtonAction(ActionEvent e) {
		int aux = view.getLibrosTable().getSelectionModel().getSelectedItem().getCodLibro();
		if (conexion.getConnected() > 0) {
			try {
				alertMessage.setAlertType(AlertType.CONFIRMATION);
				alertMessage.setTitle("Atención!");
				alertMessage.setHeaderText("Esta accción no se puede deshacer");
				alertMessage.setContentText("¿Desea eliminar el libro de todas formas?");
				Optional<ButtonType> answer = alertMessage.showAndWait();
				if (answer.get() == ButtonType.OK) {
					PreparedStatement query = conexion.getConexion()
							.prepareStatement("DELETE FROM libros WHERE codLibro = ?");
					query.setInt(1, aux);
					query.execute();

					cargarLibros();
				}
			} catch (SQLException e1) {
				alertMessage.setAlertType(AlertType.CONFIRMATION);
				alertMessage.setTitle("Error al borrar");
				alertMessage.setHeaderText("El libro que desea borrar tiene más de un ejemplar.");
				alertMessage.setContentText("¿Desea eliminar todos sus ejemplares?");
				Optional<ButtonType> answer = alertMessage.showAndWait();
				if (answer.get() == ButtonType.OK) {
					try {
						PreparedStatement query1 = conexion.getConexion()
								.prepareStatement("DELETE FROM librosautores WHERE codLibro = ?");
						query1.setInt(1, aux);
						query1.execute();

						PreparedStatement query2 = conexion.getConexion()
								.prepareStatement("DELETE FROM ejemplares WHERE codLibro = ?");
						query2.setInt(1, aux);
						query2.execute();
						PreparedStatement query3 = conexion.getConexion()
								.prepareStatement("DELETE FROM libros WHERE codLibro = ?");
						query3.setInt(1, aux);
						query3.execute();
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
		if (conexion.getConnected() > 0)
			try {
				Matcher mat = pattern.matcher(isbnText.getText());
				if (mat.matches()) {
					String sql = "INSERT INTO libros (nombreLibro, ISBN, fechaIntro) VALUES (?,?,?)";
					PreparedStatement query = conexion.getConexion().prepareStatement(sql);
					query.setString(1, nombreText.getText());
					query.setString(2, isbnText.getText());
					query.setDate(3, null);
					if (conexion.getConnected() == 2 || conexion.getConnected() == 3) {
						query.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
					}
					query.execute();

					cargarLibros();
				} else {
					alertMessage.setAlertType(AlertType.ERROR);
					alertMessage.setTitle("Error de ISBN");
					alertMessage.setHeaderText(null);
					alertMessage.setContentText("No es un ISBN válido.");
					alertMessage.show();
				}
			} catch (NullPointerException | SQLException e2) {
				e2.printStackTrace();
				System.err.println(e2.getLocalizedMessage());
				alertMessage.setAlertType(AlertType.ERROR);
				alertMessage.setTitle("Error de conexión");
				alertMessage.setHeaderText("Error al conectar");
				alertMessage.setContentText("La conexión no se ha establecido");
				alertMessage.show();
			} finally {
				nombreText.setText("");
				isbnText.setText("");
				secondaryStage.close();
			}
	}

	@FXML
	void onCancelButton(ActionEvent event) {
		secondaryStage.close();
	}

	public void cargarLibros() {

		listaLibros.librosProperty().clear();
		view.getLibrosTable().setItems(listaLibros.librosProperty());

		if (conexion.getConnected() > 0) {
			try {

				String query = "SELECT lb.codLibro, nombreLibro, ISBN, fechaIntro, codEjemplar, importe, nombreAutor, au.codAutor FROM libros as lb "
						+ "left join ejemplares as ej on ej.codLibro = lb.codLibro "
						+ "left join librosautores as lau on lau.codLibro = lb.codLibro "
						+ "left join autores as au on au.codAutor = lau.codAutor ORDER BY lb.codLibro";

				PreparedStatement sql = conexion.getConexion().prepareStatement(query);

				ResultSet resultado = sql.executeQuery();

				while (resultado.next()) {

					if (conexion.getConnected() == 1) {
						listaLibros.getLibros()
								.add(new Libro(resultado.getInt("codLibro"), resultado.getString("nombreLibro"),
										resultado.getString("isbn"), resultado.getDate("fechaIntro"),
										resultado.getInt("codEjemplar"), resultado.getDouble("importe"),
										resultado.getString("nombreAutor"), resultado.getString("codAutor")));
					} else {
						listaLibros.getLibros()
								.add(new Libro(resultado.getInt("codLibro"), resultado.getString("nombreLibro"),
										resultado.getString("isbn"), resultado.getDate("fechaIntro"),
										resultado.getInt("codEjemplar"), resultado.getDouble("importe"),
										resultado.getString("nombreAutor"), resultado.getString("codAutor")));
					}
				}
			} catch (Exception e) {
				System.err.println(e.getLocalizedMessage());
				System.err.println("SQL EXCEPTION");
			}
		}

	}

	private void FXMLloaders() {
		try {
			FXMLLoader loaderLibros = new FXMLLoader(getClass().getResource("/aed/sql/view/insertLibroView.fxml"));
			loaderLibros.setController(this);
			insertLibroView = loaderLibros.load();

			FXMLLoader loaderAutores = new FXMLLoader(getClass().getResource("/aed/sql/view/AddAutorView.fxml"));
			loaderAutores.setController(this);
			addAutorView = loaderAutores.load();

			FXMLLoader pLE = new FXMLLoader(getClass().getResource("/aed/sql/view/PLEView.fxml"));
			pLE.setController(this);
			pLEView = pLE.load();

			FXMLLoader pCE = new FXMLLoader(getClass().getResource("/aed/sql/view/PCEView.fxml"));
			pCE.setController(this);
			pCEView = pCE.load();

			FXMLLoader fNL = new FXMLLoader(getClass().getResource("/aed/sql/view/FnumAutorLibro.fxml"));
			fNL.setController(this);
			fNLView = fNL.load();
		} catch (IOException e1) {

		}
	}

	public ListaLibrosView getView() {
		return view;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

	public ListaLibros getListaLibros() {
		return listaLibros;
	}

}
