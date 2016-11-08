package aed.sql.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import aed.sql.model.Libro;
import aed.sql.model.ListaLibros;
import aed.sql.view.ListaLibrosView;

public class ListaLibrosController {

	ListaLibrosView view;
	ConexionController conexionController;
	ListaLibros listaLibros;

	public ListaLibrosController() {
		view = new ListaLibrosView();

		conexionController = new ConexionController();
		listaLibros = new ListaLibros();

		view.getLibrosTable().setItems(listaLibros.librosProperty());

		cargarLibros();

	}

	private void cargarLibros() {

		try {
			PreparedStatement sql = conexionController.getConexion().getCon().prepareStatement("SELECT * FROM libros");

			ResultSet resultado = sql.executeQuery();

			while (resultado.next()) {
				listaLibros.getLibros().add(new Libro(resultado.getInt(1), resultado.getString(2),
						resultado.getString(3), resultado.getDate(4).toLocalDate()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ListaLibrosView getView() {
		return view;
	}
}
