package aed.sql.app;

import aed.sql.controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AedSqlAPP extends Application {

	MainController mainController;

	@Override
	public void start(Stage primaryStage) throws Exception {

		mainController = new MainController();

		primaryStage.setScene(new Scene(mainController.getView(), primaryStage.getWidth(), 480));
		primaryStage.setTitle("AED SQL");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
