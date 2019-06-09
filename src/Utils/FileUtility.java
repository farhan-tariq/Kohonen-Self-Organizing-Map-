package Utils;

import NetworkCore.Layer;
import javafx.stage.FileChooser;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileUtility {
    public static List<File> getMultipleFileInput() {
        FileChooser fileChooser = new FileChooser();
        String pathDesktop = System.getProperty("user.dir");
        File userDirectory = new File(pathDesktop);
        fileChooser.setInitialDirectory(userDirectory);
        return fileChooser.showOpenMultipleDialog(null);
    }

    /**
     * Method to load data from folder
     *
     * @param datasetName    name of dataset
     * @param controllerType type of controller
     * @return files
     */
    static List<File> loadSampleData(String datasetName, String controllerType) {
        if (datasetName.equals("1 - Handwritten Numbers") && controllerType.equals("Training")) {
            File file = new File(PathList.getTrainingDataset1Url());
            return FileUtility.getFilesFromFolder(file, new ArrayList<>());
        } else if (datasetName.equals("1 - Handwritten Numbers") && controllerType.equals("Testing")) {
            File file = new File(PathList.getTestingDataset1Url());
            return FileUtility.getFilesFromFolder(file, new ArrayList<>());
        }

        if (datasetName.equals("2 - Handwritten Numbers") && controllerType.equals("Training")) {
            File file = new File(PathList.getTestingDataset2Url());
            return FileUtility.getFilesFromFolder(file, new ArrayList<>());
        }

        if (datasetName.equals("3 - Alphabets In Different Fonts") && controllerType.equals("Training")) {
            File file = new File(PathList.getTrainingDataset3Url());
            return FileUtility.getFilesFromFolder(file, new ArrayList<>());
        } else if (datasetName.equals("2 - Alphabets In Different Fonts") && controllerType.equals("Testing")) {
            File file = new File(PathList.getTestingDataset2Url());
            return FileUtility.getFilesFromFolder(file, new ArrayList<>());

        }
        return null;
    }

    public static File getMapFromWorkspace() {
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showOpenDialog(null);
    }

    public static BufferedImage convertFileToImage(File file) {
        BufferedImage bufferedImage;
        int w, h;
        if (file != null) {
            try {
                bufferedImage = ImageIO.read(file);
                w = bufferedImage.getWidth();
                h = bufferedImage.getHeight();
                if (w > 28 && h > 28) return resizeImage(bufferedImage, file);
                else return bufferedImage;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private static BufferedImage resizeImage(BufferedImage image, File file) {
        image = Scalr.resize(image, 28, 28, Scalr.OP_ANTIALIAS);
        writeImageToFile(image, file);
        return image;
    }

    private static void writeImageToFile(BufferedImage image, File f) {
        try {
            ImageIO.write(image, f.getName(), f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<File> getFilesFromFolder(final File folder, List<File> files) {
        for (final File file : Objects.requireNonNull(folder.listFiles())) {
            File file1 = new File(file.getAbsolutePath());
            files.add(file1);
        }
        return files;
    }

    public static void saveMapToFile(Layer layer, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(layer);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Layer loadMapFromFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Layer layer = (Layer) ois.readObject();
            ois.close();
            fis.close();
            return layer;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
