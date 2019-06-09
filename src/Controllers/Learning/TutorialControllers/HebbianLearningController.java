package Controllers.Learning.TutorialControllers;

import Utils.PathList;

import java.net.URL;
import java.util.ResourceBundle;

public class HebbianLearningController extends LearningToolBase {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initButtons();
        nextButtonAction(PathList.getNormalizedHebbianFxml(), "Normalized Hebbian Learning");
        backButtonAction(PathList.getLearningUrl(), "Neuron Model");
    }
}
