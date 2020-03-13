package Controllers.SettingsController;

import Utils.FileUtility;
import Utils.PathList;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class TestingSettingsController extends SettingsControllerBase {
    @FXML
    private JFXButton loadMapButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeScreenButtonAction();
        restoreDefaultsButtonAction();
        loadMapButtonAction();
        backButtonAction(PathList.getLoadTestingDataFxml());
        nextButtonAction();
    }

    private void loadMapButtonAction() {
        loadMapButton.setOnAction((ActionEvent event) -> {
            File file = FileUtility.getMapFromWorkspace();
            setLayer(FileUtility.loadMapFromFile(file));
        });
    }
}
