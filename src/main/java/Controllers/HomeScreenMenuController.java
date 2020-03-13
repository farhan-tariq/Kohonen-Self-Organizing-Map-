package Controllers;

import Utils.PathList;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

import static Utils.JavaFXUtility.closeWindow;
import static Utils.JavaFXUtility.loadFxmlAndStage;


public class HomeScreenMenuController extends BaseController {

    @FXML
    private JFXButton learningButton,clusteringButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        learningButtonAction();
        clusteringButtonAction();
    }

    //Closes the current window and opens the learning tool window
    private void learningButtonAction() {
        learningButton.setOnAction((ActionEvent event) -> {
          loadFxmlAndStage(getClass().getResource(PathList.getLearningSelectionMenuFxml()), "Learning Selection", null);
            closeWindow(learningButton);
        });
    }

    //Closes the current window and opens the clustering tool window
    private void clusteringButtonAction() {
        clusteringButton.setOnAction((ActionEvent event) -> {
           loadFxmlAndStage(getClass().getResource(PathList.getTrainingTestingSelectionMenuFxml()), "Training / Testing", null);
            closeWindow(clusteringButton);
        });
    }

}


