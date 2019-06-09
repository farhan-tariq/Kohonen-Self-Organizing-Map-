package Controllers.SettingsController;

import Utils.PathList;

import java.net.URL;
import java.util.ResourceBundle;

public class TrainingSettingsController extends SettingsControllerBase {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeScreenButtonAction();
        backButtonAction(PathList.getLoadTrainingDataFxml());
        restoreDefaultsButtonAction();
        nextButtonAction();
    }
}
