package view;

import java.io.IOException;

import controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginScene {
	
	private Stage primaryStage;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}


	public String getTitle() {
		return "Data Analytics Hub";
	}
	
	
//	public Scene getScene() {
//		
//		// load FXML
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
//		
//		// load the FXML
//		Parent parentNode = null;
//		try {
//			parentNode = loader.load();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		LoginController LoginController = new LoginController();
//		LoginController.setPrimaryStage(primaryStage);
//		
//		// create a scene
//		Scene scene = new Scene(parentNode);
//		
//		return scene;
//		
//		
//		
//	}
	
	public <T> T getScene(String fxmlfile, Stage primaryStage) {
		// use to every fxml loading
		
		// load FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlfile));
		
		// load the FXML
		Parent parentNode = null;
		try {
			parentNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Scene scene = new Scene(parentNode);
		primaryStage.setScene(scene);  // put the new scene to the old primary stage; mistake here
		T controller = loader.getController();
		primaryStage.show();
		//controller.setPrimaryStage(primaryStage);
		return controller;
		
	}

}
