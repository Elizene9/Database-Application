/*
* UCF COP 3330 Summer 2021 Assignment 5 Solution
* Copyright 2021 Charlene Creighton
*/

package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

// This class allows all other pages of app to be loaded from buttons on front page
public class FrontPageController {

    // Lists and sorted lists to store items
    public static List<String> remove = new ArrayList<>();

    // FXML controls to be accessed
    @FXML public SplitMenuButton OptionsMenu = new SplitMenuButton();
    @FXML public SplitMenuButton AllItems = new SplitMenuButton();
    @FXML public SplitMenuButton ItemsFound = new SplitMenuButton();

    // Variables to interact with user's files
    Stage stage = new Stage();
    String fileName;
    FileChooser dir = new FileChooser();
    FileChooser open = new FileChooser();
    FileChooser load = new FileChooser();
    PrintWriter writer;
    File userFile;
    File loadFile;
    File openFile;
    String extension, forward;
    Scanner input;

    @FXML
    public ListView<String> ListViewName = new ListView<>();
    @FXML
    public ListView<String> ListViewSerial = new ListView<>();
    @FXML
    public ListView<BigDecimal> ListViewValue = new ListView<>();
    @FXML
    public TextField SearchBar = new TextField();

    // Exit application when exit button is pressed
    public void ExitPressed() {
        System.exit(0);
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
    @SuppressWarnings("DuplicatedCode")
    public void SortValuePressed() {
        ListViewValue.getItems().clear();
        ListViewSerial.getItems().clear();
        ListViewName.getItems().clear();

        Inventory.sortValue();
        for (int i = 0; i < Inventory.allItems.size(); i++)
            ListViewName.getItems().add(Inventory.allItems.get(i).name);

        for (int i = 0; i < Inventory.allItems.size(); i++)
            ListViewSerial.getItems().add(Inventory.allItems.get(i).Serial);

        for (int i = 0; i < Inventory.allItems.size(); i++)
            ListViewValue.getItems().add(Inventory.allItems.get(i).value);
    }

    // Sorts list by name and prints
    @SuppressWarnings("DuplicatedCode")
    public void SortName() {

        ListViewValue.getItems().clear();
        ListViewSerial.getItems().clear();
        ListViewName.getItems().clear();

        Inventory.sortName();
        for (int i = 0; i < Inventory.allItems.size(); i++)
            ListViewName.getItems().add(Inventory.allItems.get(i).name);

        for (int i = 0; i < Inventory.allItems.size(); i++)
            ListViewSerial.getItems().add(Inventory.allItems.get(i).Serial);

        for (int i = 0; i < Inventory.allItems.size(); i++)
            ListViewValue.getItems().add(Inventory.allItems.get(i).value);
    }

    // Sorts list by serial number and prints
    @SuppressWarnings("DuplicatedCode")
    public void SortSerial() {

        ListViewValue.getItems().clear();
        ListViewSerial.getItems().clear();
        ListViewName.getItems().clear();

        Inventory.sortSerial();
        for (int i = 0; i < Inventory.allItems.size(); i++)
            ListViewName.getItems().add(Inventory.allItems.get(i).name);

        for (int i = 0; i < Inventory.allItems.size(); i++)
            ListViewSerial.getItems().add(Inventory.allItems.get(i).Serial);

        for (int i = 0; i < Inventory.allItems.size(); i++)
            ListViewValue.getItems().add(Inventory.allItems.get(i).value);
    }

    // Opens save window
    public void SavePressed() throws IOException {

        // Add htm and txt extensions to file extension selections
        dir.getExtensionFilters().clear();
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

            for (int i = 0; i < Inventory.allItems.size(); i++)
                writer.write(Inventory.allItems.get(i).value +
                        "\t" + Inventory.allItems.get(i).Serial + "\t" +
                        Inventory.allItems.get(i).name + "\n");

        }

        // Out to html file if user chooses .htm extension
        else {

            // Creates table format with 3 columns
            writer.write("<html><body><table border = \"2\"><tr><th>Value</th> <th>Serial Number</th><th>Name</th> </tr>");

            for (int x = 0; x < Inventory.allItems.size(); x++) {

                writer.write("<tr> <td>" + Inventory.allItems.get(x).value +"</td>");
                writer.write("<td>" + Inventory.allItems.get(x).Serial + "</td>");
                writer.write("<td>" + Inventory.allItems.get(x).name + "</td> </tr>");
            }

            writer.write("</table> </body> </html>");
        }
        writer.close();

    }

