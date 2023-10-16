package controller;

import ConstructorClass.PostInfo;
import Model.PostInfoModel;
import Model.RetrievePostModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.LoginScene;

public class TopNPostController {

	@FXML
	private Button BackTOmenu;

	@FXML
	private VBox Vbox;

	@FXML
	private HBox Hbox;

	@FXML
	private Label statusLabel;

	@FXML
	private TextField AuthorIDhandler;

	@FXML
	private Button SearchbUTTON;

	@FXML
	private TextField TopNumber;

	@FXML
	private AnchorPane inputPane;

	@FXML
	private TableView<PostInfo> tableView;

	@FXML
	private TableColumn<PostInfo, Integer> idColumn;

	@FXML
	private TableColumn<PostInfo, String> contentColumn;

	@FXML
	private TableColumn<PostInfo, String> authorColumn;

	@FXML
	private TableColumn<PostInfo, Integer> likeColumn;

	@FXML
	private TableColumn<PostInfo, Integer> sharescolumn;

	@FXML
	private TableColumn<PostInfo, String> datetimecolumn;

	@FXML
	private AnchorPane hintPane;

	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	// private Integer NumertopN;

	@FXML
	private void BacktoMenu(ActionEvent event) {
		// Handle the "Back to Menu" button click event here
		// You can navigate back to the menu or any other desired action here
		LoginScene LogInscene = new LoginScene(); // keep same
		FunctionMenuController functionmenucontroller = LogInscene.getScene("/view/FunctionMenu.fxml",
				this.primaryStage);
		functionmenucontroller.setPrimaryStage(this.primaryStage);

		// Your other controller methods
	}

	@FXML
	private void SearchPost(ActionEvent event) {
		idColumn.setCellValueFactory(new PropertyValueFactory<PostInfo, Integer>("id"));
		contentColumn.setCellValueFactory(new PropertyValueFactory<PostInfo, String>("content"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<PostInfo, String>("author"));
		likeColumn.setCellValueFactory(new PropertyValueFactory<PostInfo, Integer>("likes"));
		sharescolumn.setCellValueFactory(new PropertyValueFactory<PostInfo, Integer>("shares"));
		datetimecolumn.setCellValueFactory(new PropertyValueFactory<PostInfo, String>("datetime"));

		// Get values from text fields
		String authorID = AuthorIDhandler.getText();
		String topN = TopNumber.getText();

		// all empty
		if (authorID.isEmpty() && topN.isEmpty()) {
			statusLabel.setText("No input provided.");
			return;
		}
		
		// topN empty
		if (topN.isEmpty()) {
			statusLabel.setText("Please enter TopN number.");
			return;
		}


		try {
			int NumtopN = Integer.parseInt(topN);
			if (NumtopN < 1) {
				statusLabel.setText("TopN must be a positive number.");
				return;
			}

			if (authorID.isEmpty() && !topN.isEmpty()) {
				tableView.setItems(RetrievePostModel.RetrieveAllUserTopPosts(NumtopN));
			}

			else if (PostInfoModel.CheckAuthorExist(authorID) &&!authorID.isEmpty() && !topN.isEmpty()) {
				tableView.setItems(RetrievePostModel.RetrieveOneUSERTopPosts(authorID, NumtopN));
			}
			
			else {
				statusLabel.setText("Retrieve failed, check the input content again.");
			}
			
		} catch (NumberFormatException e) {
			statusLabel.setText("TopN must be a valid positive integer.");
		}
		
	}
}
