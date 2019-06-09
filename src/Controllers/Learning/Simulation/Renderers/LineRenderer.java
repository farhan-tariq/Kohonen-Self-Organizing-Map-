package Controllers.Learning.Simulation.Renderers;

import SimulationNetworkCore.BoundLine;
import animatefx.animation.FadeInLeft;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class LineRenderer extends Task<Void> {

    private AnchorPane linePane;
    private ArrayList<BoundLine> lineArrayList;

    @Override
    protected Void call() {
        for (BoundLine line : lineArrayList) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.getMessage();
            }
            render(line);
        }
        return null;
    }

    public void setStackPane(AnchorPane linePane, ArrayList<BoundLine> lineArrayList) {
        this.linePane = linePane;
        this.lineArrayList = lineArrayList;
    }


    private void render(BoundLine line) {
        Platform.runLater(() -> {
            FadeInLeft bounce = new FadeInLeft(line);
            bounce.play();
            linePane.getChildren().add(line);
        });
    }

}
