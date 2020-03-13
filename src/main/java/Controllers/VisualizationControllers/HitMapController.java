package Controllers.VisualizationControllers;

import NetworkCore.InputVector;
import NetworkCore.Layer;
import DataObjects.DataObject;
import Utils.PathList;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import static Utils.JavaFXUtility.closeWindow;

public class HitMapController extends VisualizationBaseController {
    private Vector<InputVector> inputVectors;
    private DataObject dataObject;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeButtonAction();
        startButtonAction();
        iterationLabelBindProperty();
        stopButtonAction();
        backButtonAction();
    }

    private void startButtonAction() {
        startButton.setOnAction(t -> {
            initializeHitMapGrid(getWidth(), getHeight(), dataObject);
            inputVectors = dataObject.getConvertedInputVectorsFrom2DArray();
            if (getLayer() == null) {
                Layer layer = new Layer(getWidth(), getHeight(), 784);
                getTrainer().setTraining(layer, inputVectors, hitMapGrid, getIterations());
            } else {
                getTrainer().setTraining(getLayer(), inputVectors, hitMapGrid, getIterations());
            }
            start(getTrainer());
        });
    }

    private void iterationLabelBindProperty() {
        iterationValueLabel.textProperty().bind(getTrainer().messageProperty());
        getTrainer().setOnSucceeded(e -> iterationValueLabel.textProperty().unbind());
    }

    private void stopButtonAction() {
        stopButton.setOnAction(t -> getTrainer().stop());
    }

    private void backButtonAction() {
        backButton.setOnAction(t -> {
            getHelper().initVisualizationSelectionMenuController(PathList.getVisualizationSelectionMenuFxml(), dataObject, getWidth(), getHeight(), getIterations(), null);
            closeWindow(backButton);
        });
    }

    @Override
    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }
}


