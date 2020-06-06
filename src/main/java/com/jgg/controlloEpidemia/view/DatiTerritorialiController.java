package com.jgg.controlloEpidemia.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatiTerritorialiController {

    @FXML
    private ComboBox tipoTerritorioInserimentoComuniComboBox;

    @FXML
    private ComboBox provinciaInserimentoComuniComboBox;

    @FXML
    private TextField idInserimentoProvinceTextField;

    @FXML
    private TextField nomeInserimentoProvinceTextField;

    @FXML
    private TextField superficieInserimentoProvinceTextField;

    @FXML
    private TextField comuneDiCapoluogoInserimentoProvinceTextField;

    @FXML
    private ComboBox regioneInserimentoProvinceComboBox;

    @FXML
    private TextField codiceIstatInserimentoComuniTextField;

    @FXML
    private TextField nomeInserimentoComuniTextField;

    @FXML
    private TextField superficieInserimentoComuniTextField;

    @FXML
    private ComboBox siAffacciaSulMareInserimentoComuniComboBox;

    @FXML
    private DatePicker dataDiIstituzioneInserimentoComuniDatePicker;

    @FXML
    private DatePicker dataDiIstituzioneComuneModifica;

    @FXML
    private DatePicker DataDiIstituzioneInserimentoComuniDatePicker;

    @FXML
    private void onHomepageButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) event.getSource()).getScene().setRoot(root);
    }

    @FXML
    private void DataDiIstituzioneInserimentoComuniOnClicked() {
        LocalDate data = DataDiIstituzioneInserimentoComuniDatePicker.getValue();
        String format = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("ok");
    }

    public void dataDiIstituzioneComuneModificaOnClicked(ActionEvent event) {
    }

    public void inserisciInserimentoProvinceButtonOnClicked(ActionEvent event) {
    }

    public void inserisciCsvInserimentoProvinceButtonOnClicked(ActionEvent event) {
    }

    public void inserisciInserimentoComuniButtonOnClicked(ActionEvent event) {
    }

    public void inserisciCsvInserimentoComuniButtonOnClicked(ActionEvent event) {
    }

    public void dataDiIstituzioneInserimentoComuniOnClicked(ActionEvent event) {
    }
}
