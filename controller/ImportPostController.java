package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import view.LoginScene;

import java.io.File;
//import java.io.FileNotFoundException;

import ConstructorClass.PostInfo;
import Model.ImportPostsModel;
import Model.PostInfoModel;
//import Model.PostInfoModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ImportPostController {

    @FXML
    private VBox Vbox;
    @FXML
    private HBox Hbox;
      
    @FXML
    private Button BackTOmenu;
    @FXML
    private AnchorPane inputPane;
    @FXML
    private Label fileDirectoryLabel;
    @FXML
    private TextField FileDirectory;
    @FXML
    private Button ChooseFile;
    
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
    private Label statusLabel;
    @FXML
    private Button ImportPost;

    private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

    @FXML
    private void BacktoMenu(ActionEvent event) {
    	LoginScene LogInscene = new LoginScene(); // keep same
		FunctionMenuController functionmenucontroller = LogInscene.getScene("/view/FunctionMenu.fxml",
				this.primaryStage);
		functionmenucontroller.setPrimaryStage(this.primaryStage);

    }

    @FXML
    private void HandlerChooseFile(ActionEvent event) {
        idColumn.setCellValueFactory(new PropertyValueFactory<PostInfo, Integer>("id"));
		contentColumn.setCellValueFactory(new PropertyValueFactory<PostInfo, String>("content"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<PostInfo, String>("author"));
		likeColumn.setCellValueFactory(new PropertyValueFactory<PostInfo, Integer>("likes"));
		sharescolumn.setCellValueFactory(new PropertyValueFactory<PostInfo, Integer>("shares"));
		datetimecolumn.setCellValueFactory(new PropertyValueFactory<PostInfo, String>("datetime"));
    	
    	FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV Files", "*.csv"));

        File file = fileChooser.showOpenDialog(primaryStage); // Assuming primaryStage is properly set

        if (file != null) {
            // Set the file directory to the chosen file
            FileDirectory.setText(file.getAbsolutePath());

            // Call the method to import data from the CSV file
            ObservableList<PostInfo> importedData = ImportPostsModel.ImportPostfromCSV(file.getAbsolutePath());

            // Clear any existing data in the table
            tableView.getItems().clear();

            // Set the imported data to the table
            tableView.setItems(importedData);

            if (importedData.isEmpty()) {
                statusLabel.setText("No valid data in the file.");
            } else {
                statusLabel.setText("Valid data in the file is shown in the table.");
            }
        } else {
            // No file selected
            statusLabel.setText("No file selected.");
        }
        
        
    
    }



    @FXML
    private void ImportPostFROMCSV(ActionEvent event) {
    	ObservableList<PostInfo> importedData = tableView.getItems();

        if (importedData.isEmpty()) {
            statusLabel.setText("No data to import.");
        } else {
            try {
                for (PostInfo post : importedData) {
                    PostInfoModel.PostInfoIntoTable(post.getId(), post.getContent(), post.getAuthor(), post.getLikes(), post.getShares(), post.getDatetime());
                }
                statusLabel.setText("New posts added!");
            } catch (Exception e) {
                statusLabel.setText("Error while inserting data into the database.");
                e.printStackTrace();
            }
        }
    }
}
