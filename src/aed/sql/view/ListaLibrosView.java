package aed.sql.view;

import java.time.LocalDate;
import aed.sql.model.Libro;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ListaLibrosView extends BorderPane {

	private TableView<Libro> librosTable;
	private TableColumn<Libro, String> codLibroColumn, nombreLibroColumn, isbnLibroColumn, autorColumn;
	private TableColumn<Libro, LocalDate> fechaLibroColumn;
	private TableColumn<Libro, Integer> ejemplarColumn;
	private TableColumn<Libro, Double> importeColumn;
	private Button addLibroButton, eliminarLibroButton;

	public ListaLibrosView() {

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

		addLibroButton = new Button("Añadir");
		eliminarLibroButton = new Button("Eliminar");

		HBox botones = new HBox(5, addLibroButton, eliminarLibroButton);
		botones.setPadding(new Insets(5));
		botones.setAlignment(Pos.BASELINE_RIGHT);

		setCenter(librosTable);
		setBottom(new VBox(5, new Separator(), botones));
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

	public TableColumn<Libro, String> getAutorColumn() {
		return autorColumn;
	}

	public TableColumn<Libro, Integer> getEjemplarColumn() {
		return ejemplarColumn;
	}

	public TableColumn<Libro, Double> getImporteColumn() {
		return importeColumn;
	}

	public Button getAddLibroButton() {
		return addLibroButton;
	}

	public Button getEliminarLibroButton() {
		return eliminarLibroButton;
	}

	public TableColumn<Libro, LocalDate> getFechaLibroColumn() {
		return fechaLibroColumn;
	}

	public void setLibrosTable(TableView<Libro> librosTable) {
		this.librosTable = librosTable;
	}

}
