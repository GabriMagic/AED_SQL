package aed.sql.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Libro {

	private IntegerProperty cod;
	private SimpleStringProperty nombre, isbn;
	private ObjectProperty<LocalDate> fechaIntro;

	public Libro(Integer cod, String nombre, String isbn, LocalDate fechaIntro) {
		this.cod = new SimpleIntegerProperty(this, "cod", cod);
		this.nombre = new SimpleStringProperty(this, "nombre", nombre);
		this.isbn = new SimpleStringProperty(this, "isbn", isbn);
		this.fechaIntro = new SimpleObjectProperty<>(this, "fechaIntro", fechaIntro);
	}

	public Libro() {
		cod = new SimpleIntegerProperty(this, "cod");
		nombre = new SimpleStringProperty(this, "nombre");
		isbn = new SimpleStringProperty(this, "isbn");
		fechaIntro = new SimpleObjectProperty<>(this, "fechaIntro");
	}

	public final IntegerProperty codProperty() {
		return this.cod;
	}

	public final int getCod() {
		return this.codProperty().get();
	}

	public final void setCod(final int cod) {
		this.codProperty().set(cod);
	}

	public final SimpleStringProperty nombreProperty() {
		return this.nombre;
	}

	public final String getNombre() {
		return this.nombreProperty().get();
	}

	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}

	public final SimpleStringProperty isbnProperty() {
		return this.isbn;
	}

	public final String getIsbn() {
		return this.isbnProperty().get();
	}

	public final void setIsbn(final String isbn) {
		this.isbnProperty().set(isbn);
	}

	public final ObjectProperty<LocalDate> fechaIntroProperty() {
		return this.fechaIntro;
	}

	public final LocalDate getFechaIntro() {
		return this.fechaIntroProperty().get();
	}

	public final void setFechaIntro(final LocalDate fechaIntro) {
		this.fechaIntroProperty().set(fechaIntro);
	}

}
