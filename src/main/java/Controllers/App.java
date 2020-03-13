package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(App.class.getResource(Utils.PathList.getHomeScreenMenuFxml()));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 687, 426));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}