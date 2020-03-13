package Visualizations;

import NetworkCore.WeightVector;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class SOMTrainingGrid extends VisualizationGridBase {


    @Override
    protected void render() {
        for (int i = 0; i < getLayer().getWidth(); i++) {
            for (int j = 0; j < getLayer().getHeight(); j++) {
                WeightVector vector = getLayer().getNeuron(i, j).getWeightVector();
                Image image = getUtility().returnPixelGeneratedImage(vector, getDataObject().getImageDataObjectList().get(0).getImageType());
                ImageView imageView = getVisualizationHelp().getImageView(image);
                int finalI = i;
                int finalJ = j;
                Platform.runLater((() -> {
                    setConstraints(imageView, finalI, finalJ);
                    getChildren().add(imageView);
                }));
            }
        }
    }
}

