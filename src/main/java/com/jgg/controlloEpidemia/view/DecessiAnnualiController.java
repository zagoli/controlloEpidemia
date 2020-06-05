package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.importData.EtlDecessi;
import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import com.jgg.controlloEpidemia.service.ProvinciaService;
import com.jgg.controlloEpidemia.service.TipoTerritorioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DecessiAnnualiController implements Initializable {

    @FXML
    private TextField annoInserimentoTextField;

    @FXML
    private TextField incidentiStradaliInserimentoTextField;

    @FXML
    private TextField malattieTumoraliInserimentoTextField;

    @FXML
    private TextField malattieCardiovascolariInserimentoTextField;

    @FXML
    private TextField malattieContagioseInserimentoTextField;

    @FXML
    private ComboBox<String> provinciaInserimentoComboBox;

    @FXML
    private TextField annoModificaTextField;

    @FXML
    private TextField incidentiStradaliModificaTextField;

    @FXML
    private TextField malattieTumoraliModificaTextField;

    @FXML
    private TextField malattieCardiovascolariModificaTextField;

    @FXML
    private TextField malattieContagioseModificaTextField;

    @FXML
    private ComboBox<String> provinciaModificaComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
        List<TipoTerritorio> tipoTerritorioList = tipoTerritorioService.findAll();
        for (TipoTerritorio t : tipoTerritorioList) {
            provinciaInserimentoComboBox.getItems().add(t.getNome());
            provinciaModificaComboBox.getItems().add(t.getNome());
        }
    }

    @FXML
    private void homepageButtonOnClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) event.getSource()).getScene().setRoot(root);
    }

    @FXML
    private void provinciaInserimentoComboBoxOnClicked() {
        System.out.println("ok");
    }

    @FXML
    private void inserisciInserimentoButtonOnClicked() {
        ProvinciaService provinciaService = new ProvinciaService();
        DecessiAnnuali decessiAnnuali = new DecessiAnnuali(Integer.parseInt(annoInserimentoTextField.getText()),
                Integer.parseInt(incidentiStradaliInserimentoTextField.getText()),
                Integer.parseInt(malattieTumoraliInserimentoTextField.getText()),
                Integer.parseInt(malattieCardiovascolariInserimentoTextField.getText()),
                Integer.parseInt(malattieContagioseInserimentoTextField.getText()),
                provinciaService.findByNome(provinciaInserimentoComboBox.getValue())
        );
        DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
        decessiAnnualiService.save(decessiAnnuali);
        if (decessiAnnualiService.findById(decessiAnnuali.getId()) != null) {
            System.out.println("ok");
            annoInserimentoTextField.clear();
            incidentiStradaliInserimentoTextField.clear();
            malattieTumoraliInserimentoTextField.clear();
            malattieCardiovascolariInserimentoTextField.clear();
            malattieContagioseInserimentoTextField.clear();
        }
    }

    @FXML
    private void inserisciCsvInserimentoButtonOnClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        EtlDecessi etlDecessi = new EtlDecessi();
        if (selectedFile != null) {
            System.out.println("ok");
            //etlDecessi.load(selectedFile);
        } else {
            System.out.println("no trovato il file");
        }
    }

    @FXML
    private void provinciaModificaComboBoxOnClicked() {
        System.out.println("ok");
    }

    @FXML
    private void inserisciModificaButtonOnClicked() {
        ProvinciaService provinciaService = new ProvinciaService();
        DecessiAnnuali decessiAnnuali = new DecessiAnnuali(
                Integer.parseInt(annoModificaTextField.getText()),
                Integer.parseInt(incidentiStradaliModificaTextField.getText()),
                Integer.parseInt(malattieTumoraliModificaTextField.getText()),
                Integer.parseInt(malattieCardiovascolariModificaTextField.getText()),
                Integer.parseInt(malattieContagioseModificaTextField.getText()),
                provinciaService.findByNome(provinciaInserimentoComboBox.getValue())
        );
        DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
        decessiAnnualiService.update(decessiAnnuali);
        if (decessiAnnualiService.findById(decessiAnnuali.getId()) != null) {
            System.out.println("ok");
            annoModificaTextField.clear();
            incidentiStradaliModificaTextField.clear();
            malattieTumoraliModificaTextField.clear();
            malattieCardiovascolariModificaTextField.clear();
            malattieContagioseModificaTextField.clear();
        }
    }

}
