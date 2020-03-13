package ErrorMeasures;


import NetworkCore.InputVector;
import NetworkCore.Neuron;
import NetworkCore.WeightVector;

import java.util.Vector;

public class MeanSquareError {
    public static double measure(Vector<InputVector> inputVectors, Neuron[][] matrix, int width, int height) {
        double mse = Double.MAX_VALUE;
        double rss = 0.0;

        for (InputVector inputVector : inputVectors) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    rss += calculate(inputVector, matrix[x][y].getWeightVector());
                }
            }
            if (mse > rss) mse = rss;
        }
        return mse;
    }

    private static double calculate(InputVector inputVector, WeightVector weightVector) {
        double mse = 0.0;
        int n = inputVector.size();
        for (int i = 0; i < inputVector.size(); i++) {
            mse += Math.sqrt((-inputVector.get(i)) - (-weightVector.get(i)));
        }
        return mse / n;
    }
}
