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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane {

	private Tab librosTab, insertTab;
	private ToolBar toolBar;

	private Button conectarButton;
	private TextField hostText, puertoText, dbText, userText;
	private PasswordField passwordField;
	private ComboBox<String> rutaBox;

	private ImageView cir;

	private ArrayList<String> optionList;

	public MainView() {

		toolBar = new ToolBar();

		cir = new ImageView("resources/red.png");
		cir.setFitWidth(30);
		cir.setFitHeight(30);

		optionList = new ArrayList<>();
		optionList.add("jdbc:mysql:");
		optionList.add("Conexión JDBC");

		conectarButton = new Button("Conectar");
		conectarButton.setDefaultButton(true);

		rutaBox = new ComboBox<>();
		rutaBox.setItems(FXCollections.observableArrayList(optionList));
		rutaBox.getSelectionModel().select(0);

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

		toolBar.getItems().addAll(rutaBox, hostText, puertoText, dbText, userText, passwordField, conectarButton, cir);

		librosTab = new Tab("Libros");
		librosTab.setClosable(false);
		insertTab = new Tab("Insertar");
		insertTab.setClosable(false);

		TabPane tabPane = new TabPane();

		tabPane.getTabs().addAll(librosTab, insertTab);

		setTop(toolBar);
		setCenter(tabPane);
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public Button getConectarButton() {
		return conectarButton;
	}

	public TextField getHostText() {
		return hostText;
	}

	public TextField getPuertoText() {
		return puertoText;
	}

	public TextField getDbText() {
		return dbText;
	}

	public TextField getUserText() {
		return userText;
	}

	public PasswordField getPasswordField() {
		return passwordField;
	}

	public ImageView getCir() {
		return cir;
	}

	public ComboBox<String> getRutaBox() {
		return rutaBox;
	}

	public ArrayList<String> getOptionList() {
		return optionList;
	}

	public Tab getLibrosTab() {
		return librosTab;
	}

	public Tab getInsertTab() {
		return insertTab;
	}

}
