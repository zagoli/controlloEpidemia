package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.model.*;
import com.jgg.controlloEpidemia.service.*;
import javafx.animation.ScaleTransition;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;
import org.apache.derby.iapi.sql.PreparedStatement;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.query.Query;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AnalisiDatiController implements Initializable {

    private final MalattieSettimanaliService malattieSettimanaliService = new MalattieSettimanaliService();
    private final DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
    private final RegioneService regioneService = new RegioneService();
    private final ProvinciaService provinciaService = new ProvinciaService();
    private final ComuneService comuneService = new ComuneService();
    private final List<MalattieSettimanali> malattieSettimanaliList = malattieSettimanaliService.findAll();

    @FXML
    private Tab malattieSettimanaliVisualizzazioneTab;
    @FXML
    private TabPane analisiDatiTabPane;
    @FXML
    private Tab decessiAnnualiTab;
    @FXML
    private Tab confrontaTab;
    @FXML
    private Tab malattieSettimanaliTab;
    @FXML
    private TabPane malattieSettimanaliTabPane;
    @FXML
    private BorderPane analisiDatiBorderPane;


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
    private TableView<DecessiAnnuali> decessiAnnualiConfrontaTableView;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiIdConfrontaColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Provincia> decessiAnnualiProvinciaConfrontaColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiAnnoConfrontaColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiIncidentiStradaliConfrontaColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiMalattieTumoraliConfrontaColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiMalattieCardiovascolariConfrontaColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiMalattieContagioseConfrontaColumn;
    @FXML
    private TableView<MalattieSettimanali> malattieSettimanaliConfrontaTableView;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> idConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> annoConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> settimanaConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> ricoveratiConInfluenzaConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> inCuraConInfluenzaConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> complicanzeRespiratorieConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> ricoveratiConPolmoniteConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> inCuraConPolmoniteConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> ricoveratiConMeningiteConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> inCuraConMeningiteConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> ricoveratiConEpatiteConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> inCuraConEpatiteConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> ricoveratiConMorbilloConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> inCuraConMorbilloConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> ricoveratiConTubercolosiConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> inCuraConTubercolosiConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> ricoveratiConGastroenteriteConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> inCuraConGastroenteriteConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Comune> comuneConfrontaColumn;
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
    private Button analisiDatiVisualizzaDecessiConfrontaConMalattieButton;

    @FXML
    private Button analisiDatiVisualizzaDecessiAggregaPerRegioneButton;

    @FXML
    private Button analisiDatiVisualizzaDecessiVisualizzaDatiButton;

    @FXML
    private Button analisiDatiVisualizzaMalattieAggregaPerProvinciaButton;

    @FXML
    private Button analisiDatiVisualizzaMalattieAggregaPerRegioneButton;

    @FXML
    private Button analisiDatiVisualizzaMalattieVisualizzaDatiButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        decessiAnnualiIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        decessiAnnualiAnnoColumn.setCellValueFactory(new PropertyValueFactory<>("anno"));
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
        updateListVisualizzaDatiDecessi();

        decessiAnnualiIdConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        decessiAnnualiAnnoConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("anno"));
        decessiAnnualiProvinciaConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        decessiAnnualiProvinciaConfrontaColumn.setCellFactory(column -> new TableCell<>() {
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
        decessiAnnualiIncidentiStradaliConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("incidentiStradali"));
        decessiAnnualiMalattieTumoraliConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("malattieTumorali"));
        decessiAnnualiMalattieContagioseConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("malattieContagiose"));
        decessiAnnualiMalattieCardiovascolariConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("malattieCardiovascolari"));

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
        updateListVisualizzaDatiMalattie();


        idConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        annoConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("anno"));
        malattieSettimanaliConfrontaTableView.getSortOrder().add(annoConfrontaColumn);
        settimanaConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("settimana"));
        ricoveratiConInfluenzaConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiInfluenza"));
        inCuraConInfluenzaConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraInfluenza"));
        complicanzeRespiratorieConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("complicanzeRespiratorie"));
        ricoveratiConPolmoniteConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiPolmonite"));
        inCuraConPolmoniteConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraPolmonite"));
        ricoveratiConMeningiteConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiMeningite"));
        inCuraConMeningiteConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraMeningite"));
        ricoveratiConEpatiteConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiEpatite"));
        inCuraConEpatiteConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraEpatite"));
        ricoveratiConMorbilloConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiMorbillo"));
        inCuraConMorbilloConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraMorbillo"));
        ricoveratiConTubercolosiConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiTubercolosi"));
        inCuraConTubercolosiConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraTubercolosi"));
        ricoveratiConGastroenteriteConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiGastroenterite"));
        inCuraConGastroenteriteConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraGastroenterite"));
        comuneConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("comune"));
        comuneConfrontaColumn.setCellFactory(column -> new TableCell<>() {
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

        analisiDatiVisualizzaDecessiConfrontaConMalattieButton.disableProperty().bind(Bindings.isEmpty(decessiAnnualiTableView.getSelectionModel().getSelectedItems()).or(decessiAnnualiProvinciaColumn.textProperty().isEqualTo("REGIONE")));

    }

    @FXML
    void homepageButtonOnClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        analisiDatiBorderPane.getScene().setRoot(root);
    }

    @FXML
    void analisiDatiVisualizzaGraficiButtonOnClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/grafici.fxml"));
        analisiDatiBorderPane.getScene().setRoot(root);
    }


    @FXML
    private void analisiDatiVisualizzaDecessiVisualizzaDatiButtonOnClicked() {
        updateListVisualizzaDatiDecessi();
    }

    @FXML
    private void analisiDatiVisualizzaMalattieVisualizzaDatiButtonOnClicked() {
        updateListVisualizzaDatiMalattie();
    }


    private void updateListVisualizzaDatiDecessi() {
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

    private void updateListVisualizzaDatiMalattie() {
        malattieSettimanaliTableView.getItems().clear();
        comuneColumn.setText("COMUNE");

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

        for (MalattieSettimanali malattieSettimanali : malattieSettimanaliList) {
            malattieSettimanaliTableView.getItems().add(malattieSettimanali);
        }
    }

    @FXML
    private void analisiDatiVisualizzaDecessiAggregaPerRegioneButtonOnClicked() {
        decessiAnnualiTableView.getItems().clear();
        if (!analisiDatiVisualizzaDecessiConfrontaConMalattieButton.isDisabled()) {
            analisiDatiVisualizzaDecessiConfrontaConMalattieButton.setDisable(true);
        }

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
    private void analisiDatiVisualizzaDecessiConfrontaConMalattieButtonOnClicked() {
        analisiDatiTabPane.getSelectionModel().select(2);
        malattieSettimanaliConfrontaTableView.getItems().clear();
        decessiAnnualiConfrontaTableView.getItems().clear();

        DecessiAnnuali eDecessiAnnuali = decessiAnnualiTableView.getSelectionModel().getSelectedItem();
        decessiAnnualiConfrontaTableView.getItems().add(eDecessiAnnuali);
        for (MalattieSettimanali malattieSettimanali : malattieSettimanaliList) {
            if (malattieSettimanali.getAnno().equals(eDecessiAnnuali.getAnno()) && malattieSettimanali.getComune().getProvincia().getId().equals(eDecessiAnnuali.getProvincia().getId())) {
                malattieSettimanaliConfrontaTableView.getItems().add(malattieSettimanali);
            }
        }

    }


    @FXML
    private void analisiDatiVisualizzaMalattieAggregaPerRegioneButtonOnClicked() {
        malattieSettimanaliTableView.getItems().clear();

        comuneColumn.setText("REGIONE");

        comuneColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Comune comune, boolean empty) {
                super.updateItem(comune, empty);
                if (empty || comune == null) {
                    setText("");
                } else {
                    setText(comune.getProvincia().getRegione().getNome());
                }
            }
        });

        ArrayList<Integer> anniList = new ArrayList<>();
        Comune comune;
        Integer rInfluenza,
                cInfluenza,
                cRespiratorie,
                rPolmonite,
                cPolmonite,
                rMeningite,
                cMeningite,
                rEpatite,
                cEpatite,
                rMorbillo,
                cMorbillo,
                rTubercolosi,
                cTubercolosi,
                rGastroenterite,
                cGastroenterite,
                rInfluenzaNazionale,
                cInfluenzaNazionale,
                cRespiratorieNazionale,
                rPolmoniteNazionale,
                cPolmoniteNazionale,
                rMeningiteNazionale,
                cMeningiteNazionale,
                rEpatiteNazionale,
                cEpatiteNazionale,
                rMorbilloNazionale,
                cMorbilloNazionale,
                rTubercolosiNazionale,
                cTubercolosiNazionale,
                rGastroenteriteNazionale,
                cGastroenteriteNazionale,
                id = 0;
        Regione regioneNazionale = new Regione(777, "Nazionale", 0, "999999");
        Provincia provinciaNazionale = new Provincia("Provincia", 0, "999999", regioneNazionale);
        Comune comuneNazionale = new Comune("999999", "Comune", 0, new Date(), true, new TipoTerritorio("TipoTerritorio"), provinciaNazionale);

        for (MalattieSettimanali malattieSettimanali : malattieSettimanaliList) {
            if (!anniList.contains(malattieSettimanali.getAnno())) {
                anniList.add(malattieSettimanali.getAnno());
            }
        }

        for (Integer anno : anniList) {
            rInfluenzaNazionale = 0;
            cInfluenzaNazionale = 0;
            cRespiratorieNazionale = 0;
            rPolmoniteNazionale = 0;
            cPolmoniteNazionale = 0;
            rMeningiteNazionale = 0;
            cMeningiteNazionale = 0;
            rEpatiteNazionale = 0;
            cEpatiteNazionale = 0;
            rMorbilloNazionale = 0;
            cMorbilloNazionale = 0;
            rTubercolosiNazionale = 0;
            cTubercolosiNazionale = 0;
            rGastroenteriteNazionale = 0;
            cGastroenteriteNazionale = 0;

            for (Regione r : regioneService.findAll()) {
                rInfluenza = 0;
                cInfluenza = 0;
                cRespiratorie = 0;
                rPolmonite = 0;
                cPolmonite = 0;
                rMeningite = 0;
                cMeningite = 0;
                rEpatite = 0;
                cEpatite = 0;
                rMorbillo = 0;
                cMorbillo = 0;
                rTubercolosi = 0;
                cTubercolosi = 0;
                rGastroenterite = 0;
                cGastroenterite = 0;
                comune = null;
                id++;

                for (MalattieSettimanali malattieSettimanali : malattieSettimanaliList) {
                    if (malattieSettimanali.getComune().getProvincia().getRegione().getId().equals(r.getId()) && malattieSettimanali.getAnno().equals(anno)) {
                        rInfluenza += malattieSettimanali.getRicoveratiInfluenza();
                        cInfluenza += malattieSettimanali.getInCuraInfluenza();
                        cRespiratorie += malattieSettimanali.getComplicanzeRespiratorie();
                        rPolmonite += malattieSettimanali.getRicoveratiPolmonite();
                        cPolmonite += malattieSettimanali.getInCuraPolmonite();
                        rMeningite += malattieSettimanali.getRicoveratiMeningite();
                        cMeningite += malattieSettimanali.getInCuraMeningite();
                        rEpatite += malattieSettimanali.getRicoveratiEpatite();
                        cEpatite += malattieSettimanali.getInCuraEpatite();
                        rMorbillo += malattieSettimanali.getRicoveratiMorbillo();
                        cMorbillo += malattieSettimanali.getInCuraMorbillo();
                        rTubercolosi += malattieSettimanali.getRicoveratiTubercolosi();
                        cTubercolosi += malattieSettimanali.getInCuraTubercolosi();
                        rGastroenterite += malattieSettimanali.getRicoveratiGastroenterite();
                        cGastroenterite += malattieSettimanali.getInCuraGastroenterite();
                        rInfluenzaNazionale += malattieSettimanali.getRicoveratiInfluenza();
                        cInfluenzaNazionale += malattieSettimanali.getInCuraInfluenza();
                        cRespiratorieNazionale += malattieSettimanali.getComplicanzeRespiratorie();
                        rPolmoniteNazionale += malattieSettimanali.getRicoveratiPolmonite();
                        cPolmoniteNazionale += malattieSettimanali.getInCuraPolmonite();
                        rMeningiteNazionale += malattieSettimanali.getRicoveratiMeningite();
                        cMeningiteNazionale += malattieSettimanali.getInCuraMeningite();
                        rEpatiteNazionale += malattieSettimanali.getRicoveratiEpatite();
                        cEpatiteNazionale += malattieSettimanali.getInCuraEpatite();
                        rMorbilloNazionale += malattieSettimanali.getRicoveratiMorbillo();
                        cMorbilloNazionale += malattieSettimanali.getInCuraMorbillo();
                        rTubercolosiNazionale += malattieSettimanali.getRicoveratiTubercolosi();
                        cTubercolosiNazionale += malattieSettimanali.getInCuraTubercolosi();
                        rGastroenteriteNazionale += malattieSettimanali.getRicoveratiGastroenterite();
                        cGastroenteriteNazionale += malattieSettimanali.getInCuraGastroenterite();
                        comune = malattieSettimanali.getComune();
                    }
                }
                if (comune != null) {
                    MalattieSettimanali malattieRegione = new MalattieSettimanali(id, anno, 0, rInfluenza, cInfluenza, cRespiratorie, rPolmonite, cPolmonite, rMeningite, cMeningite, rEpatite, cEpatite, rMorbillo, cMorbillo, rTubercolosi, cTubercolosi, rGastroenterite, cGastroenterite, comune);
                    malattieSettimanaliTableView.getItems().add(malattieRegione);
                }
            }
            id++;
            MalattieSettimanali malattieNazione = new MalattieSettimanali(id, anno, 0, rInfluenzaNazionale, cInfluenzaNazionale, cRespiratorieNazionale, rPolmoniteNazionale, cPolmoniteNazionale, rMeningiteNazionale, cMeningiteNazionale, rEpatiteNazionale, cEpatiteNazionale, rMorbilloNazionale, cMorbilloNazionale, rTubercolosiNazionale, cTubercolosiNazionale, rGastroenteriteNazionale, cGastroenteriteNazionale, comuneNazionale);
            malattieSettimanaliTableView.getItems().add(malattieNazione);
        }
    }

    @FXML
    private void analisiDatiVisualizzaMalattieAggregaPerProvinciaButtonOnClicked() {
        malattieSettimanaliTableView.getItems().clear();

        comuneColumn.setText("PROVINCIA");

        comuneColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Comune comune, boolean empty) {
                super.updateItem(comune, empty);
                if (empty || comune == null) {
                    setText("");
                } else {
                    setText(comune.getProvincia().getNome());
                }
            }
        });

        ArrayList<Integer> anniList = new ArrayList<>();
        Comune comune;
        Integer rInfluenza,
                cInfluenza,
                cRespiratorie,
                rPolmonite,
                cPolmonite,
                rMeningite,
                cMeningite,
                rEpatite,
                cEpatite,
                rMorbillo,
                cMorbillo,
                rTubercolosi,
                cTubercolosi,
                rGastroenterite,
                cGastroenterite,
                id = 0;

        for (MalattieSettimanali malattieSettimanali : malattieSettimanaliList) {
            if (!anniList.contains(malattieSettimanali.getAnno())) {
                anniList.add(malattieSettimanali.getAnno());
            }
        }

        for (Integer anno : anniList) {
            for (Provincia p : provinciaService.findAll()) {
                rInfluenza = 0;
                cInfluenza = 0;
                cRespiratorie = 0;
                rPolmonite = 0;
                cPolmonite = 0;
                rMeningite = 0;
                cMeningite = 0;
                rEpatite = 0;
                cEpatite = 0;
                rMorbillo = 0;
                cMorbillo = 0;
                rTubercolosi = 0;
                cTubercolosi = 0;
                rGastroenterite = 0;
                cGastroenterite = 0;
                comune = null;
                id++;

                for (MalattieSettimanali malattieSettimanali : malattieSettimanaliList) {
                    if (malattieSettimanali.getComune().getProvincia().getId().equals(p.getId()) && malattieSettimanali.getAnno().equals(anno)) {
                        rInfluenza += malattieSettimanali.getRicoveratiInfluenza();
                        cInfluenza += malattieSettimanali.getInCuraInfluenza();
                        cRespiratorie += malattieSettimanali.getComplicanzeRespiratorie();
                        rPolmonite += malattieSettimanali.getRicoveratiPolmonite();
                        cPolmonite += malattieSettimanali.getInCuraPolmonite();
                        rMeningite += malattieSettimanali.getRicoveratiMeningite();
                        cMeningite += malattieSettimanali.getInCuraMeningite();
                        rEpatite += malattieSettimanali.getRicoveratiEpatite();
                        cEpatite += malattieSettimanali.getInCuraEpatite();
                        rMorbillo += malattieSettimanali.getRicoveratiMorbillo();
                        cMorbillo += malattieSettimanali.getInCuraMorbillo();
                        rTubercolosi += malattieSettimanali.getRicoveratiTubercolosi();
                        cTubercolosi += malattieSettimanali.getInCuraTubercolosi();
                        rGastroenterite += malattieSettimanali.getRicoveratiGastroenterite();
                        cGastroenterite += malattieSettimanali.getInCuraGastroenterite();
                        comune = malattieSettimanali.getComune();
                    }
                }
                if (comune != null) {
                    MalattieSettimanali malattieProvincia = new MalattieSettimanali(id, anno, 0, rInfluenza, cInfluenza, cRespiratorie, rPolmonite, cPolmonite, rMeningite, cMeningite, rEpatite, cEpatite, rMorbillo, cMorbillo, rTubercolosi, cTubercolosi, rGastroenterite, cGastroenterite, comune);
                    malattieSettimanaliTableView.getItems().add(malattieProvincia);
                }
            }
        }
    }

    @FXML
    private void analisiDatiDecessiAnnualiEsportaDatiButtonOnClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("decessi");
        fileChooser.setInitialDirectory(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            Configuration configuration = new Configuration().configure();
            org.hibernate.Session currentSession = configuration.buildSessionFactory().openSession();
            currentSession.doWork(connection -> {
                CallableStatement cs = connection.prepareCall("CALL SYSCS_UTIL.SYSCS_EXPORT_TABLE (null,'DECESSIANNUALI',?,';',null,'UTF-8')");
                cs.setString(1, selectedFile.getAbsolutePath());
                cs.execute();
            });
            currentSession.close();
        }
    }

    @FXML
    private void analisiDatiMalattieSettimanaliEsportaDatiButtonOnClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("malattie");
        fileChooser.setInitialDirectory(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showSaveDialog(null);
        if(selectedFile!=null) {
            Configuration configuration = new Configuration().configure();
            org.hibernate.Session currentSession = configuration.buildSessionFactory().openSession();
            currentSession.doWork(connection -> {
                CallableStatement cs = connection.prepareCall("CALL SYSCS_UTIL.SYSCS_EXPORT_TABLE (null,'MALATTIESETTIMANALI',?,';',null,'UTF-8')");
                cs.setString(1, selectedFile.getAbsolutePath());
                cs.execute();
            });
            currentSession.close();
        }
    }


}
