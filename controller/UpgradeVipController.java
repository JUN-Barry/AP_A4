package controller;

import Model.UserinfoModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import view.LoginScene;
import javafx.stage.Stage;

public class UpgradeVipController {

	@FXML
	private AnchorPane upgradepage;
	
    @FXML
    private Label statusLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private Button Confirmsubmit;

    @FXML
    private Button BackMenuButten;
    
    
    private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;

	}
	
	public void setUsername(String username) {
		usernameField.setText(username);
	}


    @FXML
    private void confirmClick(ActionEvent event) {
           String VIPuser = usernameField.getText();
           UserinfoModel.upgradeVIP(VIPuser);
           statusLabel.setText("Subscription confirmed for user: " + VIPuser + ".  Please log out and log in again to access VIP functionalities");
    }

    @FXML
    private void BackMenuClick(ActionEvent event) {
    	LoginScene LogInscene = new LoginScene(); // keep same
		FunctionMenuController functionmenucontroller = LogInscene.getScene("/view/FunctionMenu.fxml",
				this.primaryStage);
		functionmenucontroller.setPrimaryStage(this.primaryStage);
    }
}

