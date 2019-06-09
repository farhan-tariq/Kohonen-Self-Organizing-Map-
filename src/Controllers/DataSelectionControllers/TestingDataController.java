package Controllers.DataSelectionControllers;


import Utils.FileUtility;
import Utils.PathList;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static Utils.JavaFXUtility.closeWindow;
import static Utils.JavaFXUtility.loadFxmlAndStage;

public class TestingDataController extends DataControllerBase {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        selectInputButtonAction();
        selectSampleInputData();
        nextButtonAction(PathList.getTestingSettingUrl());
    }

    private void selectInputButtonAction() {
        getLoadFromWorkspaceButton().setOnAction((ActionEvent event) -> {
            getDataObject().setFiles(FileUtility.getMultipleFileInput());
            addToListView();
            getDataObject().initializeImageDataList();
        });
    }

    private void selectSampleInputData() {
        getLoadSampleDataButton().setOnAction((ActionEvent event) -> {
            loadFxmlAndStage(getClass().getResource(PathList.getLoadTestingSampleDataFxml()), "Testing Sample DataObjects", null);
            closeWindow(getLoadSampleDataButton());
        });
    }
}
