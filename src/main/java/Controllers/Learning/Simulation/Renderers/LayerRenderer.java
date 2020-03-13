package Controllers.Learning.Simulation.Renderers;

import SimulationNetworkCore.OutputNode;
import animatefx.animation.Bounce;
import javafx.application.Platform;
import javafx.scene.paint.Color;


public class LayerRenderer {

    public static void renderBMU(OutputNode BMU, int i) {
        Platform.runLater(() -> {
            Bounce bounce = new Bounce(BMU);
            bounce.play();
            BMU.setStroke(Color.RED.deriveColor(0,1,1,0.5));
            BMU.setStrokeWidth(5);
        });
    }

}
