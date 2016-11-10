package aed.sql.view;

import java.time.LocalDate;
import aed.sql.model.Libro;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ListaEjemplaresView extends BorderPane {

	private TableView<Libro> librosTable;
	private TableColumn<Libro, String> codLibroColumn, nombreLibroColumn, isbnLibroColumn, autorColumn;
	private TableColumn<Libro, LocalDate> fechaLibroColumn;
	private TableColumn<Libro, Integer> ejemplarColumn;
	private TableColumn<Libro, Double> importeColumn;

	public ListaEjemplaresView() {

		librosTable = new TableView<>();

		codLibroColumn = new TableColumn<>("Código");
		codLibroColumn.setCellValueFactory(new PropertyValueFactory<>("cod"));

		nombreLibroColumn = new TableColumn<>("Nombre");
		nombreLibroColumn.setPrefWidth(200);
		nombreLibroColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

		isbnLibroColumn = new TableColumn<>("ISBN");
		isbnLibroColumn.setPrefWidth(150);
		isbnLibroColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

		fechaLibroColumn = new TableColumn<>("Fecha Intro");
		fechaLibroColumn.setCellValueFactory(new PropertyValueFactory<>("fechaIntro"));

		ejemplarColumn = new TableColumn<>("CodEjemplar");
		ejemplarColumn.setCellValueFactory(new PropertyValueFactory<>("codEjemplar"));

		autorColumn = new TableColumn<Libro, String>("Autor");
		autorColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));

		importeColumn = new TableColumn<Libro, Double>("Importe");
		importeColumn.setCellValueFactory(new PropertyValueFactory<>("importe"));

		librosTable.getColumns().add(codLibroColumn);
		librosTable.getColumns().add(nombreLibroColumn);
		librosTable.getColumns().add(isbnLibroColumn);
		librosTable.getColumns().add(fechaLibroColumn);
		librosTable.getColumns().add(ejemplarColumn);
		librosTable.getColumns().add(autorColumn);
		librosTable.getColumns().add(importeColumn);

		setCenter(librosTable);
	}

	public TableView<Libro> getLibrosTable() {
		return librosTable;
	}

	public TableColumn<Libro, String> getCodLibroColumn() {
		return codLibroColumn;
	}

	public TableColumn<Libro, String> getNombreLibroColumn() {
		return nombreLibroColumn;
	}

	public TableColumn<Libro, String> getIsbnLibroColumn() {
		return isbnLibroColumn;
	}

	public TableColumn<Libro, LocalDate> getFechaLibroColumn() {
		return fechaLibroColumn;
	}

	public void setLibrosTable(TableView<Libro> librosTable) {
		this.librosTable = librosTable;
	}

}
