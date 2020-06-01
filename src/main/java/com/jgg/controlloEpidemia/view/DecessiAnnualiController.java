package com.jgg.controlloEpidemia.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;

public class DecessiAnnualiController {

    @FXML
    private ComboBox<String> provinciaComboBox;

    @FXML
    private void onHomepageButtonClicked(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) e.getSource()).getScene().setRoot(root);
    }

    @FXML
    private void onProvinciaComboBoxClicked(ActionEvent e) {
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Option 1",
                        "Option 2",
                        "Option 3"
                );
        provinciaComboBox.getItems().addAll(options);
    }

}
