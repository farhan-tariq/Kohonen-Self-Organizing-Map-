package Visualizations;

import NetworkCore.InputVector;
import NetworkCore.Neuron;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class HitMapGrid extends VisualizationGridBase {
    private boolean sized = false;

    @Override
    protected void render() {
        Neuron bmuNode = getLayer().getBMUNode();
        InputVector vector = bmuNode.getInputVector();
        Image image = getDataObject().returnImage(vector);
        ImageView imageView = getVisualizationHelp().getImageView(image);
        int x = bmuNode.getX();
        int y = bmuNode.getY();
        int hits = bmuNode.getHits();

        Platform.runLater(() -> {
            double unitWidth = imageView.getFitWidth() / 10;
            double unitHeight = imageView.getFitHeight() / 10;
            double totalArea = unitHeight * unitWidth;
            double aspectRatio = unitWidth / unitHeight;
            double relativeArea = totalArea * (double) hits;
            int relativeWidth = (int) Math.round(Math.sqrt(relativeArea * aspectRatio));
            int relativeHeight = (int) Math.round(Math.sqrt(relativeArea * aspectRatio) * 1 / aspectRatio);
            StackPane pane = new StackPane();
            pane.setAlignment(Pos.CENTER);
            pane.setPrefHeight(unitHeight);
            pane.setPrefWidth(unitWidth);
            Rectangle rectangle;

            if (relativeWidth > imageView.getFitWidth() && relativeHeight > imageView.getFitHeight()) {
                rectangle = new Rectangle((int) (x * unitWidth + (unitWidth - relativeWidth) / 2), (int) (y * unitHeight + (unitHeight - relativeHeight) / 2), imageView.getFitWidth(), imageView.getFitHeight());
                rectangle.setFill(Color.DARKVIOLET);
                pane.getChildren().addAll(rectangle, getTextField(hits));
            } else {
                rectangle = new Rectangle((int) (x * unitWidth + (unitWidth - relativeWidth) / 2), (int) (y * unitHeight + (unitHeight - relativeHeight) / 2), relativeWidth, relativeHeight);
                rectangle.setFill(Color.DARKVIOLET);
                pane.getChildren().addAll(rectangle, getTextField(hits));
            }

            setConstraints(pane, y, x);
            getChildren().add(pane);
        });
    }


    private Text getTextField(int hits) {
        Text text = new Text(String.valueOf(hits));
        text.setFill(Color.WHITE);
        return text;
    }

}


