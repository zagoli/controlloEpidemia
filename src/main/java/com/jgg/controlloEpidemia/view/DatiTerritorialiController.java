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
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class DatiTerritorialiController implements Initializable {

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
    private Tab regioniTab;
    @FXML
    private Tab provinceVisualizzazioneTab;
    @FXML
    private Tab provinciaInserimentoTab;
    @FXML
    private Tab provinciaModificaTab;
    @FXML
    private Tab comuniVisualizzazioneTab;
    @FXML
    private Tab comuneInserimentoTab;
    @FXML
    private Tab comuneModificaTab;
    @FXML
    private ListView<String> regioniListView;
    @FXML
    private ListView<String> comuniListView;
    @FXML
    private ListView<String> provinceListView;
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

    public void initialize(URL location, ResourceBundle resources) {
        List<Provincia> provinciaList = provinciaService.findAll();
        for (Provincia p : provinciaList) {
            provinciaInserimentoComuniComboBox.getItems().add(p.getNome());
            provinciaModificaComuniComboBox.getItems().add(p.getNome());
        }
        List<Comune> comuneList = comuneService.findAll();
        for (Comune c : comuneList) {
            comuneDiCapoluogoInserimentoProvinceComboBox.getItems().add(c.getNome());
            comuneDiCapoluogoModificaProvinceComboBox.getItems().add(c.getNome());
        }
        List<TipoTerritorio> tipoTerritorioList = tipoTerritorioService.findAll();
        for (TipoTerritorio t : tipoTerritorioList) {
            tipoTerritorioInserimentoComuniComboBox.getItems().add(t.getNome());
            tipoTerritorioModificaComuniComboBox.getItems().add(t.getNome());
        }
        List<Regione> regioneList = regioneService.findAll();
        regioniListView.getItems().add("ID  NOME  SUPERFICIE  CAPOLUOGO");
        String c;
        Comune capoluogo;
        for (Regione r : regioneList) {
            regioneInserimentoProvinceComboBox.getItems().add(r.getNome());
            regioneModificaProvinceComboBox.getItems().add(r.getNome());
            capoluogo = comuneService.findByCodiceIstat(r.getCapoluogo());
            c = "null";
            if (capoluogo != null) {
                c = capoluogo.getNome();
            }
            regioniListView.getItems().add(r.getId() + "  " + r.getNome() + "  " + r.getSuperficie() + "  " + c);
        }
        updateListComuni();
        updateListProvince();
        siAffacciaSulMareInserimentoComuniComboBox.getItems().setAll(FXCollections.observableArrayList("Si", "No"));
        siAffacciaSulMareModificaComuniComboBox.getItems().setAll(FXCollections.observableArrayList("Si", "No"));
        modificaTab.setDisable(true);
        datiTerritorialiTabPane.getSelectionModel().select(0);
        visualizzazioneTabPane.getSelectionModel().select(0);
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
        LocalDate localDate = LocalDate.parse(data, formatter);
        dataDiIstituzioneModificaComuniDatePicker.setValue(localDate);
    }

    @FXML
    private void onHomepageButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) event.getSource()).getScene().setRoot(root);
    }

    private void updateListComuni() {
        comuniListView.getItems().clear();
        List<Comune> comuniList = comuneService.findAll();
        comuniListView.getItems().add("CODICE ISTAT  NOME  SUPERFICIE  DATA ISTITUZIONE  SI AFFACCIA SUL MARE  TIPO TERRITORIO  PROVINCIA");
        String siAffacciaSulMare;
        for (Comune c : comuniList) {
            siAffacciaSulMare = "Si";
            if (c.getSiAffacciaSulMare()) {
                siAffacciaSulMare = "No";
            }
            comuniListView.getItems().add(
                    c.getCodiceIstat() + "  " +
                            c.getNome() + "  " +
                            c.getSuperficie() + "  " +
                            c.getDataIstituzione().toString().split(" ")[0] + "  " +
                            siAffacciaSulMare + "  " +
                            c.getTipoTerritorio().getNome() + "  " +
                            c.getProvincia().getNome());
        }
        datiTerritorialiTabPane.getSelectionModel().select(0);
        visualizzazioneTabPane.getSelectionModel().select(2);
        comuneNoDataSelectedLabel.setVisible(false);
    }

    private void updateListProvince() {
        String capoluogo;
        Comune capoluogoDb;
        provinceListView.getItems().clear();
        List<Provincia> provinciaList = provinciaService.findAll();
        provinceListView.getItems().add("ID  NOME  SUPERFICIE  CAPOLUOGO  REGIONE");
        for (Provincia p : provinciaList) {
            capoluogo = "null";
            capoluogoDb = comuneService.findByCodiceIstat(p.getCapoluogo());
            if (capoluogoDb != null) {
                capoluogo = capoluogoDb.getNome();
            }
            provinceListView.getItems().add(
                    p.getId() + "  " +
                            p.getNome() + "  " +
                            p.getSuperficie() + "  " +
                            capoluogo + "  " +
                            p.getRegione().getNome());
        }
        datiTerritorialiTabPane.getSelectionModel().select(0);
        visualizzazioneTabPane.getSelectionModel().select(1);
        provinciaNoDataSelectedLabel.setVisible(false);
    }

    @FXML
    private java.util.Date dataDiIstituzioneInserimentoComuniOnClicked() throws ParseException {
        LocalDate data = dataDiIstituzioneInserimentoComuniDatePicker.getValue();
        String[] toParseData = data.toString().split("-");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(toParseData[2] + "/" + toParseData[1] + "/" + toParseData[0]);
    }

    @FXML
    private void comuneModificaVisualizzazioneButtonOnClicked() {
        if (comuniListView.getSelectionModel().getSelectedIndex() != 0) {
            modificaTab.setDisable(false);
            provinciaModificaTab.setDisable(true);
            visualizzazioneTab.setDisable(true);
            inserimentoTab.setDisable(true);
            String comune = comuniListView.getSelectionModel().getSelectedItem();
            String[] comuneEntry = comune.split("\\s{2}+");
            datiTerritorialiTabPane.getSelectionModel().select(2);
            modificaTabPane.getSelectionModel().select(1);
            codiceIstatModificaComuniTextField.setText(comuneEntry[0]);
            nomeModificaComuniTextField.setText(comuneEntry[1]);
            superficieModificaComuniTextField.setText(comuneEntry[2]);
            setDataDiIstituzioneModificaComuniDatePicker(comuneEntry[3]);
            siAffacciaSulMareModificaComuniComboBox.getSelectionModel().select(comuneEntry[4]);
            tipoTerritorioModificaComuniComboBox.getSelectionModel().select(comuneEntry[5]);
            provinciaModificaComuniComboBox.getSelectionModel().select(comuneEntry[6]);
        } else {
            comuneNoDataSelectedLabel.setVisible(true);
            errorAnimation(comuneNoDataSelectedLabel);
        }
    }

    @FXML
    private void provinciaModificaVisualizzazioneButtonOnClicked() {
        if (provinceListView.getSelectionModel().getSelectedIndex() != 0) {
            modificaTab.setDisable(false);
            comuneModificaTab.setDisable(true);
            visualizzazioneTab.setDisable(true);
            inserimentoTab.setDisable(true);
            String provincia = provinceListView.getSelectionModel().getSelectedItem();
            String[] provinciaEntry = provincia.split("\\s{2}");
            datiTerritorialiTabPane.getSelectionModel().select(2);
            modificaTabPane.getSelectionModel().select(0);
            idModificaProvinceTextField.setText(provinciaEntry[0]);
            nomeModificaProvinceTextField.setText(provinciaEntry[1]);
            superficieModificaProvinceTextField.setText(provinciaEntry[2]);
            comuneDiCapoluogoModificaProvinceComboBox.getSelectionModel().select(provinciaEntry[3]);
            regioneModificaProvinceComboBox.getSelectionModel().select(provinciaEntry[4]);
        } else {
            provinciaNoDataSelectedLabel.setVisible(true);
            errorAnimation(provinciaNoDataSelectedLabel);
        }
    }

    @FXML
    private void comuneEliminaVisualizzazioneButtonOnClicked() {
        if (comuniListView.getSelectionModel().getSelectedIndex() != 0) {
            String comuni = comuniListView.getSelectionModel().getSelectedItem();
            String[] comuniEntry = comuni.split("\\s{2}");
            comuneService.deleteByCodiceIstat(comuniEntry[0]);
            updateListComuni();
        } else {
            comuneNoDataSelectedLabel.setVisible(true);
            errorAnimation(comuneNoDataSelectedLabel);
        }
    }

    @FXML
    private void provinciaEliminaVisualizzazioneButtonOnClicked() {
        if (provinceListView.getSelectionModel().getSelectedIndex() != 0) {
            String provincia = provinceListView.getSelectionModel().getSelectedItem();
            String[] provinciaEntry = provincia.split("\\s{2}");
            provinciaService.deleteById(Integer.parseInt(provinciaEntry[0]));
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
            System.out.println("ok");
            idInserimentoProvinceTextField.clear();
            nomeInserimentoProvinceTextField.clear();
            superficieInserimentoProvinceTextField.clear();
            comuneDiCapoluogoInserimentoProvinceComboBox.getValue();
            regioneInserimentoProvinceComboBox.getSelectionModel().clearSelection();
        }
        updateListProvince();
    }

    @FXML
    public void inserisciCsvInserimentoProvinceButtonOnClicked() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            System.out.println("ok");
            new EtlProvincia().load(selectedFile.getPath());
        } else {
            System.out.println("non ho trovato il file");
        }
        updateListProvince();
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
            System.out.println("ok");
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
    public void inserisciCsvInserimentoComuniButtonOnClicked() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            System.out.println("ok");
            new EtlComune().load(selectedFile.getPath());
        } else {
            System.out.println("non ho trovato il file");
        }
        updateListComuni();
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
            System.out.println("ok");
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
            System.out.println("ok");
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


