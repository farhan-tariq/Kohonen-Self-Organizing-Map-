package Controllers.DataSelectionControllers;

import Controllers.BaseController;
import Utils.ControllerHelper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static Utils.JavaFXUtility.closeWindow;

public abstract class SampleDataControllerBase extends BaseController {


    @FXML
    private JFXListView<String> listView;

    @FXML
    private TextArea textArea;

    @FXML
    private JFXButton importButton;

    @FXML
    private JFXButton cancelButton;


    private final String COMPUTER_GENERATED_LETTERS_DATASET = "3 - Alphabets In Different Fonts";

    private ControllerHelper helper = new ControllerHelper();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listViewListener(datasetDescriptionList());
    }


    void initializeTextArea() {
        textArea.setEditable(false);
        textArea.setWrapText(true);
    }


    JFXButton getImportButton() {
        return importButton;
    }

    JFXButton getCancelButton() {
        return cancelButton;
    }


    ListView<String> getListView() {
        return listView;
    }

    String getCOMPUTER_GENERATED_LETTERS_DATASET() {
        return COMPUTER_GENERATED_LETTERS_DATASET;
    }

    String getMNIST_DATASET() {
        return "1 - Handwritten Numbers";
    }

    String getMNIST_DATASET2() {
        return "2 - Handwritten Numbers";
    }

    String getReplaced() {
        return COMPUTER_GENERATED_LETTERS_DATASET.replace('3', '2');
    }

    void importAction(String datasetName, String path, String controllerType, JFXButton button) {
        helper.initAndLoadDataControllers(datasetName, path, controllerType);
    closeWindow(button);
    }

    private void updateTextArea(StringBuilder builder) {
        textArea.setText(builder.toString());
    }

    private void listViewListener(List<StringBuilder> builderList) {
        listView.setOnMouseClicked(event -> {
            if (check(listView, getMNIST_DATASET())) {
                updateTextArea(builderList.get(0));
            } else if (check(listView, getMNIST_DATASET2())) {
                updateTextArea(builderList.get(1));
            } else if (check(listView, getCOMPUTER_GENERATED_LETTERS_DATASET())) {
                updateTextArea(builderList.get(2));
            } else if (check(listView, getReplaced())) {
                updateTextArea(builderList.get(2));
            }
        });
    }


    private boolean check(JFXListView<String> listView, String datasetName) {
        return listView.getSelectionModel().getSelectedItem().equals(datasetName);
    }


    private List<StringBuilder> datasetDescriptionList() {
        List<StringBuilder> stringBuilderList = new ArrayList<>();
        stringBuilderList.add(handWrittenDatasetDescription());
        stringBuilderList.add(handWrittenDatasetDescription2());
        stringBuilderList.add(computerGeneratedAlphabetsDescription());
        return stringBuilderList;
    }

    private StringBuilder handWrittenDatasetDescription() {
        StringBuilder builder = new StringBuilder();
        builder.append("MNIST Handwritten Numbers Dataset - 1\n\n");
        builder.append("This dataset consists of images of numbers \n 0 - 9 \n\n");
        builder.append("Each image in this dataset is unique\n\n");
        builder.append("The dimension of each image in pixels is 28 x 28\n\n");
        builder.append("Total Size of the dataset is 100\n\n");
        builder.append("Each number image is in 10 different hand writings");
        return builder;
    }

    private StringBuilder handWrittenDatasetDescription2() {
        StringBuilder builder = new StringBuilder();
        builder.append("MNIST Handwritten Numbers Dataset - 2\n\n");
        builder.append("This dataset consists of images of numbers \n 0 - 9 \n\n");
        builder.append("Each image in this dataset is unique\n\n");
        builder.append("The dimension of each image in pixels is 28 x 28\n\n");
        builder.append("Total Size of the dataset is 200\n\n");
        builder.append("Each number image is in 20 different hand writings");
        return builder;
    }

    private StringBuilder computerGeneratedAlphabetsDescription() {
        StringBuilder builder = new StringBuilder();
        builder.append("Alphabets Dataset \n\n");
        builder.append("This dataset consists of images of alphabets \n A - J \n\n");
        builder.append("Each alphabet in the dataset is available in 10 different fonts\n\n");
        builder.append("The dimension of each image in pixels is 28 x 28\n\n");

        builder.append("Total size of the dataset is 100");
        return builder;
    }
}
