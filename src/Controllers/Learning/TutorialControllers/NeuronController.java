package Controllers.Learning.TutorialControllers;

import Utils.ImageUtility;
import Utils.PathList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class NeuronController extends LearningToolBase {

    @FXML
    private ImageView neuronImageView,formulaImageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initButtons();
        backButtonAction(PathList.getIntroPageFxml(), "Intro");
        nextButtonAction(PathList.getLearningUrl(), "Neuron Learning");
        addImages();
    }

    private void addImages() {
        File file = new File(PathList.getNeuronFormatImageURL());
        File file2 = new File(PathList.getNeuronModel_ImageURL());
        Image image = ImageUtility.convertFileToJavaFxImages(file);
        Image image2 = ImageUtility.convertFileToJavaFxImages(file2);

        neuronImageView.setImage(image2);
        formulaImageView.setImage(image);

    }
}