    // Opens load window
    public void LoadPressed() throws IOException {
        extension = "";
        forward = "";
        int stop = 0;
        loadFile = load.showOpenDialog(stage);

        if (loadFile.exists()) {
            char[] myFile = loadFile.getName().toCharArray();

            for (int i = loadFile.getName().length() - 1; i >= 0; i--) {

                if (myFile[i] == '.') {
                    stop = i;
                    break;
                }
            }

            for (int j = stop; j < myFile.length; j++)
                extension += myFile[j];

            input = new Scanner(new File(loadFile.getAbsolutePath()));


            if (extension.equals(".txt")) {

                for (int i = 0; i < 4; i++)
                    input.next();

                Inventory.allItems.clear();
                String serial, name, value;
                while (input.hasNext()) {

                    value = input.next();
                    serial = input.next();
                    name = input.next();
                    Items myItem = new Items(name, BigDecimal.valueOf(Double.parseDouble(value)), serial);
                    Inventory.allItems.add(myItem);
                }

            }

        }
    }

    // Shows all items and removes them upon a click
    public void ShowListItems() {
        AllItems.getItems().clear();
        SortName();
        remove.clear();
        for (int i = 0; i < Inventory.allItems.size(); i++) {
            remove.add(Inventory.allItems.get(i).name + ": " + Inventory.allItems.get(i).Serial);
        }

        for (int j = 0; j < remove.size(); j++) {

            MenuItem element = new MenuItem();
            element.setText(remove.get(j));
            element.setOnAction((event -> {
                int k =0;
                for (int h = 0; h < remove.size(); h++) {

                    if (!remove.get(h).isEmpty()) {

                        if (element.getText().equals(remove.get(h))) {
                            k++;
                            Inventory.allItems.remove(h);
                            remove.remove(h);
                            break;
                        }
                    }
                }
            }));
            AllItems.getItems().add(element);
        }

    }

    public void OpenFilePressed() throws IOException {
        openFile = open.showOpenDialog(stage);

        if (openFile.exists())
            Desktop.getDesktop().open(openFile);
    }

    public void PopulateSearch() {
        String search = SearchBar.getText();
        int count;
        ListViewValue.getItems().clear();
        ListViewSerial.getItems().clear();
        ListViewName.getItems().clear();
        ItemsFound.getItems().clear();
        for (int i = 0; i < Inventory.allItems.size(); i++) {
            count = 0;
            MenuItem item = new MenuItem();
            MenuItem element = new MenuItem();

            if (Inventory.allItems.get(i).name.contains(search)) {

                item.setText("Name: " + Inventory.allItems.get(i).name);
                //ItemsFound.getItems().add(item);
                ListViewName.getItems().add(Inventory.allItems.get(i).name);
                ListViewSerial.getItems().add(Inventory.allItems.get(i).Serial);
                ListViewValue.getItems().add(Inventory.allItems.get(i).value);
                count = 1;
            }

            if (Inventory.allItems.get(i).Serial.contains(search)) {
                element.setText("Serial: " + Inventory.allItems.get(i).Serial);
                //ItemsFound.getItems().add(element);

                if (count != 1) {
                    ListViewName.getItems().add(Inventory.allItems.get(i).name);
                    ListViewSerial.getItems().add(Inventory.allItems.get(i).Serial);
                    ListViewValue.getItems().add(Inventory.allItems.get(i).value);
                }
            }
        }
    }
        public void Searched () {
            String search = SearchBar.getText();
            int count;
            ListViewValue.getItems().clear();
            ListViewSerial.getItems().clear();
            ListViewName.getItems().clear();
            ItemsFound.getItems().clear();
            for (int i = 0; i < Inventory.allItems.size(); i++) {
                count = 0;
                MenuItem item = new MenuItem();
                MenuItem element = new MenuItem();

                if (Inventory.allItems.get(i).name.contains(search)) {

                    item.setText("Name: " + Inventory.allItems.get(i).name);
                    //ItemsFound.getItems().add(item);
                    ListViewName.getItems().add(Inventory.allItems.get(i).name);
                    ListViewSerial.getItems().add(Inventory.allItems.get(i).Serial);
                    ListViewValue.getItems().add(Inventory.allItems.get(i).value);
                    count = 1;
                }

                if (Inventory.allItems.get(i).Serial.contains(search)) {
                    element.setText("Serial: " + Inventory.allItems.get(i).Serial);
                    //ItemsFound.getItems().add(element);

                    if (count != 1) {
                        ListViewName.getItems().add(Inventory.allItems.get(i).name);
                        ListViewSerial.getItems().add(Inventory.allItems.get(i).Serial);
                        ListViewValue.getItems().add(Inventory.allItems.get(i).value);
                    }
                }
            }
        }


}