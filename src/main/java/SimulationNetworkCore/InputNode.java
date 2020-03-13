package SimulationNetworkCore;


import NetworkCore.InputVector;

import java.util.ArrayList;
import java.util.List;

public class InputNode extends Node {
    private int num;
    private List<Node> toNodes;
    private InputVector inputVector;

    public InputNode(InputVector inputVector) {
        toNodes = new ArrayList<>();
        this.inputVector = inputVector;
    }

    @Override
    public int getID() {
        return num;
    }

    @Override
    public void setID(int id) {
        this.num = id;
    }

    public List<Node> getToNodes() {
        return toNodes;
    }

    public void setToNodes(List<Node> toNodes) {
        this.toNodes = toNodes;
    }

    public void setInputVector(InputVector inputVector) {
        this.inputVector = inputVector;
    }

    public InputVector getInputVector() {
        return inputVector;
    }
}
