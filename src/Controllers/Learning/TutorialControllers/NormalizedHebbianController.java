package Controllers.Learning.TutorialControllers;

import Utils.PathList;

import java.net.URL;
import java.util.ResourceBundle;

public class NormalizedHebbianController extends LearningToolBase {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initButtons();
        nextButtonAction(PathList.getDistanceMetricFxml(), "Distance Metrics");
        backButtonAction(PathList.getHebbianFxml(), "Hebbian Learning");
    }
}
