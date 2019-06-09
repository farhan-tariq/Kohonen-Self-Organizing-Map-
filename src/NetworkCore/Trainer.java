package NetworkCore;

import ErrorMeasures.AverageQuantizationError;
import ErrorMeasures.MeanSquareError;
import ErrorMeasures.TopographicError;
import Visualizations.VisualizationGridBase;
import javafx.concurrent.Task;

import java.util.Vector;

public class Trainer extends Task<Void> {

    /**
     * Learning rate of the network
     */
    private static final double START_LEARNING_RATE = 0.02;

    /**
     * Number of iterations the network should run for
     */
    private int NUM_ITERATIONS;

    /**
     * The radius of the layer
     */
    private double layerRadius;

    /**
     * Time constant of the network
     */
    private double timeConstant;

    /**
     * Abstract instance of class
     */
    private VisualizationGridBase visualizationGridBase;

    /**
     * Abstract instance variable of the layer class
     */
    private Layer layer;

    /**
     * The input data set for the network
     */
    private Vector<InputVector> inputs;

    /**
     * Variable to store the iteration count
     */
    private int iteration = 0;

    /**
     * Variable to check if the network is still training
     */
    private boolean stop = false;

    private double meanSquaredError;
    private double topographicError;
    private double averageQuantizationError;


    /**
     * Empty Constructor
     */
    public Trainer() {
    }

    /**
     * Task call method to run on a separate thread
     */
    protected Void call() {
        int lw = layer.getWidth();
        int lh = layer.getHeight();

        // These values are used to determine and update the neighbourhood
        int xStart, yStart, xEnd, yEnd;
        double dist, dFalloff;

        // These two values are used in the training algorithm
        layerRadius = (double) Math.max(lw, lh) / 2;
        timeConstant = NUM_ITERATIONS / Math.log(layerRadius);

        double nbhRadius;
        Neuron bmu;
        Neuron tempNeuron;
        InputVector curInput;
        double learningRate = START_LEARNING_RATE;

        while (iteration <= NUM_ITERATIONS && !stop) {
            nbhRadius = getNeighborhoodRadius(iteration);

            for (int i = 0; i < this.inputs.size(); i++) {
                curInput = this.inputs.elementAt(i);
                bmu = layer.getBMU(curInput);

                xStart = (int) (bmu.getX() - nbhRadius);
                yStart = (int) (bmu.getY() - nbhRadius);
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

                for (int x = xStart; x < xEnd; x++) {
                    for (int y = yStart; y < yEnd; y++) {
                        tempNeuron = layer.getNeuron(x, y);
                        dist = bmu.getMapDistance(bmu, tempNeuron);
                        if (dist <= (nbhRadius * nbhRadius)) {
                            dFalloff = getDistanceFalloff(dist, nbhRadius);
                            tempNeuron.adjustWeights(curInput, learningRate, dFalloff);
                        }
                    }
                }

                // Update if the parameter passed is BMU Grid
                checkAndUpdateBmuAndHitMapGrid(visualizationGridBase, bmu, curInput);


            }
            //Update if the parameter passed is SOM Grid
            checkAndUpdateSOMGrid(visualizationGridBase);

            updateMessage(String.valueOf(iteration));

            iteration++;
            learningRate = START_LEARNING_RATE * Math.exp(-(double) iteration / NUM_ITERATIONS);
        }


        meanSquaredError = MeanSquareError.measure(inputs, layer.getMatrix(), lw, lh);
        averageQuantizationError = AverageQuantizationError.measure(inputs, layer);
        TopographicError error = new TopographicError(layer, inputs);
        topographicError = error.getAverageError();

        System.out.println("Mean Squared Error = " + meanSquaredError);
        System.out.println("Average Quantization Error = " + averageQuantizationError);
        System.out.println("Topographic Error = " + topographicError);

        return null;
    }


    /**
     * Method to return the iteration number
     *
     * @return iteration
     */
    public int getIteration() {
        return iteration;
    }

    /**
     * Method to get the neighbourhood radius of a neuron
     *
     * @param iteration
     * @return neighbourHoodRadius
     */
    private double getNeighborhoodRadius(double iteration) {
        return layerRadius * Math.exp(-iteration / timeConstant);
    }

    /**
     * Method to get the distance from a point to its radius
     *
     * @param distSq
     * @param radius
     * @return exponent(- ( distSq / ( 2 * radius))
     */
    private double getDistanceFalloff(double distSq, double radius) {
        double radiusSq = radius * radius;
        return Math.exp(-(distSq) / (2 * radiusSq));
    }

    /**
     * Method to set the parameters passed to this class parameters
     *
     * @param latToTrain
     * @param inputs
     * @param grid
     */
    public void setTraining(Layer latToTrain, Vector<InputVector> inputs, VisualizationGridBase grid, int iterations) {
        this.layer = latToTrain;
        this.inputs = inputs;
        this.visualizationGridBase = grid;
        this.NUM_ITERATIONS = iterations;
    }

    /**
     * Method to set the boolean value to false ( stop training of the network)
     */
    public void stop() {
        stop = true;
        System.gc();
    }

    /**
     * Method to check and update the BMU Grid, BMU Neuron, Input to the BMU and set
     * parameters
     *
     * @param gridBase
     * @param curInput
     * @Param bmu
     */
    private void checkAndUpdateBmuAndHitMapGrid(VisualizationGridBase gridBase, Neuron bmu, InputVector curInput) {
        String name = gridBase.getClass().getSimpleName();
        if (name.equals("HitMapGrid")|| name.equals("BMUGrid")) {
            layer.setBMUNeuron(bmu);
            layer.setBMUInputVector(curInput);
            layer.getBMUNode().updateHits();

            gridBase.setLayer(layer);
            gridBase.setIteration(iteration);
            gridBase.run();
        }
    }

    /**
     * Method to check and update the SOM Grid and set parameters
     *
     * @param gridBase
     **/
    private void checkAndUpdateSOMGrid(VisualizationGridBase gridBase) {
        if (gridBase.getClass().getSimpleName().equals("SOMTrainingGrid")) {
            gridBase.setLayer(layer);
            gridBase.setIteration(iteration);
            gridBase.run();
        }
    }


}
