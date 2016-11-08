package aed.sql.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aed.sql.model.Conexion;
import aed.sql.model.Libro;
import aed.sql.model.ListaLibros;
import aed.sql.view.ListaLibrosView;

public class ListaLibrosController {

	private ListaLibrosView view;
	private Conexion conexion;
	private ListaLibros listaLibros;


	public ListaLibrosController(Conexion conexion) {
		
		view = new ListaLibrosView();

		this.conexion = conexion;
		conexion.conectar();
		
		listaLibros = new ListaLibros();
		
		cargarLibros();
		
		view.getLibrosTable().setItems(listaLibros.librosProperty());

	}

	public void cargarLibros() {

		try {
			PreparedStatement sql = conexion.getConexion().prepareStatement("SELECT * FROM libros");

			ResultSet resultado = sql.executeQuery();

			while (resultado.next()) {
				listaLibros.getLibros().add(new Libro(resultado.getInt(1), resultado.getString(2),
						resultado.getString(3), resultado.getDate(4).toLocalDate()));
			}
		} catch (SQLException e) {
			System.err.println("ERROR AQUIIIIIIIIIIIIII");
		}

	}

	public ListaLibrosView getView() {
		return view;
	}
}
