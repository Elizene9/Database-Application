package ucf.assignments;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class FrontPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create new scene
        var scene = new Scene(new Pane());
        ChangeScenes.stageScene(scene);

        // Load front page of application
        ChangeScenes.Change(ViewScenes.FrontPage);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
