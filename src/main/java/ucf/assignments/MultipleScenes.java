package ucf.assignments;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

// This class loads another scene simultaneously to another
public class MultipleScenes {

    // Gets filename from another class and loads that file on a "sub stage"
    MultipleScenes(String file, ViewScenes name) throws IOException {

        Stage sub = new Stage();
        Parent root = FXMLLoader.load(ChangeScenes.class.getResource(name.getFile()));
        Scene scene = new Scene(root);
        sub.setScene(scene);
        sub.show();
    }
}