package Metric;


import NetworkCore.InputVector;
import NetworkCore.WeightVector;


public class DistanceMetric {
    public static double euclideanDistance(InputVector inputVector, WeightVector weightVector) {
        if (checkDimension(inputVector, weightVector)) {
            double distance = 0;
            double temp;
            for (int i = 0; i < inputVector.size(); i++) {
                temp = (inputVector.elementAt(i) - weightVector.elementAt(i));
                temp *= temp;
                distance += temp;
            }
            return distance;
        } else {
            return -999;
        }
    }

    private static boolean checkDimension(InputVector inputVector, WeightVector weightVector) {
        return inputVector.size() == weightVector.size();
    }

}
