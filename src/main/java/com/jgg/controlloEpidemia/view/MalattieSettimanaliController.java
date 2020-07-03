package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.importData.EtlMalattie;
import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.MalattieSettimanaliService;
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

public class MalattieSettimanaliController implements Initializable {

    static Logger logger = Logger.getLogger(MalattieSettimanaliController.class);

    private final ComuneService comuneService = new ComuneService();
    private final MalattieSettimanaliService malattieSettimanaliService = new MalattieSettimanaliService();
    private final Alert alert = new Alert(Alert.AlertType.WARNING);
    @FXML
    private BorderPane malattieSettimanaliBorderPane;
    @FXML
    private TabPane malattieSettimanaliTabPane;
    @FXML
    private Tab malattieSettimanaliVisualizzazioneTab;
    @FXML
    private Tab malattieSettimanaliInserimentoTab;
    @FXML
    private Tab malattieSettimanaliModificaTab;
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
    @FXML
    private ProgressBar loadingBar;
    @FXML
    private Label noDataSelectedLabel;
    private int selectedId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        BooleanBinding annoInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!annoInserimentoTextField.getText().isEmpty() && annoInserimentoTextField.getText().matches("[0-9]+") && Integer.parseInt(annoInserimentoTextField.getText()) > 1500 && Integer.parseInt(annoInserimentoTextField.getText()) <= Calendar.getInstance().get(Calendar.YEAR)) {
                return false;
            } else {
                return true;
            }
        }, annoInserimentoTextField.textProperty());

        BooleanBinding settimanaInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!settimanaInserimentoTextField.getText().isEmpty() && settimanaInserimentoTextField.getText().matches("[0-9]+") && Integer.parseInt(settimanaInserimentoTextField.getText()) > 0 && Integer.parseInt(settimanaInserimentoTextField.getText()) <= 53) {
                return false;
            } else {
                return true;
            }
        }, settimanaInserimentoTextField.textProperty());

        BooleanBinding ricoveratiConInfluenzaInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!ricoveratiConInfluenzaInserimentoTextField.getText().isEmpty() && ricoveratiConInfluenzaInserimentoTextField.getText().matches("[0-9]+") && Integer.parseInt(ricoveratiConInfluenzaInserimentoTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, ricoveratiConInfluenzaInserimentoTextField.textProperty());

        BooleanBinding inCuraConInfluenzaInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!inCuraConInfluenzaInserimentoTextField.getText().isEmpty() && inCuraConInfluenzaInserimentoTextField.getText().matches("[0-9]+") && Integer.parseInt(inCuraConInfluenzaInserimentoTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, inCuraConInfluenzaInserimentoTextField.textProperty());

        BooleanBinding complicanzeRespiratorieInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!complicanzeRespiratorieInserimentoTextField.getText().isEmpty() && complicanzeRespiratorieInserimentoTextField.getText().matches("[0-9]+") && Integer.parseInt(complicanzeRespiratorieInserimentoTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, complicanzeRespiratorieInserimentoTextField.textProperty());

        BooleanBinding ricoveratiConPolmoniteInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!ricoveratiConPolmoniteInserimentoTextField.getText().isEmpty() && ricoveratiConPolmoniteInserimentoTextField.getText().matches("[0-9]+") && Integer.parseInt(ricoveratiConPolmoniteInserimentoTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, ricoveratiConPolmoniteInserimentoTextField.textProperty());

        BooleanBinding inCuraConPolmoniteInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!inCuraConPolmoniteInserimentoTextField.getText().isEmpty() && inCuraConPolmoniteInserimentoTextField.getText().matches("[0-9]+") && Integer.parseInt(inCuraConPolmoniteInserimentoTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, inCuraConPolmoniteInserimentoTextField.textProperty());

        BooleanBinding ricoveratiConMeningiteInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!ricoveratiConMeningiteInserimentoTextField.getText().isEmpty() && ricoveratiConMeningiteInserimentoTextField.getText().matches("[0-9]+") && Integer.parseInt(ricoveratiConMeningiteInserimentoTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, ricoveratiConMeningiteInserimentoTextField.textProperty());

        BooleanBinding inCuraConMeningiteInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!inCuraConPolmoniteInserimentoTextField.getText().isEmpty() && inCuraConPolmoniteInserimentoTextField.getText().matches("[0-9]+") && Integer.parseInt(inCuraConPolmoniteInserimentoTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, inCuraConPolmoniteInserimentoTextField.textProperty());

        BooleanBinding ricoveratiConEpatiteInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!ricoveratiConEpatiteInserimentoTextField.getText().isEmpty() && ricoveratiConEpatiteInserimentoTextField.getText().matches("[0-9]+") && Integer.parseInt(ricoveratiConEpatiteInserimentoTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, ricoveratiConEpatiteInserimentoTextField.textProperty());

        BooleanBinding inCuraConEpatiteInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!inCuraConEpatiteInserimentoTextField.getText().isEmpty() && inCuraConEpatiteInserimentoTextField.getText().matches("[0-9]+") && Integer.parseInt(inCuraConEpatiteInserimentoTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, inCuraConEpatiteInserimentoTextField.textProperty());

        BooleanBinding ricoveratiConMorbilloInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!ricoveratiConMorbilloInserimentoTextField.getText().isEmpty() && ricoveratiConMorbilloInserimentoTextField.getText().matches("[0-9]+") && Integer.parseInt(ricoveratiConMorbilloInserimentoTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, ricoveratiConMorbilloInserimentoTextField.textProperty());

        BooleanBinding inCuraConMorbilloInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!inCuraConMorbilloInserimentoTextField.getText().isEmpty() && inCuraConMorbilloInserimentoTextField.getText().matches("[0-9]+") && Integer.parseInt(inCuraConMorbilloInserimentoTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, inCuraConMorbilloInserimentoTextField.textProperty());

        BooleanBinding ricoveratiConTubercolosiInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!ricoveratiConTubercolosiInserimentoTextField.getText().isEmpty() && ricoveratiConTubercolosiInserimentoTextField.getText().matches("[0-9]+") && Integer.parseInt(ricoveratiConTubercolosiInserimentoTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, ricoveratiConTubercolosiInserimentoTextField.textProperty());

        BooleanBinding inCuraConTubercolosiInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!inCuraConTubercolosiInserimentoTextField.getText().isEmpty() && inCuraConTubercolosiInserimentoTextField.getText().matches("[0-9]+") && Integer.parseInt(inCuraConTubercolosiInserimentoTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, inCuraConTubercolosiInserimentoTextField.textProperty());

        BooleanBinding ricoveratiConGastroenteriteInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!ricoveratiConGastroenteriteInserimentoTextField.getText().isEmpty() && ricoveratiConGastroenteriteInserimentoTextField.getText().matches("[0-9]+") && Integer.parseInt(ricoveratiConGastroenteriteInserimentoTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, ricoveratiConGastroenteriteInserimentoTextField.textProperty());

        BooleanBinding inCuraConGastroenteriteInserimentoTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!inCuraConGastroenteriteInserimentoTextField.getText().isEmpty() && inCuraConGastroenteriteInserimentoTextField.getText().matches("[0-9]+") && Integer.parseInt(inCuraConGastroenteriteInserimentoTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, inCuraConGastroenteriteInserimentoTextField.textProperty());

        BooleanBinding annoModificaTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!annoModificaTextField.getText().isEmpty() && annoModificaTextField.getText().matches("[0-9]+") && Integer.parseInt(annoModificaTextField.getText()) > 1500 && Integer.parseInt(annoModificaTextField.getText()) <= Calendar.getInstance().get(Calendar.YEAR)) {
                return false;
            } else {
                return true;
            }
        }, annoModificaTextField.textProperty());

        BooleanBinding settimanaModificaTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!settimanaModificaTextField.getText().isEmpty() && settimanaModificaTextField.getText().matches("[0-9]+") && Integer.parseInt(settimanaModificaTextField.getText()) > 0 && Integer.parseInt(settimanaModificaTextField.getText()) <= 53) {
                return false;
            } else {
                return true;
            }
        }, settimanaModificaTextField.textProperty());

        BooleanBinding ricoveratiConInfluenzaModificaTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!ricoveratiConInfluenzaModificaTextField.getText().isEmpty() && ricoveratiConInfluenzaModificaTextField.getText().matches("[0-9]+") && Integer.parseInt(ricoveratiConInfluenzaModificaTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, ricoveratiConInfluenzaModificaTextField.textProperty());

        BooleanBinding inCuraConInfluenzaModificaTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!inCuraConInfluenzaModificaTextField.getText().isEmpty() && inCuraConInfluenzaModificaTextField.getText().matches("[0-9]+") && Integer.parseInt(inCuraConInfluenzaModificaTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, inCuraConInfluenzaModificaTextField.textProperty());

        BooleanBinding complicanzeRespiratorieModificaTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!complicanzeRespiratorieModificaTextField.getText().isEmpty() && complicanzeRespiratorieModificaTextField.getText().matches("[0-9]+") && Integer.parseInt(complicanzeRespiratorieModificaTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, complicanzeRespiratorieModificaTextField.textProperty());

        BooleanBinding ricoveratiConPolmoniteModificaTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!ricoveratiConPolmoniteModificaTextField.getText().isEmpty() && ricoveratiConPolmoniteModificaTextField.getText().matches("[0-9]+") && Integer.parseInt(ricoveratiConPolmoniteModificaTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, ricoveratiConPolmoniteModificaTextField.textProperty());

        BooleanBinding inCuraConPolmoniteModificaTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!inCuraConPolmoniteModificaTextField.getText().isEmpty() && inCuraConPolmoniteModificaTextField.getText().matches("[0-9]+") && Integer.parseInt(inCuraConPolmoniteModificaTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, inCuraConPolmoniteModificaTextField.textProperty());

        BooleanBinding ricoveratiConMeningiteModificaTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!ricoveratiConMeningiteModificaTextField.getText().isEmpty() && ricoveratiConMeningiteModificaTextField.getText().matches("[0-9]+") && Integer.parseInt(ricoveratiConMeningiteModificaTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, ricoveratiConMeningiteModificaTextField.textProperty());

        BooleanBinding inCuraConMeningiteModificaTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!inCuraConPolmoniteModificaTextField.getText().isEmpty() && inCuraConPolmoniteModificaTextField.getText().matches("[0-9]+") && Integer.parseInt(inCuraConPolmoniteModificaTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, inCuraConPolmoniteModificaTextField.textProperty());

        BooleanBinding ricoveratiConEpatiteModificaTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!ricoveratiConEpatiteModificaTextField.getText().isEmpty() && ricoveratiConEpatiteModificaTextField.getText().matches("[0-9]+") && Integer.parseInt(ricoveratiConEpatiteModificaTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, ricoveratiConEpatiteModificaTextField.textProperty());

        BooleanBinding inCuraConEpatiteModificaTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!inCuraConEpatiteModificaTextField.getText().isEmpty() && inCuraConEpatiteModificaTextField.getText().matches("[0-9]+") && Integer.parseInt(inCuraConEpatiteModificaTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, inCuraConEpatiteModificaTextField.textProperty());

        BooleanBinding ricoveratiConMorbilloModificaTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!ricoveratiConMorbilloModificaTextField.getText().isEmpty() && ricoveratiConMorbilloModificaTextField.getText().matches("[0-9]+") && Integer.parseInt(ricoveratiConMorbilloModificaTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, ricoveratiConMorbilloModificaTextField.textProperty());

        BooleanBinding inCuraConMorbilloModificaTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!inCuraConMorbilloModificaTextField.getText().isEmpty() && inCuraConMorbilloModificaTextField.getText().matches("[0-9]+") && Integer.parseInt(inCuraConMorbilloModificaTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, inCuraConMorbilloModificaTextField.textProperty());

        BooleanBinding ricoveratiConTubercolosiModificaTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!ricoveratiConTubercolosiModificaTextField.getText().isEmpty() && ricoveratiConTubercolosiModificaTextField.getText().matches("[0-9]+") && Integer.parseInt(ricoveratiConTubercolosiModificaTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, ricoveratiConTubercolosiModificaTextField.textProperty());

        BooleanBinding inCuraConTubercolosiModificaTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!inCuraConTubercolosiModificaTextField.getText().isEmpty() && inCuraConTubercolosiModificaTextField.getText().matches("[0-9]+") && Integer.parseInt(inCuraConTubercolosiModificaTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, inCuraConTubercolosiModificaTextField.textProperty());

        BooleanBinding ricoveratiConGastroenteriteModificaTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!ricoveratiConGastroenteriteModificaTextField.getText().isEmpty() && ricoveratiConGastroenteriteModificaTextField.getText().matches("[0-9]+") && Integer.parseInt(ricoveratiConGastroenteriteModificaTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, ricoveratiConGastroenteriteModificaTextField.textProperty());

        BooleanBinding inCuraConGastroenteriteModificaTextFieldValid = Bindings.createBooleanBinding(() -> {
            if (!inCuraConGastroenteriteModificaTextField.getText().isEmpty() && inCuraConGastroenteriteModificaTextField.getText().matches("[0-9]+") && Integer.parseInt(inCuraConGastroenteriteModificaTextField.getText()) >= 0) {
                return false;
            } else {
                return true;
            }
        }, inCuraConGastroenteriteModificaTextField.textProperty());

        malattieSettimanaliInserisciButton.disableProperty().bind(
                annoInserimentoTextFieldValid
                        .or(settimanaInserimentoTextFieldValid)
                        .or(ricoveratiConInfluenzaInserimentoTextFieldValid)
                        .or(inCuraConInfluenzaInserimentoTextFieldValid)
                        .or(complicanzeRespiratorieInserimentoTextFieldValid)
                        .or(ricoveratiConPolmoniteInserimentoTextFieldValid)
                        .or(inCuraConPolmoniteInserimentoTextFieldValid)
                        .or(ricoveratiConMeningiteInserimentoTextFieldValid)
                        .or(inCuraConMeningiteModificaTextFieldValid)
                        .or(ricoveratiConEpatiteInserimentoTextFieldValid)
                        .or(inCuraConEpatiteInserimentoTextFieldValid)
                        .or(ricoveratiConMorbilloInserimentoTextFieldValid)
                        .or(inCuraConMorbilloInserimentoTextFieldValid)
                        .or(ricoveratiConTubercolosiInserimentoTextFieldValid)
                        .or(inCuraConTubercolosiInserimentoTextFieldValid)
                        .or(ricoveratiConGastroenteriteInserimentoTextFieldValid)
                        .or(inCuraConGastroenteriteInserimentoTextFieldValid)
                        .or(comuneInserimentoComboBox.valueProperty().isNull()));

        malattieSettimanaliModificaButton.disableProperty().bind(
                annoModificaTextFieldValid
                        .or(settimanaModificaTextFieldValid)
                        .or(ricoveratiConInfluenzaModificaTextFieldValid)
                        .or(inCuraConInfluenzaModificaTextFieldValid)
                        .or(complicanzeRespiratorieModificaTextFieldValid)
                        .or(ricoveratiConPolmoniteModificaTextFieldValid)
                        .or(inCuraConPolmoniteModificaTextFieldValid)
                        .or(ricoveratiConMeningiteModificaTextFieldValid)
                        .or(inCuraConMeningiteModificaTextFieldValid)
                        .or(ricoveratiConEpatiteModificaTextFieldValid)
                        .or(inCuraConEpatiteModificaTextFieldValid)
                        .or(ricoveratiConMorbilloModificaTextFieldValid)
                        .or(inCuraConMorbilloModificaTextFieldValid)
                        .or(ricoveratiConTubercolosiModificaTextFieldValid)
                        .or(inCuraConTubercolosiModificaTextFieldValid)
                        .or(ricoveratiConGastroenteriteModificaTextFieldValid)
                        .or(inCuraConGastroenteriteModificaTextFieldValid)
                        .or(comuneModificaComboBox.valueProperty().isNull()
                        ));
        alert.setTitle("Autorizzazioni comuni");
        alert.setHeaderText(null);
        alert.setContentText("Non sei autorizzato a gestire questo comune!");

        new Thread(new Task<>() {
            @Override
            protected Void call() {
                List<Comune> comuniOrdinati = new ArrayList<>(comuneService.findAll());
                comuniOrdinati.sort(Comparator.comparing(Comune::getNome));
                for (Comune comune : comuniOrdinati) {
                    if (App.utenteCorrente.getRuolo().getId() == 1 || App.utenteCorrente.getComuni().contains(comune)) {
                        comuneInserimentoComboBox.getItems().add(comune.getNome());
                        comuneModificaComboBox.getItems().add(comune.getNome());
                    }
                }
                updateList();
                Platform.runLater(() -> malattieSettimanaliBorderPane.setDisable(false));
                return null;
            }
        }).start();
    }

    @FXML
    void homepageButtonOnClicked() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        malattieSettimanaliTabPane.getScene().setRoot(root);
    }

    private void updateList() {
        malattieSettimanaliTableView.getItems().clear();
        for (MalattieSettimanali malattieSettimanali : malattieSettimanaliService.findAll()) {
            malattieSettimanaliTableView.getItems().add(malattieSettimanali);
        }
        Platform.runLater(() -> {
            malattieSettimanaliTableView.getSortOrder().remove(annoColumn);
            malattieSettimanaliTableView.getSortOrder().add(annoColumn);
        });
        noDataSelectedLabel.setVisible(false);
    }


    @FXML
    private void malattieSettimanaliCancellaButtonOnClicked() {
        MalattieSettimanali malattieSettimanali = malattieSettimanaliTableView.getSelectionModel().getSelectedItem();
        if (malattieSettimanali != null) {
            if (App.utenteCorrente.getComuni().contains(malattieSettimanali.getComune()) || App.utenteCorrente.getRuolo().getId() == 1) {
                logger.info("Cancellato record malattie settimanali: " + malattieSettimanaliTableView.getSelectionModel().getSelectedItem());
                malattieSettimanaliService.deleteById(malattieSettimanali.getId());
                updateList();
            } else {
                alert.showAndWait();
            }
        } else {
            noDataSelectedLabel.setVisible(true);
            errorAnimation(noDataSelectedLabel);
        }
    }

    @FXML
    private void malattieSettimanaliVisualizzazioneModificaButtonOnClicked() {
        if (malattieSettimanaliTableView.getSelectionModel().getSelectedItem() != null) {
            MalattieSettimanali malattie = malattieSettimanaliTableView.getSelectionModel().getSelectedItem();
            if (App.utenteCorrente.getComuni().contains(malattie.getComune()) || App.utenteCorrente.getRuolo().getId() == 1) {
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
                alert.showAndWait();
            }
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
        if (App.utenteCorrente.getComuni().contains(malattieSettimanali.getComune()) || App.utenteCorrente.getRuolo().getId() == 1) {
            malattieSettimanaliService.save(malattieSettimanali);
            if (malattieSettimanaliService.findById(malattieSettimanali.getId()) != null) {
                logger.info("Inserito record malattie settimanali: " + malattieSettimanali);
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
        } else {
            alert.showAndWait();
        }

    }

    @FXML
    void inserisciCsvInserimentoOnClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Csv files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            loadingBar.setVisible(true);
            malattieSettimanaliBorderPane.setDisable(true);

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Errori inserimento CSV malattie settimanali");
            alert.setHeaderText(null);

            new Thread(new Task<>() {
                @Override
                protected Void call() {
                    int[] ritornoErrori = new EtlMalattie().load(selectedFile.getPath(),false);
                    updateList();
                    Platform.runLater(() -> {
                        alert.setContentText("Righe con errore: " + ritornoErrori[0] + "\n" + "Righe non lette: " + ritornoErrori[1]);
                        if (ritornoErrori[0] > 0 || ritornoErrori[1] > 0) {
                            alert.showAndWait();
                        }
                        loadingBar.setVisible(false);
                        malattieSettimanaliBorderPane.setDisable(false);
                        malattieSettimanaliTabPane.getSelectionModel().select(0);
                    });
                    return null;
                }
            }).start();
            logger.info("Inserito csv malattie settimanali");
        } else {
            logger.error("File non selezionato");
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
            logger.info("Modificato record malattie settimanali: " + malattieSettimanali);
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
