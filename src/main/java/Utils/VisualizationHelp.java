package Utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VisualizationHelp {

    public ImageView getImageView(Image image) {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        imageView.setImage(image);
        return imageView;
    }

    public Thread getThread(Runnable task) {
        Thread backGroundThread = new Thread(task);
        backGroundThread.setDaemon(true);
        backGroundThread.setPriority(Thread.MAX_PRIORITY);
        return backGroundThread;
    }
}
