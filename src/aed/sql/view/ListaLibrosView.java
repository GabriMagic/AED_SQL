package aed.sql.view;

import java.time.LocalDate;

import aed.sql.model.Libro;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ListaLibrosView extends BorderPane {

	private TableView<Libro> librosTable;
	private TableColumn<Libro, String> codLibroColumn;
	private TableColumn<Libro, String> nombreLibroColumn;
	private TableColumn<Libro, String> isbnLibroColumn;
	private TableColumn<Libro, LocalDate> fechaLibroColumn;

	public ListaLibrosView() {

		librosTable = new TableView<>();
		librosTable.setMaxHeight(Double.MAX_VALUE);

		codLibroColumn = new TableColumn<>("Código");
		codLibroColumn.setCellValueFactory(new PropertyValueFactory<>("cod"));

		nombreLibroColumn = new TableColumn<>("Nombre");
		nombreLibroColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

		isbnLibroColumn = new TableColumn<>("ISBN");
		isbnLibroColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

		fechaLibroColumn = new TableColumn<>("Fecha Intro");
		fechaLibroColumn.setCellValueFactory(new PropertyValueFactory<>("fechaIntro"));

		librosTable.getColumns().add(codLibroColumn);
		librosTable.getColumns().add(nombreLibroColumn);
		librosTable.getColumns().add(isbnLibroColumn);
		librosTable.getColumns().add(fechaLibroColumn);

		setCenter(librosTable);
	}

	public TableView<Libro> getLibrosTable() {
		return librosTable;
	}

}
