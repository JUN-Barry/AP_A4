package controller;

//import ConstructorClass.UserInfo;
import Model.UserinfoModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.LoginScene;

public class UpdateProfileController {

	@FXML
	private TextField usernameField;

	@FXML
	private TextField passwordField;

	@FXML
	private TextField firstNameField;

	@FXML
	private TextField lastNameField;

	@FXML
	private Button Confirmsubmit;

	@FXML
	private Button BackMenuButten;

	@FXML
	private Label statusLabel;

	// pass the primary stage to the controller
	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;

	}

	public void setprofile(String username, String password, String firstName, String lastName) {
		usernameField.setText(username);
		passwordField.setText(password);
		firstNameField.setText(firstName);
		lastNameField.setText(lastName);
	}

	@FXML
	private void confirmClick(ActionEvent event) {
		// setText here, retrieve the data fromDB, based on USERNAME, IF SAME in the DB,
		// output error message

		// setText first
		String Newusername = usernameField.getText();
		String Newpassword = passwordField.getText();
		String NewfirstName = firstNameField.getText();
		String NewlastName = lastNameField.getText();

		if (UserinfoModel.validUserToEditProfile(Newusername)) {

			UserinfoModel.SignUpUserInfoINTOTable(Newusername, Newpassword, NewfirstName, NewlastName);
			statusLabel.setText("User information has been updated.");
		}

		else {
			statusLabel.setText("You don't have right to edit other's profile.");
		}

	}

	@FXML
	private void BackMenuClick(ActionEvent event) {
		// Handle the "Back to Menu" button click event here
		// You can navigate back to the menu or any other desired action here
		LoginScene LogInscene = new LoginScene(); // keep same
		FunctionMenuController functionmenucontroller = LogInscene.getScene("/view/FunctionMenu.fxml",
				this.primaryStage);
		functionmenucontroller.setPrimaryStage(this.primaryStage);

	}

}
