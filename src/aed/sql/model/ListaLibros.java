package aed.sql.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListaLibros {

	private ListProperty<Libro> libros;

	public ListaLibros() {
		libros = new SimpleListProperty<>(this, "libros", FXCollections.observableArrayList());
	}

	public ListProperty<Libro> librosProperty() {
		return this.libros;
	}

	public ObservableList<Libro> getLibros() {
		return this.librosProperty().get();
	}

	public void setLibros(final ObservableList<Libro> libros) {
		this.librosProperty().set(libros);
	}

}
