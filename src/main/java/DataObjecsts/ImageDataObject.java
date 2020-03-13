package DataObjects;

import NetworkCore.InputVector;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.io.File;

public class ImageDataObject {
    private Image image;
    private double[][] image2DArray;
    private File file;
    private BufferedImage bufferedImage;
    private InputVector inputVector;
    private int imageType;

    Image getImage() {
        return image;
    }

    void setImage(Image image) {
        this.image = image;
    }

    double[][] getImage2DArray() {
        return image2DArray;
    }

    void setImage2DArray(double[][] image2DArray) {
        this.image2DArray = image2DArray;
    }

    public File getFile() {
        return file;
    }

    void setFile(File file) {
        this.file = file;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    InputVector getInputVector() {
        return inputVector;
    }

    void setInputVector(InputVector vector) {
        this.inputVector = vector;
    }

    void setImageType(int type) {
        this.imageType = type;
    }

    public int getImageType() {
        return imageType;
    }
}

