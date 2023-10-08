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

public class RemovePostController {

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
	private Button SearchbUTTON;

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

	@FXML
	private Button Deletebutton;

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
		idColumn.setCellValueFactory(new PropertyValueFactory<PostInfo, Integer>("id")); // "name" should be same in the
		// PostInfo class
		contentColumn.setCellValueFactory(new PropertyValueFactory<PostInfo, String>("content"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<PostInfo, String>("author"));
		likeColumn.setCellValueFactory(new PropertyValueFactory<PostInfo, Integer>("likes"));
		sharescolumn.setCellValueFactory(new PropertyValueFactory<PostInfo, Integer>("shares"));
		datetimecolumn.setCellValueFactory(new PropertyValueFactory<PostInfo, String>("datetime"));

// Get values from text fields
		int id = Integer.parseInt(PostIDhandler.getText());

		if (!PostInfoModel.CheckIDexist(id)) {
			ObservableList<PostInfo> ToaddPost = tableView.getItems();
			ToaddPost.add(RetrievePostModel.RetrieveSinglePost(id));
			tableView.setItems(ToaddPost);

		} else {
			statusLabel.setText("Post ID not found.");
		}
	}

	@FXML
	private void RemovePost(ActionEvent event) {
		int id = Integer.parseInt(PostIDhandler.getText());
		PostInfoModel.removePost(id);
		ObservableList<PostInfo> items = tableView.getItems();

		// Find the PostInfo object to remove based on its ID
		PostInfo postToRemove = null;
		for (PostInfo post : items) {
			if (post.getId() == id) {
				postToRemove = post;
				break;
			}
		}

		// If the post was found, remove it from the ObservableList
		if (postToRemove != null) {
			items.remove(postToRemove);
		}

		// Clear the PostIDhandler TextField
		PostIDhandler.clear();

	}
}
