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
import javafx.util.Duration;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class AutorizzaComuniController implements Initializable {

    private final UtenteService utenteService = new UtenteService();
    private final ComuneService comuneService = new ComuneService();
    private final List<Comune> allComuniList = comuneService.findAll();
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
        // Come visualizzare i comuni
        allComuniListView.setCellFactory(comuneListView -> new ComuneFormatCell());
        authorizedComuniListView.setCellFactory(comuneListView -> new ComuneFormatCell());
        // Selezione multipla
        allComuniListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        authorizedComuniListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // Imposto comuni vuoti inizialmente nelle ListView
        allComuniListView.setItems(allComuniObservableList);
        authorizedComuniListView.setItems(authorizedComuniObservableList);
        // Come visualizzare gli utenti
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
        // Imposto gli utenti nella combobox
        utenteComboBox.setItems(FXCollections.observableList(utenteService.findAllPersonaleContratto()));
    }

    @FXML
    private void onHomePageButtonClicked(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) e.getSource()).getScene().setRoot(root);
    }

    @FXML
    private void utenteChanged() {
        Utente user = utenteComboBox.getValue();
        authorizedComuniObservableList.setAll(user.getComuni());
        allComuniObservableList.setAll(allComuniList);
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
            fadeAnimation(savedLabel);
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

    @FXML
    private void addComuni() {
        // Devo usare una nuova lista non osservabile per contenere gli elementi selezionati perché altrimenti si scombina tutto.
        // Penso che sia perché la lista selected deriva da allComuniObservableList e poi quando cambi una roba si ripete a cascata o robe simili
        List<Comune> selected = new LinkedList<>(allComuniListView.getSelectionModel().getSelectedItems());
        if (!selected.isEmpty()) {
            // Non devo aggiornare nulla, perché sono observable
            authorizedComuniObservableList.addAll(selected);
            authorizedComuniObservableList.sort(new ComuneComparator());
            allComuniObservableList.removeAll(selected);
        }
    }

    @FXML
    private void removeComuni() {
        // Devo usare una nuova lista non osservabile per contenere gli elementi selezionati perché altrimenti si scombina tutto.
        // Penso che sia perché la lista selected deriva da allComuniObservableList e poi quando cambi una roba si ripete a cascata o robe simili
        List<Comune> selected = new LinkedList<>(authorizedComuniListView.getSelectionModel().getSelectedItems());
        if (!selected.isEmpty()) {
            // Non devo aggiornare nulla, perché sono observable
            allComuniObservableList.addAll(selected);
            allComuniObservableList.sort(new ComuneComparator());
            authorizedComuniObservableList.removeAll(selected);
        }
    }

    // Classe interna che definisce la cella che visualizza i comuni nelle ListView.
    // Se si vorrà si potrà personalizzare ancora di più
    private static class ComuneFormatCell extends ListCell<Comune> {
        public ComuneFormatCell() {
        }

        @Override
        protected void updateItem(Comune item, boolean empty) {
            super.updateItem(item, empty);
            setText(item == null ? "" : item.getNome());
        }
    }

    // Comparator per ordinare alfabeticamente i comuni
    private static class ComuneComparator implements Comparator<Comune> {
        @Override
        public int compare(Comune o1, Comune o2) {
            return o1.getNome().compareTo(o2.getNome());
        }
    }
}
