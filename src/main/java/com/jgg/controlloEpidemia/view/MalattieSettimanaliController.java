package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.importData.EtlMalattie;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.MalattieSettimanaliService;
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

public class MalattieSettimanaliController implements Initializable {

    @FXML
    private TextField annoInserimentoTextField;

    @FXML
    private TextField settimanaInserimentoTextField;

    @FXML
    private TextField ricoveratiConInfluenzaInserimentoTextField;

    @FXML
    private TextField inCuraConInfluenzaInserimentoTextField;

    @FXML
    private TextField complicanzeRespiratorieInserimentoTextField;

    @FXML
    private TextField inCuraConPolmoniteInserimentoTextField;

    @FXML
    private TextField ricoveratiConMeningiteInserimentoTextField;

    @FXML
    private TextField ricoveratiConPolmoniteInserimentoTextField;

    @FXML
    private TextField ricoveratiConEpatiteInserimentoTextField;

    @FXML
    private TextField ricoveratiConTubercolosiInserimentoTextField;

    @FXML
    private TextField ricoveratiConMorbilloInserimentoTextField;

    @FXML
    private TextField ricoveratiConGastroenteriteInserimentoTextField;

    @FXML
    private TextField InCuraConTubercolosiInserimentoTextField;

    @FXML
    private TextField inCuraConMeningiteInserimentoTextField;

    @FXML
    private TextField inCuraConEpatiteInserimentoTextField;

    @FXML
    private TextField inCuraConMorbilloInserimentoTextField;

    @FXML
    private TextField inCuraConGastroenteriteInserimentoTextField;

