package aed.sql.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aed.sql.model.Conexion;
import aed.sql.model.Libro;
import aed.sql.model.ListaLibros;
import aed.sql.view.ListaLibrosView;
import javafx.event.ActionEvent;

public class ListaLibrosController {

	private ListaLibrosView view;
	private ListaLibros listaLibros;
	private Conexion conexion;

	public ListaLibrosController(Conexion conexion) {

		view = new ListaLibrosView();

		this.conexion = conexion;
		conexion.conectar();

		listaLibros = new ListaLibros();

		view.getAddLibroButton().setOnAction(e -> onAddButtonAction(e));

	}

	private void onAddButtonAction(ActionEvent e) {

		// try {
		String sql = "INSERT INTO libros (nombreLibro, ISBN) VALUES (?,?)";
		// PreparedStatement query =
		// conexion.getConexion().prepareStatement(sql);
		cargarLibros();
		// } catch (SQLException e1) {
		// System.out.println("HOZA ZULU");
		// }

	}

	public void cargarLibros() {

		listaLibros.librosProperty().clear();
		view.getLibrosTable().setItems(listaLibros.librosProperty());

		if (conexion.conectar()) {
			try {

				String query = "SELECT lb.codLibro, nombreLibro, ISBN, fechaIntro, codEjemplar, importe, nombreAutor FROM libros as lb "
						+ "left join ejemplares as ej on ej.codLibro = lb.codLibro "
						+ "left join librosautores as lau on lau.codLibro = lb.codLibro "
						+ "left join autores as au on au.codAutor = lau.codAutor";

				PreparedStatement sql = conexion.getConexion().prepareStatement(query);

				ResultSet resultado = sql.executeQuery();

				while (resultado.next()) {

					listaLibros.getLibros()
							.add(new Libro(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
									resultado.getDate(4).toLocalDate(), resultado.getInt(5), resultado.getDouble(6),
									resultado.getString(7)));
				}
			} catch (SQLException e) {
				System.err.println("ERROR AQUIIIIIIIIIIIIII");
			}
		}
	}

	public ListaLibrosView getView() {
		return view;
	}

	public ListaLibros getListaLibros() {
		return listaLibros;
	}
}
