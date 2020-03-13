package Controllers.VisualizationControllers;

import Controllers.BaseController;
import NetworkCore.Layer;
import NetworkCore.Trainer;
import DataObjects.DataObject;
import Utils.ControllerHelper;
import Utils.PathList;
import Visualizations.BMUGrid;
import Visualizations.HitMapGrid;
import Visualizations.SOMTrainingGrid;
import Visualizations.VisualizationGridBase;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

import static Utils.JavaFXUtility.closeWindow;
import static Utils.JavaFXUtility.loadFxmlAndStage;

public abstract class VisualizationBaseController extends BaseController {

    @FXML
    VBox vBox;

    @FXML
    Label iterationValueLabel;

    @FXML
    JFXButton startButton, stopButton, backButton, homeButton;

    @FXML
    HitMapGrid hitMapGrid;

    @FXML
    SOMTrainingGrid somGrid;

    @FXML
    BMUGrid bmuGrid;

    private Trainer trainer = new Trainer();
    private ControllerHelper helper = new ControllerHelper();

    private int height;
    private int width;
    private int iterations;

    private Layer layer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    void initializeGridSOM(int i, int j, DataObject dataObject) {
        initializeGrid(i,j,somGrid, dataObject);
    }

    void initializeGridBMU(int i, int j, DataObject dataObject) {
        initializeGrid(i,j,bmuGrid, dataObject);
    }

    void initializeHitMapGrid(int i, int j, DataObject dataObject) {
        initializeGrid(i,j,hitMapGrid, dataObject);
    }


    private void initializeGrid(int i, int j,VisualizationGridBase gridBase, DataObject dataObject) {
        gridBase.setDataObject(dataObject);
        gridBase.setVgap(5);
        gridBase.setHgap(5);
        gridBase.setPadding(new Insets(5, 5, 5, 5));
        setColumnConstraints(i, gridBase);
        setRowConstraints(j, gridBase);

    }

    void start(Trainer trainer) {
        Thread thread = new Thread(trainer);
        thread.setDaemon(true);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    public void setDataObject(DataObject dataObject) {
    }

    public DataObject getDataObject() {
        return null;
    }

    public void setHeight(int value) {
        this.height = value;
    }

    public void setWidth(int value) {
        this.width = value;
    }

    private void setColumnConstraints(int i, VisualizationGridBase gridBase) {
        for (int j = 0; j < i; j++) {
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setMinWidth(0);
            constraints.setPrefWidth(45);
            constraints.setHgrow(Priority.NEVER);
            gridBase.getColumnConstraints().add(constraints);
        }
    }

    private void setRowConstraints(int i, VisualizationGridBase gridBase) {
        for (int j = 0; j < i; j++) {
            RowConstraints constraints = new RowConstraints();
            constraints.setMinHeight(0);
            constraints.setPrefHeight(45);
            constraints.setVgrow(Priority.NEVER);
            gridBase.getRowConstraints().add(constraints);
        }
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    public Layer getLayer() {
        if (layer != null) return layer;
        else return null;
    }


    public void homeButtonAction() {
        homeButton.setOnAction((ActionEvent event) -> {
            loadFxmlAndStage(getClass().getResource(PathList.getHomeScreenMenuFxml()), "Home Screen", null);
            closeWindow(homeButton);
        });
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    int getIterations() {
        return iterations;
    }

    int getIterationLabelValue() {
        return Integer.parseInt(iterationValueLabel.getText());
    }

    Trainer getTrainer() {
        return trainer;
    }

    public ControllerHelper getHelper() {
        return helper;
    }
}
