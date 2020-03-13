package Controllers.Learning.MCQ.Controller;

import Controllers.BaseController;
import Utils.PathList;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ModelController extends BaseController {

    @FXML
    private JFXButton question1, question2, question3, question4, question5, question6, question7, question8, question9, question10;

    private JFXButton[] buttons = new JFXButton[10];

    @FXML
    private AnchorPane anchorPane;
    private static ModelController instance;

    public ModelController() {
        instance = this;
    }

    static ModelController getInstance() {
        return instance;
    }

    private void initializeButtons(JFXButton[] buttons) {
        buttons[0] = question1;
        buttons[1] = question2;
        buttons[2] = question3;
        buttons[3] = question4;
        buttons[4] = question5;
        buttons[5] = question6;
        buttons[6] = question7;
        buttons[7] = question8;
        buttons[8] = question9;
        buttons[9] = question10;
    }

    private void buttonAction(JFXButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            int j = i;
            buttons[j].setOnAction(event -> {
                MCQController.getInstance().readQuestionAnswers(j);
                MCQController.getInstance().setQuestionNumber(j);
                MCQController.getInstance().setQuestionAnswerID(j);
            });
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeButtons(buttons);
        buttonAction(buttons);
        this.create(PathList.getMultipleChoiceQuestionsFxml());
    }

    public void setNode(Node node) {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(node);
    }

    private void create(String fxmlPath) {
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(fxmlPath));
            setNode(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void setColorGreen(int num) {
        String style = "-fx-background-color: #29a827; -fx-text-fill: white;";
        for (int i = 0; i < 10; i++) {
            if (Objects.equals(num, i)) {
                buttons[i].setStyle(style);
            }
        }
    }


    void setColorRed(int num) {
        String style = "-fx-background-color: #eb3b3b; -fx-text-fill: white;";
        for (int i = 0; i < 10; i++) {
            if (Objects.equals(num, i)) {
                buttons[i].setStyle(style);
            }
        }
    }

}
