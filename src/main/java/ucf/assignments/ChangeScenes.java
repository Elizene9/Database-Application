/*
 * UCF COP 3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Charlene Creighton
 */

package ucf.assignments;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

// This class switches windows of the application when its methods are called
public class ChangeScenes {

    // Create scene and parent variables
    public static Scene scene;
    public static Parent root;

    // Scene change here
    public static void stageScene(Scene scene) {
        ChangeScenes.scene = scene;
    }

    // Open fxml file passed in to this method
    public static void Change(ViewScenes view) throws IOException {

        root = FXMLLoader.load(Objects.requireNonNull(ChangeScenes.class.getResource(view.getFile())));
        scene.setRoot(root);
    }

}
