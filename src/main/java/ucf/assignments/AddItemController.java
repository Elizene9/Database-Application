package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

// This class adds a new item to inventory
public class AddItemController {

    // FXML controls
    @FXML public Button DoneButton = new Button();
    @FXML public TextField ItemName = new TextField();
    @FXML public TextField ItemValue = new TextField();
    @FXML public TextField ItemSerial = new TextField();
    @FXML public TextArea ShowItemStatus = new TextArea();

    // Value to check value validity
    Double checkValue;

    // Booleans check if user entry data is valid
    Boolean serialCheck, nameCheck, valueCheck;

    // Stores serial number entered and checks if it is a valid 10 character unique number
    char[] serial;

    // Adds an item if valid data is presented prints error if not
    public void AddPressed() {

        // Add item to list if all booleans are true and valid data has been entered
        if (serialCheck && nameCheck && valueCheck) {
            FrontPageController.serials.add(ItemSerial.getText());
            FrontPageController.serialsSorted.add(ItemSerial.getText());
            FrontPageController.names.add(ItemName.getText());
            FrontPageController.namesSorted.add(ItemName.getText());
            FrontPageController.values.add("$" + ItemValue.getText());
            FrontPageController.valuesSorted.add("$" + ItemValue.getText());
        }

        // Display error message if user tries to add item with invalid input
        else {
            ShowItemStatus.setText("ERROR: ITEM NOT ADDED: Check input Values");
            ItemName.setText("Item Name Here");
            ItemValue.setText("Item Value Here");
            ItemSerial.setText("Item Serial Number Here");
        }
    }

    // Checks if name entry is valid
    public void ItemNameEntered() {
        nameCheck = true;

        // Check if item name length is valid
        if (ItemName.getText().length() < 2 || ItemName.getText().length() > 256) {
            nameCheck = false;
            ShowItemStatus.setText("Error: Enter a name between 2 and 256 characters inclusive");
        }

        // Check if user entered anything
        if (ItemName.getText().equals("Item Name Here") || ItemName.getText().isEmpty()) {
            nameCheck = false;
            ShowItemStatus.setText("Error: Enter a name!");
        }

        // Get rid of any error message once valid value is entered
        if (nameCheck)
            ShowItemStatus.setText("    Press add once you are finished adding the details of your item");
    }

    // Checks if value entry is valid
    public void ItemValueEntered() {

        valueCheck = true;

        // Check if user entered valid numeric value
        try {
            checkValue = Double.parseDouble(ItemValue.getText());
        }
        catch (NumberFormatException e) {

            valueCheck = false;
            ShowItemStatus.setText("Invalid item value: Enter a valid numerical value");
        }

        // Check if user entered anything
        if (ItemValue.getText().equals("Item Value Here") || ItemValue.getText().isEmpty()) {
            valueCheck = false;
            ShowItemStatus.setText("Error: Enter a Value");
        }

        // Get rid of error messages if user enters valid value
        if (valueCheck)
            ShowItemStatus.setText("    Press add once you are finished adding the details of your item");

    }

    // Checks if serial number entry is valid
    public void ItemSerialEntered() {
        serial = ItemSerial.getText().toCharArray();
        serialCheck = true;

        // Checks if serial number is already in use
        for (int i = 0; i < FrontPageController.serials.size(); i++) {
            if (ItemSerial.getText().equals(FrontPageController.serials.get(i))) {
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
        if (ItemSerial.getText().length() != 10) {
            serialCheck = false;
            ShowItemStatus.setText("Error: Serial number must be 10 characters long!");
        }

        // Checks if user entered anything
        if (ItemSerial.getText().equals("Item Serial Number Here") || ItemSerial.getText().isEmpty()) {
            serialCheck = false;
            ShowItemStatus.setText("Error: Enter a serial number!");
        }

        // Deletes error if user enters valid serial number
        if (serialCheck)
            ShowItemStatus.setText("    Press add once you are finished adding the details of your item");
    }

    // Exits window when user finishes adding items
    public void DonePressed() {
        // Get rid of any error items and exit window
        ShowItemStatus.setText("    Press add once you are finished adding the details of your item");

        Stage stage = (Stage) DoneButton.getScene().getWindow();
        stage.close();
    }
}
