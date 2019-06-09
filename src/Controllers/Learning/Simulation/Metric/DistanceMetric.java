package Controllers.Learning.Simulation.Metric;

import SimulationNetworkCore.BoundLine;
import SimulationNetworkCore.OutputNode;
import Controllers.Learning.Simulation.Helper.LatexFormula;
import NetworkCore.InputVector;
import NetworkCore.WeightVector;
import animatefx.animation.Shake;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DistanceMetric {

    private Node node;

    public double euclideanDistance(InputVector inputVector, WeightVector weightVector, int inputIndex, int nodeIndex, VBox formulaVBox, boolean yes, OutputNode node) throws InterruptedException {
        if (weightVector.size() != inputVector.size()) {
            return -999;
        }
        double distance = 0;
        double temp;
        for (int i = 0; i < inputVector.size(); i++) {
            temp = (inputVector.elementAt(i) - weightVector.elementAt(i));
            temp *= temp;
            distance += temp;
            if (yes) {
                render(nodeIndex, inputIndex, formulaVBox, inputVector.elementAt(i), weightVector.elementAt(i), distance);
                BoundLine line = node.getLineArrayList().get(i);
                Shake shake = new Shake(line);
                shake.play();
                Thread.sleep(1000);
            }
        }
        return distance;
    }


    private void render(int nodeIndex, int inputIndex, VBox formulaVBox, double inputValue, double weightValue, double distance) {
        LatexFormula latexFormula = new LatexFormula(nodeIndex, inputIndex);
        LatexFormula latexFormula2 = new LatexFormula(inputValue, weightValue);
        LatexFormula latexFormula3 = new LatexFormula(distance);
        Platform.runLater(() -> {
            node = formulaVBox.getChildren().get(nodeIndex);
            HBox hBox = (HBox) node;
            hBox.getChildren().add(latexFormula);
            hBox.getChildren().add(latexFormula2);
            hBox.getChildren().add(latexFormula3);
        });
    }
}


