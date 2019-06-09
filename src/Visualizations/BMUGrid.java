package Visualizations;

import NetworkCore.InputVector;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class BMUGrid extends VisualizationGridBase {

    @Override
    protected void render() {
        InputVector vector = getLayer().getBMUNode().getInputVector();

        Image image = getDataObject().returnImage(vector);
        ImageView imageView = getVisualizationHelp().getImageView(image);
        int x = getLayer().getBMUNode().getX();
        int y = getLayer().getBMUNode().getY();

        Platform.runLater((() -> {
            StackPane pane = new StackPane();
            pane.setAlignment(Pos.CENTER);
            pane.setPrefHeight(imageView.getFitHeight() + 200);
            pane.setPrefWidth(imageView.getFitWidth() + 200);
            final StackPane layout = new StackPane();
            layout.setPrefSize(100, 100);
            layout.getChildren().addAll(imageView, pane);
            layout.setStyle("-fx-background-color: silver; -fx-padding: 2");
            setConstraints(layout, y, x);
            getChildren().add(layout);

        }));
    }
}
