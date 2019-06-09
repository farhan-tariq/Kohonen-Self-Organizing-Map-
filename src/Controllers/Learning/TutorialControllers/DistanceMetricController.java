package Controllers.Learning.TutorialControllers;

import Utils.PathList;

import java.net.URL;
import java.util.ResourceBundle;

public class DistanceMetricController extends LearningToolBase {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initButtons();
        nextButtonAction(PathList.getSelfOrganizingMapFxml(), "Self-Organizing Map");
        backButtonAction(PathList.getNormalizedHebbianFxml(), "Normalized Hebbian Learning");
    }
}
