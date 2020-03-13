package Utils;

import Controllers.VisualizationControllers.VisualizationBaseController;
import Controllers.VisualizationControllers.VisualizationSelectionMenuController;
import Controllers.DataSelectionControllers.DataControllerBase;
import Controllers.DataSelectionControllers.TestingDataController;
import Controllers.DataSelectionControllers.TrainingDataController;
import Controllers.SettingsController.TestingSettingsController;
import Controllers.SettingsController.TrainingSettingsController;
import NetworkCore.Layer;
import DataObjects.DataObject;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static Utils.FileUtility.loadSampleData;
import static Utils.JavaFXUtility.*;

public class ControllerHelper {

    public void initSettingControllers(String path, DataObject dataObject) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
            if (compareString(path, (PathList.getTestingSettingUrl()))) {
                TestingSettingsController controller = loader.getController();
                controller.setDataObject(dataObject);
                controller.setUseMap(true);
            } else if (compareString(path, (PathList.getTrainingSettingUrl()))) {
                TrainingSettingsController controller = loader.getController();
                controller.setDataObject(dataObject);
                controller.setUseMap(false);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        loadAndShowStage(loader);
    }

    /**
     * Method to initialize visualize type selection controller
     *
     * @param path       FXML path of controller
     * @param dataObject DataObjects object
     * @param width      width of the layer
     * @param height     height of the layer
     * @param layer      network Layer
     */
    public void initVisualizationSelectionMenuController(String path, DataObject dataObject, int width, int height, int iterations, Layer layer) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
            if (compareString(path, PathList.getVisualizationSelectionMenuFxml())) {
                VisualizationSelectionMenuController controller = loader.getController();
                controller.setDataObject(dataObject);
                controller.setHeight(height);
                controller.setWidth(width);
                controller.setIterations(iterations);
                if (layer != null) {
                    controller.setLayer(layer);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        loadAndShowStage(loader);
    }


    /**
     * Method to initialize visualization controller either HitMap or Training Visualization
     *
     * @param path       FXML path of controller
     * @param dataObject DataObjects object
     * @param width      width of the layer
     * @param height     height of the layer
     * @param layer      network Layer
     */
    public void initVisualizationController(String path, DataObject dataObject, int width, int height, int iterations, Layer layer) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
            VisualizationBaseController controller;
            if (compareString(path, PathList.getHitMapFxml()) || compareString(path, PathList.getTrainingVisualizationFxml()) || compareString(path, PathList.getbmuMapFxml())) {
                controller = loader.getController();
                controller.setHeight(height);
                controller.setWidth(width);
                controller.setDataObject(dataObject);
                controller.setIterations(iterations);
                if (layer != null) {
                    controller.setLayer(layer);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        loadAndShowStage(loader);
    }

    /**
     * @param datasetName    name of the dataset
     * @param path           path of the FXML
     * @param controllerType type of controller
     */
    public void initAndLoadDataControllers(String datasetName, String path, String controllerType) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
            initDataControllers(loader, datasetName, controllerType);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        loadAndShowStage(loader);
    }

    /**
     * @param loader         FXML Loader
     * @param datasetName    name of the dataset to load
     * @param controllerType type of data controller
     */
    private void initDataControllers(FXMLLoader loader, String datasetName, String controllerType) {
        List<File> files = loadSampleData(datasetName, controllerType);

        if (controllerType.equals("Training")) {
            TrainingDataController controller;
            controller = loader.getController();
            initDataControllers(controller, files);

        } else if (controllerType.equals("Testing")) {
            TestingDataController controller;
            controller = loader.getController();
            initDataControllers(controller, files);
        }
    }

    /**
     * @param controller DataObjects Controller
     * @param files      files to load
     */
    private void initDataControllers(DataControllerBase controller, List<File> files) {
        controller.getDataObject().setFiles(files);
        controller.getDataObject().initializeImageDataList();
        controller.addToListView();
        sleepThread();
    }
}
