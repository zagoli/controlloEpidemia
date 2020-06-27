package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.Utente;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.UtenteService;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class AutorizzaComuniController implements Initializable {

    static Logger logger = Logger.getLogger(AutorizzaComuniController.class);

    private final ComuneService comuneService = new ComuneService();
    private final UtenteService utenteService = new UtenteService();
    private final ObservableList<Comune> comuniAllObservableList = FXCollections.observableArrayList();
    private final ObservableList<Comune> comuniAutorizzatiObservableList = FXCollections.observableArrayList();

    @FXML
    private  BorderPane autorizzaComuniBorderPane;
    @FXML
    private ListView<Comune> comuniAllListView;
    @FXML
    private ListView<Comune> comuniAutorizzatiListView;
    @FXML
    private ComboBox<Utente> utenteComboBox;
    @FXML
    private Label salvaLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comuniAllListView.setCellFactory(comuneListView -> new ComuneFormatCell());
        comuniAutorizzatiListView.setCellFactory(comuneListView -> new ComuneFormatCell());
        comuniAllListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        comuniAutorizzatiListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        comuniAllListView.setItems(comuniAllObservableList);
        comuniAutorizzatiListView.setItems(comuniAutorizzatiObservableList);
        utenteComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Utente utente) {
                return utente.getNome() + " " + utente.getCognome();
            }

            @Override
            public Utente fromString(String s) {
                return utenteComboBox.getValue();
            }
        });
        utenteComboBox.setItems(FXCollections.observableList(utenteService.findAllPersonaleContratto()));
    }

    @FXML
    private void homepageButtonClicked() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        autorizzaComuniBorderPane.getScene().setRoot(root);
    }

    @FXML
    private void cambioUtente() {
        Utente user = utenteComboBox.getValue();
        comuniAutorizzatiObservableList.setAll(user.getComuni());
        comuniAllObservableList.setAll(comuneService.findAll());
        comuniAllObservableList.removeAll(comuniAutorizzatiObservableList);
    }

    @FXML
    private void aggiungiComuni() {
        List<Comune> selected = new LinkedList<>(comuniAllListView.getSelectionModel().getSelectedItems());
        if (!selected.isEmpty()) {
            comuniAutorizzatiObservableList.addAll(selected);
            comuniAutorizzatiObservableList.sort(new ComuneComparator());
            comuniAllObservableList.removeAll(selected);
        }
    }

    @FXML
    private void rimuoviComuni() {
        List<Comune> selected = new LinkedList<>(comuniAutorizzatiListView.getSelectionModel().getSelectedItems());
        if (!selected.isEmpty()) {
            comuniAllObservableList.addAll(selected);
            comuniAllObservableList.sort(new ComuneComparator());
            comuniAutorizzatiObservableList.removeAll(selected);
        }
    }

    @FXML
    private void salvaButtonClicked() {
        Utente utente = utenteComboBox.getValue();
        if (utente != null) {
            List<Comune> toComuniAutorizzati = comuniAutorizzatiListView.getItems();
            utente.getComuni().clear();
            utente.getComuni().addAll(toComuniAutorizzati);
            utenteService.update(utente);
            logger.info("Aggiunti comuni: " + toComuniAutorizzati + " all'utente: " + utente);
            fadeAnimation(salvaLabel);
        }
    }

    private void fadeAnimation(Label label) {
        FadeTransition transition = new FadeTransition(Duration.millis(500), label);
        transition.setFromValue(0);
        transition.setByValue(1);
        transition.setCycleCount(2);
        transition.setAutoReverse(true);
        transition.play();
    }

    private static class ComuneComparator implements Comparator<Comune> {
        @Override
        public int compare(Comune o1, Comune o2) {
            return o1.getNome().compareTo(o2.getNome());
        }
    }

    private static class ComuneFormatCell extends ListCell<Comune> {
        public ComuneFormatCell() {
        }

        @Override
        protected void updateItem(Comune item, boolean empty) {
            super.updateItem(item, empty);
            setText(item == null ? "" : item.getNome());
        }
    }

}
