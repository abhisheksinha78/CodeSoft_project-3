/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package addressbookgui;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.*;
import java.util.ArrayList;

public class AddressBookGUI extends Application {
    private ArrayList<Contact> contacts = new ArrayList<>();
    private ListView<Contact> contactListView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Address Book");

        // Create UI components
        TextField nameField = new TextField();
        TextField phoneField = new TextField();
        TextField emailField = new TextField();
        contactListView = new ListView<>();

        Button addButton = new Button("Add Contact");
        Button removeButton = new Button("Remove Contact");

        // Add event handlers
        addButton.setOnAction(e -> addContact(nameField, phoneField, emailField));
        removeButton.setOnAction(e -> removeContact());

        // Create layouts
        HBox inputLayout = new HBox(10, nameField, phoneField, emailField, addButton, removeButton);
        VBox mainLayout = new VBox(10, inputLayout, contactListView);

        // Create the scene
        Scene scene = new Scene(mainLayout, 400, 300);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void addContact(TextField nameField, TextField phoneField, TextField emailField) {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
            Contact newContact = new Contact(name, phone, email);
            contacts.add(newContact);
            contactListView.getItems().add(newContact);

            // Clear input fields
            nameField.clear();
            phoneField.clear();
            emailField.clear();
        } else {
            // Display an error message for empty fields
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Input Error");
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
        }
    }

    private void removeContact() {
        Contact selectedContact = contactListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            contacts.remove(selectedContact);
            contactListView.getItems().remove(selectedContact);
        }
    }
}

class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
