package Controllers.DataSelectionControllers;

import Controllers.BaseController;
import Utils.PathList;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

import static Utils.JavaFXUtility.closeWindow;
import static Utils.JavaFXUtility.loadFxmlAndStage;

public class DataSelectionMenuController extends BaseController {

    @FXML
    private JFXButton trainNetworkButton,testNetworkButton,homeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trainingButtonAction();
        testingButtonAction();
        homeButtonAction();
    }

    private void trainingButtonAction() {
        trainNetworkButton.setOnAction(event -> {
            loadFxmlAndStage(getClass().getResource(PathList.getLoadTrainingDataFxml()), "Training Data Selection", null);
           closeWindow(trainNetworkButton);
        });
    }

    private void testingButtonAction() {
        testNetworkButton.setOnAction(event -> {
            loadFxmlAndStage(getClass().getResource(PathList.getLoadTestingDataFxml()), "Testing Data Selection", null);
            closeWindow(testNetworkButton);
        });
    }

    private void homeButtonAction() {
        homeButton.setOnAction((ActionEvent event) -> {
            loadFxmlAndStage(getClass().getResource(PathList.getHomeScreenMenuFxml()), "Home Screen", null);
            closeWindow(homeButton);
        });
    }
}
