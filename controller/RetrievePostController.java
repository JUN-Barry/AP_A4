package controller;

import ConstructorClass.PostInfo;
import Model.PostInfoModel;
import Model.RetrievePostModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.LoginScene;

public class RetrievePostController {

	@FXML
	private Button BackTOmenu;

	@FXML
	private VBox Vbox;

	@FXML
	private HBox Hbox;

	@FXML
	private Label statusLabel;

	@FXML
	private TextField PostIDhandler;

	@FXML
	private TextField filePath;

	@FXML
	private TextField fileName;

	@FXML
	private Button SearchbUTTON;

	@FXML
	private Button ExportPost;

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

		try {
			int id = Integer.parseInt(PostIDhandler.getText());

			if (!PostInfoModel.CheckIDexist(id)) {
				ObservableList<PostInfo> ToaddPost = tableView.getItems();
				ToaddPost.add(RetrievePostModel.RetrieveSinglePost(id));
				tableView.setItems(ToaddPost);
			} else {
				statusLabel.setText("Post ID not found.");
			}
		} catch (NumberFormatException e) {
			statusLabel.setText("Please enter a valid Post ID.");
		}

	}

	@FXML
	private void exportPostintoCSV(ActionEvent event) {
		try {
			String csvFilePath = filePath.getText();
			int postID = Integer.parseInt(PostIDhandler.getText());
			String filename = fileName.getText();

			boolean result = RetrievePostModel.ExportSinglePost(csvFilePath, postID, filename);

			if (result) {
				statusLabel.setText("Export successful!");
			} else {
				statusLabel.setText("Export failed. Check your inputs and try again.");

			}
		} catch (Exception e) {
			statusLabel.setText("An error occurred. Check your inputs and try again.");
		}

	}

}
