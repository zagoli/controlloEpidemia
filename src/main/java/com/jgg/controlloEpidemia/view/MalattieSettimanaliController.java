package com.jgg.controlloEpidemia.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

public class MalattieSettimanaliController {
    @FXML
    private void onHomepageButtonClicked(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) e.getSource()).getScene().setRoot(root);
    }
}
