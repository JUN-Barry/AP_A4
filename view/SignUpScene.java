package view;

import java.io.IOException;

import controller.SignUpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignUpScene {

	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public String getTitle() {
		return "Data Analytics Hub";
	}

	public Scene getScene() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignUp.fxml"));

		// load the FXML
		Parent parentNode = null;
		try {
			parentNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		SignUpController SignUpController = new SignUpController();
		SignUpController.setPrimaryStage(primaryStage);

		// create a scene
		Scene scene = new Scene(parentNode);

		return scene;

	}

}
