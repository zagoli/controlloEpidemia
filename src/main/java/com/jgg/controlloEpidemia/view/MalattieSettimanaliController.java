package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.importData.EtlMalattie;
import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.MalattieSettimanaliService;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MalattieSettimanaliController implements Initializable {

    @FXML
    private Label noDataSelectedLabel;

    @FXML
    private Tab malattieSettimanaliVisualizzazioneTab;

    @FXML
    private Tab malattieSettimanaliInserimentoTab;

    @FXML
    private Tab malattieSettimanaliModificaTab;

    @FXML
    private TabPane malattieSettimanaliTabPane;

    @FXML
    private ListView<String> idMalattieSettimanaliListView;

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
    private TextField inCuraConTubercolosiInserimentoTextField;

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

    @FXML
    private TextField annoModificaTextField;

    @FXML
    private TextField settimanaModificaTextField;

    @FXML
    private TextField ricoveratiConInfluenzaModificaTextField;

    @FXML
    private TextField inCuraConInfluenzaModificaTextField;

    @FXML
    private TextField complicanzeRespiratorieModificaTextField;

    @FXML
    private TextField inCuraConPolmoniteModificaTextField;

    @FXML
    private TextField ricoveratiConMeningiteModificaTextField;

    @FXML
    private TextField ricoveratiConPolmoniteModificaTextField;

    @FXML
    private TextField ricoveratiConEpatiteModificaTextField;

    @FXML
    private TextField ricoveratiConTubercolosiModificaTextField;

    @FXML
    private TextField ricoveratiConMorbilloModificaTextField;

    @FXML
    private TextField ricoveratiConGastroenteriteModificaTextField;

    @FXML
    private TextField inCuraConTubercolosiModificaTextField;

    @FXML
    private TextField inCuraConMeningiteModificaTextField;

    @FXML
    private TextField inCuraConEpatiteModificaTextField;

    @FXML
    private TextField inCuraConMorbilloModificaTextField;

    @FXML
    private TextField inCuraConGastroenteriteModificaTextField;

    @FXML
    private ComboBox<String> comuneModificaComboBox;

    private int selectedId;

    private final ComuneService comuneService = new ComuneService();

    private final MalattieSettimanaliService malattieSettimanaliService = new MalattieSettimanaliService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        malattieSettimanaliModificaTab.setDisable(true);
        List<Comune> comuneList = comuneService.findAll();
        for (Comune c : comuneList) {
            comuneInserimentoComboBox.getItems().add(c.getNome());
            comuneModificaComboBox.getItems().add(c.getNome());
        }
        updateList();
        noDataSelectedLabel.setVisible(false);
        idMalattieSettimanaliListView.getSelectionModel().select(0);
    }

    @FXML
    void homepageButtonOnClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) event.getSource()).getScene().setRoot(root);
    }

    private void updateList() {
        idMalattieSettimanaliListView.getItems().clear();
        List<MalattieSettimanali> malattieSettimanaliList = malattieSettimanaliService.findAll();
        idMalattieSettimanaliListView.getItems().add("ID|COMUNE|ANNO|SETTIMANA|R.INFLUENZA|C.INFLUENZA|CP.RESPIRATORIE|R.POLMONITE|C.POLMONITE|R.MENINGITE|C.MENINGITE|R.EPATITE|C.EPATITE|R.MORBILLO|C.MORBILLO|R.TUBERCOLOSI|C.TUBERCOLOSI|R.GASTROENTERITE|C.GASTROENTERITE");
        for (MalattieSettimanali m : malattieSettimanaliList) {
            idMalattieSettimanaliListView.getItems().add(
                    m.getId()
                            + "|" + m.getComune().getNome()
                            + "|" + m.getAnno()
                            + "|" + m.getSettimana()
                            + "|" + m.getRicoveratiInfluenza()
                            + "|" + m.getInCuraInfluenza()
                            + "|" + m.getComplicanzeRespiratorie()
                            + "|" + m.getRicoveratiPolmonite()
                            + "|" + m.getInCuraPolmonite()
                            + "|" + m.getRicoveratiMeningite()
                            + "|" + m.getInCuraMeningite()
                            + "|" + m.getRicoveratiEpatite()
                            + "|" + m.getInCuraEpatite()
                            + "|" + m.getRicoveratiMorbillo()
                            + "|" + m.getInCuraMorbillo()
                            + "|" + m.getRicoveratiTubercolosi()
                            + "|" + m.getInCuraTubercolosi()
                            + "|" + m.getRicoveratiGastroenterite()
                            + "|" + m.getInCuraGastroenterite());
        }
        noDataSelectedLabel.setVisible(false);
    }

    @FXML
    private void malattieSettimanaliCancellaButtonOnClicked() {
        if (idMalattieSettimanaliListView.getSelectionModel().getSelectedIndex() != 0) {
            String malattie = idMalattieSettimanaliListView.getSelectionModel().getSelectedItem();
            String[] malattieEntry;
            malattieEntry = malattie.split("\\|");
            selectedId = Integer.parseInt(malattieEntry[0]);
            malattieSettimanaliService.deleteById(selectedId);
            updateList();
        } else {
            noDataSelectedLabel.setVisible(true);
            errorAnimation(noDataSelectedLabel);
        }
    }

    @FXML
    private void malattieSettimanaliVisualizzazioneModificaButtonOnClicked() {
        if (idMalattieSettimanaliListView.getSelectionModel().getSelectedIndex() != 0) {
            malattieSettimanaliModificaTab.setDisable(false);
            malattieSettimanaliVisualizzazioneTab.setDisable(true);
            malattieSettimanaliInserimentoTab.setDisable(true);
            String malattie = idMalattieSettimanaliListView.getSelectionModel().getSelectedItem();
            String[] malattieEntry;
            malattieEntry = malattie.split("\\|");
            malattieSettimanaliTabPane.getSelectionModel().select(2);
            selectedId = Integer.parseInt(malattieEntry[0]);
            comuneModificaComboBox.getSelectionModel().select(malattieEntry[1]);
            annoModificaTextField.setText(malattieEntry[2]);
            settimanaModificaTextField.setText(malattieEntry[3]);
            ricoveratiConInfluenzaModificaTextField.setText(malattieEntry[4]);
            inCuraConInfluenzaModificaTextField.setText(malattieEntry[5]);
            complicanzeRespiratorieModificaTextField.setText(malattieEntry[6]);
            ricoveratiConPolmoniteModificaTextField.setText(malattieEntry[7]);
            inCuraConPolmoniteModificaTextField.setText(malattieEntry[8]);
            ricoveratiConMeningiteModificaTextField.setText(malattieEntry[9]);
            inCuraConMeningiteModificaTextField.setText(malattieEntry[10]);
            ricoveratiConEpatiteModificaTextField.setText(malattieEntry[11]);
            inCuraConEpatiteModificaTextField.setText(malattieEntry[12]);
            ricoveratiConMorbilloModificaTextField.setText(malattieEntry[13]);
            inCuraConMorbilloModificaTextField.setText(malattieEntry[14]);
            ricoveratiConTubercolosiModificaTextField.setText(malattieEntry[15]);
            inCuraConTubercolosiModificaTextField.setText(malattieEntry[16]);
            ricoveratiConGastroenteriteModificaTextField.setText(malattieEntry[17]);
            inCuraConGastroenteriteModificaTextField.setText(malattieEntry[18]);
        } else {
            noDataSelectedLabel.setVisible(true);
            errorAnimation(noDataSelectedLabel);
        }
    }

    @FXML
    void inserisciInserimentoOnClicked() {
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
                Integer.parseInt(inCuraConTubercolosiInserimentoTextField.getText()),
                Integer.parseInt(inCuraConMeningiteInserimentoTextField.getText()),
                Integer.parseInt(inCuraConEpatiteInserimentoTextField.getText()),
                Integer.parseInt(inCuraConMorbilloInserimentoTextField.getText()),
                Integer.parseInt(inCuraConGastroenteriteInserimentoTextField.getText()),
                comuneService.findByNome(comuneInserimentoComboBox.getValue())
        );
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
            inCuraConTubercolosiInserimentoTextField.clear();
            inCuraConMeningiteInserimentoTextField.clear();
            inCuraConEpatiteInserimentoTextField.clear();
            inCuraConMorbilloInserimentoTextField.clear();
            inCuraConGastroenteriteInserimentoTextField.clear();
        }
        malattieSettimanaliTabPane.getSelectionModel().select(0);
        idMalattieSettimanaliListView.getSelectionModel().select(0);
        updateList();
    }

    @FXML
    void inserisciCsvInserimentoOnClicked() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            System.out.println("ok");
            new EtlMalattie().load(selectedFile.getPath());
            malattieSettimanaliTabPane.getSelectionModel().select(0);
            idMalattieSettimanaliListView.getSelectionModel().select(0);
            updateList();
        } else {
            System.out.println("non ho trovato il file");
        }
    }

    @FXML
    void modificaModificaOnClicked() {
        MalattieSettimanali malattieSettimanali = new MalattieSettimanali(
                selectedId,
                Integer.parseInt(annoModificaTextField.getText()),
                Integer.parseInt(settimanaModificaTextField.getText()),
                Integer.parseInt(ricoveratiConInfluenzaModificaTextField.getText()),
                Integer.parseInt(inCuraConInfluenzaModificaTextField.getText()),
                Integer.parseInt(complicanzeRespiratorieModificaTextField.getText()),
                Integer.parseInt(inCuraConPolmoniteModificaTextField.getText()),
                Integer.parseInt(ricoveratiConMeningiteModificaTextField.getText()),
                Integer.parseInt(ricoveratiConPolmoniteModificaTextField.getText()),
                Integer.parseInt(ricoveratiConEpatiteModificaTextField.getText()),
                Integer.parseInt(ricoveratiConTubercolosiModificaTextField.getText()),
                Integer.parseInt(ricoveratiConMorbilloModificaTextField.getText()),
                Integer.parseInt(ricoveratiConGastroenteriteModificaTextField.getText()),
                Integer.parseInt(inCuraConTubercolosiModificaTextField.getText()),
                Integer.parseInt(inCuraConMeningiteModificaTextField.getText()),
                Integer.parseInt(inCuraConEpatiteModificaTextField.getText()),
                Integer.parseInt(inCuraConMorbilloModificaTextField.getText()),
                Integer.parseInt(inCuraConGastroenteriteModificaTextField.getText()),
                comuneService.findByNome(comuneModificaComboBox.getValue())
        );
        malattieSettimanaliService.update(malattieSettimanali);
        if (malattieSettimanaliService.findById(malattieSettimanali.getId()) != null) {
            System.out.println("ok");
            annoModificaTextField.clear();
            settimanaModificaTextField.clear();
            ricoveratiConInfluenzaModificaTextField.clear();
            inCuraConInfluenzaModificaTextField.clear();
            complicanzeRespiratorieModificaTextField.clear();
            inCuraConPolmoniteModificaTextField.clear();
            ricoveratiConMeningiteModificaTextField.clear();
            ricoveratiConPolmoniteModificaTextField.clear();
            ricoveratiConEpatiteModificaTextField.clear();
            ricoveratiConTubercolosiModificaTextField.clear();
            ricoveratiConMorbilloModificaTextField.clear();
            ricoveratiConGastroenteriteModificaTextField.clear();
            inCuraConTubercolosiModificaTextField.clear();
            inCuraConMeningiteModificaTextField.clear();
            inCuraConEpatiteModificaTextField.clear();
            inCuraConMorbilloModificaTextField.clear();
            inCuraConGastroenteriteModificaTextField.clear();
        }
        malattieSettimanaliTabPane.getSelectionModel().select(0);
        idMalattieSettimanaliListView.getSelectionModel().select(0);
        malattieSettimanaliModificaTab.setDisable(true);
        malattieSettimanaliVisualizzazioneTab.setDisable(false);
        malattieSettimanaliInserimentoTab.setDisable(false);
        updateList();
    }

    private void errorAnimation(Label label) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), label);
        st.setByX(0.2);
        st.setByY(0.2);
        st.setCycleCount(2);
        st.setAutoReverse(true);
        st.play();
    }

}
