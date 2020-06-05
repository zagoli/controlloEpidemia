package com.jgg.controlloEpidemia.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatiTerritorialiController {

    @FXML
    DatePicker dataDiIstituzioneComuneInserimento;

    @FXML
    private void onHomepageButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) event.getSource()).getScene().setRoot(root);
    }

    @FXML
    private void dataDiIstituzioneComuneInserimentoOnClicked() {
        LocalDate data = dataDiIstituzioneComuneInserimento.getValue();
        String format = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("ok");
    }
}
