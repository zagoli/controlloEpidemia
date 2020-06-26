package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.Utente;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.UtenteService;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class AutorizzaComuniController implements Initializable {

    static Logger logger = Logger.getLogger(AutorizzaComuniController.class);

    private final ObservableList<Comune> allComuniObservableList = FXCollections.observableArrayList();
    private final ObservableList<Comune> authorizedComuniObservableList = FXCollections.observableArrayList();

    @FXML
    private ListView<Comune> allComuniListView;
    @FXML
    private ListView<Comune> authorizedComuniListView;
    @FXML
    private ComboBox<Utente> utenteComboBox;
    @FXML
    private Label savedLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyConfigurator.configure("src\\main\\resources\\log4j.properties");
        allComuniListView.setCellFactory(comuneListView -> new ComuneFormatCell());
        authorizedComuniListView.setCellFactory(comuneListView -> new ComuneFormatCell());
        allComuniListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        authorizedComuniListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        allComuniListView.setItems(allComuniObservableList);
        authorizedComuniListView.setItems(authorizedComuniObservableList);
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
        utenteComboBox.setItems(FXCollections.observableList(new UtenteService().findAllPersonaleContratto()));
    }

    @FXML
    private void onHomepageButtonClicked() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        utenteComboBox.getScene().setRoot(root);
    }

    @FXML
    private void utenteChanged() {
        Utente user = utenteComboBox.getValue();
        authorizedComuniObservableList.setAll(user.getComuni());
        allComuniObservableList.setAll(new ComuneService().findAll());
        allComuniObservableList.removeAll(authorizedComuniObservableList);
    }

    @FXML
    private void onSaveButtonClicked() {
        Utente utente = utenteComboBox.getValue();
        if (utente != null) {
            List<Comune> toAuthorizeComuni = authorizedComuniListView.getItems();
            utente.getComuni().clear();
            utente.getComuni().addAll(toAuthorizeComuni);
            UtenteService utenteService = new UtenteService();
            utenteService.update(utente);
            logger.info("Aggiunti comuni: " + toAuthorizeComuni + " all'utente: " + utente);
            fadeAnimation(savedLabel);
        }
    }

    @FXML
    private void addComuni() {
        List<Comune> selected = new LinkedList<>(allComuniListView.getSelectionModel().getSelectedItems());
        if (!selected.isEmpty()) {
            authorizedComuniObservableList.addAll(selected);
            authorizedComuniObservableList.sort(new ComuneComparator());
            allComuniObservableList.removeAll(selected);
        }
    }

    @FXML
    private void removeComuni() {
        List<Comune> selected = new LinkedList<>(authorizedComuniListView.getSelectionModel().getSelectedItems());
        if (!selected.isEmpty()) {
            allComuniObservableList.addAll(selected);
            allComuniObservableList.sort(new ComuneComparator());
            authorizedComuniObservableList.removeAll(selected);
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

    private static class ComuneComparator implements Comparator<Comune> {
        @Override
        public int compare(Comune o1, Comune o2) {
            return o1.getNome().compareTo(o2.getNome());
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

}
