package Controllers.DataSelectionControllers;

import Utils.PathList;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

import static Utils.JavaFXUtility.closeWindow;
import static Utils.JavaFXUtility.loadFxmlAndStage;

public class TrainingSampleDataController extends SampleDataControllerBase {
    private String controllerType = "Training";
    private String path = PathList.getLoadTrainingDataFxml();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        importButtonAction();
        cancelButtonAction();
        initializeListView();
        initializeTextArea();

    }

    private void importButtonAction() {
        getImportButton().setOnAction((event) -> {
            String selectedItem = getListView().getSelectionModel().getSelectedItem();
            if (getListView().getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No data selected");
                alert.setContentText("Please select some data before continuing");
                alert.showAndWait();
            } else {
                if (selectedItem.equals(getMNIST_DATASET())) {
                    importAction(getMNIST_DATASET(), path, controllerType, getImportButton());
                }

                if (selectedItem.equals(getMNIST_DATASET2())) {
                    importAction(getMNIST_DATASET2(), path, controllerType, getImportButton());
                }
                if (selectedItem.equals(getCOMPUTER_GENERATED_LETTERS_DATASET())) {
                    importAction(getCOMPUTER_GENERATED_LETTERS_DATASET(), path, controllerType, getImportButton());
                }
            }
        });

    }

    private void cancelButtonAction() {
        getCancelButton().setOnAction((event -> {
            loadFxmlAndStage(getClass().getResource(path), "Select Training DataObjects", null);
            closeWindow(getCancelButton());
        }));
    }

    private void initializeListView() {
        getListView().getItems().addAll(getMNIST_DATASET(), getMNIST_DATASET2(), getCOMPUTER_GENERATED_LETTERS_DATASET());
    }


}
