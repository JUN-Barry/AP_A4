package controller;

import ConstructorClass.UserInfo;
import Model.SharedUsernameModel;
import Model.ValidUserLoginModel;
//import Model.UserinfoModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.LoginScene;

public class FunctionMenuController {

	@FXML
	private VBox wholeMenu;

	@FXML
	private HBox welcomeINFO;

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
	private AnchorPane VipPane;

	@FXML
	private ToolBar NormalUserTool;

	@FXML
	private ToolBar LogoutToolBar;

	@FXML
	private Button upgradeToVIPButton;

	@FXML
	private Button dataVisualizationVIPButton;

	@FXML
	private Button bulkImportPostsVIPButton;

	@FXML
	private Label statuslabel;

	@FXML
	private Button logOutbutton;

	private Stage primaryStage;

	private UserInfo user;

	// private String fullName;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void setFullName(String fullName) {
		// this.fullName = fullName;
		fullNamefill.setText(fullName); // Set the fullName to the TextField
	}

	public void setUser(UserInfo user) {
		this.user = user; // transfer value from other class
	}

	@FXML
	private void editProfile(ActionEvent event) {
		// Handle Edit Profile button click here
		LoginScene LogInscene = new LoginScene(); // keep same
		UpdateProfileController updateProfileController = LogInscene.getScene("/view/UpdateProfile.fxml", primaryStage);
		updateProfileController.setPrimaryStage(primaryStage);
		// System.out.println(user.getUsername());
		updateProfileController.setprofile(SharedUsernameModel.getUsername(), 
				                                                   SharedUsernameModel.getpassword(),
				                                                   SharedUsernameModel.getFirstName(), 
				                                                   SharedUsernameModel.getlastName());
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
	private void retrieveTopNPosts(ActionEvent event) {
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
	private void upgradeToVIP(ActionEvent event) {
		LoginScene LogInscene = new LoginScene(); // keep same
		UpgradeVipController upgradeVipController = LogInscene.getScene("/view/UpgradeVip.fxml", primaryStage);
		upgradeVipController.setPrimaryStage(primaryStage);
		// System.out.println(user.getUsername());
		upgradeVipController.setUsername(SharedUsernameModel.getUsername());
	}

	@FXML
	private void dataVisualizationVIP() {
		String username = SharedUsernameModel.getUsername();

		if (ValidUserLoginModel.CheckRole(username)) {
			LoginScene LogInscene = new LoginScene(); // keep same
			PieChartController pieChartController = LogInscene.getScene("/view/PieChart.fxml", primaryStage);
			pieChartController.setPrimaryStage(primaryStage);
		} else {
			// Display a message to users who are not VIP
			statuslabel.setText("You don't have the right to use this function. Please upgrade to VIP.");
		}
	}

	@FXML
	private void bulkImportPostsVIP() {
		String username = SharedUsernameModel.getUsername();

		if (ValidUserLoginModel.CheckRole(username)) {
			LoginScene LogInscene = new LoginScene(); // keep same
			ImportPostController importPostController = LogInscene.getScene("/view/ImportPost.fxml", primaryStage);
			importPostController.setPrimaryStage(primaryStage);
		} else {
			// Display a message to users who are not VIP
			statuslabel.setText("You don't have the right to use this function. Please upgrade to VIP.");
		}
	}

	@FXML
	public void logOut(ActionEvent event) {
		LoginScene LogInscene = new LoginScene(); // keep same
		LoginController loginController = LogInscene.getScene("/view/Login.fxml", primaryStage);
		loginController.setPrimaryStage(primaryStage);

	}

}
