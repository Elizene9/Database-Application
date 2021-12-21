/*
 * UCF COP 3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Charlene Creighton
 */

package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class EditItemController {

    // FXML Control accessors
    @FXML public Button DoneButton = new Button();
    @FXML public TextField EditName = new TextField();
    @FXML public TextField EditValue = new TextField();
    @FXML public TextField EditSerial = new TextField();
    @FXML public TextArea ShowItemStatus = new TextArea();
    @FXML public SplitMenuButton AllItems = new SplitMenuButton();

    // Counters to hold index of items to be edited
    public int counter = -1;
    public int sort = -1;
    public int AllItemsControl = 0;

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
        for (int i = 0; i < Inventory.allItems.size(); i++) {
            if (EditSerial.getText().equals(Inventory.allItems.get(i).Serial)) {
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
        for (int z = 0; z < Inventory.allItems.size(); z++) {

            FrontPageController.remove.add(Inventory.allItems.get(z).name + ": " + Inventory.allItems.get(z).Serial);
        }
        AllItems.getItems().clear();
        AllItemsControl = 0;
        Stage stage = (Stage) DoneButton.getScene().getWindow();
        stage.close();

    }

    // Allows item to be edited when pressed
    public void EditPressed() {
        // Add item to list if all booleans are true and valid data has been entered
        AllItemsControl = 0;

        if (EditSerial.getText().isEmpty() || EditSerial.getText().equals("New Item Serial Number Here") || !serialEntry)
            serialEdit = false;

        if (EditName.getText().isEmpty() || EditName.getText().equals("New Item Name Here") || !nameEntry)
            nameEdit = false;

        if (EditValue.getText().isEmpty() || EditValue.getText().equals("New Item Value Here") || !valueEntry)
            valueEdit = false;


        if (serialCheck && nameCheck && valueCheck && serialEdit && nameEdit && valueEdit) {
            ShowItemStatus.setText("                    Item Edited");
            Inventory.allItems.get(counter).Serial = EditSerial.getText();
            Inventory.allItems.get(counter).name = EditName.getText();
            Inventory.allItems.get(counter).value = BigDecimal.valueOf(Double.parseDouble(EditValue.getText()));
        }

        else if (serialCheck && nameCheck && valueCheck && serialEdit && nameEdit) {
            ShowItemStatus.setText("                    Item Edited");
            Inventory.allItems.get(counter).name = EditName.getText();
            Inventory.allItems.get(counter).value = BigDecimal.valueOf(Double.parseDouble(EditValue.getText()));
        }

        else if (serialCheck && nameCheck && valueCheck && serialEdit && !valueEdit) {
            ShowItemStatus.setText("                    Item Edited");
            Inventory.allItems.get(counter).Serial = EditSerial.getText();
        }

        else if (serialCheck && nameCheck && valueCheck && !serialEdit && !nameEdit && !valueEdit) {
            ShowItemStatus.setText("                    No Change");
        }

        else if (serialCheck && nameCheck && valueCheck && !serialEdit && nameEdit && valueEdit) {
            ShowItemStatus.setText("                    Item Edited");
            Inventory.allItems.get(counter).name = EditName.getText();
            Inventory.allItems.get(counter).value = BigDecimal.valueOf(Double.parseDouble(EditValue.getText()));
        }

        else if (serialCheck && nameCheck && valueCheck && !serialEdit && nameEdit) {
            ShowItemStatus.setText("                    Item Edited");
            Inventory.allItems.get(counter).name = EditName.getText();
        }

        else if (serialCheck && nameCheck && valueCheck && serialEdit) {
            ShowItemStatus.setText("                    Item Edited");
            Inventory.allItems.get(counter).Serial = EditSerial.getText();
            Inventory.allItems.get(counter).value = BigDecimal.valueOf(Double.parseDouble(EditValue.getText()));
        }
        else if (serialCheck && nameCheck && valueCheck) {
            ShowItemStatus.setText("                    Item Edited");
            Inventory.allItems.get(counter).value = BigDecimal.valueOf(Double.parseDouble(EditValue.getText()));
        }

        // Display error message if user tries to add item with invalid input
        if (!serialCheck || !nameCheck || !valueCheck){
            ShowItemStatus.setText("ERROR: ITEM NOT EDITED: Check input Values");
            EditName.setText("New Item Name Here");
            EditValue.setText("New Item Value Here");
            EditSerial.setText("New Item Serial Number Here");
            AllItemsControl = -1;
        }

        if (AllItemsControl == 0)
            FrontPageController.remove.add(Inventory.allItems.get(counter).name + ": " + Inventory.allItems.get(counter).Serial);

        serialEntry = false;
        nameEntry = false;
        valueEntry = false;
    }

    // Displays dropdown items
    public void ViewAllItems() {

        if (AllItemsControl == 0) {
            AllItems.getItems().clear();
            int length = Inventory.allItems.size();

            for (int x = 0; x < length; x++) {
                MenuItem elements = new MenuItem();
                int edited = x;
                elements.setText(Inventory.allItems.get(counter).name + ": " + Inventory.allItems.get(counter).Serial);
                elements.setOnAction((event -> {

                    counter = edited;
                    sort = edited;
                }));
                AllItems.getItems().add(elements);
            }
        }
        AllItemsControl = -1;
    }

    public void SetBoxItems() {
        /*
        AllItems.getItems().clear();
        int length = FrontPageController.namesSorted.size();

        for (int x = 0; x < length; x++) {
            MenuItem elements = new MenuItem();
            int edited = x;
            elements.setText(FrontPageController.namesSorted.get(x) + ": " + FrontPageController.serialsSorted.get(x));
            elements.setOnAction((event -> {

                counter = edited;
                sort = edited;
            }));
            AllItems.getItems().add(elements);
        }
        */
    }
}
