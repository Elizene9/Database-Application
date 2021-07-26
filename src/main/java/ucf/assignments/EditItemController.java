/*
 * UCF COP 3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Charlene Creighton
 */

package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditItemController {

    // FXML Control accessors
    @FXML public Button DoneButton = new Button();
    @FXML
    public TextField EditName = new TextField();
    @FXML
    public TextField EditValue = new TextField();
    @FXML
    public TextField EditSerial = new TextField();

    @FXML public TextArea ShowItemStatus = new TextArea();
    @FXML
    public SplitMenuButton AllItems = new SplitMenuButton();

    // Counters to hold index of items to be edited
    public int counter = -1;
    public int sort = -1;

    // Temporarily stores list values
    public String temp;

    // Booleans check if user entry data is valid
    boolean serialCheck = true, nameCheck = true, valueCheck = true, serialEdit = true, nameEdit = true, valueEdit = true;
    boolean serialEntry = false, nameEntry = false, valueEntry = false;

    // Value to check value validity
    public Double checkValue;
    public char[] serial;

    // Check for valid name entry
    public void ItemNameEntered() {
        nameCheck = true;
        nameEdit = true;
        nameEntry = true;

        // Check if item name length is valid
        if (EditName.getText().length() < 2 || EditName.getText().length() > 256 && !EditName.getText().isEmpty() && !EditName.getText().equals("Item Name Here")) {
            nameCheck = false;
            ShowItemStatus.setText("Error: Enter a name between 2 and 256 characters inclusive");
        }

        // Check if user entered anything
        if (EditName.getText().equals("New Item Name Here") || EditName.getText().isEmpty()) {
            nameEdit = false;
        }

        // Get rid of any error message once valid value is entered
        if (nameCheck)
            ShowItemStatus.setText("    Press add once you are finished adding the details of your item");
    }

    // Check for valid value entry
    public void ItemValueEntered() {
        valueCheck = true;
        valueEdit = true;
        valueEntry = true;

        // Check if user entered valid numeric value
        try {
            checkValue = Double.parseDouble(EditValue.getText());
        }
        catch (NumberFormatException e) {

            valueCheck = false;
            ShowItemStatus.setText("Invalid item value: Enter a valid numerical value");
        }

        // Check if user entered anything
        if (EditValue.getText().equals("New Item Value Here") || EditValue.getText().isEmpty()) {
            valueEdit = false;
        }

        // Get rid of error messages if user enters valid value
        if (valueCheck)
            ShowItemStatus.setText("    Press add once you are finished adding the details of your item");
    }

    // Check for valid serial entry
    public void ItemSerialEntered() {

        serial = EditSerial.getText().toCharArray();
        serialCheck = true;
        serialEdit = true;
        serialEntry = true;

        // Checks if serial number is already in use
        for (int i = 0; i < FrontPageController.serials.size(); i++) {
            if (EditSerial.getText().equals(FrontPageController.serials.get(i))) {
                serialCheck = false;
                ShowItemStatus.setText("Error: This serial number already exists!");
            }
        }

        // Checks if serial number has characters other than letters and digits
        for (char c : serial) {

            if (!Character.isDigit(c) && !Character.isLetter(c)) {
                serialCheck = false;
                ShowItemStatus.setText("Error: Serial number contains invalid characters!");
            }
        }

        // Checks if serial number is not 10 characters long
        if (EditSerial.getText().length() != 10 && !EditSerial.getText().equals("New Item Serial Number Here") && !EditSerial.getText().isEmpty()) {
            serialCheck = false;
            ShowItemStatus.setText("Error: Serial number must be 10 characters long!");

        }

        // Deletes error if user enters valid serial number
        if (serialCheck)
            ShowItemStatus.setText("    Press add once you are finished adding the details of your item");
    }

    // Exits window when user finishes editing items they want to edit
    public void DonePressed() {
        // Get rid of any error items and exit window
        ShowItemStatus.setText("    Press add once you are finished adding the details of your item");
        FrontPageController.remove.clear();
        for (int z = 0; z < FrontPageController.namesSorted.size(); z++) {

            FrontPageController.remove.add(FrontPageController.names.get(z) + ": " + FrontPageController.serials.get(z));
        }
        Stage stage = (Stage) DoneButton.getScene().getWindow();
        stage.close();

    }

    // Allows item to be edited when pressed
    public void EditPressed() {
        // Add item to list if all booleans are true and valid data has been entered

        if (EditSerial.getText().isEmpty() || EditSerial.getText().equals("New Item Serial Number Here") || !serialEntry)
            serialEdit = false;

        if (EditName.getText().isEmpty() || EditName.getText().equals("New Item Name Here") || !nameEntry)
            nameEdit = false;

        if (EditValue.getText().isEmpty() || EditValue.getText().equals("New Item Value Here") || !valueEntry)
            valueEdit = false;


        if (serialCheck && nameCheck && valueCheck && serialEdit && nameEdit && valueEdit) {
            ShowItemStatus.setText("                    Item Edited");
            FrontPageController.serials.set(counter, EditSerial.getText());
            FrontPageController.serialsSorted.set(sort, EditSerial.getText());
            FrontPageController.names.set(counter, EditName.getText());
            FrontPageController.namesSorted.set(sort, EditName.getText());
            FrontPageController.values.set(counter, Double.parseDouble(EditValue.getText()));
            FrontPageController.valuesSorted.set(sort, Double.parseDouble(EditValue.getText()));
        }

        else if (serialCheck && nameCheck && valueCheck && serialEdit && nameEdit && !valueEdit) {
            ShowItemStatus.setText("                    Item Edited");
            FrontPageController.serials.set(counter, EditSerial.getText());
            FrontPageController.serialsSorted.set(sort, EditSerial.getText());
            FrontPageController.names.set(counter, EditName.getText());
            FrontPageController.namesSorted.set(sort, EditName.getText());
        }

        else if (serialCheck && nameCheck && valueCheck && serialEdit && !nameEdit && !valueEdit) {
            ShowItemStatus.setText("                    Item Edited");
            FrontPageController.serials.set(counter, EditSerial.getText());
            FrontPageController.serialsSorted.set(sort, EditSerial.getText());
        }

        else if (serialCheck && nameCheck && valueCheck && !serialEdit && !nameEdit && !valueEdit) {
            ShowItemStatus.setText("                    No Change");
        }

        else if (serialCheck && nameCheck && valueCheck && !serialEdit && nameEdit && valueEdit) {
            ShowItemStatus.setText("                    Item Edited");
            FrontPageController.values.set(counter, Double.parseDouble(EditValue.getText()));
            FrontPageController.valuesSorted.set(sort, Double.parseDouble(EditValue.getText()));
            FrontPageController.names.set(counter, EditName.getText());
            FrontPageController.namesSorted.set(sort, EditName.getText());
        }

        else if (serialCheck && nameCheck && valueCheck && !serialEdit && nameEdit && !valueEdit) {
            ShowItemStatus.setText("                    Item Edited");
            FrontPageController.names.set(counter, EditName.getText());
            FrontPageController.namesSorted.set(sort, EditName.getText());
        }

        else if (serialCheck && nameCheck && valueCheck && serialEdit && !nameEdit && valueEdit) {
            ShowItemStatus.setText("                    Item Edited");
            FrontPageController.serials.set(counter, EditSerial.getText());
            FrontPageController.serialsSorted.set(sort, EditSerial.getText());
            FrontPageController.values.set(counter, Double.parseDouble(EditValue.getText()));
            FrontPageController.valuesSorted.set(sort, Double.parseDouble(EditValue.getText()));
        }
        else if (serialCheck && nameCheck && valueCheck && !serialEdit && !nameEdit && valueEdit) {
            ShowItemStatus.setText("                    Item Edited");
            FrontPageController.values.set(counter, Double.parseDouble(EditValue.getText()));
            FrontPageController.valuesSorted.set(sort, Double.parseDouble(EditValue.getText()));
        }

        // Display error message if user tries to add item with invalid input
        if (!serialCheck || !nameCheck || !valueCheck){
            ShowItemStatus.setText("ERROR: ITEM NOT EDITED: Check input Values");
            EditName.setText("New Item Name Here");
            EditValue.setText("New Item Value Here");
            EditSerial.setText("New Item Serial Number Here");
        }

        serialEntry = false;
        nameEntry = false;
        valueEntry = false;
    }

    // Displays dropdown items
    public void ViewAllItems() {
        AllItems.getItems().clear();
        FrontPageController.remove.clear();
        for (int x = 0; x < FrontPageController.namesSorted.size(); x++) {

            MenuItem item = new MenuItem();
            FrontPageController.remove.add(FrontPageController.namesSorted.get(x) + ": " + FrontPageController.serialsSorted.get(x));
            item.setText(FrontPageController.remove.get(x));
            item.setOnAction((event -> {
                for (int t = 0; t < FrontPageController.namesSorted.size(); t++) {
                    temp = FrontPageController.namesSorted.get(t) + ": " + FrontPageController.serialsSorted.get(t);
                    if (item.getText().equals(FrontPageController.remove.get(t)) && item.getText().equals(temp)) {
                        counter = t;
                        sort = t;
                    }

                    else if (item.getText().equals(FrontPageController.remove.get(t)) && !item.getText().equals(temp)) {
                        counter = t;
                    }

                    else if (!item.getText().equals(FrontPageController.remove.get(t)) && item.getText().equals(temp)) {
                        sort = t;
                    }
                }
            }));
            AllItems.getItems().add(item);

        }
    }

    //Ensures dropdown items are properly displayed to be edited
    public void SetItems() {
        AllItems.getItems().clear();
        FrontPageController.remove.clear();
        for (int x = 0; x < FrontPageController.namesSorted.size(); x++) {

            MenuItem item = new MenuItem();
            FrontPageController.remove.add(FrontPageController.namesSorted.get(x) + ": " + FrontPageController.serialsSorted.get(x));
            item.setText(FrontPageController.remove.get(x));
            item.setOnAction((event -> {
                for (int t = 0; t < FrontPageController.namesSorted.size(); t++) {
                    temp = FrontPageController.namesSorted.get(t) + ": " + FrontPageController.serialsSorted.get(t);
                    if (item.getText().equals(FrontPageController.remove.get(t)) && item.getText().equals(temp)) {
                        counter = t;
                        sort = t;
                    }

                    else if (item.getText().equals(FrontPageController.remove.get(t)) && !item.getText().equals(temp)) {
                        counter = t;
                    }

                    else if (!item.getText().equals(FrontPageController.remove.get(t)) && item.getText().equals(temp)) {
                        sort = t;
                    }
                }
            }));
            AllItems.getItems().add(item);

        }
    }
}
