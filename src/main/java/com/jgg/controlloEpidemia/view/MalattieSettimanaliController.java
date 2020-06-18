package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.importData.EtlMalattie;
import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.MalattieSettimanaliService;
import javafx.animation.ScaleTransition;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MalattieSettimanaliController implements Initializable {

    private final ComuneService comuneService = new ComuneService();
    private final MalattieSettimanaliService malattieSettimanaliService = new MalattieSettimanaliService();

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
    private TableView<MalattieSettimanali> malattieSettimanaliTableView;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> idColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> annoColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> settimanaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> ricoveratiConInfluenzaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> inCuraConInfluenzaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> complicanzeRespiratorieColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> ricoveratiConPolmoniteColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> inCuraConPolmoniteColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> ricoveratiConMeningiteColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> inCuraConMeningiteColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> ricoveratiConEpatiteColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> inCuraConEpatiteColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> ricoveratiConMorbilloColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> inCuraConMorbilloColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> ricoveratiConTubercolosiColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> inCuraConTubercolosiColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> ricoveratiConGastroenteriteColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> inCuraConGastroenteriteColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Comune> comuneColumn;
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
    @FXML
    private Button malattieSettimanaliInserisciButton;
    @FXML
    private Button malattieSettimanaliModificaButton;

    private int selectedId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        malattieSettimanaliModificaTab.setDisable(true);
        List<Comune> comuneList = comuneService.findAll();
        for (Comune comune : comuneList) {
            comuneInserimentoComboBox.getItems().add(comune.getNome());
            comuneModificaComboBox.getItems().add(comune.getNome());
        }
        noDataSelectedLabel.setVisible(false);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        annoColumn.setCellValueFactory(new PropertyValueFactory<>("anno"));
        malattieSettimanaliTableView.getSortOrder().add(annoColumn);
        settimanaColumn.setCellValueFactory(new PropertyValueFactory<>("settimana"));
        ricoveratiConInfluenzaColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiInfluenza"));
        inCuraConInfluenzaColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraInfluenza"));
        complicanzeRespiratorieColumn.setCellValueFactory(new PropertyValueFactory<>("complicanzeRespiratorie"));
        ricoveratiConPolmoniteColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiPolmonite"));
        inCuraConPolmoniteColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraPolmonite"));
        ricoveratiConMeningiteColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiMeningite"));
        inCuraConMeningiteColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraMeningite"));
        ricoveratiConEpatiteColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiEpatite"));
        inCuraConEpatiteColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraEpatite"));
        ricoveratiConMorbilloColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiMorbillo"));
        inCuraConMorbilloColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraMorbillo"));
        ricoveratiConTubercolosiColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiTubercolosi"));
        inCuraConTubercolosiColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraTubercolosi"));
        ricoveratiConGastroenteriteColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiGastroenterite"));
        inCuraConGastroenteriteColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraGastroenterite"));
        comuneColumn.setCellValueFactory(new PropertyValueFactory<>("comune"));
        comuneColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Comune comune, boolean empty) {
                super.updateItem(comune, empty);
                if (empty || comune == null) {
                    setText("");
                } else {
                    setText(comune.getNome());
                }
            }
        });

        malattieSettimanaliInserisciButton.disableProperty().bind(
                Bindings.isEmpty(annoInserimentoTextField.textProperty())
                        .or(Bindings.isEmpty(settimanaInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(ricoveratiConInfluenzaInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(inCuraConInfluenzaInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(complicanzeRespiratorieInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(ricoveratiConPolmoniteInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(inCuraConPolmoniteInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(ricoveratiConMeningiteInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(inCuraConMeningiteInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(ricoveratiConEpatiteInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(inCuraConEpatiteInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(ricoveratiConMorbilloInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(inCuraConMorbilloInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(ricoveratiConTubercolosiInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(inCuraConTubercolosiInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(ricoveratiConGastroenteriteInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(inCuraConGastroenteriteInserimentoTextField.textProperty()))
                        .or(comuneInserimentoComboBox.valueProperty().isNull())
        );
        malattieSettimanaliModificaButton.disableProperty().bind(
                Bindings.isEmpty(annoModificaTextField.textProperty())
                        .or(Bindings.isEmpty(settimanaModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(ricoveratiConInfluenzaModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(inCuraConInfluenzaModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(complicanzeRespiratorieModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(ricoveratiConPolmoniteModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(inCuraConPolmoniteModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(ricoveratiConMeningiteModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(inCuraConMeningiteModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(ricoveratiConEpatiteModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(inCuraConEpatiteModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(ricoveratiConMorbilloModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(inCuraConMorbilloModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(ricoveratiConTubercolosiModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(inCuraConTubercolosiModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(ricoveratiConGastroenteriteModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(inCuraConGastroenteriteModificaTextField.textProperty()))
                        .or(comuneModificaComboBox.valueProperty().isNull())
        );

        updateList();
    }

    @FXML
    void homepageButtonOnClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) event.getSource()).getScene().setRoot(root);
    }

    private void updateList() {
        malattieSettimanaliTableView.getItems().clear();
        for (MalattieSettimanali malattieSettimanali : malattieSettimanaliService.findAll()) {
            malattieSettimanaliTableView.getItems().add(malattieSettimanali);
        }
        noDataSelectedLabel.setVisible(false);
    }

    @FXML
    private void malattieSettimanaliCancellaButtonOnClicked() {
        if (malattieSettimanaliTableView.getSelectionModel().getSelectedItem() != null) {
            malattieSettimanaliService.deleteById(malattieSettimanaliTableView.getSelectionModel().getSelectedItem().getId());
            updateList();
        } else {
            noDataSelectedLabel.setVisible(true);
            errorAnimation(noDataSelectedLabel);
        }
    }

    @FXML
    private void malattieSettimanaliVisualizzazioneModificaButtonOnClicked() {
        if (malattieSettimanaliTableView.getSelectionModel().getSelectedItem() != null) {
            MalattieSettimanali malattie = malattieSettimanaliTableView.getSelectionModel().getSelectedItem();
            selectedId = malattie.getId();
            annoModificaTextField.setText(String.valueOf(malattie.getAnno()));
            settimanaModificaTextField.setText(String.valueOf(malattie.getSettimana()));
            ricoveratiConInfluenzaModificaTextField.setText(String.valueOf(malattie.getRicoveratiInfluenza()));
            inCuraConInfluenzaModificaTextField.setText(String.valueOf(malattie.getInCuraInfluenza()));
            complicanzeRespiratorieModificaTextField.setText(String.valueOf(malattie.getComplicanzeRespiratorie()));
            ricoveratiConPolmoniteModificaTextField.setText(String.valueOf(malattie.getRicoveratiPolmonite()));
            inCuraConPolmoniteModificaTextField.setText(String.valueOf(malattie.getInCuraPolmonite()));
            ricoveratiConMeningiteModificaTextField.setText(String.valueOf(malattie.getRicoveratiMeningite()));
            inCuraConMeningiteModificaTextField.setText(String.valueOf(malattie.getInCuraMeningite()));
            ricoveratiConEpatiteModificaTextField.setText(String.valueOf(malattie.getRicoveratiEpatite()));
            inCuraConEpatiteModificaTextField.setText(String.valueOf(malattie.getInCuraEpatite()));
            ricoveratiConMorbilloModificaTextField.setText(String.valueOf(malattie.getRicoveratiMorbillo()));
            inCuraConMorbilloModificaTextField.setText(String.valueOf(malattie.getInCuraMorbillo()));
            ricoveratiConTubercolosiModificaTextField.setText(String.valueOf(malattie.getRicoveratiTubercolosi()));
            inCuraConTubercolosiModificaTextField.setText(String.valueOf(malattie.getInCuraTubercolosi()));
            ricoveratiConGastroenteriteModificaTextField.setText(String.valueOf(malattie.getRicoveratiGastroenterite()));
            inCuraConGastroenteriteModificaTextField.setText(String.valueOf(malattie.getInCuraGastroenterite()));
            comuneModificaComboBox.getSelectionModel().select(malattie.getComune().getNome());

            malattieSettimanaliVisualizzazioneTab.setDisable(true);
            malattieSettimanaliInserimentoTab.setDisable(true);
            malattieSettimanaliModificaTab.setDisable(false);
            malattieSettimanaliTabPane.getSelectionModel().select(2);
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
        updateList();
    }

    @FXML
    void inserisciCsvInserimentoOnClicked() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Csv files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            System.out.println("ok");
            new EtlMalattie().load(selectedFile.getPath());
            malattieSettimanaliTabPane.getSelectionModel().select(0);
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
