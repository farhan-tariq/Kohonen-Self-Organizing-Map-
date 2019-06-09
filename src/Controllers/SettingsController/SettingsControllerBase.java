package Controllers.SettingsController;

import Controllers.BaseController;
import NetworkCore.Layer;
import DataObjects.DataObject;
import Utils.ControllerHelper;
import Utils.PathList;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static Utils.JavaFXUtility.closeWindow;
import static Utils.JavaFXUtility.loadFxmlAndStage;


public abstract class SettingsControllerBase extends BaseController {

    private Layer layer;
    private boolean useMap;
    private DataObject dataObject;
    private ControllerHelper helper = new ControllerHelper();

    @FXML
    private TextField mapWidth, mapHeight, learningRate, iterations;

    @FXML
    private JFXButton homeButton, nextButton, backButton, restoreDefaults;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    //Closes the current window and opens the welcome screen window
    void homeScreenButtonAction() {
        homeButton.setOnAction(event1 -> {
            loadFxmlAndStage(getClass().getResource(PathList.getHomeScreenMenuFxml()), "Welcome Screen", null);
            closeWindow(homeButton);
        });
    }

    //Closes the current window and opens the visualization selection window
    void nextButtonAction() {
        nextButton.setOnAction((ActionEvent event) -> {
            if (!useMap) {
                if (!mapWidth.getText().isEmpty() && !mapHeight.getText().isEmpty() && !iterations.getText().isEmpty() && !learningRate.getText().isEmpty()) {
                    helper.initVisualizationSelectionMenuController(PathList.getVisualizationSelectionMenuFxml(), dataObject, Integer.parseInt(mapWidth.getText()), Integer.parseInt(mapHeight.getText()), Integer.parseInt(iterations.getText()), null);
                    closeWindow(nextButton);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Empty Fields");
                    alert.setContentText("Please fill in the text fields before continuing");
                    alert.showAndWait();
                }
            } else if (useMap && layer != null) {
                helper.initVisualizationSelectionMenuController(PathList.getVisualizationSelectionMenuFxml(), dataObject, layer.getWidth(), layer.getHeight(), 1, layer);
                closeWindow(nextButton);
            } else if (layer == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Layer Added");
                alert.setContentText("Please add a layer before continuing");
                alert.showAndWait();
            }
        });
    }

    //Closes the current window and goes back to the previous window
    void backButtonAction(String path) {
        backButton.setOnAction((ActionEvent event) -> {
            loadFxmlAndStage(getClass().getResource(path), "Select Input DataObjects", null);
            closeWindow(backButton);
        });
    }

    //Restores the textfield values back to default
    void restoreDefaultsButtonAction() {
        restoreDefaults.setOnAction((ActionEvent event) -> {
            mapWidth.setText("10");
            mapHeight.setText("10");
            learningRate.setText("0.07");
            iterations.setText("500");
        });
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    public void setUseMap(boolean useMap) {
        this.useMap = useMap;
    }

    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }

}
