package aed.sql.app;

import aed.sql.controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AedSqlAPP extends Application {

	private MainController mainController;
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;

		mainController = new MainController(primaryStage);

		primaryStage.setScene(new Scene(mainController.getView(), primaryStage.getWidth(), 480));
		primaryStage.getIcons().add(new Image("/resources/db.png"));
		primaryStage.setTitle("AED SQL");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
}
