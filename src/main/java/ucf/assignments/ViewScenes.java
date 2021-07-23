package ucf.assignments;

// This enum allows scene switching
public enum ViewScenes {

    // Allows scenes to be switched upon access to this class
    FrontPage("FrontPage.fxml"),
    AddItem("AddItem.fxml"),
    EditItem("EditItem.fxml");

    // Get file to be opened
    public String file;
    ViewScenes(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }
}
