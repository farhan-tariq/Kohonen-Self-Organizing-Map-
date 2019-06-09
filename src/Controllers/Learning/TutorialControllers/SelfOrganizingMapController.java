package Controllers.Learning.TutorialControllers;

import Utils.PathList;

import java.net.URL;
import java.util.ResourceBundle;

public class SelfOrganizingMapController extends LearningToolBase {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initButtons();
        nextButtonAction(PathList.getVisualizingSomFxml(), "Self-Organizing Map Visualization");
        backButtonAction(PathList.getNormalizedHebbianFxml(), "Normalized Hebbian Learning");
    }
}
