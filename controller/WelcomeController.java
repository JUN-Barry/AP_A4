package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WelcomeController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private TextField fullNameField;
    
    @FXML
    private void initialize() {
        // Initialize the welcome label with a default message
        welcomeLabel.setText("Welcome, ");
    }


    @FXML
    private void dismissButtonClicked() {
        // Get the stage and close it to dismiss the welcome scene
        Stage stage = (Stage) fullNameField.getScene().getWindow();
        stage.close();
    }

    public void setFullName(String fullName) {
        // Set the welcome label with the provided message
        fullNameField.setText(fullName);
    }
}

