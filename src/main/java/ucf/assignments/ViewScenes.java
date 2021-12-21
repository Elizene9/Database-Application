/*
 * UCF COP 3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Charlene Creighton
 */

package ucf.assignments;

// This enum Stores Pages of the Application
public enum ViewScenes
{
    // Allows scenes to be switched upon access to this class
    FrontPage("FrontPage.fxml"),
    AddItem("AddItem.fxml"),
    EditItem("EditItem.fxml");

    // Get file to be opened
    public String file;
    ViewScenes(String file)
    {
        this.file = file;
    }

    public String getFile()
    {
        return file;
    }
}
