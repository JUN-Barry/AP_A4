package controller;

import java.io.IOException;

import ConstructorClass.UserInfo;
import Model.ValidUserLoginModel;
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
import view.SignUpScene;

public class LoginController {
	
	@FXML
	private Stage primaryStage; // Add this field to hold the primary stage
	
	@FXML
	private String fullName;
	
	
	private  UserInfo user;
	
   
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public String getFullName() {
        return fullName;
    }
	
	public  UserInfo getUser() {
        return user;
    }
	// why here would print null value?

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
		//UserInfo user = ValidUserLoginModel.getUserInfo(username);	
		
		// WelcomeController.setFullName(fullName);

		if (fullName != null) {
			statusLabel.setText("Login successful, welcome " + fullName + ".");
			
			// SWITCH to a new scene
			
			LoginScene LogInscene = new LoginScene(); // keep same
			FunctionMenuController functionmenucontroller  = LogInscene.getScene("/view/FunctionMenu.fxml", this.primaryStage); 
			functionmenucontroller.setPrimaryStage(this.primaryStage);
			functionmenucontroller.setFullName(fullName);
			
			//this.user = ValidUserLoginModel.getUserInfo(username);	  //get the information of the user, for future edit
			functionmenucontroller.setUser(ValidUserLoginModel.getUserInfo(username));
			System.out.println(this.user.getFirstName());
			//			String usernameLogin = user.getUsername();
//			String firstnameLogin = user.getFirstName();
//			String lastnameLogin = user.getLastName();
//			String passwordLogin = user.getPassword();
			
			

		} else {
			statusLabel.setText("Login failed. Please try again.");
		}
	}

	@FXML
	public void signupButtonClicked(ActionEvent event) {
		LoginScene LogInscene = new LoginScene(); // keep same
		
		SignUpController signUpController = LogInscene.getScene("/view/SignUp.fxml", this.primaryStage); 
			
		//LoginController loginController = LogInscene.getScene("/view/Login.fxml", this.primaryStage); 
		signUpController.setPrimaryStage(this.primaryStage);
	}
}
