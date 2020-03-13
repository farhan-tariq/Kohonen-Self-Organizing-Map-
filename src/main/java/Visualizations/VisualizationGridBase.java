package Visualizations;

import NetworkCore.Layer;
import DataObjects.DataObject;
import Utils.ImageUtility;
import Utils.VisualizationHelp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.awt.*;


public abstract class VisualizationGridBase extends GridPane {
    private int iteration;
    private ImageUtility utility = new ImageUtility();
    private VisualizationHelp visualizationHelp = new VisualizationHelp();
    private Layer layer;
    private DataObject dataObject;


    protected void render() {

    }

    public void run() {
        Runnable task = this::render;
        Thread backgroundThread = getVisualizationHelp().getThread(task);
        backgroundThread.start();
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    Layer getLayer() {
        return layer;
    }

    ImageUtility getUtility() {
        return utility;
    }

    VisualizationHelp getVisualizationHelp() {
        return visualizationHelp;
    }

    public int getIteration() {
        return iteration;
    }

    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }

    public DataObject getDataObject() {
        return dataObject;
    }

}
