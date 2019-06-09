package Utils;


import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class JavaFXUtility {
    /**
     * Method to load FXML and show stage
     *
     * @param url         location of the fxml
     * @param title       title of the stage
     * @param parentStage parent Stage if any
     */
    public static void loadFxmlAndStage(URL url, String title, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(url);
            Parent parent = loader.load();
            Stage stage;
            if (parentStage != null) stage = parentStage;
            else stage = new Stage(StageStyle.DECORATED);

            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage() + " at :" + e.getClass());
        }
    }

    /**
     * Method to load the stage
     *
     * @param loader FXML Loader
     */
    static void loadAndShowStage(FXMLLoader loader) {
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(parent));
        stage.show();
    }

    /**
     * Method to close the stage on press of a button
     *
     * @param button JFXButton
     */
    public static void closeWindow(JFXButton button) {
        ((Stage) (button.getScene().getWindow())).close();
    }

    /**
     * Method to sleep the thread
     */
    public static void sleepThread() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
    }

    /**
     * Method to compare two strings
     *
     * @param s1 string 1
     * @param s2 stirng 2
     * @return boolean true or false
     */
    public static boolean compareString(String s1, String s2) {
        return s1.toLowerCase().contains(s2.toLowerCase());
    }

}
