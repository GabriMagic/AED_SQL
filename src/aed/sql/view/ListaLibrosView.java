package aed.sql.view;

import java.time.LocalDate;
import aed.sql.model.Libro;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ListaLibrosView extends BorderPane {

	private TableView<Libro> librosTable;
	private TableColumn<Libro, String> nombreLibroColumn, isbnLibroColumn, autorColumn;
	private TableColumn<Libro, LocalDate> fechaLibroColumn;
	private TableColumn<Libro, Integer> codLibroColumn, ejemplarColumn;
	private TableColumn<Libro, Double> importeColumn;
	private ContextMenu contextMenu;
	private MenuItem eliminarMenu, addLibroMenu, addAutor;

	public ListaLibrosView() {

		eliminarMenu = new MenuItem("Eliminar");
		addLibroMenu = new MenuItem("Añadir libro");
		addAutor = new MenuItem("Añadir autor");

		contextMenu = new ContextMenu();
		contextMenu.getItems().addAll(addLibroMenu, eliminarMenu, new SeparatorMenuItem(), addAutor);

		librosTable = new TableView<>();
		librosTable.setEditable(true);
		librosTable.setContextMenu(contextMenu);

		codLibroColumn = new TableColumn<>("Código");
		codLibroColumn.setCellValueFactory(new PropertyValueFactory<>("codLibro"));

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

	public TableColumn<Libro, Integer> getCodLibroColumn() {
		return codLibroColumn;
	}

	public ContextMenu getContextMenu() {
		return contextMenu;
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

	public TableColumn<Libro, LocalDate> getFechaLibroColumn() {
		return fechaLibroColumn;
	}

	public void setLibrosTable(TableView<Libro> librosTable) {
		this.librosTable = librosTable;
	}

	public MenuItem getEliminarMenu() {
		return eliminarMenu;
	}

	public MenuItem getAddLibroMenu() {
		return addLibroMenu;
	}

	public MenuItem getAddAutor() {
		return addAutor;
	}

}
