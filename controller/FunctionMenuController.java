package controller;

import ConstructorClass.UserInfo;
import Model.SignUPModel;
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

	private UserInfo user;

	//private String fullName;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void setFullName(String fullName) {
        //this.fullName = fullName;
        fullNamefill.setText(fullName); // Set the fullName to the TextField
    }
    
    public void setUser(UserInfo user) {
        this.user = user; //why do we need this? transfer the value from other class????
    }


	@FXML
    private void editProfile(ActionEvent event) {
        // Handle Edit Profile button click here
		LoginScene LogInscene = new LoginScene(); // keep same
		UpdateProfileController updateProfileController = LogInscene.getScene("/view/UpdateProfile.fxml", primaryStage); 
		updateProfileController.setPrimaryStage(primaryStage);
		//System.out.println(user.getUsername());
		updateProfileController.setprofile(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName());
		// delete the content before
		SignUPModel.deleteUserInfoFromTable(user.getUsername()); // delete it, and update again for next step, no need to check again
		// we should note that, if  they dn't log out at single time, everytime into update, info is same
		
    }

    @FXML
    private void addPost(ActionEvent event) {
		LoginScene LogInscene = new LoginScene(); // keep same
		AddPostController addPostController = LogInscene.getScene("/view/addPost.fxml", primaryStage); 
		addPostController.setPrimaryStage(primaryStage);
    	
        // Handle Add Post button click here
    }

    @FXML
    private void retrievePostByID(ActionEvent event) {
    	LoginScene LogInscene = new LoginScene(); // keep same
    	RetrievePostController retrievePostController = LogInscene.getScene("/view/RetrievePost.fxml", primaryStage); 
    	retrievePostController.setPrimaryStage(primaryStage);
    }

    @FXML
    private void retrieveTopNPosts() {
    	LoginScene LogInscene = new LoginScene(); // keep same
    	TopNPostController topNPostController = LogInscene.getScene("/view/TopNPost.fxml", primaryStage); 
    	topNPostController.setPrimaryStage(primaryStage);
    }

    @FXML
    private void removePost(ActionEvent event) {
    	LoginScene LogInscene = new LoginScene(); // keep same
    	RemovePostController removePostController = LogInscene.getScene("/view/RemovePost.fxml", primaryStage); 
    	removePostController.setPrimaryStage(primaryStage);
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
