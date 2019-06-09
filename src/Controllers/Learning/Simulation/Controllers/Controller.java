package Controllers.Learning.Simulation.Controllers;

import Controllers.BaseController;
import SimulationNetworkCore.*;
import Controllers.Learning.Simulation.Helper.Helper;
import Controllers.Learning.Simulation.Renderers.LineRenderer;
import Utils.JavaFXUtility;
import Utils.PathList;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static Utils.JavaFXUtility.closeWindow;
import static Utils.JavaFXUtility.loadFxmlAndStage;

public class Controller extends BaseController {


    @FXML
    private VBox weightFormulaPane;
    @FXML
    private AnchorPane linePane, nodeLabelPane, nodePane, radiusCirclePane;

    @FXML
    private JFXButton homeButton,startButton;

    private Layer layer = new Layer(4, 4);
    private Trainer trainer = new Trainer();
    private JavaFXUtility javaFXUtility = new JavaFXUtility();


    private LineRenderer lineRenderer = new LineRenderer();
    private ArrayList<InputNode> inputNodes = new ArrayList<>();
    private ArrayList<OutputNode> outputNodes = new ArrayList<>();
    private ArrayList<BoundLine> lineArrayList = new ArrayList<>();
    private ArrayList<WebView> webViewArrayList = new ArrayList<>();
    private ArrayList<ImageView> imageViewArrayList = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeButtonAction();
        Helper.createNodes(inputNodes, outputNodes);
        Helper.initArrayLists(outputNodes, webViewArrayList, imageViewArrayList);
        Helper.setNodeID(inputNodes, outputNodes);
        Helper.addNodesToPane(nodePane, inputNodes, outputNodes);
        Helper.setNodeIDLabel(inputNodes, outputNodes, nodeLabelPane);
        Helper.generateLines(inputNodes, outputNodes, lineArrayList);
        Helper.setInputVectors(inputNodes);
        Helper.initialize(weightFormulaPane, outputNodes, webViewArrayList);
        startButtonAction();
    }

    private void startLineRenderer(LineRenderer lineRenderer) {
        Thread thread = new Thread(lineRenderer);
        thread.setDaemon(true);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    private void startTrainer(Trainer trainer) {
        Thread thread = new Thread(trainer);
        thread.setDaemon(true);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    private void homeButtonAction() {
        homeButton.setOnAction((ActionEvent event) -> {
            loadFxmlAndStage(getClass().getResource(PathList.getHomeScreenMenuFxml()), "Home Screen", null);
            closeWindow(homeButton);
        });
    }

    private void startButtonAction(){
        startButton.setOnAction(event -> {
            lineRenderer.setStackPane(linePane, lineArrayList);
            startLineRenderer(lineRenderer);
            layer.initializeOutputNodes(outputNodes);
            trainer.setTraining(layer, inputNodes, outputNodes, webViewArrayList, weightFormulaPane, radiusCirclePane);
            startTrainer(trainer);
        });
    }
}


