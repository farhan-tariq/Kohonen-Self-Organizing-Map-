package NetworkCore;

import Metric.DistanceMetric;

import java.io.Serializable;
import java.util.*;

public class Layer implements Serializable {
    private int width, height;
    private Neuron[][] matrix;
    private Neuron BMU = null;
    private final HashMap<Long, Set<Long>> linkMap = new HashMap<>();
    private List<Neuron> bestMatchingUnits = new ArrayList<>();


    public Layer() {

    }

    //The lattice constructor initializes the Neuron 2-dimensional array and its size of the weight vector
    //Also it sets the position of each neuron in the lattice
    public Layer(int w, int h, int numberOfWeights) {
        this.width = w;
        this.height = h;
        long count = 0;

        matrix = new Neuron[width][height];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                count++;
                matrix[i][j] = new Neuron(numberOfWeights);
                matrix[i][j].setX(i);
                matrix[i][j].setY(j);
                matrix[i][j].setId(count);
                linkMap.put(count, new HashSet<>());
            }
        }
    }

    public Collection<Neuron> getNeighbours(Neuron neuron) {
        final Set<Long> idList = linkMap.get(neuron.getId());
        final List<Neuron> neuronList = new ArrayList<>();
        for (Long id : idList) {
            neuronList.add(getNeuron(id));
        }
        return neuronList;
    }


    // Returns the SOMNode at the given point (x,y)
    public Neuron getNeuron(int x, int y) {
        return this.matrix[x][y];
    }

    private Neuron getNeuron(long id) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (matrix[i][j].getId() == id) {
                    return matrix[i][j];
                }
            }
        }
        return null;
    }

    Neuron getBMU(InputVector inputVector) {
        // Start out assuming that 0,0 is our best matching unit
        Neuron bmu = matrix[0][0];
        double bestDist = DistanceMetric.euclideanDistance(inputVector, bmu.getWeightVector());
        double curDist;

        // Now step through the entire matrix and check the euclidean distance
        // between the input vector and the given node
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                //get the position of the weight vector passed and find the
                //euclidean distance of it against each neuron's weight actor in the lattice
                curDist = DistanceMetric.euclideanDistance(inputVector, matrix[x][y].getWeightVector());

                if (curDist < bestDist) {
                    // If the distance from the current node to the input vector
                    // is less than the distance to our current BMU, we have a
                    // new BMU
                    bmu = matrix[x][y];
                    bestDist = curDist;
                }
            }
        }
        bestMatchingUnits.add(bmu);
        return bmu;
    }

    public Neuron[][] getMatrix() {
        return this.matrix;
    }

    public List<Neuron> getBestMatchingUnits() {
        return bestMatchingUnits;
    }

    public Neuron getBMUNode() {
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

    void setBMUInputVector(InputVector inputVector) {
        if (inputVector != null) {
            this.BMU.setInputVector(inputVector);
        } else {
            System.out.println(getClass().getName() + " input Vector is null");
        }
    }

    void setBMUNeuron(Neuron BMU) {
        this.BMU = BMU;
    }
}

