package Controllers.Learning.TutorialControllers;

import Utils.PathList;

import java.net.URL;
import java.util.ResourceBundle;

public class LearningController extends LearningToolBase {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initButtons();
        nextButtonAction(PathList.getHebbianFxml(), "Hebbian Learning");
        backButtonAction(PathList.getNeuronFxml(), "Neuron Model");
    }

}
