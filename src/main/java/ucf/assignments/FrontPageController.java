package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.*;

// This class allows all other pages of app to be loaded from buttons on front page
public class FrontPageController {

    // Lists and sorted lists to store items
    public static List<String> names = new ArrayList<>();
    public static List<String> values = new ArrayList<>();
    public static List<String> serials = new ArrayList<>();
    public static List<String> namesSorted = new ArrayList<>();
    public static List<String> serialsSorted = new ArrayList<>();
    public static List<String> valuesSorted = new ArrayList<>();

    // FXML controls to be accessed
    @FXML
    public SplitMenuButton OptionsMenu = new SplitMenuButton();
    @FXML
    public MenuItem Add = new MenuItem();
    @FXML
    public MenuItem Remove = new MenuItem();
    @FXML
    public MenuItem Edit = new MenuItem();
    @FXML
    public MenuItem SortByValue = new MenuItem();
    @FXML
    public MenuItem SortByName = new MenuItem();
    @FXML
    public MenuItem SortBySerial = new MenuItem();
    @FXML
    public MenuItem Save = new MenuItem();
    @FXML
    public MenuItem Load = new MenuItem();

    @FXML
    public ListView<String> ListViewName = new ListView<>();
    @FXML
    public ListView<String> ListViewSerial = new ListView<>();
    @FXML
    public ListView<String> ListViewValue = new ListView<>();

    // Exit application when exit button is pressed
    public void ExitPressed() {
        System.exit(0);
    }

    // Displays unsorted list in order of the time items were added
    public void DisplayPressed() {
        ListViewValue.getItems().clear();
        ListViewSerial.getItems().clear();
        ListViewName.getItems().clear();
        ListViewName.getItems().addAll(names);
        ListViewSerial.getItems().addAll(serials);
        ListViewValue.getItems().addAll(values);
    }

    // Shows user how to use app on click
    public void HelpPressed()  {

    }

    // Load new add item window when pressed
    public void AddItemPressed() throws IOException {
        new MultipleScenes("/ucf.assignments.AddItem.fxml", ViewScenes.AddItem);
    }

    // Load new remove item window when pressed
    public void RemoveItemPressed() {
        
    }

    // Load new edit item window when pressed
    public void EditItemPressed() {
    }

    // Sorts list by value and prints it
    public void SortValuePressed() {
        Collections.sort(valuesSorted);
        for (int x = 0; x < values.size(); x++) {

            for (int y = 0; y < values.size(); y++) {
                if (values.get(y).equals(valuesSorted.get(x))) {

                    namesSorted.set(x, names.get(y));
                    serialsSorted.set(x, serials.get(y));
                }
            }
        }
        ListViewValue.getItems().clear();
        ListViewSerial.getItems().clear();
        ListViewName.getItems().clear();
        ListViewName.getItems().addAll(namesSorted);
        ListViewSerial.getItems().addAll(serialsSorted);
        ListViewValue.getItems().addAll(valuesSorted);
    }

    // Sorts list by name and prints
    public void SortName() {

        Collections.sort(namesSorted);
        for (int x = 0; x < names.size(); x++) {

            for (int y = 0; y < names.size(); y++) {
                if (names.get(y).equals(namesSorted.get(x))) {

                    valuesSorted.set(x, values.get(y));
                    serialsSorted.set(x, serials.get(y));
                }
            }
        }
        ListViewValue.getItems().clear();
        ListViewSerial.getItems().clear();
        ListViewName.getItems().clear();
        ListViewName.getItems().addAll(namesSorted);
        ListViewSerial.getItems().addAll(serialsSorted);
        ListViewValue.getItems().addAll(valuesSorted);
    }

    // Sorts list by serial number and prints
    public void SortSerial() {
        Collections.sort(serialsSorted);
        for (int x = 0; x < serials.size(); x++) {

            for (int y = 0; y < serials.size(); y++) {
                if (serials.get(y).equals(serialsSorted.get(x))) {

                    valuesSorted.set(x, values.get(y));
                    namesSorted.set(x, names.get(y));
                }
            }
        }
        ListViewValue.getItems().clear();
        ListViewSerial.getItems().clear();
        ListViewName.getItems().clear();
        ListViewName.getItems().addAll(namesSorted);
        ListViewSerial.getItems().addAll(serialsSorted);
        ListViewValue.getItems().addAll(valuesSorted);
    }

    // Opens save window
    public void SavePressed() {
    }

    // Opens load window
    public void LoadPressed() {
    }
}