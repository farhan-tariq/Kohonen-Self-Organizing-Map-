package Controllers.Learning.TutorialControllers;

import Utils.PathList;

import java.net.URL;
import java.util.ResourceBundle;

public class VisualizingSOMController extends LearningToolBase {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initButtons();
        nextButtonAction(PathList.getMcqMainFxml(), "Multiple Choice Questions");
        backButtonAction(PathList.getSelfOrganizingMapFxml(), "Self-Organizing Map");
    }
}
