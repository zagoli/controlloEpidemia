package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.importData.EtlDecessi;
import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import com.jgg.controlloEpidemia.service.ProvinciaService;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
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
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class DecessiAnnualiController implements Initializable {

    static Logger logger = Logger.getLogger(DecessiAnnualiController.class);

    private final ProvinciaService provinciaService = new ProvinciaService();
    private final DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();

    @FXML
    private BorderPane decessiAnnualiBorderPane;
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
    private TableColumn<DecessiAnnuali, Integer> idColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Provincia> provinciaColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> annoColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> incidentiStradaliColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> malattieTumoraliColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> malattieCardiovascolariColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> malattieContagioseColumn;

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
    private Button decessiAnnualiInserisciButton;
    @FXML
    private Button decessiAnnualiModificaButton;
    @FXML
    private ProgressBar loadingBar;
    @FXML
    private Label noDataSelectedLabel;

    private int selectedId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        annoColumn.setCellValueFactory(new PropertyValueFactory<>("anno"));
        decessiAnnualiTableView.getSortOrder().add(annoColumn);

        provinciaColumn.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        provinciaColumn.setCellFactory(column -> new TableCell<>() {
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

        incidentiStradaliColumn.setCellValueFactory(new PropertyValueFactory<>("incidentiStradali"));
        malattieTumoraliColumn.setCellValueFactory(new PropertyValueFactory<>("malattieTumorali"));
        malattieContagioseColumn.setCellValueFactory(new PropertyValueFactory<>("malattieContagiose"));
        malattieCardiovascolariColumn.setCellValueFactory(new PropertyValueFactory<>("malattieCardiovascolari"));

        BooleanBinding annoInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> annoInserimentoTextField.getText().isEmpty() || !annoInserimentoTextField.getText().matches("[0-9]+") || Integer.parseInt(annoInserimentoTextField.getText()) <= 1500 || Integer.parseInt(annoInserimentoTextField.getText()) > Calendar.getInstance().get(Calendar.YEAR), annoInserimentoTextField.textProperty());

        BooleanBinding incidentiStradaliInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> incidentiStradaliInserimentoTextField.getText().isEmpty() || !incidentiStradaliInserimentoTextField.getText().matches("[0-9]+") || Integer.parseInt(incidentiStradaliInserimentoTextField.getText()) < 0, incidentiStradaliInserimentoTextField.textProperty());

        BooleanBinding malattieTumoraliInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> malattieTumoraliInserimentoTextField.getText().isEmpty() || !malattieTumoraliInserimentoTextField.getText().matches("[0-9]+") || Integer.parseInt(malattieTumoraliInserimentoTextField.getText()) < 0, malattieTumoraliInserimentoTextField.textProperty());

        BooleanBinding malattieCardiovascolariInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> malattieCardiovascolariInserimentoTextField.getText().isEmpty() || !malattieCardiovascolariInserimentoTextField.getText().matches("[0-9]+") || Integer.parseInt(malattieCardiovascolariInserimentoTextField.getText()) < 0, malattieCardiovascolariInserimentoTextField.textProperty());

        BooleanBinding malattieContagioseInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> malattieContagioseInserimentoTextField.getText().isEmpty() || !malattieContagioseInserimentoTextField.getText().matches("[0-9]+") || Integer.parseInt(malattieContagioseInserimentoTextField.getText()) < 0, malattieContagioseInserimentoTextField.textProperty());

        BooleanBinding annoModificaTextFieldValid = Bindings.createBooleanBinding(() -> annoModificaTextField.getText().isEmpty() || !annoModificaTextField.getText().matches("[0-9]+") || Integer.parseInt(annoModificaTextField.getText()) <= 1500 || Integer.parseInt(annoModificaTextField.getText()) > Calendar.getInstance().get(Calendar.YEAR), annoModificaTextField.textProperty());

        BooleanBinding incidentiStradaliModificaTextFieldValid = Bindings.createBooleanBinding(() -> incidentiStradaliModificaTextField.getText().isEmpty() || !incidentiStradaliModificaTextField.getText().matches("[0-9]+") || Integer.parseInt(incidentiStradaliModificaTextField.getText()) < 0, incidentiStradaliModificaTextField.textProperty());

        BooleanBinding malattieTumoraliModificaTextFieldValid = Bindings.createBooleanBinding(() -> malattieTumoraliModificaTextField.getText().isEmpty() || !malattieTumoraliModificaTextField.getText().matches("[0-9]+") || Integer.parseInt(malattieTumoraliModificaTextField.getText()) < 0, malattieTumoraliModificaTextField.textProperty());

        BooleanBinding malattieCardiovascolariModificaTextFieldValid = Bindings.createBooleanBinding(() -> malattieCardiovascolariModificaTextField.getText().isEmpty() || !malattieCardiovascolariModificaTextField.getText().matches("[0-9]+") || Integer.parseInt(malattieCardiovascolariModificaTextField.getText()) < 0, malattieCardiovascolariModificaTextField.textProperty());

        BooleanBinding malattieContagioseModificaTextFieldValid = Bindings.createBooleanBinding(() -> malattieContagioseModificaTextField.getText().isEmpty() || !malattieContagioseModificaTextField.getText().matches("[0-9]+") || Integer.parseInt(malattieContagioseModificaTextField.getText()) < 0, malattieContagioseModificaTextField.textProperty());

        decessiAnnualiInserisciButton.disableProperty().bind(
                annoInserimentoTextFieldValid
                        .or(incidentiStradaliInserimentoTextFieldValid)
                        .or(malattieTumoraliInserimentoTextFieldValid)
                        .or(malattieCardiovascolariInserimentoTextFieldValid)
                        .or(malattieContagioseInserimentoTextFieldValid)
                        .or(provinciaInserimentoComboBox.valueProperty().isNull())
        );
        decessiAnnualiModificaButton.disableProperty().bind(
                annoModificaTextFieldValid
                        .or(incidentiStradaliModificaTextFieldValid)
                        .or(malattieTumoraliModificaTextFieldValid)
                        .or(malattieCardiovascolariModificaTextFieldValid)
                        .or(malattieContagioseModificaTextFieldValid)
                        .or(provinciaModificaComboBox.valueProperty().isNull())
        );

        new Thread(new Task<>() {
            @Override
            protected Void call() {
                List<Provincia> provinceOrdinate = new ArrayList<>(provinciaService.findAll());
                provinceOrdinate.sort(Comparator.comparing(Provincia::getNome));
                for (Provincia provincia : provinceOrdinate) {
                    provinciaInserimentoComboBox.getItems().add(provincia.getNome());
                    provinciaModificaComboBox.getItems().add(provincia.getNome());
                }
                updateList();
                Platform.runLater(() -> decessiAnnualiBorderPane.setDisable(false));
                return null;
            }
        }).start();
    }

    @FXML
    private void homepageButtonOnClicked() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        decessiAnnualiBorderPane.getScene().setRoot(root);
    }

    private void updateList() {
        decessiAnnualiTableView.getItems().clear();
        for (DecessiAnnuali decessiAnnuali : decessiAnnualiService.findAll()) {
            decessiAnnualiTableView.getItems().add(decessiAnnuali);
        }
        Platform.runLater(() -> {
            decessiAnnualiTableView.getSortOrder().remove(annoColumn);
            decessiAnnualiTableView.getSortOrder().add(annoColumn);
        });
        noDataSelectedLabel.setVisible(false);
    }


    @FXML
    private void decessiAnnualiVisualizzazioneModificaButtonOnClicked() {
        if (decessiAnnualiTableView.getSelectionModel().getSelectedItem() != null) {

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
    private void decessiAnnualiCancellaButtonOnClicked() {
        if (decessiAnnualiTableView.getSelectionModel().getSelectedItem() != null) {
            logger.info("Cancellato record decessi annuali: " + decessiAnnualiTableView.getSelectionModel().getSelectedItem());
            decessiAnnualiService.deleteById(decessiAnnualiTableView.getSelectionModel().getSelectedItem().getId());
            updateList();
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
            logger.info("Inserito record decessi annuali: " + decessiAnnuali);
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
        fileChooser.setInitialDirectory(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Csv files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            loadingBar.setVisible(true);
            decessiAnnualiBorderPane.setDisable(true);

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Errori inserimento CSV decessi annuali");
            alert.setHeaderText(null);

            new Thread(new Task<>() {
                @Override
                protected Void call() {
                    int[] ritornoErrori = new EtlDecessi().load(selectedFile.getPath());
                    updateList();
                    Platform.runLater(() -> {
                        alert.setContentText("Righe con errore: " + ritornoErrori[0] + "\n" + "Righe non lette: " + ritornoErrori[1]);
                        if (ritornoErrori[0] > 0 || ritornoErrori[1] > 0) {
                            alert.showAndWait();
                        }
                        loadingBar.setVisible(false);
                        decessiAnnualiBorderPane.setDisable(false);
                        decessiAnnualiTabPane.getSelectionModel().select(0);
                    });
                    return null;
                }
            }).start();
            logger.info("Inserito csv decessi annuali");
        } else {
            logger.error("File non selezionato");
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
            logger.info("Modificato record decessi annuali: " + decessiAnnuali);
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
