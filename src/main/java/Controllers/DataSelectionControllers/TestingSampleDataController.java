package Controllers.DataSelectionControllers;

import Utils.PathList;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

import static Utils.JavaFXUtility.closeWindow;
import static Utils.JavaFXUtility.loadFxmlAndStage;

public class TestingSampleDataController extends SampleDataControllerBase {
    private String path = PathList.getLoadTestingDataFxml();
    private String controllerType = "Testing";

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
            String selectedItem = null;

            if (getListView().getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No data selected");
                alert.setContentText("Please select some data before continuing");
                alert.showAndWait();
            } else {
                selectedItem = getListView().getSelectionModel().getSelectedItem();
                if (selectedItem.equals(getMNIST_DATASET())) {
                    importAction(getMNIST_DATASET(), path, controllerType, getImportButton());
                }

                if (selectedItem.equals(getReplaced())) {
                    importAction(getReplaced(), path, controllerType, getImportButton());
                }

            }
        });

    }

    private void cancelButtonAction() {
        getCancelButton().setOnAction((event -> {
            loadFxmlAndStage(getClass().getResource(path), "Select Testing DataObjects", null);
            closeWindow(getCancelButton());
        }));
    }

    private void initializeListView() {
        getListView().getItems().addAll(getMNIST_DATASET(), getReplaced());
    }


}
