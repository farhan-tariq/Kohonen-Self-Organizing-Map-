package Controllers.Learning.TutorialControllers;

import Controllers.BaseController;
import Utils.PathList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import static Utils.JavaFXUtility.closeWindow;
import static Utils.JavaFXUtility.loadFxmlAndStage;
import static javafx.scene.layout.BorderPane.setMargin;

public abstract class LearningToolBase extends BaseController {
    @FXML
    JFXButton backButton, nextButton, homeButton, somButton,
              hebbianButton, normalizedHebbianButton, distanceMetricButton,
              neuronButton, learningButton, visualizingSOMButton;

    @FXML
    VBox drawer;

    @FXML
    JFXHamburger hamburger;

    @FXML
    AnchorPane menuPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void homeButtonAction() {
        homeButton.setOnAction((ActionEvent event) -> {
            loadFxmlAndStage(getClass().getResource(PathList.getHomeScreenMenuFxml()), "Home Screen", null);
            closeWindow(homeButton);
        });
    }

    void nextButtonAction(String path, String title) {
        nextButton.setOnAction(event -> {
            loadFxmlAndStage(getClass().getResource(path), title, null);
            closeWindow(nextButton);
        });
    }

    void backButtonAction(String path, String title) {
        backButton.setOnAction(event -> {
            loadFxmlAndStage(getClass().getResource(path), title, null);
            closeWindow(backButton);
        });
    }

    private void somButtonAction() {
        somButton.setOnAction(event -> {
            loadFxmlAndStage(getClass().getResource(PathList.getSelfOrganizingMapFxml()), "Self-Organizing Map", null);
            closeWindow(somButton);
        });
    }


    private void hebbianButtonAction() {
        hebbianButton.setOnAction(event -> {
            loadFxmlAndStage(getClass().getResource(PathList.getHebbianFxml()), "Hebbian Learning", null);
            closeWindow(hebbianButton);
        });
    }


    private void hebbianLearningButtonAction() {
        normalizedHebbianButton.setOnAction(event -> {
            loadFxmlAndStage(getClass().getResource(PathList.getNormalizedHebbianFxml()), "Normalized Hebbian Learning", null);
            closeWindow(normalizedHebbianButton);
        });
    }


    private void neuronButtonAction() {
        neuronButton.setOnAction(event -> {
            loadFxmlAndStage(getClass().getResource(PathList.getNeuronFxml()), "Neuron", null);
            closeWindow(neuronButton);
        });
    }


    private void distanceMetricButtonAction() {
        distanceMetricButton.setOnAction(event -> {
            loadFxmlAndStage(getClass().getResource(PathList.getDistanceMetricFxml()), "Distance Metrics", null);
            closeWindow(distanceMetricButton);
        });
    }

    private void learningButtonAction() {
        learningButton.setOnAction(event -> {
            loadFxmlAndStage(getClass().getResource(PathList.getLearningUrl()), "Neuron Learning", null);
            closeWindow(learningButton);
        });
    }

    private void visualizingSOMButtonAction() {
        visualizingSOMButton.setOnAction(event -> {
            loadFxmlAndStage(getClass().getResource(PathList.getVisualizingSomFxml()), "Visualizing Self-Organizing Map", null);
            closeWindow(visualizingSOMButton);
        });
    }


    private void clearChildren() {
        menuPane.getChildren().clear();
    }


    private void hamburgerAction() {
        final TranslateTransition translateLeftAnchor = new TranslateTransition(Duration.millis(200), drawer);

        HamburgerBasicCloseTransition transition = new HamburgerBasicCloseTransition(hamburger);
        transition.setRate(-1);

        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            if (transition.getRate() == -1) {
                menuPane.getChildren().add(drawer);

                transition.setRate(transition.getRate() * -1);
                transition.play();
                translateLeftAnchor.setFromX(-170);
                translateLeftAnchor.setToX(0);
                translateLeftAnchor.play();

            } else {
                transition.setRate(transition.getRate() * -1);
                transition.play();
                translateLeftAnchor.setFromX(0);
                translateLeftAnchor.setToX(-170);
                translateLeftAnchor.play();

                menuPane.getChildren().clear();
            }
        });

        drawer.translateXProperty().addListener(e -> setMargin(drawer, new Insets(0, drawer.translateXProperty().doubleValue(), 0, 0)));
    }

    void initButtons() {
        learningButtonAction();
        distanceMetricButtonAction();
        somButtonAction();
        neuronButtonAction();
        hebbianButtonAction();
        hebbianLearningButtonAction();
        hamburgerAction();
        visualizingSOMButtonAction();
        homeButtonAction();
        clearChildren();
    }
}
