package aed.sql.view;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane {

	private Tab librosTab, insertTab;
	private ToolBar toolBar;

	private Button conectarButton;
	private TextField hostText, puertoText, dbText, userText;
	private PasswordField passwordField;
	private ComboBox<String> rutaBox;

	private ArrayList<String> optionList;

	public MainView() {

		toolBar = new ToolBar();

		optionList = new ArrayList<>();
		optionList.add("jdbc:mysql:");
		optionList.add("Conexión JDBC");

		conectarButton = new Button("Conectar");

		rutaBox = new ComboBox<>();
		rutaBox.setPromptText("-------------");
		rutaBox.setItems(FXCollections.observableArrayList(optionList));

		hostText = new TextField("localhost");
		hostText.setPromptText("Host...");

		puertoText = new TextField("3306");
		puertoText.setPromptText("Puerto...");

		userText = new TextField("root");
		userText.setPromptText("Usuario...");

		dbText = new TextField("bdbiblioteca");
		dbText.setPromptText("Base de datos...");

		passwordField = new PasswordField();
		passwordField.setPromptText("Contraseña...");

		toolBar.getItems().addAll(rutaBox, hostText, puertoText, dbText, userText, passwordField, conectarButton);

		librosTab = new Tab("Libros");
		librosTab.setClosable(false);
		insertTab = new Tab("Insertar");
		insertTab.setClosable(false);

		TabPane tabPane = new TabPane();

		tabPane.getTabs().addAll(librosTab, insertTab);

		setTop(toolBar);
		setCenter(tabPane);
	}

	public Tab getLibrosTab() {
		return librosTab;
	}

	public Tab getInsertTab() {
		return insertTab;
	}

}
