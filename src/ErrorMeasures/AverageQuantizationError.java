package ErrorMeasures;

import Metric.DistanceMetric;
import NetworkCore.InputVector;
import NetworkCore.Layer;
import NetworkCore.Neuron;

import java.util.Vector;

public class AverageQuantizationError {
    public static double measure(Vector<InputVector> inputs, Layer layer) {
        double d = 0;
        int count = 0;
        for (InputVector vector : inputs) {
            ++count;
            d += DistanceMetric.euclideanDistance(vector, findBest(vector, layer.getMatrix(), layer).getWeightVector());

            if (count == 0) {
                System.out.println("Count = 0");
            }
        }
        return d / count;
    }

    private static Neuron findBest(InputVector inputVector, Neuron[][] matrix, Layer layer) {
        Neuron best = null;
        double min = Double.POSITIVE_INFINITY;
        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                final double d = DistanceMetric.euclideanDistance(inputVector, matrix[x][y].getWeightVector());
                if (d < min) {
                    min = d;
                    best = matrix[x][y];
                }
            }
        }
        return best;
    }
}