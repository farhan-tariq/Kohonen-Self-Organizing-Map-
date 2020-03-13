package Controllers.Learning.MCQ.Controller;

import Controllers.BaseController;
import Controllers.Learning.MCQ.Answers;
import Controllers.Learning.MCQ.Questions;
import Utils.PathList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;

import static Utils.JavaFXUtility.loadFxmlAndStage;

public class MCQController extends BaseController {

    private ArrayList<ArrayList<String>> questionAndOptions;
    private ArrayList<ArrayList<String>> questionAndAnswers;
    private static int questionID;
    private HashMap<Integer, String> hashMap;
    private static String check;
    private static MCQController instance;
    private ToggleGroup tgGroup = new ToggleGroup();

    @FXML
    private JFXRadioButton radioButton1, radioButton2, radioButton3, radioButton4;

    private JFXRadioButton[] radioButtons = new JFXRadioButton[4];

    @FXML
    private JFXButton nextButton, finishButton;

    @FXML
    private Text questionNoText, questionText;

    public MCQController() {
        instance = this;
    }

    static MCQController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initRadioButtons();
        nextButtonAction();
        setToggleGroup();

        questionAndOptions = Questions.setQuiz();
        questionAndAnswers = Answers.getAnswers();

        hashMap = new HashMap<>();
        readQuestionAnswers(questionID);
    }


    void readQuestionAnswers(int i) {
        questionText.setText(questionAndOptions.get(i).get(0));

        radioButtons[0].setText("A)  ".concat(questionAndOptions.get(i).get(1)));
        radioButtons[1].setText("B)  ".concat(questionAndOptions.get(i).get(2)));
        radioButtons[2].setText("C)  ".concat(questionAndOptions.get(i).get(3)));
        radioButtons[3].setText("D)  ".concat(questionAndOptions.get(i).get(4)));

        String btn = getSelectedToggle(i, radioButtons);

        for (JFXRadioButton radioButton : radioButtons) {
            if (Objects.equals(btn, radioButton.getText())) {
                radioButton.setSelected(true);
            } else {
                radioButton.setSelected(false);
            }
        }
    }


    private String getSelectedToggle(int questionAnswer, JFXRadioButton[] radioButtons) {
        String temp = hashMap.get(questionAnswer);
        String selection = null;
        for (JFXRadioButton radioButton : radioButtons) {
            if (Objects.equals(temp, radioButton.getText().substring(4))) {
                selection = radioButton.getText();
            }
        }
        return selection;
    }


    void setQuestionAnswerID(int questionID) {
        MCQController.questionID = questionID;
    }

    void setQuestionNumber(int questionNumber) {
        int questionNum = questionNumber;
        questionNum++;
        questionNoText.setText("Q." + questionNum + " |");
    }

    public String getSelection() {
        return check;
    }

    public void nextButtonAction() {
        nextButton.setOnAction(event -> {
            if (questionID < 10) {
                hashMap.put(questionID, getSelection());
                if (Objects.equals(getSelection(), null)) {
                    ModelController.getInstance().setColorRed(questionID);
                } else {
                    ModelController.getInstance().setColorGreen(questionID);
                }

                if (Objects.equals(questionID, 9)) {
                    setQuestionNumber(questionID);
                    readQuestionAnswers(questionID);
                    questionID++;
                } else {
                    questionID++;
                    check = null;
                    setQuestionNumber(questionID);
                    readQuestionAnswers(questionID);
                }
            } else {
                setDialogBox();
            }
        });
    }

    private void setDialogBox() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        String submit = " Do you want submit?";
        alert.setContentText(submit);

        Optional<ButtonType> action = alert.showAndWait();

        if ((action.isPresent()) && (action.get() == javafx.scene.control.ButtonType.OK)) {
            finish();
            System.gc();
        } else {
            questionID--;
        }
    }

    private void finish() {
        finishButton.setOnAction(event -> {
            questionID = 0;
            finishButton.getScene().getWindow().hide();
            loadFxmlAndStage(getClass().getResource(PathList.getMCQResultsFxml()), "Result", null);
        });
    }

    int getResult() {
        int questionListSize = 10;
        int count = 0;
        for (int i = 0; i < questionListSize; i++)
            if (questionAndAnswers.get(i).get(1).equals(hashMap.get(i))) {
                count++;
                count++;
            }
        return count;
    }


    private void initRadioButtons() {
        radioButtons[0] = radioButton1;
        radioButtons[1] = radioButton2;
        radioButtons[2] = radioButton3;
        radioButtons[3] = radioButton4;
    }


    private void setToggleGroup() {
        for (JFXRadioButton button : radioButtons) {
            button.setToggleGroup(tgGroup);
        }
    }

    @FXML
    public void groupAction() {
        for (JFXRadioButton radioButton : radioButtons) {
            if (radioButton.isSelected()) {
                check = radioButton.getText().substring(4);
            }
        }
    }
}


