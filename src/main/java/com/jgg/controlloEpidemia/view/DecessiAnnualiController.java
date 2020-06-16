package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.importData.EtlDecessi;
import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import com.jgg.controlloEpidemia.service.ProvinciaService;
import javafx.animation.ScaleTransition;
import javafx.beans.binding.Bindings;
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

public class DecessiAnnualiController implements Initializable {

    private final ProvinciaService provinciaService = new ProvinciaService();
    private final DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();

    @FXML
    private Button decessiAnnualiInserisciButton;
    @FXML
    private Button decessiAnnualiModificaButton;
    @FXML
    private Label noDataSelectedLabel;
    @FXML
    private Tab decessiAnnualiVisualizzazioneTab;
    @FXML
    private Tab decessiAnnualiInserimentoTab;
    @FXML
    private Tab decessiAnnualiModificaTab;
    @FXML
    private TabPane decessiAnnualiTabPane;
    @FXML
    private ListView<String> decessiAnnualiListView;
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

    private int selectedId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        decessiAnnualiModificaTab.setDisable(true);
        List<Provincia> provinciaList = provinciaService.findAll();
        for (Provincia provincia : provinciaList) {
            provinciaInserimentoComboBox.getItems().add(provincia.getNome());
            provinciaModificaComboBox.getItems().add(provincia.getNome());
        }
        updateList();
        noDataSelectedLabel.setVisible(false);
        decessiAnnualiListView.getSelectionModel().select(0);

        decessiAnnualiInserisciButton.disableProperty().bind(
                Bindings.isEmpty(incidentiStradaliInserimentoTextField.textProperty())
                        .or(Bindings.isEmpty(annoInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(malattieTumoraliInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(malattieCardiovascolariInserimentoTextField.textProperty()))
                        .or(Bindings.isEmpty(malattieContagioseInserimentoTextField.textProperty()))
                        .or(provinciaInserimentoComboBox.valueProperty().isNull())
        );
        decessiAnnualiModificaButton.disableProperty().bind(
                Bindings.isEmpty(incidentiStradaliModificaTextField.textProperty())
                        .or(Bindings.isEmpty(annoModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(malattieTumoraliModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(malattieCardiovascolariModificaTextField.textProperty()))
                        .or(Bindings.isEmpty(malattieContagioseModificaTextField.textProperty()))
                        .or(provinciaModificaComboBox.valueProperty().isNull())
        );
    }

    @FXML
    private void homepageButtonOnClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) event.getSource()).getScene().setRoot(root);
    }

    private void updateList() {
        decessiAnnualiListView.getItems().clear();
        List<DecessiAnnuali> decessiAnnualiList = decessiAnnualiService.findAll();
        decessiAnnualiListView.getItems().add("ID ANNO INCIDENTI STRADALI MALATTIE TUMORALI MALATTIE CARDIOVSCOLARI MALATTIE CONTAGIOSE PROVINCIA");
        for (DecessiAnnuali decessiAnnuali : decessiAnnualiList) {
            decessiAnnualiListView.getItems().add(
                    decessiAnnuali.getId()
                            + "  " + decessiAnnuali.getAnno()
                            + "  " + decessiAnnuali.getIncidentiStradali()
                            + "  " + decessiAnnuali.getMalattieTumorali()
                            + "  " + decessiAnnuali.getMalattieCardiovascolari()
                            + "  " + decessiAnnuali.getMalattieContagiose()
                            + "  " + decessiAnnuali.getProvincia().getNome());
        }
        noDataSelectedLabel.setVisible(false);
    }

    @FXML
    private void decessiAnnualiCancellaButtonOnClicked() {
        if (decessiAnnualiListView.getSelectionModel().getSelectedIndex() != 0) {
            String decessi = decessiAnnualiListView.getSelectionModel().getSelectedItem();
            String[] decessiEntry;
            decessiEntry = decessi.split("\\s{2}");
            selectedId = Integer.parseInt(decessiEntry[0]);
            decessiAnnualiService.deleteById(selectedId);
            updateList();
        } else {
            noDataSelectedLabel.setVisible(true);
            errorAnimation(noDataSelectedLabel);
        }
    }

