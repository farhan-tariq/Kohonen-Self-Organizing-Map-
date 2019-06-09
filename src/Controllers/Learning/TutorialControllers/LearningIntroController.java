package Controllers.Learning.TutorialControllers;


import Utils.PathList;

import java.net.URL;
import java.util.ResourceBundle;


public class LearningIntroController extends LearningToolBase {


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initButtons();
        nextButtonAction(PathList.getNeuronFxml(), "Neuron Model");
        backButtonAction(PathList.getLearningSelectionMenuFxml(), "Learning Selection");
    }

}
