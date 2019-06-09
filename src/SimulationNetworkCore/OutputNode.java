package SimulationNetworkCore;

import NetworkCore.InputVector;
import NetworkCore.WeightVector;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent the outputNode/outputNeuron in the network as a circle
 * <p>
 * Each outputNode has a weight vector
 * Each outputNode has line coming to it representing input
 * Each outputNode has its xPosition and yPosition to represent it in grid
 */
public class OutputNode extends Node {
    private WeightVector weightVector;
    private List<InputNode> inputNodes;
    private List<BoundLine> lineArrayList;
    private int xPosition, yPosition;

    /**
     * Constructor to create and init
     *
     * @param numberOfWeights number of weight of connections
     */
    public OutputNode(int numberOfWeights) {
        inputNodes = new ArrayList<>();
        lineArrayList = new ArrayList<>();
        weightVector = new WeightVector();

        for (int i = 0; i < numberOfWeights; i++) {
            weightVector.addElement(Math.random());
        }
    }

    /**
     * Method to get distance between two nodes
     *
     * @param node1 node1
     * @param node2 node2
     * @return distance
     */
    double getDistance(OutputNode node1, OutputNode node2) {
        return getDistance(node1.getXPos(), node1.getYPos(), node2.getXPos(), node2.getYPos());
    }

    /**
     * @param x1 xValue
     * @param y1 yValue
     * @param x2 xValue
     * @param y2 yValue
     * @return distance
     */
    private double getDistance(int x1, int y1, int x2, int y2) {
        return ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /**
     * @param inputVector     inputs
     * @param learningRate    learning rate of network
     * @param distanceFalloff distance
     */
     void adjustWeights(InputVector inputVector, double learningRate, double distanceFalloff) {
        double weight, input;
        for (int w = 0; w < weightVector.size(); w++) {
            weight = weightVector.elementAt(w);
            input = inputVector.elementAt(w);
            weight += distanceFalloff * learningRate * (input - weight);
            weightVector.setElementAt(weight, w);
        }
    }

        /**
     * Method to add input node to List
     *
     * @param inputNode inputNode
     */
    public void addInputNode(InputNode inputNode) {
        inputNodes.add(inputNode);
    }


    /**
     * Method to return weight Vector
     *
     * @return weightVector
     */
    WeightVector getWeightVector() {
        return weightVector;
    }

    /**
     * Method to set weight Vector
     *
     * @param weightVector
     */
    public void setWeightVector(WeightVector weightVector) {
        this.weightVector = weightVector;
    }

    /**
     * Method to return List of inputNodes
     *
     * @return inputNodes
     */
    public List<InputNode> getInputNodes() {
        return inputNodes;
    }

    /**
     * @param boundLine line object
     * @param id
     */
    public void addLine(BoundLine boundLine, int id) {
        lineArrayList.add(boundLine);
    }

    /**
     * Method to return line List
     *
     * @return line List
     */
    public List<BoundLine> getLineArrayList() {
        return lineArrayList;
    }

    /**
     * Method to set xPosition
     *
     * @param xPos xPosition
     */
    public void setXPos(int xPos) {
        this.xPosition = xPos;
    }

    /**
     * Method to set yPosition
     *
     * @param yPos yPosition
     */
    public void setYPos(int yPos) {
        this.yPosition = yPos;
    }

    /**
     * Method to get yPosition
     *
     * @return yPosition
     */
    int getYPos() {
        return yPosition;
    }

    /**
     * Method to get xPosition
     *
     * @return xPosition
     */
    int getXPos() {
        return xPosition;
    }
}


