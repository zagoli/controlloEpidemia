package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import com.jgg.controlloEpidemia.service.RegioneService;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VisualizzaDecessiController implements Initializable {

    private final DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
    private final RegioneService regioneService = new RegioneService();

    @FXML
    private ProgressIndicator loadingAggregazione;
    @FXML
    private BorderPane visualizzaDecessiBorderPane;
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
    private Button visualizzaDecessiAggregaPerRegioneButton;
    @FXML
    private Button visualizzaDecessiAggregaPerNazioneButton;
    @FXML
    private Button visualizzaDecessiVisualizzaDatiButton;


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
        updateListVisualizzaDati();
    }

    @FXML
    private void homepageButtonOnClicked() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        visualizzaDecessiBorderPane.getScene().setRoot(root);
    }

    private void updateListVisualizzaDati() {
        visualizzaDecessiBorderPane.setDisable(true);
        loadingAggregazione.setVisible(true);
        decessiAnnualiTableView.getItems().clear();
        provinciaColumn.setText("PROVINCIA");
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
        new Thread(
                new Task<Void>() {
                    @Override
                    protected Void call() {
                        for (DecessiAnnuali decessiAnnuali : decessiAnnualiService.findAll())
                            decessiAnnualiTableView.getItems().add(decessiAnnuali);
                        Platform.runLater(() -> {
                            loadingAggregazione.setVisible(false);
                            visualizzaDecessiBorderPane.setDisable(false);
                            decessiAnnualiTableView.getSortOrder().remove(annoColumn);
                            decessiAnnualiTableView.getSortOrder().add(annoColumn);
                        });
                        return null;
                    }
                }
        ).start();
    }


    @FXML
    private void visualizzaDecessiVisualizzaDatiButtonOnClicked() {
        visualizzaDecessiVisualizzaDatiButton.setDisable(true);
        visualizzaDecessiAggregaPerNazioneButton.setDisable(false);
        visualizzaDecessiAggregaPerRegioneButton.setDisable(false);

        updateListVisualizzaDati();
    }

    @FXML
    private void visualizzaDecessiAggregaPerNazioneButtonOnClicked() {
        visualizzaDecessiBorderPane.setDisable(true);
        loadingAggregazione.setVisible(true);
        visualizzaDecessiVisualizzaDatiButton.setDisable(false);
        visualizzaDecessiAggregaPerNazioneButton.setDisable(true);
        visualizzaDecessiAggregaPerRegioneButton.setDisable(false);

        decessiAnnualiTableView.getItems().clear();

        provinciaColumn.setText("NAZIONALE");
        provinciaColumn.setCellFactory(column -> new TableCell<>() {
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

        new Thread(
                new Task<Void>() {
                    @Override
                    protected Void call() {
                        List<DecessiAnnuali> decessiAnnualiList = decessiAnnualiService.findAll();
                        Integer incidentiNazionale,
                                tumoraliNazionale,
                                cardiovascolariNazionale,
                                contagioseNazionale,
                                id = 0;
                        Regione regioneNazionale = new Regione(777, "Nazionale", 0, "999999");
                        Provincia provinciaNazionale = new Provincia("Provincia", 0, "999999", regioneNazionale);
                        ArrayList<Integer> anniList = new ArrayList<>(decessiAnnualiList.parallelStream().mapToInt(DecessiAnnuali::getAnno).distinct().collect(ArrayList::new, ArrayList::add, ArrayList::addAll));

                        for (Integer anno : anniList) {
                            incidentiNazionale = 0;
                            tumoraliNazionale = 0;
                            cardiovascolariNazionale = 0;
                            contagioseNazionale = 0;
                            for (DecessiAnnuali decessiAnnuali : decessiAnnualiList) {
                                if (decessiAnnuali.getAnno().equals(anno)) {
                                    incidentiNazionale += decessiAnnuali.getIncidentiStradali();
                                    tumoraliNazionale += decessiAnnuali.getMalattieTumorali();
                                    cardiovascolariNazionale += decessiAnnuali.getMalattieCardiovascolari();
                                    contagioseNazionale += decessiAnnuali.getMalattieContagiose();
                                }
                            }
                            id++;
                            DecessiAnnuali decessiNazione = new DecessiAnnuali(id, anno, incidentiNazionale, tumoraliNazionale, cardiovascolariNazionale, contagioseNazionale, provinciaNazionale);
                            decessiAnnualiTableView.getItems().add(decessiNazione);
                        }
                        Platform.runLater(() -> {
                            visualizzaDecessiBorderPane.setDisable(false);
                            loadingAggregazione.setVisible(false);
                            decessiAnnualiTableView.getSortOrder().remove(annoColumn);
                            decessiAnnualiTableView.getSortOrder().add(annoColumn);
                        });
                        return null;
                    }
                }
        ).start();
    }

    @FXML
    private void visualizzaDecessiAggregaPerRegioneButtonOnClicked() {
        visualizzaDecessiBorderPane.setDisable(true);
        loadingAggregazione.setVisible(true);
        visualizzaDecessiVisualizzaDatiButton.setDisable(false);
        visualizzaDecessiAggregaPerNazioneButton.setDisable(false);
        visualizzaDecessiAggregaPerRegioneButton.setDisable(true);

        decessiAnnualiTableView.getItems().clear();

        provinciaColumn.setText("REGIONE");
        provinciaColumn.setCellFactory(column -> new TableCell<>() {
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

        new Thread(
                new Task<Void>() {
                    @Override
                    protected Void call() {
                        List<DecessiAnnuali> decessiAnnualiList = decessiAnnualiService.findAll();
                        Provincia provincia;
                        Integer incidenti,
                                tumorali,
                                cardiovascolari,
                                contagiose,
                                id = 0;
                        ArrayList<Integer> anniList = new ArrayList<>(decessiAnnualiList.parallelStream().mapToInt(DecessiAnnuali::getAnno).distinct().collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
                        for (Integer anno : anniList) {
                            for (Regione regione : regioneService.findAll()) {
                                incidenti = 0;
                                tumorali = 0;
                                cardiovascolari = 0;
                                contagiose = 0;
                                provincia = null;
                                id++;
                                for (DecessiAnnuali decessiAnnuali : decessiAnnualiList) {
                                    if (decessiAnnuali.getProvincia().getRegione().getId().equals(regione.getId()) && decessiAnnuali.getAnno().equals(anno)) {
                                        incidenti += decessiAnnuali.getIncidentiStradali();
                                        tumorali += decessiAnnuali.getMalattieTumorali();
                                        cardiovascolari += decessiAnnuali.getMalattieCardiovascolari();
                                        contagiose += decessiAnnuali.getMalattieContagiose();
                                        provincia = decessiAnnuali.getProvincia();
                                    }
                                }
                                if (provincia != null) {
                                    DecessiAnnuali decessiRegione = new DecessiAnnuali(id, anno, incidenti, tumorali, cardiovascolari, contagiose, provincia);
                                    decessiAnnualiTableView.getItems().add(decessiRegione);
                                }
                            }
                        }
                        Platform.runLater(() -> {
                            visualizzaDecessiBorderPane.setDisable(false);
                            loadingAggregazione.setVisible(false);
                            decessiAnnualiTableView.getSortOrder().remove(annoColumn);
                            decessiAnnualiTableView.getSortOrder().add(annoColumn);
                        });
                        return null;
                    }
                }
        ).start();
    }

}
