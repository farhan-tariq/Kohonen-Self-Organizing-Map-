package Controllers.DataSelectionControllers;

import Controllers.BaseController;
import DataObjects.DataObject;
import Utils.ControllerHelper;
import Utils.PathList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static Utils.JavaFXUtility.closeWindow;
import static Utils.JavaFXUtility.loadFxmlAndStage;

public abstract class DataControllerBase extends BaseController {

    @FXML
    private JFXButton nextButton, backButton, homeButton, loadSampleDataButton, loadFromWorkspaceButton;

    @FXML
    private JFXListView<String> listView;

    @FXML
    private Label input_data;


    private DataObject dataObject = new DataObject();
    private ControllerHelper helper = new ControllerHelper();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backButtonAction();
        homeButtonAction();
    }

    //Close the current window and load the settings window
    public void nextButtonAction(String path) {
        nextButton.setOnAction((ActionEvent event) -> {
            if (listView.getItems().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please select some dataObject before continuing");
                alert.showAndWait();
                System.out.println("Error- Please select some files");
            } else {
                helper.initSettingControllers(path, dataObject);
                closeWindow(nextButton);
            }
        });
    }

    //Close the current window and load the previous window
    private void backButtonAction() {
        backButton.setOnAction((ActionEvent event) -> {
            loadFxmlAndStage(getClass().getResource(PathList.getTrainingTestingSelectionMenuFxml()), "Selection", null);
            closeWindow(backButton);
        });
    }

    //Close the current window and load the welcome screen window
    private void homeButtonAction() {
        homeButton.setOnAction((ActionEvent event) -> {
            loadFxmlAndStage(getClass().getResource(PathList.getHomeScreenMenuFxml()), "Home Screen", null);
            closeWindow(homeButton);
        });
    }

    public void addToListView() {
        if (listView.getItems().isEmpty()) {
            Platform.runLater(() -> {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!dataObject.getFiles().isEmpty()) {
                    for (File file : dataObject.getFiles()) {
                        listView.getItems().add(file.getName());
                    }
                    input_data.setText("Input DataObjects Selected");
                } else {
                    System.out.println("Files empty");
                }
            });
        }
    }

    public DataObject getDataObject() {
        return dataObject;

    }

    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }

    JFXButton getLoadSampleDataButton() {
        return loadSampleDataButton;
    }

    JFXButton getLoadFromWorkspaceButton() {
        return loadFromWorkspaceButton;
    }
}
