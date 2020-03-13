package ErrorMeasures;

import NetworkCore.InputVector;
import NetworkCore.Layer;
import NetworkCore.Neuron;

import java.util.Vector;


public class TopographicError {
    private double[][] unitError;
    private double averageError;

    public TopographicError(Layer layer, Vector<InputVector> inputVector) {
        int xSize = layer.getWidth();
        int ySize = layer.getHeight();
        unitError = new double[xSize][ySize];

        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                unitError[x][y] = 0.0;
            }
        }

        int numVectors = inputVector.size();
        double sum = 0.0;
        double[] sampleError = new double[numVectors];

        for (int i = 0; i < inputVector.size(); i++) {
            Neuron bmu = layer.getBestMatchingUnits().get(0);
            Neuron sbmu = layer.getBestMatchingUnits().get(1);

            if (Math.abs(bmu.getX() - sbmu.getX()) == 1 && bmu.getY() == sbmu.getY()
                    || bmu.getX() == sbmu.getX() && Math.abs(bmu.getY() - sbmu.getY()) == 1) {
                sampleError[i] = 0.0;
            } else {
                sampleError[i] = 1.0;
                sum++;
                unitError[bmu.getX()][bmu.getY()]++;
            }
        }
        averageError = sum / numVectors;
    }

    public double getAverageError() {
        return averageError;
    }
}
