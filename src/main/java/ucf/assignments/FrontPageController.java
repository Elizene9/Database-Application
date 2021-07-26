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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;

// This class allows all other pages of app to be loaded from buttons on front page
public class FrontPageController {

    // Lists and sorted lists to store items
    public static List<String> names = new ArrayList<>();
    public static List<Double> values = new ArrayList<>();
    public static List<String> serials = new ArrayList<>();
    public static List<String> namesSorted = new ArrayList<>();
    public static List<String> serialsSorted = new ArrayList<>();
    public static List<Double> valuesSorted = new ArrayList<>();
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
    Document doc;


    @FXML
    public ListView<String> ListViewName = new ListView<>();
    @FXML
    public ListView<String> ListViewSerial = new ListView<>();
    @FXML
    public ListView<Double> ListViewValue = new ListView<>();
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
        ListViewValue.getItems().addAll((valuesSorted));
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
        ListViewValue.getItems().addAll((valuesSorted));
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

            for (int i = 0; i < namesSorted.size(); i++)
                writer.write(valuesSorted.get(i) + "\t" + serialsSorted.get(i) + "\t" + namesSorted.get(i) + "\n");

            writer.close();
        }

        // Out to html file if user chooses .htm extension
        else {

            // Creates table format with 3 columns
            writer.write("<html><body><table border = \"2\"><tr><th>Value</th> <th>Serial Number</th><th>Name</th> </tr>");

            for (int x = 0; x < namesSorted.size(); x++) {

                writer.write("<tr> <td>" + valuesSorted.get(x) + ";" + "</td>");
                writer.write("<td>" + serialsSorted.get(x) +";" + "</td>");
                writer.write("<td>" + namesSorted.get(x) +";" + "</td> </tr>");
            }

            writer.write("</table> </body> </html>");
            writer.close();
        }

        ext = "";
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
            String tags = "";

            // Read all file data for html file

            if (extension.equals(".htm")) {
                while (input.hasNext()) {

                    tags += input.next();

                }
                doc = Jsoup.parse(tags);
                String myText = doc.text();

                char[] newText = new char[myText.length()];
                newText = myText.toCharArray();

                valuesSorted.clear();
                values.clear();
                namesSorted.clear();
                names.clear();
                serials.clear();
                serialsSorted.clear();
                int counter = 0;
                String mySerial = "", myNames = "", myValue = "";
                int h = 21;

                // Adds html elements to user inventory

                System.out.println(newText.length);
                while (h < newText.length) {
                    mySerial = "";
                    myNames = "";
                    myValue = "";


                    while (newText[h] != ';') {
                        myValue += newText[h];

                        if (h == newText.length - 1)
                            break;
                            h++;
                    }
                    h++;
                    valuesSorted.add(counter, Double.parseDouble(myValue));
                    values.add(counter, Double.parseDouble(myValue));

                    while (newText[h] != ';') {
                        mySerial += newText[h];
                        h++;
                    }
                    h++;
                    serialsSorted.add(counter, mySerial);
                    serials.add(counter, mySerial);

                    while (newText[h] != ';') {
                        myNames += newText[h];
                        h++;
                    }

                    h++;

                    namesSorted.add(counter, myNames);
                    names.add(counter, myNames);
                    if (h == newText.length)
                        break;

                    counter++;
                }
            } else if (extension.equals(".txt")) {

                for (int i = 0; i < 4; i++)
                    input.next();

                int myCount = 0;
                valuesSorted.clear();
                values.clear();
                namesSorted.clear();
                names.clear();
                serials.clear();
                serialsSorted.clear();
                String serial, name, value;
                while (input.hasNext()) {

                    value = input.next();
                    serial = input.next();
                    name = input.next();

                    values.add(Double.parseDouble(value));
                    valuesSorted.add(Double.parseDouble(value));
                    serials.add(serial);
                    serialsSorted.add(serial);
                    names.add(name);
                    namesSorted.add(name);
                }

            }
        }
    }

    // Shows all items and removes them upon a click
    public void ShowListItems() {
        AllItems.getItems().clear();
        SortName();
        remove.clear();
        for (int i = 0; i < namesSorted.size(); i++) {
            remove.add(namesSorted.get(i) + ": " + serialsSorted.get(i));
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
                            namesSorted.remove(h);
                            serialsSorted.remove(h);
                            valuesSorted.remove(h);
                        }

                        if (element.getText().equals(names.get(h) + ": " + serials.get(h))) {

                            k++;
                            names.remove(h);
                            serials.remove(h);
                            values.remove(h);
                        }

                        if (k == 2) {
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
        SearchBar.setText("SEARCH by Name or Serial Number (Case Sensitive)");
    }

    public void Searched() {
        ItemsFound.getItems().clear();
        for (int i = 0; i < namesSorted.size(); i++) {
            MenuItem item = new MenuItem();
            MenuItem element = new MenuItem();

            if (namesSorted.get(i).contains(SearchBar.getText())) {

                item.setText("Name: " + namesSorted.get(i));
                ItemsFound.getItems().add(item);
            }

            if (serialsSorted.get(i).contains(SearchBar.getText())) {
                element.setText("Serial: " + serialsSorted.get(i));
                ItemsFound.getItems().add(element);
            }
        }
    }
}