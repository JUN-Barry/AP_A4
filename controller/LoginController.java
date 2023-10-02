package controller;

import java.io.IOException;
import Model.ValidUserLoginModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

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

    private Stage primaryStage; // Add this field to hold the primary stage

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void loginButtonClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Replace this with your authentication logic
        String fullName = ValidUserLoginModel.authenticateUser(username, password);
        
        if  (fullName != null)  {
             statusLabel.setText("Login successful");

             try {
                 // Load the welcome scene
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/welcomePage2.fxml"));
                
                 Parent root = loader.load();

                 // Access the controller of the welcome scene to set the user's full name
                 WelcomeController welcomeController = loader.getController();
                 welcomeController.setFullName(fullName);

                 // Create a new scene
                 Scene welcomeScene = new Scene(root);

                 // Set the scene on the primary stage
                 primaryStage.setScene(welcomeScene);

             } catch (IOException e) {
                 e.printStackTrace();
             }
        } else {
             statusLabel.setText("Login failed. Please try again.");
        }
    }

    @FXML
    private void signupButtonClicked() {
        // Handle the signup button action here
        statusLabel.setText("Sign Up button clicked.");
    }
}
