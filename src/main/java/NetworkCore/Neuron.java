package NetworkCore;

import java.io.Serializable;

public class Neuron implements Serializable {

    private long id;
    /**
     * Variable to store the weightVector of inputs to the neuron
     */
    private WeightVector weightVector;

    /**
     * Variable to store the input given to the neuron at a time instant
     */
    private InputVector inputVector;

    /**
     * Neuron's x-axis position
     */
    private int xp;

    /**
     * Neuron's y-axis position
     */
    private int yp;

    /**
     * Variable to store the times this neuron was a best matching unit
     */
    private int hits = 0;


    private int count = 0;

    /**
     * Constructor
     * Initialises the weight vector by setting the size and assigning random weight values
     *
     * @param numberOfWeights
     */
    public Neuron(int numberOfWeights) {
        weightVector = new WeightVector();
        for (int i = 0; i < numberOfWeights; i++) {
            weightVector.addElement(Math.random());
        }
    }

    /**
     * Method to set the x-axis position of the neuron
     *
     * @param xpos
     */
    public void setX(int xpos) {
        xp = xpos;
    }

    /**
     * Method to set the y-axis position of the neuron
     *
     * @param ypos
     */
    public void setY(int ypos) {
        yp = ypos;
    }

    /**
     * Method to set the input
     */
    void setInputVector(InputVector inputs) {
        this.inputVector = inputs;
    }

    //Method to get X position
    public int getX() {
        return xp;
    }

    //Method to get Y position
    public int getY() {
        return yp;
    }

    public InputVector getInputVector() {
        return this.inputVector;
    }

    /**
     * Computes the distance to another node.  Used for
     * neighbourhood determination.  Returns the SQUARE of the distance
     */
    double getMapDistance(Neuron u1, Neuron u2) {
        return getMapDistance(u1.getX(), u1.getY(), u2.getX(), u2.getY());
    }

    private double getMapDistance(int x1, int y1, int x2, int y2) {
        return ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public WeightVector getWeightVector() {
        return this.weightVector;
    }

    void adjustWeights(InputVector input, double learningRate, double distanceFalloff) {
        double wt, vw;
        for (int w = 0; w < weightVector.size(); w++) {
            wt = (weightVector.elementAt(w));
            vw = input.elementAt(w);
            wt += distanceFalloff * learningRate * (vw - wt);
            weightVector.setElementAt(wt, w);
        }
    }

    void updateHits() {
        this.hits = this.hits + 1;
    }

    public int getHits() {
        return hits;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void updateCount() {
        this.count = count + 1;
    }

   public int getCount() {
        return this.count;
    }
}
