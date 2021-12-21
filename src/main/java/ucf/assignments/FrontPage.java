/*
 * UCF COP 3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Charlene Creighton
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.lang.*;

// Start up application
public class FrontPage extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    // Load Front Page of Application
    public void start(Stage primaryStage) throws Exception
    {
        setUserAgentStylesheet(STYLESHEET_MODENA);
        // Create new scene
        var scene = new Scene(new Pane());
        ChangeScenes.stageScene(scene);

        // Load front page of application
        ChangeScenes.Change(ViewScenes.FrontPage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
