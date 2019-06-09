package DataObjects;

import NetworkCore.InputVector;
import Utils.FileUtility;
import Utils.ImageUtility;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DataObject {

    private List<ImageDataObject> imageDataObjectList;
    private List<File> files;
    private Vector<InputVector> inputVectors;

    public DataObject() {
        imageDataObjectList = new ArrayList<>();
    }


    public void setFiles(List<File> files) {
        if (files != null && this.files == null)
            this.files = files;
    }

    public void initializeImageDataList()  {
        if (files != null) {
            for (File file : this.files) {
                ImageDataObject imageDataObject = new ImageDataObject();
                setInputFile(imageDataObject, file);
                setInputImagesFile(imageDataObject, file);
                setJavaFxImages(imageDataObject, file);
                setImageFiles2DArray(imageDataObject, file);
                convert2DArrayToVectorOfInputVectors();
                imageDataObjectList.add(imageDataObject);
            }
        }
    }

    private void setInputImagesFile(ImageDataObject imageDataObject, File file) {
        if (file != null) {
            BufferedImage image = FileUtility.convertFileToImage(file);
            imageDataObject.setBufferedImage(image);
            imageDataObject.setImageType(image.getType());
        } else {
            System.out.println(getClass() + " cannot set the converted Buffered Image");
        }
    }

    private void setInputFile(ImageDataObject imageDataObject, File file) {
        if (file != null) {
            imageDataObject.setFile(file);
        } else {
            System.out.println(getClass() + " cannot set the file");
        }
    }


    private void setJavaFxImages(ImageDataObject imageDataObject, File file) {
        if (file != null) {
            imageDataObject.setImage(ImageUtility.convertFileToJavaFxImages(file));
        } else {
            System.out.println(getClass() + " cannot set the converted JavaFx Image ");
        }
    }

    private void setImageFiles2DArray(ImageDataObject imageDataObject, File file) {
        int type = imageDataObject.getImageType();
        if (file != null) {
            imageDataObject.setImage2DArray(ImageUtility.generatePixelArray(file, type));
        } else {
            System.out.println(getClass() + " cannot set the Image 2D-Array");
        }
    }


    public Vector<InputVector> getConvertedInputVectorsFrom2DArray() {
        return inputVectors;
    }

    private void convert2DArrayToVectorOfInputVectors() {
        this.inputVectors = new Vector<>();
        InputVector tempVec;

        for (ImageDataObject imageCheck : imageDataObjectList) {
            tempVec = new InputVector();
            for (int j = 0; j < 28; j++) {
                for (int k = 0; k < 28; k++) {
                    tempVec.addElement(imageCheck.getImage2DArray()[j][k]);
                }
            }
            inputVectors.addElement(tempVec);
            imageCheck.setInputVector(tempVec);
        }
    }

    public List<ImageDataObject> getImageDataObjectList() {
        return imageDataObjectList;
    }


    private boolean compareInputVectors(InputVector v1, InputVector v2) {
        return v1.equals(v2);
    }

    public Image returnImage(InputVector inputVector) {
        for (ImageDataObject image : imageDataObjectList) {
            if (compareInputVectors(image.getInputVector(), inputVector)) {
                return image.getImage();
            }
        }
        return null;
    }

    public List<File> getFiles() {
        return files;
    }

}










