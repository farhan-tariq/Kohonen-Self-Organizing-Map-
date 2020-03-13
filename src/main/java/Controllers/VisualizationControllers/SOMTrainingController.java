package Controllers.VisualizationControllers;

import DataObjects.DataObject;
import NetworkCore.InputVector;
import NetworkCore.Layer;
import Utils.FileUtility;
import Utils.PathList;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import static Utils.JavaFXUtility.closeWindow;

public class SOMTrainingController extends VisualizationBaseController {

    @FXML
    private JFXButton saveMapButton;
    private Vector<InputVector> inputVectors;
    private DataObject dataObject;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeButtonAction();
        startButtonAction();
        iterationLabelBindProperty();
        stopButtonAction();
        backButtonAction();
        saveMapButtonAction();
    }

    private void startButtonAction() {
        startButton.setOnAction(actionEvent -> {
            initializeGridSOM(getHeight(),getWidth(),dataObject);
            inputVectors = dataObject.getConvertedInputVectorsFrom2DArray();
            if (getLayer() == null) {
                Layer layer = new Layer(getWidth(), getHeight(), 784);
                setLayer(layer);
                getTrainer().setTraining(layer, inputVectors, somGrid, getIterations());
            } else {
                getTrainer().setTraining(getLayer(), inputVectors, somGrid, getIterations());
            }
            start(getTrainer());
        });
    }

    private void iterationLabelBindProperty() {
        iterationValueLabel.textProperty().bind(getTrainer().messageProperty());
        getTrainer().setOnSucceeded(stateEvent -> iterationValueLabel.textProperty().unbind());
    }

    private void stopButtonAction() {
        stopButton.setOnAction(t -> getTrainer().stop());
    }

    private void backButtonAction() {
        backButton.setOnAction(actionEvent -> {
            getHelper().initVisualizationSelectionMenuController(PathList.getVisualizationSelectionMenuFxml(), dataObject, getWidth(), getHeight(), getIterations(), null);
            closeWindow(backButton);
        });
    }


    private void saveMapButtonAction() {
        saveMapButton.setOnAction(actionEvent -> {
            if (getIterations() > getIterationLabelValue()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Training in process");
                alert.setContentText("Cannot save until training is not finished");
                alert.showAndWait();
            } else {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);

                File file = fileChooser.showSaveDialog(saveMapButton.getScene().getWindow());
                if (file != null) {
                    FileUtility.saveMapToFile(getLayer(), file);
                }
            }
        });
    }

    @Override
    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }


}
