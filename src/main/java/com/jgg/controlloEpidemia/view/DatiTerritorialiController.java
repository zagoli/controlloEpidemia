package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.importData.EtlComune;
import com.jgg.controlloEpidemia.importData.EtlProvincia;
import com.jgg.controlloEpidemia.model.*;
import com.jgg.controlloEpidemia.service.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class DatiTerritorialiController implements Initializable {


    @FXML
    private Tab visualizzazioneTab;

    @FXML
    private Tab regioniTab;

    @FXML
    private ListView<String> regioniListView;

    @FXML
    private ListView<String> comuniListView;

    @FXML
    private ListView<String> provinceListView;

    @FXML
    private Tab provinceVisualizzazioneTab;

    @FXML
    private Tab comuniVisualizzazioneTab;

    @FXML
    private Tab inserimentoTab;

    @FXML
    private Tab provinciaInserimentoTab;

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
    private Tab comuneInserimentoTab;

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
    private Tab modificaTab;

    @FXML
    private Tab provinciaModificaTab;

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
    private Tab comuneModificaTab;

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


    private int selectedId;

    public void initialize(URL location, ResourceBundle resources) {
        ProvinciaService provinciaService = new ProvinciaService();
        List<Provincia> provinciaList = provinciaService.findAll();
        for (Provincia p : provinciaList) {
            provinciaInserimentoComuniComboBox.getItems().add(p.getNome());
            provinciaModificaComuniComboBox.getItems().add(p.getNome());
        }
        ComuneService comuneService = new ComuneService();
        List<Comune> comuneList = comuneService.findAll();
        for (Comune c : comuneList) {
            comuneDiCapoluogoInserimentoProvinceComboBox.getItems().add(c.getNome());
            comuneDiCapoluogoModificaProvinceComboBox.getItems().add(c.getNome());
        }
        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
        List<TipoTerritorio> tipoTerritorioList = tipoTerritorioService.findAll();
        for (TipoTerritorio t : tipoTerritorioList) {
            tipoTerritorioInserimentoComuniComboBox.getItems().add(t.getNome());
            tipoTerritorioModificaComuniComboBox.getItems().add(t.getNome());
        }
        RegioneService regioneService = new RegioneService();
        List<Regione> regioneList = regioneService.findAll();
        regioniListView.getItems().add("ID|NOME|SUPERFICIE|CAPOLUOGO");
        String c;
        Comune capoluogo;
        for (Regione r : regioneList) {
            regioneInserimentoProvinceComboBox.getItems().add(r.getNome());
            regioneModificaProvinceComboBox.getItems().add(r.getNome());
            capoluogo=comuneService.findByCodiceIstat(r.getCapoluogo());
            c="null";
            if(capoluogo!=null){
                c=capoluogo.getNome();
            }
            regioniListView.getItems().add(r.getId()+"|"+r.getNome()+"|"+r.getSuperficie()+"|"+c);
        }
        updateListComuni();
        updateListProvince();
        siAffacciaSulMareInserimentoComuniComboBox.getItems().setAll(FXCollections.observableArrayList("Si", "No"));
        siAffacciaSulMareModificaComuniComboBox.getItems().setAll(FXCollections.observableArrayList("Si", "No"));

        //noDataSelectedLabel.setVisible(false);


    }

    private void updateListComuni(){
        comuniListView.getItems().clear();
        ComuneService comuneService = new ComuneService();
        List<Comune> comuniList = comuneService.findAll();
        comuniListView.getItems().add("CODICE ISTAT|NOME|SUPERFICIE|DATA ISTITUZIONE|SI AFFACCIA SUL MARE|TIPO TERRITORIO|PROVINCIA");
        String siAffacciaSulMare;
        for (Comune c : comuniList) {
            siAffacciaSulMare="si";
            if(c.getSiAffacciaSulMare()){
                siAffacciaSulMare="no";
            }
            comuniListView.getItems().add(
                    c.getCodiceIstat()+"|"+
                            c.getNome()+"|"+
                            c.getSuperficie()+"|"+
                            c.getDataIstituzione()+"|"+
                            siAffacciaSulMare+"|"+
                            c.getTipoTerritorio().getNome()+"|"+
                            c.getProvincia().getNome());
        }
        //noDataSelectedLabel.setVisible(false);
    }

    private void updateListProvince(){
        String c;
        Comune capoluogo;
        ComuneService comuneService = new ComuneService();
        provinceListView.getItems().clear();
        ProvinciaService provinciaService = new ProvinciaService();
        List<Provincia> provinciaList = provinciaService.findAll();
        provinceListView.getItems().add("ID|NOME|SUPERFICIE|CAPOLUOGO|REGIONE");
        for (Provincia p : provinciaList) {
            c="null";
            capoluogo=comuneService.findByCodiceIstat(p.getCapoluogo());
            if(capoluogo!=null){
                c=capoluogo.getNome();
            }
            provinceListView.getItems().add(
                   p.getId()+"|"+
                            p.getNome()+"|"+
                            p.getSuperficie()+"|"+
                            c+"|"+
                            p.getRegione().getNome());
        }
        //noDataSelectedLabel.setVisible(false);
    }




    @FXML
    private void onHomepageButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) event.getSource()).getScene().setRoot(root);
    }

    @FXML
    private java.util.Date dataDiIstituzioneInserimentoComuniOnClicked() throws ParseException {
        LocalDate data = dataDiIstituzioneInserimentoComuniDatePicker.getValue();
        String[] toParseData = data.toString().split("-");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(toParseData[2] + "/" + toParseData[1] + "/" + toParseData[0]);
    }

    @FXML
    public void inserisciInserimentoProvinceButtonOnClicked() {
        ProvinciaService provinciaService = new ProvinciaService();
        RegioneService regioneService = new RegioneService();
        ComuneService comuneService = new ComuneService();
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
    }

    @FXML
    public void inserisciInserimentoComuniButtonOnClicked() throws ParseException {
        ComuneService comuneService = new ComuneService();
        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
        ProvinciaService provinciaService = new ProvinciaService();

        Comune comune = new Comune(
                Integer.parseInt(codiceIstatInserimentoComuniTextField.getText()),
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
    }

    @FXML
    private void modificaModificaProvinciaButtonOnClicked() {
       /* ProvinciaService provinciaService = new ProvinciaService();
        DecessiAnnuali decessiAnnuali = new DecessiAnnuali(
                selectedId,
                Integer.parseInt(annoModificaTextField.getText()),
                Integer.parseInt(incidentiStradaliModificaTextField.getText()),
                Integer.parseInt(malattieTumoraliModificaTextField.getText()),
                Integer.parseInt(malattieCardiovascolariModificaTextField.getText()),
                Integer.parseInt(malattieContagioseModificaTextField.getText()),
                provinciaService.findByNome(provinciaModificaComboBox.getValue())
        );
        DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
        decessiAnnualiService.update(decessiAnnuali);
        if (decessiAnnualiService.findById(decessiAnnuali.getId()) != null) {
            System.out.println("ok");
            annoModificaTextField.clear();
            incidentiStradaliModificaTextField.clear();
            malattieTumoraliModificaTextField.clear();
            malattieCardiovascolariModificaTextField.clear();
            malattieContagioseModificaTextField.clear();
        }
        decessiAnnualiTabPane.getSelectionModel().select(0);
        decessiAnnualiModificaTab.setDisable(true);*/
    }

    @FXML
    private void modificaModificaComuneButtonOnClicked() {
     /*   ProvinciaService provinciaService = new ProvinciaService();
        DecessiAnnuali decessiAnnuali = new DecessiAnnuali(
                selectedId,
                Integer.parseInt(annoModificaTextField.getText()),
                Integer.parseInt(incidentiStradaliModificaTextField.getText()),
                Integer.parseInt(malattieTumoraliModificaTextField.getText()),
                Integer.parseInt(malattieCardiovascolariModificaTextField.getText()),
                Integer.parseInt(malattieContagioseModificaTextField.getText()),
                provinciaService.findByNome(provinciaModificaComboBox.getValue())
        );
        DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
        decessiAnnualiService.update(decessiAnnuali);
        if (decessiAnnualiService.findById(decessiAnnuali.getId()) != null) {
            System.out.println("ok");
            annoModificaTextField.clear();
            incidentiStradaliModificaTextField.clear();
            malattieTumoraliModificaTextField.clear();
            malattieCardiovascolariModificaTextField.clear();
            malattieContagioseModificaTextField.clear();
        }
        decessiAnnualiTabPane.getSelectionModel().select(0);
        decessiAnnualiModificaTab.setDisable(true);*/
    }

}