    @FXML
    private ComboBox<String> comuneInserimentoComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
        List<TipoTerritorio> tipoTerritorioList = tipoTerritorioService.findAll();
        for (TipoTerritorio t : tipoTerritorioList) {
            comuneInserimentoComboBox.getItems().add(t.getNome());
            //comuneInserimentoComboBox.getItems().add(t.getNome());
        }
    }

    @FXML
    void homepageButtonOnClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) event.getSource()).getScene().setRoot(root);
    }

    @FXML
    void inserisciInserimentoOnClicked(ActionEvent event) {
        /*ComuneService comuneService = new ComuneService();
        MalattieSettimanali malattieSettimanali = new MalattieSettimanali(
                Integer.parseInt(annoInserimentoTextField.getText()),
                Integer.parseInt(settimanaInserimentoTextField.getText()),
                Integer.parseInt(ricoveratiConInfluenzaInserimentoTextField.getText()),
                Integer.parseInt(inCuraConInfluenzaInserimentoTextField.getText()),
                Integer.parseInt(complicanzeRespiratorieInserimentoTextField.getText()),
                Integer.parseInt(inCuraConPolmoniteInserimentoTextField.getText()),
                Integer.parseInt(ricoveratiConMeningiteInserimentoTextField.getText()),
                Integer.parseInt(ricoveratiConPolmoniteInserimentoTextField.getText()),
                Integer.parseInt(ricoveratiConEpatiteInserimentoTextField.getText()),
                Integer.parseInt(ricoveratiConTubercolosiInserimentoTextField.getText()),
                Integer.parseInt(ricoveratiConMorbilloInserimentoTextField.getText()),
                Integer.parseInt(ricoveratiConGastroenteriteInserimentoTextField.getText()),
                Integer.parseInt(InCuraConTubercolosiInserimentoTextField.getText()),
                Integer.parseInt(inCuraConMeningiteInserimentoTextField.getText()),
                Integer.parseInt(inCuraConEpatiteInserimentoTextField.getText()),
                Integer.parseInt(inCuraConMorbilloInserimentoTextField.getText()),
                Integer.parseInt(inCuraConGastroenteriteInserimentoTextField.getText()),
                comuneService.findByNome(comuneInserimentoComboBox.getValue())
        );
        MalattieSettimanaliService malattieSettimanaliService = new MalattieSettimanaliService();
        malattieSettimanaliService.save(malattieSettimanali);
        if (malattieSettimanaliService.findById(malattieSettimanali.getId()) != null) {
            System.out.println("ok");
            annoInserimentoTextField.clear();
            settimanaInserimentoTextField.clear();
            ricoveratiConInfluenzaInserimentoTextField.clear();
            inCuraConInfluenzaInserimentoTextField.clear();
            complicanzeRespiratorieInserimentoTextField.clear();
            inCuraConPolmoniteInserimentoTextField.clear();
            ricoveratiConMeningiteInserimentoTextField.clear();
            ricoveratiConPolmoniteInserimentoTextField.clear();
            ricoveratiConEpatiteInserimentoTextField.clear();
            ricoveratiConTubercolosiInserimentoTextField.clear();
            ricoveratiConMorbilloInserimentoTextField.clear();
            ricoveratiConGastroenteriteInserimentoTextField.clear();
            InCuraConTubercolosiInserimentoTextField.clear();
            inCuraConMeningiteInserimentoTextField.clear();
            inCuraConEpatiteInserimentoTextField.clear();
            inCuraConMorbilloInserimentoTextField.clear();
            inCuraConGastroenteriteInserimentoTextField.clear();
        }*/
    }

    @FXML
    void inserisciCsvInserimentoOnClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        EtlMalattie etlMalattie = new EtlMalattie();
        if (selectedFile != null) {
            System.out.println("ok");
            //etlMalattie.load(selectedFile);
        } else {
            System.out.println("no trovato il file");
        }
    }

    @FXML
    void inserisciModificaOnClicked(ActionEvent event) {
        /*ComuneService comuneService = new ComuneService();
        MalattieSettimanali malattieSettimanali = new MalattieSettimanali(
                Integer.parseInt(annoInserimentoTextField.getText()),
                Integer.parseInt(settimanaInserimentoTextField.getText()),
                Integer.parseInt(ricoveratiConInfluenzaInserimentoTextField.getText()),
                Integer.parseInt(inCuraConInfluenzaInserimentoTextField.getText()),
                Integer.parseInt(complicanzeRespiratorieInserimentoTextField.getText()),
                Integer.parseInt(inCuraConPolmoniteInserimentoTextField.getText()),
                Integer.parseInt(ricoveratiConMeningiteInserimentoTextField.getText()),
                Integer.parseInt(ricoveratiConPolmoniteInserimentoTextField.getText()),
                Integer.parseInt(ricoveratiConEpatiteInserimentoTextField.getText()),
                Integer.parseInt(ricoveratiConTubercolosiInserimentoTextField.getText()),
                Integer.parseInt(ricoveratiConMorbilloInserimentoTextField.getText()),
                Integer.parseInt(ricoveratiConGastroenteriteInserimentoTextField.getText()),
                Integer.parseInt(InCuraConTubercolosiInserimentoTextField.getText()),
                Integer.parseInt(inCuraConMeningiteInserimentoTextField.getText()),
                Integer.parseInt(inCuraConEpatiteInserimentoTextField.getText()),
                Integer.parseInt(inCuraConMorbilloInserimentoTextField.getText()),
                Integer.parseInt(inCuraConGastroenteriteInserimentoTextField.getText()),
                comuneService.findByNome(comuneModificaComboBox.getValue())
        );
        MalattieSettimanaliService malattieSettimanaliService = new MalattieSettimanaliService();
        malattieSettimanaliService.update(malattieSettimanali);
        if (malattieSettimanaliService.findById(malattieSettimanali.getId()) != null) {
            System.out.println("ok");
            annoInserimentoTextField.clear();
            settimanaInserimentoTextField.clear();
            ricoveratiConInfluenzaInserimentoTextField.clear();
            inCuraConInfluenzaInserimentoTextField.clear();
            complicanzeRespiratorieInserimentoTextField.clear();
            inCuraConPolmoniteInserimentoTextField.clear();
            ricoveratiConMeningiteInserimentoTextField.clear();
            ricoveratiConPolmoniteInserimentoTextField.clear();
            ricoveratiConEpatiteInserimentoTextField.clear();
            ricoveratiConTubercolosiInserimentoTextField.clear();
            ricoveratiConMorbilloInserimentoTextField.clear();
            ricoveratiConGastroenteriteInserimentoTextField.clear();
            InCuraConTubercolosiInserimentoTextField.clear();
            inCuraConMeningiteInserimentoTextField.clear();
            inCuraConEpatiteInserimentoTextField.clear();
            inCuraConMorbilloInserimentoTextField.clear();
            inCuraConGastroenteriteInserimentoTextField.clear();
        }*/
    }

}
