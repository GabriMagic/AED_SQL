package aed.sql.view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;

public class MainView extends BorderPane {

	private ToolBar toolBar;

	private Button actualizarButton;
	private TextField hostText, puertoText, dbText, userText;
	private PasswordField passwordField;
	private ComboBox<String> rutaBox;
	private ToggleButton conDisconButton;
	private ImageView cir;

	private ArrayList<String> optionList;

	public MainView() {

		toolBar = new ToolBar();

		cir = new ImageView("resources/red.png");
		cir.setFitWidth(30);
		cir.setFitHeight(30);

		optionList = new ArrayList<>();
		optionList.add("jdbc:mysql:");
		optionList.add("jdbc:ucanaccess:");
		optionList.add("jdbc:sqlserver:");

		conDisconButton = new ToggleButton("", cir);
		conDisconButton.setFocusTraversable(false);
		conDisconButton.setShape(new Circle(1.5));
		conDisconButton.setMaxSize(0, 0);

		actualizarButton = new Button("Actualizar");
		actualizarButton.setDefaultButton(true);
		actualizarButton.setDisable(true);

		rutaBox = new ComboBox<>();
		rutaBox.setItems(FXCollections.observableArrayList(optionList));
		rutaBox.getSelectionModel().select(0);

		hostText = new TextField("ALUMNO-2DAM\\SQLEXPRESS");
		hostText.setPromptText("Host...");

		puertoText = new TextField("3306");
		puertoText.setPromptText("Puerto...");

		userText = new TextField("root");
		userText.setPromptText("Usuario...");

		dbText = new TextField("bdbiblioteca");
		dbText.setPromptText("Base de datos...");

		passwordField = new PasswordField();
		passwordField.setPromptText("Contraseña...");

		toolBar.getItems().addAll(rutaBox, hostText, puertoText, dbText, userText, passwordField, actualizarButton,
				conDisconButton);

		setTop(toolBar);
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public TextField getHostText() {
		return hostText;
	}

	public Button getActualizarButton() {
		return actualizarButton;
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

	public ToggleButton getConDisconButton() {
		return conDisconButton;
	}

}
