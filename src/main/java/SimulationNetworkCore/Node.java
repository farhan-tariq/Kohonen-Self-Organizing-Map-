package SimulationNetworkCore;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 * Class to represent a node/neuron in the network
 * Extends the circle class
 */
public abstract class Node extends Circle {
    private int id;

    /**
     * Constructor that initializes the node object
     */
    Node() {
        setRadius(35);
        setFill(Color.WHITE);
        setStroke(Color.GRAY);
        setStrokeWidth(2);
        setStrokeType(StrokeType.CENTERED);
    }

    /**
     * Method to set the id of the node
     *
     * @param id id of node
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Method to return id of the node
     *
     * @return id
     */
    public int getID() {
        if (id > -1) return id;
        else return -1;
    }
}
