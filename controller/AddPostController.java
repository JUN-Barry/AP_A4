package controller;

import ConstructorClass.PostInfo; // Import the necessary class
import Model.CheckPostFormatModel;
import Model.PostInfoModel;
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
//import java.net.URL;
//import java.util.ResourceBundle;

public class AddPostController {

	@FXML
	private Button BackTOmenu;

	@FXML
	private HBox Hbox;

	@FXML
	private AnchorPane inputPane;

	@FXML
	private AnchorPane hintPane;

	@FXML
	private VBox Vbox;

	@FXML
	private TableView<PostInfo> tableView; // Define the type as PostInfo

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
	private TextField PostIDhandler;

	@FXML
	private TextField ContentHandler;

	@FXML
	private TextField AuthorHandler;

	@FXML
	private TextField Likes;

	@FXML
	private TextField Shares;

	@FXML
	private TextField datetimehalder;

	@FXML
	private Button Submitbutton;

	@FXML
	private Label statusLabel;

	// pass the primary stage to the controller
	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;

	}

	@FXML
	private void AddPost(ActionEvent event) {
		idColumn.setCellValueFactory(new PropertyValueFactory<PostInfo, Integer>("id")); // "name" should be same in the
																							// PostInfo class
		contentColumn.setCellValueFactory(new PropertyValueFactory<PostInfo, String>("content"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<PostInfo, String>("author"));
		likeColumn.setCellValueFactory(new PropertyValueFactory<PostInfo, Integer>("likes"));
		sharescolumn.setCellValueFactory(new PropertyValueFactory<PostInfo, Integer>("shares"));
		datetimecolumn.setCellValueFactory(new PropertyValueFactory<PostInfo, String>("datetime"));

		String idText = PostIDhandler.getText();
		String content = ContentHandler.getText();
		String author = AuthorHandler.getText();
		String likesText = Likes.getText();
		String sharesText = Shares.getText();
		String datetime = datetimehalder.getText();

		// Check for null or empty values
		if (idText.isEmpty() || content.isEmpty() || author.isEmpty() || likesText.isEmpty() || sharesText.isEmpty()
				|| datetime.isEmpty()) {
			statusLabel.setText("Please fill in all fields.");
			return;
		}

		try {
			int id = Integer.parseInt(idText);
			int likes = Integer.parseInt(likesText);
			int shares = Integer.parseInt(sharesText);

			if (PostInfoModel.CheckIDexist(id)) {
				if (CheckPostFormatModel.isNonNegativeInt(id) && CheckPostFormatModel.hasNoComma(content)
						&& CheckPostFormatModel.hasNoComma(author) && CheckPostFormatModel.isNonNegativeInt(likes)
						&& CheckPostFormatModel.isNonNegativeInt(shares)
						&& CheckPostFormatModel.isValidDateTime(datetime)) {

					PostInfo newPost = new PostInfo(id, content, author, likes, shares, datetime);
					System.out.println(newPost.getLikes());

					ObservableList<PostInfo> ToaddPost = tableView.getItems();
					ToaddPost.add(newPost);
					tableView.setItems(ToaddPost);

					PostInfoModel.PostInfoIntoTable(id, content, author, likes, shares, datetime); // ADD THE post
					statusLabel.setText("New post added!");
				} else {
					statusLabel.setText("Please enter valid contents. Have a check please.");
				}
			} else {
				statusLabel.setText("ID has already existed in the System, change one please.");
			}

		} catch (NumberFormatException e) {
			statusLabel.setText("ID, Likes, and Shares must be valid integers.");
			return;
		}
	}

	@FXML
	private void BacktoMenu(ActionEvent event) {
		// Handle the "Back to Menu" button click event here
		// You can navigate back to the menu or any other desired action here
		LoginScene LogInscene = new LoginScene(); // keep same
		FunctionMenuController functionmenucontroller = LogInscene.getScene("/view/FunctionMenu.fxml",
				this.primaryStage);
		functionmenucontroller.setPrimaryStage(this.primaryStage);
	}
}
