package ucf.assignments;

import javafx.collections.ObservableList;
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
    public static List<String> remove = new ArrayList<>();
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
    @FXML public SplitMenuButton AllItems = new SplitMenuButton();

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

    // Shows user how to use app on click
    public void HelpPressed()  {

    }

    // Load new add item window when pressed
    public void AddItemPressed() throws IOException {
        new MultipleScenes(ViewScenes.AddItem);

    }

    // Load new edit item window when pressed
    public void EditItemPressed() throws IOException {
        new MultipleScenes((ViewScenes.EditItem));
    }

    // Sorts list by value and prints it
    public void SortValuePressed() {
        ListViewValue.getItems().clear();
        ListViewSerial.getItems().clear();
        ListViewName.getItems().clear();

        Collections.sort(valuesSorted);
        for (int x = 0; x < values.size(); x++) {
            for (int y = 0; y < values.size(); y++) {
                if (values.get(y).equals(valuesSorted.get(x)) && !serials.get(y).equals(serials.get(x))) {
                    namesSorted.set(x, names.get(y));
                    serialsSorted.set(x, serials.get(y));
                }
            }
        }
        ListViewName.getItems().addAll(namesSorted);
        ListViewSerial.getItems().addAll(serialsSorted);
        ListViewValue.getItems().addAll(valuesSorted);
    }

    // Sorts list by name and prints
    public void SortName() {
        ListViewValue.getItems().clear();
        ListViewSerial.getItems().clear();
        ListViewName.getItems().clear();
        Collections.sort(namesSorted);
        for (int x = 0; x < names.size(); x++) {

            for (int y = 0; y < names.size(); y++) {
                if (names.get(y).equals(namesSorted.get(x)) && !serials.get(y).equals(serials.get(x))) {

                    valuesSorted.set(x, values.get(y));
                    serialsSorted.set(x, serials.get(y));
                }
            }
        }

        ListViewName.getItems().addAll(namesSorted);
        ListViewSerial.getItems().addAll(serialsSorted);
        ListViewValue.getItems().addAll(valuesSorted);
    }

    // Sorts list by serial number and prints
    public void SortSerial() {
        ListViewValue.getItems().clear();
        ListViewSerial.getItems().clear();
        ListViewName.getItems().clear();

        Collections.sort(serialsSorted);
        for (int x = 0; x < serials.size(); x++) {
            for (int y = 0; y < serials.size(); y++) {
                if (serials.get(y).equals(serialsSorted.get(x))) {
                    valuesSorted.set(x, values.get(y));
                    namesSorted.set(x, names.get(y));
                }
            }
        }

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

    // Shows all items and removes them upon a click
    public void ShowListItems() {
        AllItems.getItems().clear();
        remove.clear();
        for (int z = 0; z < namesSorted.size(); z++) {

            MenuItem element = new MenuItem();
            remove.add(namesSorted.get(z) + ": " + serialsSorted.get(z));
            element.setText(remove.get(z));
            element.setOnAction((event -> {
                for (int j = 0; j < namesSorted.size(); j++) {
                    if (element.getText().equals(names.get(j) + ": " + serials.get(j)) && element.getText().equals(namesSorted.get(j) + ": " + serialsSorted.get(j))) {
                        names.remove(j);
                        serials.remove(j);
                        values.remove(j);
                        namesSorted.remove(j);
                        valuesSorted.remove(j);
                        serialsSorted.remove(j);
                    }

                    else if (element.getText().equals(namesSorted.get(j) + ": " + serialsSorted.get(j)) && !element.getText().equals(names.get(j) + ": " + serials.get(j))) {
                        namesSorted.remove(j);
                        valuesSorted.remove(j);
                        serialsSorted.remove(j);
                    }

                    else if (!element.getText().equals(namesSorted.get(j) + ": " + serialsSorted.get(j)) && element.getText().equals(names.get(j) + ": " + serials.get(j))) {
                        names.remove(j);
                        values.remove(j);
                        serials.remove(j);
                    }
                }
                AllItems.getItems().remove(element);
            }));
            AllItems.getItems().add(element);

        }

    }
}