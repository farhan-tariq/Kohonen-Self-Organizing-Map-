package SimulationNetworkCore;

import Controllers.Learning.Simulation.Helper.Helper;
import NetworkCore.InputVector;
import Controllers.Learning.Simulation.Metric.DistanceMetric;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;

import java.util.ArrayList;
import java.util.List;

public class   Layer {
    private OutputNode[][] outputNodes = new OutputNode[4][4];
    private OutputNode BMU = null;
    private int width, height;
    private DistanceMetric distanceMetric = new DistanceMetric();
    private ArrayList<OutputNode> outputNodesArrayList;
    private ArrayList<WebView> webViewArrayList;

    public Layer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setOutputNodePosition() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                outputNodes[i][j].setXPos(i);
                outputNodes[i][j].setYPos(j);
            }
        }
    }

    public void initializeOutputNodes(List<OutputNode> outputNodes) {
        int counter = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                OutputNode node = outputNodes.get(counter);
                this.outputNodes[i][j] = node;
                counter++;
            }
        }
    }


    OutputNode getBMU(InputVector inputVector, int inputIndex, VBox formulaVBox) throws InterruptedException {
        // Start by assuming that 0,0 is our best matching unit
        OutputNode BMU = outputNodes[0][0];
        double bestEuclideanDistance = distanceMetric.euclideanDistance(inputVector, BMU.getWeightVector(), inputIndex, BMU.getID(), formulaVBox, false,BMU);
        double currentDistance;

        // Now step through the entire matrix and check the euclidean distance
        // between the input vector and the given node
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                //get the position of the weight vector passed and find the
                //euclidean distance of it against each neuron's weight actor in the lattice
                currentDistance = distanceMetric.euclideanDistance(inputVector, (outputNodes[x][y].getWeightVector()), inputIndex, outputNodes[x][y].getID(), formulaVBox, true,outputNodes[x][y]);

                if (currentDistance < bestEuclideanDistance) {
                    // If the distance from the current node to the input vector
                    // is less than the distance to our current BMU, we have a
                    // new BMU
                    BMU = outputNodes[x][y];
                    bestEuclideanDistance = currentDistance;
                }
            }
        }
        Thread.sleep(500);
        Node node = formulaVBox.getChildren().get(BMU.getID());
        HBox hBox = (HBox) node;
        hBox.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));

        Thread.sleep(15000);


        BMU.setStroke(Color.RED);
        Platform.runLater(() -> {
            formulaVBox.getChildren().clear();
            Helper.initialize(formulaVBox, outputNodesArrayList, webViewArrayList);
        });
        return BMU;
    }

    

    private void addRegion(VBox vBox) {
        Platform.runLater(() -> {
            vBox.getChildren().add(Helper.returnNewRegion(135, 260));
        });
    }

    void setBMUNode(OutputNode bestMU) {
        this.BMU = bestMU;
    }


    public void setLists(ArrayList<OutputNode> outputNodesArrayList, ArrayList<WebView> webViewArrayList) {
        this.outputNodesArrayList = outputNodesArrayList;
        this.webViewArrayList = webViewArrayList;

    }
    
    public OutputNode getBMUNode() {
        if (BMU != null) {
            return this.BMU;
        } else {
            return null;
        }
    }

        public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Returns the SOMNode at the given point (x,y)
    public OutputNode getNeuron(int x, int y) {
        return outputNodes[x][y];
    }

}
