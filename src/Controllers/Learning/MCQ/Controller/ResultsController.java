package Controllers.Learning.MCQ.Controller;

import Controllers.BaseController;
import Utils.PathList;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static Utils.JavaFXUtility.closeWindow;
import static Utils.JavaFXUtility.loadFxmlAndStage;

public class ResultsController extends BaseController {
    @FXML
    private Label  marksLabel;

    @FXML
    private JFXButton homeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int marks = MCQController.getInstance().getResult();
        marksLabel.setText(marks + " / 30");
        homeButtonAction();
    }

    private void homeButtonAction() {
        homeButton.setOnAction((ActionEvent event) -> {
            loadFxmlAndStage(getClass().getResource(PathList.getHomeScreenMenuFxml()), "Home Screen", null);
            closeWindow(homeButton);
        });
    }
}
