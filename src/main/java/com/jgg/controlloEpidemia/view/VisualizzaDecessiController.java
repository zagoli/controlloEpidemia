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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VisualizzaDecessiController implements Initializable {

    private final DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
    private final RegioneService regioneService = new RegioneService();

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        updateListVisualizzaDati();
    }

    @FXML
    private void visualizzaDecessiAggregaPerRegioneButtonOnClicked() {
        decessiAnnualiTableView.getItems().clear();

        decessiAnnualiProvinciaColumn.setText("REGIONE");

        decessiAnnualiProvinciaColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Provincia provincia, boolean empty) {
                super.updateItem(provincia, empty);
                if (empty || provincia == null) {
                    setText("");
                } else {
                    setText(provincia.getRegione().getNome());
                }
            }
        });

        ArrayList<Integer> anniList = new ArrayList<>();
        List<DecessiAnnuali> decessiAnnualiList = decessiAnnualiService.findAll();
        Provincia provincia;
        Integer incidenti,
                tumorali,
                cardiovascolari,
                contagiose,
                incidentiNazionale,
                tumoraliNazionale,
                cardiovascolariNazionale,
                contagioseNazionale,
                id = 0;
        Regione regioneNazionale = new Regione(777, "Nazionale", 0, "999999");
        Provincia provinciaNazionale = new Provincia("Provincia", 0, "999999", regioneNazionale);

        for (DecessiAnnuali decessiAnnuali : decessiAnnualiList) {
            if (!anniList.contains(decessiAnnuali.getAnno())) {
                anniList.add(decessiAnnuali.getAnno());
            }
        }

        for (Integer anno : anniList) {
            incidentiNazionale = 0;
            tumoraliNazionale = 0;
            cardiovascolariNazionale = 0;
            contagioseNazionale = 0;

            for (Regione r : regioneService.findAll()) {
                incidenti = 0;
                tumorali = 0;
                cardiovascolari = 0;
                contagiose = 0;
                provincia = null;
                id++;

                for (DecessiAnnuali decessiAnnuali : decessiAnnualiList) {
                    if (decessiAnnuali.getProvincia().getRegione().getId().equals(r.getId()) && decessiAnnuali.getAnno().equals(anno)) {
                        incidenti += decessiAnnuali.getIncidentiStradali();
                        tumorali += decessiAnnuali.getMalattieTumorali();
                        cardiovascolari += decessiAnnuali.getMalattieCardiovascolari();
                        contagiose += decessiAnnuali.getMalattieContagiose();
                        incidentiNazionale += decessiAnnuali.getIncidentiStradali();
                        tumoraliNazionale += decessiAnnuali.getMalattieTumorali();
                        cardiovascolariNazionale += decessiAnnuali.getMalattieCardiovascolari();
                        contagioseNazionale += decessiAnnuali.getMalattieContagiose();
                        provincia = decessiAnnuali.getProvincia();
                    }
                }
                if (provincia != null) {
                    DecessiAnnuali decessiRegione = new DecessiAnnuali(id, anno, incidenti, tumorali, cardiovascolari, contagiose, provincia);
                    decessiAnnualiTableView.getItems().add(decessiRegione);
                }
            }
            id++;
            DecessiAnnuali decessiNazione = new DecessiAnnuali(id, anno, incidentiNazionale, tumoraliNazionale, cardiovascolariNazionale, contagioseNazionale, provinciaNazionale);
            decessiAnnualiTableView.getItems().add(decessiNazione);
        }
    }

    @FXML
    private void visualizzaDecessiVisualizzaDatiButtonOnClicked() {
        updateListVisualizzaDati();
    }

    private void updateListVisualizzaDati() {
        decessiAnnualiTableView.getItems().clear();
        decessiAnnualiProvinciaColumn.setText("PROVINCIA");

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

        for (DecessiAnnuali decessiAnnuali : decessiAnnualiService.findAll()) {
            decessiAnnualiTableView.getItems().add(decessiAnnuali);
        }
    }

    @FXML
    private void homepageButtonOnClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) event.getSource()).getScene().setRoot(root);
    }

}
