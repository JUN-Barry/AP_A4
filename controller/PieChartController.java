package controller;

import java.util.List;

import ConstructorClass.SharesCategory;
import Model.PieChartVisModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.LoginScene;

public class PieChartController {
	
	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;

	}
	
	@FXML
	private BorderPane borderPane;

    @FXML
    private PieChart pieChart;

    @FXML
    private void HandlePieChart(ActionEvent event) {
    	List<SharesCategory> data = PieChartVisModel.PostShareCategory();
        pieChart.getData().clear(); // Clear any existing data
    	

        for (SharesCategory categoryData : data) {
            PieChart.Data dataPoint = new PieChart.Data(categoryData.getCategory(), categoryData.getAmount());
            pieChart.getData().add(dataPoint);
        }
        
        pieChart.setTitle("Shares proportions In the HUB ");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50) ;
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);     
        borderPane.setCenter(pieChart);
    }


    @FXML
    private void HandleUpdateData(ActionEvent event) {
    	HandlePieChart(event);
    }

    @FXML
    private void HandleBackMenu(ActionEvent event) {
    	LoginScene LogInscene = new LoginScene(); // keep same
		FunctionMenuController functionmenucontroller = LogInscene.getScene("/view/FunctionMenu.fxml",
				this.primaryStage);
		functionmenucontroller.setPrimaryStage(this.primaryStage);
    }
}

