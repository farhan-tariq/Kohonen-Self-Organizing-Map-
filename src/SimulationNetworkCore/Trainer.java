package SimulationNetworkCore;

import Controllers.Learning.Simulation.Renderers.LayerRenderer;
import NetworkCore.InputVector;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebView;

import java.util.ArrayList;

public class Trainer extends Task<Void> {
    private static final double START_LEARNING_RATE = 0.02;
    private static final int NUM_ITERATIONS = 100;
    private double layerRadius;
    private double timeConstant;
    private Layer layer;
    private ArrayList<InputNode> inputNodes;
    private ArrayList<OutputNode> outputNodes;
    private ArrayList<WebView> webViewArrayList;
    private int iteration = 0;
    private VBox formulaVBox;
    private AnchorPane radiusCirclePane;

    public Trainer() {
    }

    @Override
    protected Void call() throws Exception {
        Thread.sleep(1500);
        int lw = layer.getWidth();
        int lh = layer.getHeight();

        // These values are used to determine and update the neighbourhood
        int xStart, yStart, xEnd, yEnd;
        double distance, dFalloff;

        // These two values are used in the training algorithm
        layerRadius = (double) Math.max(lw, lh) / 2;
        timeConstant = NUM_ITERATIONS / Math.log(layerRadius);

        boolean stop = false;
        double nbhRadius;
        OutputNode BMU;
        OutputNode tempNeuron;
        InputVector currentInput;
        double learningRate = START_LEARNING_RATE;
        layer.setLists(outputNodes, webViewArrayList);

        while (iteration <= NUM_ITERATIONS && !stop) {
            nbhRadius = getNeighborhoodRadius(iteration);
            for (int i = 0; i < inputNodes.size(); i++) {
                currentInput = inputNodes.get(i).getInputVector();
                BMU = layer.getBMU(currentInput, i + 1, formulaVBox);

                Thread.sleep(100);
                LayerRenderer.renderBMU(BMU, i);

                xStart = (int) (BMU.getXPos() - nbhRadius);
                yStart = (int) (BMU.getYPos() - nbhRadius);
                xEnd = (int) (xStart + (nbhRadius * 2));
                yEnd = (int) (yStart + (nbhRadius * 2));


                if (xEnd > lw)
                    xEnd = lw;
                if (xStart < 0)
                    xStart = 0;
                if (yEnd > lh)
                    yEnd = lh;
                if (yStart < 0)
                    yStart = 0;


                double finalNbhRadius = nbhRadius;
                OutputNode finalBMU = BMU;

                int count = 0;
                int radius = 0;
                for (int x = xStart; x < xEnd; x++) {
                    for (int y = yStart; y < yEnd; y++) {
                        radius += 30;
                        radius = radius - count;
                        count++;
                    }
                }

                int finalRadius = radius;

                Platform.runLater(() -> {
                    Circle circle = new Circle();
                    circle.setRadius(finalRadius);
                    circle.setCenterX(finalBMU.getCenterX());
                    circle.setCenterY(finalBMU.getCenterY());
                    circle.setFill(Color.LIGHTBLUE);
                    circle.setOpacity(0.1);
                    radiusCirclePane.getChildren().add(circle);
                });

                Thread.sleep(3000);

                for (int x = xStart; x < xEnd; x++) {
                    for (int y = yStart; y < yEnd; y++) {
                        tempNeuron = layer.getNeuron(x, y);
                        distance = BMU.getDistance(BMU, tempNeuron);
                        if (distance <= (nbhRadius * nbhRadius)) {
                            dFalloff = getDistanceFalloff(distance, nbhRadius);
                            tempNeuron.adjustWeights(currentInput, learningRate, dFalloff);
                        }
                    }
                }
                Thread.sleep(200);
                Platform.runLater(() -> radiusCirclePane.getChildren().clear());
            }
            iteration++;
            learningRate = START_LEARNING_RATE * Math.exp(-(double) iteration / NUM_ITERATIONS);
        }
        return null;
    }


    private double getNeighborhoodRadius(double iteration) {
        return layerRadius * Math.exp(-iteration / timeConstant);
    }

    private double getDistanceFalloff(double distSq, double radius) {
        double radiusSq = radius * radius;
        return Math.exp(-(distSq) / (2 * radiusSq));
    }

    public void setTraining(Layer latToTrain, ArrayList<InputNode> inputNodes, ArrayList<OutputNode> outputNodes
            , ArrayList<WebView> webViewArrayList, VBox formulaVBox, AnchorPane radiusCirclePane) {
        this.layer = latToTrain;
        this.inputNodes = inputNodes;
        this.formulaVBox = formulaVBox;
        this.outputNodes = outputNodes;
        this.webViewArrayList = webViewArrayList;
        this.radiusCirclePane = radiusCirclePane;
    }
}
