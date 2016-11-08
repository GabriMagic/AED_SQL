package aed.sql.controller;

import aed.sql.view.ListaLibrosView;

public class ListaLibrosController {

	private ListaLibrosView view;

	public ListaLibrosController() {

		view = new ListaLibrosView();
	}

	public ListaLibrosView getView() {
		return view;
	}
}
