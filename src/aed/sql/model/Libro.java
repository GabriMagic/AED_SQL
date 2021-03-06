package aed.sql.model;

import java.sql.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Libro {

	private IntegerProperty codLibro, codEjemplar;
	private StringProperty nombre, isbn, autor, codAutor;
	private ObjectProperty<Date> fechaIntro;
	private DoubleProperty importe;

	public Libro(Integer cod, String nombre, String isbn, Date fechaIntro, Integer codEjemplar, Double importe,
			String autor, String codAutor) {
		this.codLibro = new SimpleIntegerProperty(this, "cod", cod);
		this.nombre = new SimpleStringProperty(this, "nombre", nombre);
		this.isbn = new SimpleStringProperty(this, "isbn", isbn);
		this.fechaIntro = new SimpleObjectProperty<>(this, "fechaIntro", fechaIntro);

		this.codEjemplar = new SimpleIntegerProperty(this, "codEjemplar", codEjemplar);
		this.importe = new SimpleDoubleProperty(this, "importe", importe);
		this.autor = new SimpleStringProperty(this, "autor", autor);
		this.codAutor = new SimpleStringProperty(this, "codAutor", codAutor);
	}

	public Libro() {
		codLibro = new SimpleIntegerProperty(this, "cod");
		nombre = new SimpleStringProperty(this, "nombre");
		isbn = new SimpleStringProperty(this, "isbn");
		codAutor = new SimpleStringProperty(this, "codAutor");
		fechaIntro = new SimpleObjectProperty<>(this, "fechaIntro");
		autor = new SimpleStringProperty(this, "autor");
	}

	public IntegerProperty codLibroProperty() {
		return this.codLibro;
	}

	public int getCodLibro() {
		return this.codLibroProperty().get();
	}

	public void setCodLibro(final int codLibro) {
		this.codLibroProperty().set(codLibro);
	}

	public IntegerProperty codEjemplarProperty() {
		return this.codEjemplar;
	}

	public int getCodEjemplar() {
		return this.codEjemplarProperty().get();
	}

	public void setCodEjemplar(final int codEjemplar) {
		this.codEjemplarProperty().set(codEjemplar);
	}

	public StringProperty nombreProperty() {
		return this.nombre;
	}

	public String getNombre() {
		return this.nombreProperty().get();
	}

	public void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}

	public StringProperty isbnProperty() {
		return this.isbn;
	}

	public String getIsbn() {
		return this.isbnProperty().get();
	}

	public void setIsbn(final String isbn) {
		this.isbnProperty().set(isbn);
	}

	public StringProperty autorProperty() {
		return this.autor;
	}

	public String getAutor() {
		return this.autorProperty().get();
	}

	public void setAutor(final String autor) {
		this.autorProperty().set(autor);
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

	public ObjectProperty<Date> fechaIntroProperty() {
		return this.fechaIntro;
	}

	public Date getFechaIntro() {
		return this.fechaIntroProperty().get();
	}

	public void setFechaIntro(final Date fechaIntro) {
		this.fechaIntroProperty().set(fechaIntro);
	}

	public DoubleProperty importeProperty() {
		return this.importe;
	}

	public double getImporte() {
		return this.importeProperty().get();
	}

	public void setImporte(final double importe) {
		this.importeProperty().set(importe);
	}

}
