package RunHub;



import controller.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.LoginScene;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
    	
    	LoginScene LogInscene = new LoginScene(); // initiate new scene for login, the format is same in every event
    	
    	
    	primaryStage.setTitle(LogInscene.getTitle()); // the title of the stage is the title of the scene
    	//primaryStage.setScene(LogInscene.getScene());
    	
    	LoginController loginController = LogInscene.getScene("/view/Login.fxml", primaryStage);  // this format would apply every new scene, and keep stage (where login scene kick off) consistent
    	loginController.setPrimaryStage(primaryStage); 


    }

    public static void main(String[] args) {
        launch(args);
    }
}
