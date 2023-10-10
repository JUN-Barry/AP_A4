package controller;

import java.io.IOException;
import Model.UserinfoModel;
import RunHub.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.LoginScene;

public class SignUpController {

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private TextField firstNameField;

	@FXML
	private TextField lastNameField;

	@FXML
	private Button signupSubmit;

	@FXML
	private Label statusLabel;

	// pass the primary stage to the controller
	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@FXML
	private void signupSubmitClick() {
		// Handle the signup form submission here
		String username = usernameField.getText();
		String password = passwordField.getText();
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();

		if (UserinfoModel.CheckUsername(username)) {
			statusLabel.setText("Signup successful, Back to Log IN page again.");

			// insert the value into database
			UserinfoModel.SignUpUserInfoINTOTable(username, password, firstName, lastName);
			


		} else {
			statusLabel.setText("The username has been registered. Try a new one.");
		}

	}
	
	@FXML
	public void BackLogInClick(ActionEvent event) {
    	LoginScene LogInscene = new LoginScene(); // keep same
    	LoginController loginController = LogInscene.getScene("/view/Login.fxml", primaryStage); 
    	loginController.setPrimaryStage(primaryStage);
		
	}
	
	
	


}
