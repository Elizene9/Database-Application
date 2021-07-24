package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

    Stage stage = new Stage();
    String fileName;
    FileChooser dir = new FileChooser();
    PrintWriter writer;
    File userFile;

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

        int[] index = new int[valuesSorted.size()];

        Collections.sort(valuesSorted);
        for (int i = 0; i < valuesSorted.size(); i++) {

            for (int p = 0; p < valuesSorted.size(); p++) {

                if (valuesSorted.get(i).equals(values.get(p)))
                    index[i] = p;

            }
        }
        for (int x = 0; x < values.size(); x++) {

            namesSorted.set(x, names.get(index[x]));
            serialsSorted.set(x, serials.get(index[x]));
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

        int[] index = new int[namesSorted.size()];

        Collections.sort(namesSorted);
        for (int i = 0; i < namesSorted.size(); i++) {

            for (int p = 0; p < namesSorted.size(); p++) {

                if (namesSorted.get(i).equals(names.get(p)))
                    index[i] = p;

            }
        }
        for (int x = 0; x < names.size(); x++) {
            valuesSorted.set(x, values.get(index[x]));
            serialsSorted.set(x, serials.get(index[x]));
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

        int[] index = new int[serialsSorted.size()];

        Collections.sort(serialsSorted);
        for (int i = 0; i < serialsSorted.size(); i++) {

            for (int p = 0; p < serialsSorted.size(); p++) {

                if (serialsSorted.get(i).equals(serials.get(p)))
                    index[i] = p;

            }
        }
        for (int x = 0; x < serials.size(); x++) {

            namesSorted.set(x, names.get(index[x]));
            valuesSorted.set(x, values.get(index[x]));
        }

        ListViewName.getItems().addAll(namesSorted);
        ListViewSerial.getItems().addAll(serialsSorted);
        ListViewValue.getItems().addAll(valuesSorted);
    }

    // Opens save window
    public void SavePressed() throws IOException {

        // Add htm and txt extensions to file extension selections
        dir.getExtensionFilters().removeAll();
        dir.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Html Files", "*.htm"));

        // Show user's computer file directories for them to save file to
        userFile = dir.showSaveDialog(stage);
        fileName = userFile.getName();
        String ext = "";
        char[] name = fileName.toCharArray();

        // Create writer to output to file
        writer = new PrintWriter(userFile);

        // Get file extension name
        for (int x = fileName.length() - 1; x >= 0; x--) {

            if (name[x] == '.')
                break;

            ext += name[x];
        }

        // txt is txt backwards so if ext name is txt out to txt file
        if (ext.equals("txt")) {
            writer.println("Value\tSerial Number\tName\n");

            for (int i = 0; i < namesSorted.size(); i++)
                writer.write(valuesSorted.get(i) + "\t" + serialsSorted.get(i) + "\t" + namesSorted.get(i) + "\n");

            writer.close();
        }

        // Out to html file if user chooses .htm extension
        else {

            // Creates table format with 3 columns
            writer.write("<html><body><table border = \"2\"><tr><th>Value</th> <th>Serial Number</th><th>Name</th> </tr>");

            for (int x = 0; x < namesSorted.size(); x++) {

                writer.write("<tr> <td>" + valuesSorted.get(x) + "</td>");
                writer.write("<td>" + serialsSorted.get(x) + "</td>");
                writer.write("<td>" + namesSorted.get(x) + "</td> </tr>");
            }

            writer.write("</table> </body> </html>");
            writer.close();
        }

        ext = "";
    }

    // Opens load window
    public void LoadPressed() throws IOException {
        System.out.println(fileName);

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