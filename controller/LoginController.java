package controller;

import java.io.IOException;

import ConstructorClass.UserInfo;
import Model.ValidUserLoginModel;
import Model.SharedUsernameModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.LoginScene;
//import view.SignUpScene;

public class LoginController {

	@FXML
	private Stage primaryStage; // Add this field to hold the primary stage

	@FXML
	private String fullName;

	@FXML
	private String username;


	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button loginButton;

	@FXML
	private Button signupButton;

	@FXML
	private Label statusLabel;

	@FXML
	public void loginButtonClicked() {
		String username = usernameField.getText();
		String password = passwordField.getText();

		// Replace this with your authentication logic
		String fullName = ValidUserLoginModel.authenticateUser(username, password);
//       SharedUsernameModel.setUsername(username);
       SharedUsernameModel.setFullname(fullName);

		// UserInfo user = ValidUserLoginModel.getUserInfo(username);

		// WelcomeController.setFullName(fullName);

		if (fullName != null) {
			statusLabel.setText("Login successful, welcome " + fullName + ".");
			
			UserInfo LoginUser = ValidUserLoginModel.getUserInfo(username);
			SharedUsernameModel.setUsername(LoginUser.getUsername());
			SharedUsernameModel.setpassword(LoginUser.getPassword());
			SharedUsernameModel.setFirstName(LoginUser.getFirstName());
			SharedUsernameModel.setlastName(LoginUser.getLastName());

			// SWITCH to a new scene

			LoginScene LogInscene = new LoginScene(); // keep same
			FunctionMenuController functionmenucontroller = LogInscene.getScene("/view/FunctionMenu.fxml",
					this.primaryStage);
			functionmenucontroller.setPrimaryStage(this.primaryStage);
			functionmenucontroller.setFullName(SharedUsernameModel.getFullname());


			// issues here
//			UserInfo LoginUser = ValidUserLoginModel.getUserInfo(username);
//			SharedUsernameModel.setUsername(LoginUser.getUsername());
//			SharedUsernameModel.setpassword(LoginUser.getPassword());
//			SharedUsernameModel.setFirstName(LoginUser.getFirstName());
//			SharedUsernameModel.setlastName(LoginUser.getLastName());

		} else {
			statusLabel.setText("Login failed. Please try again.");
		}
	}

	@FXML
	public void signupButtonClicked(ActionEvent event) {
		LoginScene LogInscene = new LoginScene(); // keep same

		SignUpController signUpController = LogInscene.getScene("/view/SignUp.fxml", this.primaryStage);

		// LoginController loginController = LogInscene.getScene("/view/Login.fxml",
		// this.primaryStage);
		signUpController.setPrimaryStage(this.primaryStage);
	}
}
