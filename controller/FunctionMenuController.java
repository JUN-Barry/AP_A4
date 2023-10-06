package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.LoginScene;

public class FunctionMenuController {

    @FXML
    private TextField fullNamefill;

    @FXML
    private Button editProfilebutton;

    @FXML
    private Button addPostButton;

    @FXML
    private Button retrievePostByIDButton;

    @FXML
    private Button retrieveTopNPostsButton;

    @FXML
    private Button removePostButton;
    
    @FXML
    private Button exportPostByIDButton;

    @FXML
    private Button upgradeToVIPButton;

    @FXML
    private Button dataVisualizationVIPButton;

    @FXML
    private Button bulkImportPostsVIPButton;

    @FXML
    private Button logOutbutton;

    private Stage primaryStage;

	//private String fullName;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void setFullName(String fullName) {
        //this.fullName = fullName;
        fullNamefill.setText(fullName); // Set the fullName to the TextField
    }


	@FXML
    private void editProfile() {
        // Handle Edit Profile button click here
    }

    @FXML
    private void addPost() {
        // Handle Add Post button click here
    }

    @FXML
    private void retrievePostByID() {
        // Handle Retrieve Post by ID button click here
    }

    @FXML
    private void retrieveTopNPosts() {
        // Handle Retrieve TOP N Posts button click here
    }

    @FXML
    private void removePost() {
        // Handle Remove Post button click here
    }
    
    @FXML
    private void exportPostByID(){
    	
    }

    @FXML
    private void upgradeToVIP() {
        // Handle Upgrade to VIP button click here
    }

    @FXML
    private void dataVisualizationVIP() {
        // Handle Data Visualization (VIP) button click here
    }

    @FXML
    private void bulkImportPostsVIP() {
        // Handle Bulk Import Social Media Posts (VIP) button click here
    }


    @FXML
	public void logOut(ActionEvent event) {
    	LoginScene LogInscene = new LoginScene(); // keep same
    	LoginController loginController = LogInscene.getScene("/view/Login.fxml", primaryStage); 
    	loginController.setPrimaryStage(primaryStage);
		
	}
    
}
