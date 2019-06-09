package Controllers.VisualizationControllers;

import Controllers.BaseController;
import NetworkCore.Layer;
import DataObjects.DataObject;
import Utils.ControllerHelper;
import Utils.PathList;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

import static Utils.JavaFXUtility.closeWindow;
import static Utils.JavaFXUtility.loadFxmlAndStage;

public class VisualizationSelectionMenuController extends BaseController {

    @FXML
    JFXButton homeButton, hitMapVisualizationButton, trainingVisualizationButton, bmuVisualizationButton;
    private ControllerHelper helper = new ControllerHelper();

    private int height;
    private int width;
    private DataObject dataObject;
    private Layer layer;
    private int iterations;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trainingVisualizationButton();
        hitMapVisualizationButtonAction();
        bmuVisualizationButtonAction();
        homeButtonAction();
    }


    private void bmuVisualizationButtonAction() {
        bmuVisualizationButton.setOnAction(a -> {
            if (layer == null) {
                helper.initVisualizationController(PathList.getbmuMapFxml(), dataObject, width, height, iterations, new Layer(width, height, 784));
            } else {
                helper.initVisualizationController(PathList.getbmuMapFxml(), dataObject, layer.getWidth(), iterations, layer.getHeight(), layer);
            }
            closeWindow(bmuVisualizationButton);
        });
    }

    private void hitMapVisualizationButtonAction() {
        hitMapVisualizationButton.setOnAction(a -> {
            if (layer == null) {
                helper.initVisualizationController(PathList.getHitMapFxml(), dataObject, width, height, iterations, new Layer(width, height, 784));
            } else {
                helper.initVisualizationController(PathList.getHitMapFxml(), dataObject, layer.getWidth(), iterations, layer.getHeight(), layer);
            }
            closeWindow(hitMapVisualizationButton);
        });
    }

    private void trainingVisualizationButton() {
        trainingVisualizationButton.setOnAction(a -> {
            if (layer == null) {
                helper.initVisualizationController(PathList.getTrainingVisualizationFxml(), dataObject, width, height, iterations, null);
            } else {
                helper.initVisualizationController(PathList.getTrainingVisualizationFxml(), dataObject, width, height, iterations, layer);
            }
            closeWindow(trainingVisualizationButton);
        });
    }

    private void homeButtonAction() {
        homeButton.setOnAction((ActionEvent event) -> {
            loadFxmlAndStage(getClass().getResource(PathList.getHomeScreenMenuFxml()), "Home Screen", null);
            closeWindow(homeButton);
        });
    }

    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }

    public void setHeight(int value) {
        this.height = value;
    }

    public void setWidth(int value) {
        this.width = value;
    }

    public Layer getLayer() {
        if (layer != null) return layer;
        else return null;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
}
