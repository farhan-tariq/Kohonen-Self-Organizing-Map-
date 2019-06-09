package Controllers.Learning.TutorialControllers;

import Controllers.BaseController;
import Utils.PathList;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

import static Utils.JavaFXUtility.closeWindow;
import static Utils.JavaFXUtility.loadFxmlAndStage;

public class LearningSelectionController extends BaseController {

    @FXML
    private JFXButton simulationButton,learnButton,homeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeButtonAction();
        simulationButtonAction();
        learnButtonAction();
    }

    private void homeButtonAction() {
        homeButton.setOnAction((ActionEvent event) -> {
            loadFxmlAndStage(getClass().getResource(PathList.getHomeScreenMenuFxml()), "Home Screen", null);
            closeWindow(homeButton);
        });
    }

    private void simulationButtonAction() {
        simulationButton.setOnAction(event -> {
            loadFxmlAndStage(getClass().getResource(PathList.getSimulationFxml()), "Algorithm Simulation", null);
            closeWindow(simulationButton);
        });
    }

    private void learnButtonAction() {
        learnButton.setOnAction(event -> {
            loadFxmlAndStage(getClass().getResource(PathList.getIntroPageFxml()), "Introduction", null);
            closeWindow(learnButton);
        });
    }
}
