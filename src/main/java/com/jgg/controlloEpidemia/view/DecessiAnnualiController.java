package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.importData.EtlDecessi;
import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import com.jgg.controlloEpidemia.service.ProvinciaService;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
    private TabPane decessiAnnualiTabPane;
    @FXML
    private Tab decessiAnnualiVisualizzazioneTab;
    @FXML
    private Tab decessiAnnualiInserimentoTab;
    @FXML
    private Tab decessiAnnualiModificaTab;
    @FXML
    private TableView<DecessiAnnuali> decessiAnnualiTableView;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiIdColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Provincia> decessiAnnualiProvinciaColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiAnnoColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiIncidentiStradaliColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiMalattieTumoraliColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiMalattieCardiovascolariColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiMalattieContagioseColumn;
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
    @FXML
    private BorderPane mainPane;
    @FXML
    private ProgressBar loadingBar;

    private int selectedId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        decessiAnnualiModificaTab.setDisable(true);

        decessiAnnualiIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        decessiAnnualiAnnoColumn.setCellValueFactory(new PropertyValueFactory<>("anno"));
        decessiAnnualiTableView.getSortOrder().add(decessiAnnualiAnnoColumn);
        decessiAnnualiProvinciaColumn.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        decessiAnnualiProvinciaColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Provincia provincia, boolean empty) {
                super.updateItem(provincia, empty);
                if (empty || provincia == null) {
                    setText("");
                } else {
                    setText(provincia.getNome());
                }
            }
        });
        decessiAnnualiIncidentiStradaliColumn.setCellValueFactory(new PropertyValueFactory<>("incidentiStradali"));
        decessiAnnualiMalattieTumoraliColumn.setCellValueFactory(new PropertyValueFactory<>("malattieTumorali"));
        decessiAnnualiMalattieContagioseColumn.setCellValueFactory(new PropertyValueFactory<>("malattieContagiose"));
        decessiAnnualiMalattieCardiovascolariColumn.setCellValueFactory(new PropertyValueFactory<>("malattieCardiovascolari"));

        for (Provincia provincia : provinciaService.findAll()) {
            provinciaInserimentoComboBox.getItems().add(provincia.getNome());
            provinciaModificaComboBox.getItems().add(provincia.getNome());
        }

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

        updateList();
    }

    @FXML
    private void homepageButtonOnClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        decessiAnnualiTabPane.getScene().setRoot(root);
    }

    private void updateList() {
        System.out.println("ok");
        decessiAnnualiTableView.getItems().clear();
        for (DecessiAnnuali decessiAnnuali : decessiAnnualiService.findAll()) {
            decessiAnnualiTableView.getItems().add(decessiAnnuali);
        }
        noDataSelectedLabel.setVisible(false);
    }

    @FXML
    private void decessiAnnualiCancellaButtonOnClicked() {
        if (decessiAnnualiTableView.getSelectionModel().getSelectedItem() != null) {
            System.out.println("ok");
            decessiAnnualiService.deleteById(decessiAnnualiTableView.getSelectionModel().getSelectedItem().getId());
            updateList();
        } else {
            noDataSelectedLabel.setVisible(true);
            errorAnimation(noDataSelectedLabel);
        }
    }

    @FXML
    private void decessiAnnualiVisualizzazioneModificaButtonOnClicked() {
        if (decessiAnnualiTableView.getSelectionModel().getSelectedItem() != null) {
            System.out.println("ok");

            DecessiAnnuali decessi = decessiAnnualiTableView.getSelectionModel().getSelectedItem();
            selectedId = Integer.parseInt(String.valueOf(decessi.getId()));
            annoModificaTextField.setText(String.valueOf(decessi.getAnno()));
            incidentiStradaliModificaTextField.setText(String.valueOf(decessi.getIncidentiStradali()));
            malattieTumoraliModificaTextField.setText(String.valueOf(decessi.getMalattieTumorali()));
            malattieCardiovascolariModificaTextField.setText(String.valueOf(decessi.getMalattieCardiovascolari()));
            malattieContagioseModificaTextField.setText(String.valueOf(decessi.getMalattieContagiose()));
            provinciaModificaComboBox.getSelectionModel().select(decessi.getProvincia().getNome());

            decessiAnnualiVisualizzazioneTab.setDisable(true);
            decessiAnnualiInserimentoTab.setDisable(true);
            decessiAnnualiModificaTab.setDisable(false);
            decessiAnnualiTabPane.getSelectionModel().select(2);
        } else {
            noDataSelectedLabel.setVisible(true);
            errorAnimation(noDataSelectedLabel);
        }
    }

    @FXML
    private void inserisciInserimentoButtonOnClicked() {
        DecessiAnnuali decessiAnnuali = new DecessiAnnuali(
                Integer.parseInt(annoInserimentoTextField.getText()),
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
        updateList();
        decessiAnnualiTabPane.getSelectionModel().select(0);
    }

    @FXML
    private void inserisciCsvInserimentoButtonOnClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Csv files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            System.out.println("ok");
            loadingBar.setVisible(true);
            mainPane.setDisable(true);
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    new EtlDecessi().load(selectedFile.getPath());
                    updateList();
                    Platform.runLater(() -> {
                        loadingBar.setVisible(false);
                        mainPane.setDisable(false);
                        decessiAnnualiTabPane.getSelectionModel().select(0);
                    });
                    return null;
                }
            };
            new Thread(task).start();
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
            System.out.println("ok");
            annoModificaTextField.clear();
            incidentiStradaliModificaTextField.clear();
            malattieTumoraliModificaTextField.clear();
            malattieCardiovascolariModificaTextField.clear();
            malattieContagioseModificaTextField.clear();
        }
        updateList();
        decessiAnnualiVisualizzazioneTab.setDisable(false);
        decessiAnnualiInserimentoTab.setDisable(false);
        decessiAnnualiModificaTab.setDisable(true);
        decessiAnnualiTabPane.getSelectionModel().select(0);
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
