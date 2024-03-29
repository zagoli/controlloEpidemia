package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.importData.EtlComune;
import com.jgg.controlloEpidemia.importData.EtlProvincia;
import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.ProvinciaService;
import com.jgg.controlloEpidemia.service.RegioneService;
import com.jgg.controlloEpidemia.service.TipoTerritorioService;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DatiTerritorialiController implements Initializable {

    static final Logger logger = LogManager.getLogger(DatiTerritorialiController.class);

    private final ProvinciaService provinciaService = new ProvinciaService();
    private final ComuneService comuneService = new ComuneService();
    private final TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
    private final RegioneService regioneService = new RegioneService();

    @FXML
    private BorderPane datiTerritorialiBorderPane;
    @FXML
    private TabPane datiTerritorialiTabPane;
    @FXML
    private TabPane visualizzazioneTabPane;
    @FXML
    private TabPane modificaTabPane;
    @FXML
    private Tab visualizzazioneTab;
    @FXML
    private Tab inserimentoTab;
    @FXML
    private Tab modificaTab;
    @FXML
    private Tab provinciaModificaTab;
    @FXML
    private Tab comuneModificaTab;
    @FXML
    private TableView<Regione> regioniTableView;
    @FXML
    private TableColumn<Regione, Integer> regioniIdColumn;
    @FXML
    private TableColumn<Regione, String> regioniNomeColumn;
    @FXML
    private TableColumn<Regione, Integer> regioniSuperficieColumn;
    @FXML
    private TableColumn<Regione, String> regioniCapoluogoColumn;
    @FXML
    private TableView<Provincia> provinceTableView;
    @FXML
    private TableColumn<Provincia, Integer> provinceIdColumn;
    @FXML
    private TableColumn<Provincia, String> provinceNomeColumn;
    @FXML
    private TableColumn<Provincia, Integer> provinceSuperficieColumn;
    @FXML
    private TableColumn<Provincia, String> provinceCapoluogoColumn;
    @FXML
    private TableColumn<Provincia, Regione> provinceRegioneColumn;
    @FXML
    private TableView<Comune> comuniTableView;
    @FXML
    private TableColumn<Comune, String> comuniCodiceIstatColumn;
    @FXML
    private TableColumn<Comune, String> comuniNomeColumn;
    @FXML
    private TableColumn<Comune, Integer> comuniSuperficieColumn;
    @FXML
    private TableColumn<Comune, Date> comuniDataIstituzioneColumn;
    @FXML
    private TableColumn<Comune, Boolean> comuniSiAffacciaSulMareColumn;
    @FXML
    private TableColumn<Comune, TipoTerritorio> comuniTipoTerritorioColumn;
    @FXML
    private TableColumn<Comune, Provincia> comuniProvinciaColumn;
    @FXML
    private TextField idInserimentoProvinceTextField;
    @FXML
    private TextField nomeInserimentoProvinceTextField;
    @FXML
    private TextField superficieInserimentoProvinceTextField;
    @FXML
    private ComboBox<String> regioneInserimentoProvinceComboBox;
    @FXML
    private ComboBox<String> comuneDiCapoluogoInserimentoProvinceComboBox;
    @FXML
    private TextField codiceIstatInserimentoComuniTextField;
    @FXML
    private TextField nomeInserimentoComuniTextField;
    @FXML
    private TextField superficieInserimentoComuniTextField;
    @FXML
    private ComboBox<String> siAffacciaSulMareInserimentoComuniComboBox;
    @FXML
    private ComboBox<String> tipoTerritorioInserimentoComuniComboBox;
    @FXML
    private ComboBox<String> provinciaInserimentoComuniComboBox;
    @FXML
    private DatePicker dataDiIstituzioneInserimentoComuniDatePicker;
    @FXML
    private TextField idModificaProvinceTextField;
    @FXML
    private TextField nomeModificaProvinceTextField;
    @FXML
    private TextField superficieModificaProvinceTextField;
    @FXML
    private ComboBox<String> regioneModificaProvinceComboBox;
    @FXML
    private ComboBox<String> comuneDiCapoluogoModificaProvinceComboBox;
    @FXML
    private TextField codiceIstatModificaComuniTextField;
    @FXML
    private TextField nomeModificaComuniTextField;
    @FXML
    private TextField superficieModificaComuniTextField;
    @FXML
    private ComboBox<String> siAffacciaSulMareModificaComuniComboBox;
    @FXML
    private ComboBox<String> tipoTerritorioModificaComuniComboBox;
    @FXML
    private ComboBox<String> provinciaModificaComuniComboBox;
    @FXML
    private DatePicker dataDiIstituzioneModificaComuniDatePicker;
    @FXML
    private Button provinciaInserisciButton;
    @FXML
    private Button comuneInserisciButton;
    @FXML
    private Button provinciaModificaButton;
    @FXML
    private Button comuneModificaButton;
    @FXML
    private ProgressBar loadingBarComuni;
    @FXML
    private ProgressBar loadingBarProvince;
    @FXML
    private Label comuneNoDataSelectedLabel;
    @FXML
    private Label provinciaNoDataSelectedLabel;

    public void initialize(URL location, ResourceBundle resources) {
        regioniIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        regioniNomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        regioniTableView.getSortOrder().add(regioniNomeColumn);
        regioniSuperficieColumn.setCellValueFactory(new PropertyValueFactory<>("superficie"));
        regioniCapoluogoColumn.setCellValueFactory(new PropertyValueFactory<>("capoluogo"));

        provinceIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        provinceNomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        provinceTableView.getSortOrder().add(provinceNomeColumn);
        provinceSuperficieColumn.setCellValueFactory(new PropertyValueFactory<>("superficie"));
        provinceCapoluogoColumn.setCellValueFactory(new PropertyValueFactory<>("capoluogo"));
        provinceRegioneColumn.setCellValueFactory(new PropertyValueFactory<>("regione"));
        provinceRegioneColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Regione regione, boolean empty) {
                super.updateItem(regione, empty);
                if (empty || regione == null) {
                    setText("");
                } else {
                    setText(regione.getNome());
                }
            }
        });

        comuniCodiceIstatColumn.setCellValueFactory(new PropertyValueFactory<>("codiceIstat"));
        comuniNomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        comuniTableView.getSortOrder().add(comuniNomeColumn);
        comuniSuperficieColumn.setCellValueFactory(new PropertyValueFactory<>("superficie"));
        comuniDataIstituzioneColumn.setCellValueFactory(new PropertyValueFactory<>("dataIstituzione"));
        comuniDataIstituzioneColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Date date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setText("");
                } else {
                    String[] toParseData = date.toString().split(" ")[0].split("-");
                    setText(toParseData[2] + "/" + toParseData[1] + "/" + toParseData[0]);
                }
            }
        });
        comuniSiAffacciaSulMareColumn.setCellValueFactory(new PropertyValueFactory<>("siAffacciaSulMare"));
        comuniSiAffacciaSulMareColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean bool, boolean empty) {
                super.updateItem(bool, empty);
                if (empty || bool == null) {
                    setText("");
                } else {
                    if (bool) {
                        setText("Si");
                    } else {
                        setText("No");
                    }
                }
            }
        });
        comuniTipoTerritorioColumn.setCellValueFactory(new PropertyValueFactory<>("tipoTerritorio"));
        comuniTipoTerritorioColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(TipoTerritorio tipoTerritorio, boolean empty) {
                super.updateItem(tipoTerritorio, empty);
                if (empty || tipoTerritorio == null) {
                    setText("");
                } else {
                    setText(tipoTerritorio.getNome());
                }
            }
        });
        comuniProvinciaColumn.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        comuniProvinciaColumn.setCellFactory(column -> new TableCell<>() {
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

        //controllo id gia usati
        BooleanBinding idInserimentoProvinceTextFieldValid = Bindings.createBooleanBinding(() -> idInserimentoProvinceTextField.getText().isEmpty() || !idInserimentoProvinceTextField.getText().matches("[0-9]+") || Integer.parseInt(idInserimentoProvinceTextField.getText()) <= 0, idInserimentoProvinceTextField.textProperty());

        BooleanBinding nomeInserimentoProvinceTextFieldValid = Bindings.createBooleanBinding(() -> nomeInserimentoProvinceTextField.getText().isEmpty() || !nomeInserimentoProvinceTextField.getText().matches("[- a-zA-Z'àèéìòóù]+"), nomeInserimentoProvinceTextField.textProperty());

        BooleanBinding superficieInserimentoProvinceTextFieldValid = Bindings.createBooleanBinding(() -> superficieInserimentoProvinceTextField.getText().isEmpty() || !superficieInserimentoProvinceTextField.getText().matches("[0-9]+") || Integer.parseInt(superficieInserimentoProvinceTextField.getText()) <= 0, superficieInserimentoProvinceTextField.textProperty());

        //controllo id gia usati
        BooleanBinding idModificaProvinceTextFieldValid = Bindings.createBooleanBinding(() -> idModificaProvinceTextField.getText().isEmpty() || !idModificaProvinceTextField.getText().matches("[0-9]+") || Integer.parseInt(idModificaProvinceTextField.getText()) <= 0, idModificaProvinceTextField.textProperty());

        BooleanBinding nomeModificaProvinceTextFieldValid = Bindings.createBooleanBinding(() -> nomeModificaProvinceTextField.getText().isEmpty() || !nomeModificaProvinceTextField.getText().matches("[- a-zA-Z'àèéìòóù]+"), nomeModificaProvinceTextField.textProperty());

        BooleanBinding superficieModificaProvinceTextFieldValid = Bindings.createBooleanBinding(() -> superficieModificaProvinceTextField.getText().isEmpty() || !superficieModificaProvinceTextField.getText().matches("[0-9]+") || Integer.parseInt(superficieModificaProvinceTextField.getText()) <= 0, superficieModificaProvinceTextField.textProperty());

        //controllo id gia usati
        BooleanBinding codiceIstatInserimentoComuniTextFieldValid = Bindings.createBooleanBinding(() -> codiceIstatInserimentoComuniTextField.getText().isEmpty() || !codiceIstatInserimentoComuniTextField.getText().matches("[0-9]+") || codiceIstatInserimentoComuniTextField.getText().length() != 6, codiceIstatInserimentoComuniTextField.textProperty());

        BooleanBinding nomeInserimentoComuniTextFieldValid = Bindings.createBooleanBinding(() -> nomeInserimentoComuniTextField.getText().isEmpty() || !nomeInserimentoComuniTextField.getText().matches("[-. ()a-zA-Z'àâßèéêìö/òôóçüù]+"), nomeInserimentoComuniTextField.textProperty());

        BooleanBinding superficieInserimentoComuniTextFieldValid = Bindings.createBooleanBinding(() -> superficieInserimentoComuniTextField.getText().isEmpty() || !superficieInserimentoComuniTextField.getText().matches("[0-9]+") || Integer.parseInt(superficieInserimentoComuniTextField.getText()) <= 0, superficieInserimentoComuniTextField.textProperty());

        //controllo id gia usati
        BooleanBinding codiceIstatModificaComuniTextFieldValid = Bindings.createBooleanBinding(() -> codiceIstatModificaComuniTextField.getText().isEmpty() || !codiceIstatModificaComuniTextField.getText().matches("[0-9]+") || codiceIstatModificaComuniTextField.getText().length() != 6, codiceIstatModificaComuniTextField.textProperty());

        BooleanBinding nomeModificaComuniTextFieldValid = Bindings.createBooleanBinding(() -> nomeModificaComuniTextField.getText().isEmpty() || !nomeModificaComuniTextField.getText().matches("[-. ()a-zA-Z'àâßèéêìö/òôóçüù]+"), nomeModificaComuniTextField.textProperty());

        BooleanBinding superficieModificaComuniTextFieldValid = Bindings.createBooleanBinding(() -> superficieModificaComuniTextField.getText().isEmpty() || !superficieModificaComuniTextField.getText().matches("[0-9]+") || Integer.parseInt(superficieModificaComuniTextField.getText()) <= 0, superficieModificaComuniTextField.textProperty());

        provinciaInserisciButton.disableProperty().bind(
                idInserimentoProvinceTextFieldValid
                        .or(nomeInserimentoProvinceTextFieldValid)
                        .or(superficieInserimentoProvinceTextFieldValid)
                        .or(comuneDiCapoluogoInserimentoProvinceComboBox.valueProperty().isNull())
                        .or(regioneInserimentoProvinceComboBox.valueProperty().isNull())
        );

        provinciaModificaButton.disableProperty().bind(
                idModificaProvinceTextFieldValid
                        .or(nomeModificaProvinceTextFieldValid)
                        .or(superficieModificaProvinceTextFieldValid)
                        .or(comuneDiCapoluogoModificaProvinceComboBox.valueProperty().isNull())
                        .or(regioneModificaProvinceComboBox.valueProperty().isNull())
        );

        comuneInserisciButton.disableProperty().bind(
                codiceIstatInserimentoComuniTextFieldValid
                        .or(nomeInserimentoComuniTextFieldValid)
                        .or(superficieInserimentoComuniTextFieldValid)
                        .or(siAffacciaSulMareInserimentoComuniComboBox.valueProperty().isNull())
                        .or(tipoTerritorioInserimentoComuniComboBox.valueProperty().isNull())
                        .or(provinciaInserimentoComuniComboBox.valueProperty().isNull())
                        .or(dataDiIstituzioneInserimentoComuniDatePicker.valueProperty().isNull())
        );
        comuneModificaButton.disableProperty().bind(
                codiceIstatModificaComuniTextFieldValid
                        .or(nomeModificaComuniTextFieldValid)
                        .or(superficieModificaComuniTextFieldValid)
                        .or(siAffacciaSulMareModificaComuniComboBox.valueProperty().isNull())
                        .or(tipoTerritorioModificaComuniComboBox.valueProperty().isNull())
                        .or(provinciaModificaComuniComboBox.valueProperty().isNull())
                        .or(dataDiIstituzioneModificaComuniDatePicker.valueProperty().isNull())
        );
        new Thread(new Task<>() {
            @Override
            protected Void call() {
                List<Provincia> provinceOrdinate = new ArrayList<>(provinciaService.findAll());
                provinceOrdinate.sort(Comparator.comparing(Provincia::getNome));
                for (Provincia provincia : provinceOrdinate) {
                    provinciaInserimentoComuniComboBox.getItems().add(provincia.getNome());
                    provinciaModificaComuniComboBox.getItems().add(provincia.getNome());
                }
                List<Comune> comuniOrdinati = new ArrayList<>(comuneService.findAll());
                comuniOrdinati.sort(Comparator.comparing(Comune::getNome));
                for (Comune comune : comuniOrdinati) {
                    comuneDiCapoluogoInserimentoProvinceComboBox.getItems().add(comune.getNome());
                    comuneDiCapoluogoModificaProvinceComboBox.getItems().add(comune.getNome());
                }
                List<TipoTerritorio> tipoTerritorioOrdinati = new ArrayList<>(tipoTerritorioService.findAll());
                tipoTerritorioOrdinati.sort(Comparator.comparing(TipoTerritorio::getNome));
                for (TipoTerritorio tipoTerritorio : tipoTerritorioOrdinati) {
                    tipoTerritorioInserimentoComuniComboBox.getItems().add(tipoTerritorio.getNome());
                    tipoTerritorioModificaComuniComboBox.getItems().add(tipoTerritorio.getNome());
                }
                List<Regione> regioniOrdinate = new ArrayList<>(regioneService.findAll());
                regioniOrdinate.sort(Comparator.comparing(Regione::getNome));
                for (Regione regione : regioniOrdinate) {
                    regioneInserimentoProvinceComboBox.getItems().add(regione.getNome());
                    regioneModificaProvinceComboBox.getItems().add(regione.getNome());
                    regioniTableView.getItems().add(regione);
                }
                siAffacciaSulMareInserimentoComuniComboBox.getItems().setAll(FXCollections.observableArrayList("Si", "No"));
                siAffacciaSulMareModificaComuniComboBox.getItems().setAll(FXCollections.observableArrayList("Si", "No"));
                updateListProvince();
                updateListComuni();
                Platform.runLater(() -> datiTerritorialiBorderPane.setDisable(false));
                return null;
            }
        }).start();
    }

    @FXML
    private java.util.Date getDataDiIstituzioneModificaComuniDatePicker() {
        LocalDate data = dataDiIstituzioneModificaComuniDatePicker.getValue();
        String[] toParseData = data.toString().split("-");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(toParseData[2] + "/" + toParseData[1] + "/" + toParseData[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private void setDataDiIstituzioneModificaComuniDatePicker(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(data.split(" ")[0], formatter);
        dataDiIstituzioneModificaComuniDatePicker.setValue(localDate);
    }

    @FXML
    private java.util.Date dataDiIstituzioneInserimentoComuniOnClicked() {
        LocalDate data = dataDiIstituzioneInserimentoComuniDatePicker.getValue();
        String[] toParseData = data.toString().split("-");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(toParseData[2] + "/" + toParseData[1] + "/" + toParseData[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @FXML
    private void homepageButtonOnClicked() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        datiTerritorialiTabPane.getScene().setRoot(root);
    }


    private void updateListProvince() {
        provinceTableView.getItems().clear();
        for (Provincia provincia : provinciaService.findAll()) {
            provinceTableView.getItems().add(provincia);
        }
        Platform.runLater(() -> {
            provinceTableView.getSortOrder().remove(provinceNomeColumn);
            provinceTableView.getSortOrder().add(provinceNomeColumn);
        });
        datiTerritorialiTabPane.getSelectionModel().select(0);
        visualizzazioneTabPane.getSelectionModel().select(1);
        provinciaNoDataSelectedLabel.setVisible(false);
    }

    private void updateListComuni() {
        comuniTableView.getItems().clear();
        for (Comune comune : comuneService.findAll()) {
            comuniTableView.getItems().add(comune);
        }
        Platform.runLater(() -> {
            comuniTableView.getSortOrder().remove(comuniNomeColumn);
            comuniTableView.getSortOrder().add(comuniNomeColumn);
        });
        datiTerritorialiTabPane.getSelectionModel().select(0);
        visualizzazioneTabPane.getSelectionModel().select(2);
        comuneNoDataSelectedLabel.setVisible(false);
    }


    @FXML
    private void comuneModificaVisualizzazioneButtonOnClicked() {
        if (comuniTableView.getSelectionModel().getSelectedItem() != null) {
            modificaTab.setDisable(false);
            provinciaModificaTab.setDisable(true);
            visualizzazioneTab.setDisable(true);
            inserimentoTab.setDisable(true);
            Comune comune = comuniTableView.getSelectionModel().getSelectedItem();
            datiTerritorialiTabPane.getSelectionModel().select(2);
            modificaTabPane.getSelectionModel().select(1);
            codiceIstatModificaComuniTextField.setText(comune.getCodiceIstat());
            nomeModificaComuniTextField.setText(comune.getNome());
            superficieModificaComuniTextField.setText(String.valueOf(comune.getSuperficie()));
            setDataDiIstituzioneModificaComuniDatePicker(String.valueOf(comune.getDataIstituzione()));
            siAffacciaSulMareModificaComuniComboBox.getSelectionModel().select(comune.getSiAffacciaSulMare() ? "Si" : "No");
            tipoTerritorioModificaComuniComboBox.getSelectionModel().select(comune.getTipoTerritorio().getNome());
            provinciaModificaComuniComboBox.getSelectionModel().select(comune.getProvincia().getNome());
        } else {
            comuneNoDataSelectedLabel.setVisible(true);
            errorAnimation(comuneNoDataSelectedLabel);
        }
    }

    @FXML
    private void comuneEliminaVisualizzazioneButtonOnClicked() {
        if (comuniTableView.getSelectionModel().getSelectedItem() != null) {
            logger.info("Cancellato record comune: " + comuniTableView.getSelectionModel().getSelectedItem());
            Comune comuni = comuniTableView.getSelectionModel().getSelectedItem();
            comuneService.deleteByCodiceIstat(comuni.getCodiceIstat());
            updateListComuni();
        } else {
            comuneNoDataSelectedLabel.setVisible(true);
            errorAnimation(comuneNoDataSelectedLabel);
        }
    }

    @FXML
    private void provinciaModificaVisualizzazioneButtonOnClicked() {
        if (provinceTableView.getSelectionModel().getSelectedItem() != null) {
            modificaTab.setDisable(false);
            comuneModificaTab.setDisable(true);
            visualizzazioneTab.setDisable(true);
            inserimentoTab.setDisable(true);
            Provincia provincia = provinceTableView.getSelectionModel().getSelectedItem();
            datiTerritorialiTabPane.getSelectionModel().select(2);
            modificaTabPane.getSelectionModel().select(0);
            idModificaProvinceTextField.setText(String.valueOf(provincia.getId()));
            nomeModificaProvinceTextField.setText(provincia.getNome());
            superficieModificaProvinceTextField.setText(String.valueOf(provincia.getSuperficie()));
            comuneDiCapoluogoModificaProvinceComboBox.getSelectionModel().select(comuneService.findByCodiceIstat(provincia.getCapoluogo()).getNome());
            regioneModificaProvinceComboBox.getSelectionModel().select(provincia.getRegione().getNome());
        } else {
            provinciaNoDataSelectedLabel.setVisible(true);
            errorAnimation(provinciaNoDataSelectedLabel);
        }
    }

    @FXML
    private void provinciaEliminaVisualizzazioneButtonOnClicked() {
        if (provinceTableView.getSelectionModel().getSelectedItem() != null) {
            logger.info("Cancellato record provincia: " + provinceTableView.getSelectionModel().getSelectedItem());
            Provincia provincia = provinceTableView.getSelectionModel().getSelectedItem();
            provinciaService.deleteById(provincia.getId());
            updateListProvince();
        } else {
            provinciaNoDataSelectedLabel.setVisible(true);
            errorAnimation(provinciaNoDataSelectedLabel);
        }
    }

    @FXML
    public void inserisciInserimentoProvinceButtonOnClicked() {
        Provincia provincia = new Provincia(
                Integer.parseInt(idInserimentoProvinceTextField.getText()),
                nomeInserimentoProvinceTextField.getText(),
                Integer.parseInt(superficieInserimentoProvinceTextField.getText()),
                comuneService.findByNome(comuneDiCapoluogoInserimentoProvinceComboBox.getValue()).getCodiceIstat(),
                regioneService.findByNome(regioneInserimentoProvinceComboBox.getValue())
        );
        if (provinciaService.findById(provincia.getId()) == null) {
            provinciaService.save(provincia);
            if (provinciaService.findById(provincia.getId()) != null) {
                logger.info("Inserito record provincia: " + provincia);
                idInserimentoProvinceTextField.clear();
                nomeInserimentoProvinceTextField.clear();
                superficieInserimentoProvinceTextField.clear();
                comuneDiCapoluogoInserimentoProvinceComboBox.getValue();
                regioneInserimentoProvinceComboBox.getSelectionModel().clearSelection();
            }
            updateListProvince();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Errore inserimento Provincia");
            alert.setHeaderText(null);
            alert.setContentText("Provincia con id: " + provincia.getId() + " già presente nel database");
            alert.showAndWait();
        }
    }

    @FXML
    public void inserisciCsvInserimentoProvinceButtonOnClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            loadingBarProvince.setVisible(true);
            datiTerritorialiBorderPane.setDisable(true);

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Errori inserimento CSV province");
            alert.setHeaderText(null);

            new Thread(new Task<>() {
                @Override
                protected Void call() {
                    int[] ritornoErrori = new EtlProvincia().load(selectedFile.getPath());
                    updateListProvince();
                    Platform.runLater(() -> {
                        alert.setContentText("Righe con errore: " + ritornoErrori[0] + "\n" + "Righe non lette: " + ritornoErrori[1]);
                        if (ritornoErrori[0] > 0 || ritornoErrori[1] > 0) {
                            alert.showAndWait();
                        }
                        loadingBarProvince.setVisible(false);
                        datiTerritorialiBorderPane.setDisable(false);
                    });
                    return null;
                }
            }).start();
            logger.info("Inserito csv province");
        } else {
            logger.error("File non selezionato");
        }
    }

    @FXML
    private void modificaModificaProvinciaButtonOnClicked() {
        Integer idNonModificato = provinceTableView.getSelectionModel().getSelectedItem().getId();
        Provincia provincia = new Provincia(
                Integer.parseInt(idModificaProvinceTextField.getText()),
                nomeModificaProvinceTextField.getText(),
                Integer.parseInt(superficieModificaProvinceTextField.getText()),
                comuneService.findByNome(comuneDiCapoluogoModificaProvinceComboBox.getValue()).getCodiceIstat(),
                regioneService.findByNome(regioneModificaProvinceComboBox.getValue())
        );
        if (provinciaService.findById(provincia.getId()) == null || provinciaService.findById(idNonModificato).getId().equals(provincia.getId())) {
            provinciaService.update(provincia);

            if (provinciaService.findById(provincia.getId()) != null) {
                logger.info("Modificato record provincia: " + provincia);
                idInserimentoProvinceTextField.clear();
                nomeInserimentoProvinceTextField.clear();
                superficieInserimentoProvinceTextField.clear();
                comuneDiCapoluogoInserimentoProvinceComboBox.getValue();
                regioneInserimentoProvinceComboBox.getSelectionModel().clearSelection();
            }
            datiTerritorialiTabPane.getSelectionModel().select(0);
            modificaTab.setDisable(true);
            visualizzazioneTab.setDisable(false);
            inserimentoTab.setDisable(false);
            updateListProvince();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Errore modifica Provincia");
            alert.setHeaderText(null);
            alert.setContentText("Stai cercando di modificare una provincia già esistente con id: " + provincia.getId());
            alert.showAndWait();
        }
    }

    @FXML
    public void inserisciInserimentoComuniButtonOnClicked() {
        Comune comune = new Comune(
                codiceIstatInserimentoComuniTextField.getText(),
                nomeInserimentoComuniTextField.getText(),
                Integer.parseInt(superficieInserimentoComuniTextField.getText()),
                dataDiIstituzioneInserimentoComuniOnClicked(),
                siAffacciaSulMareInserimentoComuniComboBox.getValue().equals("Si"),
                tipoTerritorioService.findByNome(tipoTerritorioInserimentoComuniComboBox.getValue()),
                provinciaService.findByNome(provinciaInserimentoComuniComboBox.getValue())
        );
        if (comuneService.findByCodiceIstat(comune.getCodiceIstat()) == null) {
            comuneService.save(comune);
            if (comuneService.findByCodiceIstat(comune.getCodiceIstat()) != null) {
                logger.info("Inserito record comune: " + comune);
                codiceIstatInserimentoComuniTextField.clear();
                nomeInserimentoComuniTextField.clear();
                superficieInserimentoComuniTextField.clear();
                dataDiIstituzioneInserimentoComuniDatePicker.getEditor().clear();
                siAffacciaSulMareInserimentoComuniComboBox.getSelectionModel().clearSelection();
                tipoTerritorioInserimentoComuniComboBox.getSelectionModel().clearSelection();
                provinciaInserimentoComuniComboBox.getSelectionModel().clearSelection();
            }
            updateListComuni();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Errore inserimento Comune");
            alert.setHeaderText(null);
            alert.setContentText("Comune con codice istat: " + comune.getCodiceIstat() + " già presente nel database");
            alert.showAndWait();
        }
    }

    @FXML
    public void inserisciCsvInserimentoComuniButtonOnClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            loadingBarComuni.setVisible(true);
            datiTerritorialiBorderPane.setDisable(true);

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Errori inserimento CSV comuni");
            alert.setHeaderText(null);

            new Thread(new Task<>() {
                @Override
                protected Void call() {
                    int[] ritornoErrori = new EtlComune().load(selectedFile.getPath());
                    updateListComuni();

                    Platform.runLater(() -> {
                        alert.setContentText("Righe con errore: " + ritornoErrori[0] + "\n" + "Righe non lette: " + ritornoErrori[1]);
                        if (ritornoErrori[0] > 0 || ritornoErrori[1] > 0) {
                            alert.showAndWait();
                        }
                        loadingBarComuni.setVisible(false);
                        datiTerritorialiBorderPane.setDisable(false);
                    });
                    return null;
                }
            }).start();

            logger.info("Inserito csv comuni");
        } else {
            logger.error("File non selezionato");
        }
    }

    @FXML
    private void modificaModificaComuneButtonOnClicked() {
        String codiceIstatNonModificato = comuniTableView.getSelectionModel().getSelectedItem().getCodiceIstat();
        Comune comune = new Comune(
                codiceIstatModificaComuniTextField.getText(),
                nomeModificaComuniTextField.getText(),
                Integer.parseInt(superficieModificaComuniTextField.getText()),
                getDataDiIstituzioneModificaComuniDatePicker(),
                siAffacciaSulMareModificaComuniComboBox.getValue().equals("Si"),
                tipoTerritorioService.findByNome(tipoTerritorioModificaComuniComboBox.getValue()),
                provinciaService.findByNome(provinciaModificaComuniComboBox.getValue())
        );
        if (comuneService.findByCodiceIstat(comune.getCodiceIstat()) == null || comuneService.findByCodiceIstat(codiceIstatNonModificato).getCodiceIstat().equals(comune.getCodiceIstat())) {
            comuneService.update(comune);
            if (comuneService.findByCodiceIstat(comune.getCodiceIstat()) != null) {
                logger.info("Modificato record comune: " + comune);
                codiceIstatInserimentoComuniTextField.clear();
                nomeInserimentoComuniTextField.clear();
                superficieInserimentoComuniTextField.clear();
                dataDiIstituzioneInserimentoComuniDatePicker.getEditor().clear();
                siAffacciaSulMareInserimentoComuniComboBox.getSelectionModel().clearSelection();
                tipoTerritorioInserimentoComuniComboBox.getSelectionModel().clearSelection();
                provinciaInserimentoComuniComboBox.getSelectionModel().clearSelection();
            }
            datiTerritorialiTabPane.getSelectionModel().select(0);
            modificaTab.setDisable(true);
            visualizzazioneTab.setDisable(false);
            inserimentoTab.setDisable(false);
            updateListComuni();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Errore modifica Comune");
            alert.setHeaderText(null);
            alert.setContentText("Stai cercando di modificare un comune già esistente con codice istat: " + comune.getCodiceIstat());
            alert.showAndWait();
        }
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


