package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import com.jgg.controlloEpidemia.service.RegioneService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VisualizzaDecessiController implements Initializable {


    @FXML
    private TabPane decessiAnnualiTabPane;

    @FXML
    private Tab decessiAnnualiVisualizzazioneTab;

    @FXML
    private TableView<DecessiAnnuali> decessiTableView;

    @FXML
    private TableColumn<DecessiAnnuali,Integer> decessiIdColumn;

    @FXML
    private TableColumn<DecessiAnnuali, Provincia> decessiProvinciaColumn;

    @FXML
    private TableColumn<DecessiAnnuali,Integer> decessiAnnoColumn;

    @FXML
    private TableColumn<DecessiAnnuali,Integer> decessiIncidentiStradaliColumn;

    @FXML
    private TableColumn<DecessiAnnuali,Integer> decessiMalattieTumoraliColumn;

    @FXML
    private TableColumn<DecessiAnnuali,Integer> decessiMalattieCardiovascolariColumn;

    @FXML
    private TableColumn<DecessiAnnuali,Integer> decessiMalattieContagioseColumn;

    @FXML
    private Button visualizzaDecessiAggregaPerRegioneButton;

    @FXML
    private Button visualizzaDecessiVisualizzaDatiButton;

    private final DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();

    private final RegioneService regioneService = new RegioneService();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        decessiIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        decessiAnnoColumn.setCellValueFactory(new PropertyValueFactory<>("anno"));
        decessiProvinciaColumn.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        decessiProvinciaColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Provincia provincia, boolean empty) {
                super.updateItem(provincia, empty);
                if (empty || provincia == null) {
                    setText("");
                }
            else {
                    setText(provincia.getNome());
                 }
                }
            });
        decessiIncidentiStradaliColumn.setCellValueFactory(new PropertyValueFactory<>("incidentiStradali"));
        decessiMalattieTumoraliColumn.setCellValueFactory(new PropertyValueFactory<>("malattieTumorali"));
        decessiMalattieContagioseColumn.setCellValueFactory(new PropertyValueFactory<>("malattieContagiose"));
        decessiMalattieCardiovascolariColumn.setCellValueFactory(new PropertyValueFactory<>("malattieCardiovascolari"));
        updateListVisualizzaDati();

    }


    @FXML
    private void visualizzaDecessiAggregaPerRegioneButtonOnClicked(){

        decessiTableView.getItems().clear();
        decessiProvinciaColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Provincia provincia, boolean empty) {
                super.updateItem(provincia, empty);
                if (empty || provincia == null) {
                    setText("");
                }
                else {
                    setText(provincia.getRegione().getNome());
                }
            }
        });


        decessiProvinciaColumn.setText("REGIONE");
        List<Regione> regioniList = regioneService.findAll();
        List<DecessiAnnuali> decessiAnnualiList = decessiAnnualiService.findAll();
        List<Integer> anniList = new ArrayList<>();

        Provincia p;
        Integer incidenti;
        Integer tumorali;
        Integer cardiovascolari;
        Integer contagiose;
        Integer id=0;
        Integer incidentiNazionale;
        Integer tumoraliNazionale;
        Integer cardiovascolariNazionale;
        Integer contagioseNazionale;

        Regione rF = new Regione(777,"Nazionale",0,"999999");
        Provincia pF = new Provincia("Provincia",0,"999999", rF);


        for(DecessiAnnuali d : decessiAnnualiList){
            if(!anniList.contains(d.getAnno())){
                anniList.add(d.getAnno());
            }
        }

        for(Integer anno: anniList){
            incidentiNazionale=0;
            tumoraliNazionale=0;
            cardiovascolariNazionale=0;
            contagioseNazionale=0;
            for (Regione r: regioniList){
                incidenti=0;
                tumorali=0;
                cardiovascolari=0;
                contagiose=0;
                p=null;
                id++;
                for(DecessiAnnuali d: decessiAnnualiList){
                    if(d.getProvincia().getRegione().getId().equals(r.getId()) && d.getAnno().equals(anno)){
                        incidenti+=d.getIncidentiStradali();
                        tumorali+=d.getMalattieTumorali();
                        cardiovascolari+=d.getMalattieCardiovascolari();
                        contagiose+=d.getMalattieContagiose();
                        incidentiNazionale+=d.getIncidentiStradali();
                        tumoraliNazionale+=d.getMalattieTumorali();
                        cardiovascolariNazionale+=d.getMalattieCardiovascolari();
                        contagioseNazionale+=d.getMalattieContagiose();
                        p=d.getProvincia();
                    }
                }
                if(p!=null){
                    DecessiAnnuali decessiRegione = new DecessiAnnuali(id,anno,incidenti,tumorali,cardiovascolari,contagiose,p);
                    decessiTableView.getItems().add(decessiRegione);
                }
            }
            id++;
            DecessiAnnuali decessiNazione = new DecessiAnnuali(id,anno,incidentiNazionale,tumoraliNazionale,cardiovascolariNazionale,contagioseNazionale,pF);
            decessiTableView.getItems().add(decessiNazione);

        }



    }

    @FXML
    private void visualizzaDecessiVisualizzaDatiButtonOnClicked(){
            updateListVisualizzaDati();
    }

    private void updateListVisualizzaDati(){
        decessiProvinciaColumn.setText("PROVINCIA");
        decessiTableView.getItems().clear();
        decessiProvinciaColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Provincia provincia, boolean empty) {
                super.updateItem(provincia, empty);
                if (empty || provincia == null) {
                    setText("");
                }
                else {
                    setText(provincia.getNome());
                }
            }
        });
        List<DecessiAnnuali> decessiAnnualiList = decessiAnnualiService.findAll();
        for(DecessiAnnuali d : decessiAnnualiList){
            decessiTableView.getItems().add(d);

        }
    }


    @FXML
    private void homepageButtonOnClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) event.getSource()).getScene().setRoot(root);
    }


}
