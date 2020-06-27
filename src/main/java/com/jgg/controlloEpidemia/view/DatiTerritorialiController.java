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
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class DatiTerritorialiController implements Initializable {

    static Logger logger = Logger.getLogger(DatiTerritorialiController.class);

    private final ProvinciaService provinciaService = new ProvinciaService();
    private final ComuneService comuneService = new ComuneService();
    private final TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
    private final RegioneService regioneService = new RegioneService();

    @FXML
    private Label comuneNoDataSelectedLabel;
    @FXML
    private Label provinciaNoDataSelectedLabel;
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
    private BorderPane mainPane;

    public void initialize(URL location, ResourceBundle resources) {
        for (Provincia provincia : provinciaService.findAll()) {
            provinciaInserimentoComuniComboBox.getItems().add(provincia.getNome());
            provinciaModificaComuniComboBox.getItems().add(provincia.getNome());
        }
        for (Comune comune : comuneService.findAll()) {
            comuneDiCapoluogoInserimentoProvinceComboBox.getItems().add(comune.getNome());
            comuneDiCapoluogoModificaProvinceComboBox.getItems().add(comune.getNome());
        }
        for (TipoTerritorio tipoTerritorio : tipoTerritorioService.findAll()) {
            tipoTerritorioInserimentoComuniComboBox.getItems().add(tipoTerritorio.getNome());
            tipoTerritorioModificaComuniComboBox.getItems().add(tipoTerritorio.getNome());
        }
        for (Regione regione : regioneService.findAll()) {
            regioneInserimentoProvinceComboBox.getItems().add(regione.getNome());
            regioneModificaProvinceComboBox.getItems().add(regione.getNome());
            regioniTableView.getItems().add(regione);
        }
        siAffacciaSulMareInserimentoComuniComboBox.getItems().setAll(FXCollections.observableArrayList("Si", "No"));
        siAffacciaSulMareModificaComuniComboBox.getItems().setAll(FXCollections.observableArrayList("Si", "No"));

        datiTerritorialiTabPane.getSelectionModel().select(0);
        visualizzazioneTabPane.getSelectionModel().select(0);
        regioniIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        regioniNomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        regioniSuperficieColumn.setCellValueFactory(new PropertyValueFactory<>("superficie"));
        regioniCapoluogoColumn.setCellValueFactory(new PropertyValueFactory<>("capoluogo"));

        provinceIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        provinceNomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
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

        modificaTab.setDisable(true);
        comuneNoDataSelectedLabel.setVisible(false);
        provinciaNoDataSelectedLabel.setVisible(false);
        provinciaInserisciButton.disableProperty().bind(
                Bindings.isEmpty(idInserimentoProvinceTextField.textProperty())
                        .or(Bindings.isEmpty(nomeInserimentoProvinceTextField.textProperty()))
                        .or(Bindings.isEmpty(superficieInserimentoProvinceTextField.textProperty()))
                        .or(comuneDiCapoluogoInserimentoProvinceComboBox.valueProperty().isNull())
                        .or(regioneInserimentoProvinceComboBox.valueProperty().isNull())
        );
        provinciaModificaButton.disableProperty().bind(
                Bindings.isEmpty(idModificaProvinceTextField.textProperty())
                        .or(Bindings.isEmpty(nomeModificaProvinceTextField.textProperty()))
                        .or(Bindings.isEmpty(superficieModificaProvinceTextField.textProperty()))
                        .or(comuneDiCapoluogoModificaProvinceComboBox.valueProperty().isNull())
                        .or(regioneModificaProvinceComboBox.valueProperty().isNull())
        );
        comuneInserisciButton.disableProperty().bind(
                Bindings.isEmpty(codiceIstatInserimentoComuniTextField.textProperty())
                        .or(Bindings.isEmpty(nomeInserimentoComuniTextField.textProperty()))
                        .or(Bindings.isEmpty(superficieInserimentoComuniTextField.textProperty()))
                        .or(siAffacciaSulMareInserimentoComuniComboBox.valueProperty().isNull())
                        .or(tipoTerritorioInserimentoComuniComboBox.valueProperty().isNull())
                        .or(provinciaInserimentoComuniComboBox.valueProperty().isNull())
                        .or(dataDiIstituzioneInserimentoComuniDatePicker.valueProperty().isNull())
        );
        comuneModificaButton.disableProperty().bind(
                Bindings.isEmpty(codiceIstatModificaComuniTextField.textProperty())
                        .or(Bindings.isEmpty(nomeModificaComuniTextField.textProperty()))
                        .or(Bindings.isEmpty(superficieModificaComuniTextField.textProperty()))
                        .or(siAffacciaSulMareModificaComuniComboBox.valueProperty().isNull())
                        .or(tipoTerritorioModificaComuniComboBox.valueProperty().isNull())
                        .or(provinciaModificaComuniComboBox.valueProperty().isNull())
                        .or(dataDiIstituzioneModificaComuniDatePicker.valueProperty().isNull())
        );
        updateListProvince();
        updateListComuni();
        datiTerritorialiTabPane.getSelectionModel().select(0);
        visualizzazioneTabPane.getSelectionModel().select(0);
    }

    @FXML
    private java.util.Date getDataDiIstituzioneModificaComuniDatePicker() throws ParseException {
        LocalDate data = dataDiIstituzioneModificaComuniDatePicker.getValue();
        String[] toParseData = data.toString().split("-");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(toParseData[2] + "/" + toParseData[1] + "/" + toParseData[0]);
    }

    private void setDataDiIstituzioneModificaComuniDatePicker(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(data.split(" ")[0], formatter);
        dataDiIstituzioneModificaComuniDatePicker.setValue(localDate);
    }

    @FXML
    private java.util.Date dataDiIstituzioneInserimentoComuniOnClicked() throws ParseException {
        LocalDate data = dataDiIstituzioneInserimentoComuniDatePicker.getValue();
        String[] toParseData = data.toString().split("-");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(toParseData[2] + "/" + toParseData[1] + "/" + toParseData[0]);
    }

    @FXML
    private void onHomepageButtonClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        datiTerritorialiTabPane.getScene().setRoot(root);
    }

    private void updateListComuni() {
        comuniTableView.getItems().clear();
        for (Comune comune : comuneService.findAll()) {
            comuniTableView.getItems().add(comune);
        }
        datiTerritorialiTabPane.getSelectionModel().select(0);
        visualizzazioneTabPane.getSelectionModel().select(2);
        comuneNoDataSelectedLabel.setVisible(false);
    }

    private void updateListProvince() {
        provinceTableView.getItems().clear();
        for (Provincia provincia : provinciaService.findAll()) {
            provinceTableView.getItems().add(provincia);
        }
        datiTerritorialiTabPane.getSelectionModel().select(0);
        visualizzazioneTabPane.getSelectionModel().select(1);
        provinciaNoDataSelectedLabel.setVisible(false);
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
            mainPane.setDisable(true);

            Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    new EtlProvincia().load(selectedFile.getPath());
                    updateListProvince();
                    Platform.runLater(() -> {
                        loadingBarProvince.setVisible(false);
                        mainPane.setDisable(false);
                    });
                    return null;
                }
            };
            new Thread(task).start();
            logger.info("Inserito csv province");
        } else {
            logger.error("File non selezionato");
        }
    }

    @FXML
    public void inserisciInserimentoComuniButtonOnClicked() throws ParseException {
        Comune comune = new Comune(
                codiceIstatInserimentoComuniTextField.getText(),
                nomeInserimentoComuniTextField.getText(),
                Integer.parseInt(superficieInserimentoComuniTextField.getText()),
                dataDiIstituzioneInserimentoComuniOnClicked(),
                siAffacciaSulMareInserimentoComuniComboBox.getValue().equals("Si"),
                tipoTerritorioService.findByNome(tipoTerritorioInserimentoComuniComboBox.getValue()),
                provinciaService.findByNome(provinciaInserimentoComuniComboBox.getValue())
        );
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
            mainPane.setDisable(true);

            Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    new EtlComune().load(selectedFile.getPath());
                    updateListComuni();
                    Platform.runLater(() -> {
                        loadingBarComuni.setVisible(false);
                        mainPane.setDisable(false);
                    });
                    return null;
                }
            };
            new Thread(task).start();
            logger.info("Inserito csv comuni");
        } else {
            logger.error("File non selezionato");
        }
    }

    @FXML
    private void modificaModificaProvinciaButtonOnClicked() {
        Provincia provincia = new Provincia(
                Integer.parseInt(idModificaProvinceTextField.getText()),
                nomeModificaProvinceTextField.getText(),
                Integer.parseInt(superficieModificaProvinceTextField.getText()),
                comuneService.findByNome(comuneDiCapoluogoModificaProvinceComboBox.getValue()).getCodiceIstat(),
                regioneService.findByNome(regioneModificaProvinceComboBox.getValue())
        );
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
    }

    @FXML
    private void modificaModificaComuneButtonOnClicked() throws ParseException {
        Comune comune = new Comune(
                codiceIstatModificaComuniTextField.getText(),
                nomeModificaComuniTextField.getText(),
                Integer.parseInt(superficieModificaComuniTextField.getText()),
                getDataDiIstituzioneModificaComuniDatePicker(),
                siAffacciaSulMareModificaComuniComboBox.getValue().equals("Si"),
                tipoTerritorioService.findByNome(tipoTerritorioModificaComuniComboBox.getValue()),
                provinciaService.findByNome(provinciaModificaComuniComboBox.getValue())
        );
        comuneService.update(comune);
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
        datiTerritorialiTabPane.getSelectionModel().select(0);
        modificaTab.setDisable(true);
        visualizzazioneTab.setDisable(false);
        inserimentoTab.setDisable(false);
        updateListComuni();
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


