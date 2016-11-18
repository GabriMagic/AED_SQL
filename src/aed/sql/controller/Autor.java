package aed.sql.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Autor {

	private StringProperty nombreAutor, codAutor;

	public Autor(String codAutor, String nombreAutor) {

		this.codAutor = new SimpleStringProperty(this, "codAutor", codAutor);
		this.nombreAutor = new SimpleStringProperty(this, "nombreAutor", nombreAutor);

	}

	public StringProperty nombreAutorProperty() {
		return this.nombreAutor;
	}

	public String getNombreAutor() {
		return this.nombreAutorProperty().get();
	}

	public void setNombreAutor(final String nombreAutor) {
		this.nombreAutorProperty().set(nombreAutor);
	}

	public StringProperty codAutorProperty() {
		return this.codAutor;
	}

	public String getCodAutor() {
		return this.codAutorProperty().get();
	}

	public void setCodAutor(final String codAutor) {
		this.codAutorProperty().set(codAutor);
	}

	@Override
	public String toString() {
		return getNombreAutor();
	}

}