    @FXML
    private void decessiAnnualiVisualizzazioneModificaButtonOnClicked() {
        if (decessiAnnualiListView.getSelectionModel().getSelectedIndex() != 0) {
            decessiAnnualiModificaTab.setDisable(false);
            decessiAnnualiVisualizzazioneTab.setDisable(true);
            decessiAnnualiInserimentoTab.setDisable(true);
            String decessi = decessiAnnualiListView.getSelectionModel().getSelectedItem();
            String[] decessiEntry;
            decessiEntry = decessi.split("\\s{2}");
            decessiAnnualiTabPane.getSelectionModel().select(2);
            selectedId = Integer.parseInt(decessiEntry[0]);
            annoModificaTextField.setText(decessiEntry[1]);
            incidentiStradaliModificaTextField.setText(decessiEntry[2]);
            malattieTumoraliModificaTextField.setText(decessiEntry[3]);
            malattieCardiovascolariModificaTextField.setText(decessiEntry[4]);
            malattieContagioseModificaTextField.setText(decessiEntry[5]);
            provinciaModificaComboBox.getSelectionModel().select(decessiEntry[6]);
        } else {
            noDataSelectedLabel.setVisible(true);
            errorAnimation(noDataSelectedLabel);
        }
    }

    @FXML
    private void inserisciInserimentoButtonOnClicked() {
        DecessiAnnuali decessiAnnuali = new DecessiAnnuali(Integer.parseInt(annoInserimentoTextField.getText()),
                Integer.parseInt(incidentiStradaliInserimentoTextField.getText()),
                Integer.parseInt(malattieTumoraliInserimentoTextField.getText()),
                Integer.parseInt(malattieCardiovascolariInserimentoTextField.getText()),
                Integer.parseInt(malattieContagioseInserimentoTextField.getText()),
                provinciaService.findByNome(provinciaInserimentoComboBox.getValue())
        );
        decessiAnnualiService.save(decessiAnnuali);
        if (decessiAnnualiService.findById(decessiAnnuali.getId()) != null) {
            System.out.println("ok");
            annoInserimentoTextField.clear();
            incidentiStradaliInserimentoTextField.clear();
            malattieTumoraliInserimentoTextField.clear();
            malattieCardiovascolariInserimentoTextField.clear();
            malattieContagioseInserimentoTextField.clear();
        }
        decessiAnnualiTabPane.getSelectionModel().select(0);
        decessiAnnualiListView.getSelectionModel().select(0);
        updateList();
    }

    @FXML
    private void inserisciCsvInserimentoButtonOnClicked() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            System.out.println("ok");
            new EtlDecessi().load(selectedFile.getPath());
            decessiAnnualiTabPane.getSelectionModel().select(0);
            decessiAnnualiListView.getSelectionModel().select(0);
            updateList();
        } else {
            System.out.println("non ho trovato il file");
        }
    }

    @FXML
    private void modificaModificaButtonOnClicked() {
        DecessiAnnuali decessiAnnuali = new DecessiAnnuali(
                selectedId,
                Integer.parseInt(annoModificaTextField.getText()),
                Integer.parseInt(incidentiStradaliModificaTextField.getText()),
                Integer.parseInt(malattieTumoraliModificaTextField.getText()),
                Integer.parseInt(malattieCardiovascolariModificaTextField.getText()),
                Integer.parseInt(malattieContagioseModificaTextField.getText()),
                provinciaService.findByNome(provinciaModificaComboBox.getValue())
        );
        decessiAnnualiService.update(decessiAnnuali);
        if (decessiAnnualiService.findById(decessiAnnuali.getId()) != null) {
            annoModificaTextField.clear();
            incidentiStradaliModificaTextField.clear();
            malattieTumoraliModificaTextField.clear();
            malattieCardiovascolariModificaTextField.clear();
            malattieContagioseModificaTextField.clear();
        }
        decessiAnnualiTabPane.getSelectionModel().select(0);
        decessiAnnualiListView.getSelectionModel().select(0);
        decessiAnnualiModificaTab.setDisable(true);
        decessiAnnualiVisualizzazioneTab.setDisable(false);
        decessiAnnualiInserimentoTab.setDisable(false);
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
