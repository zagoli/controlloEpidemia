package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.Utente;
import com.jgg.controlloEpidemia.service.UtenteService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AutorizzaComuniController implements Initializable {

    @FXML
    private ListView<Comune> allComuniList;
    @FXML
    private ListView<Comune> authorizedComuni;
    @FXML
    private ComboBox<Utente> utentiComboBox;

    private UtenteService utenteService = new UtenteService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //utentiComboBox.setItems(FXCollections.observableList(utenteService.findAllPersonaleContratto()));
    }

    @FXML
    private void onHomePageButtonClicked(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) e.getSource()).getScene().setRoot(root);
    }

    @FXML
    private void onSaveButtonClicked() {

    }

    @FXML
    private void addComune() {

    }

    @FXML
    private void removeComune() {

    }
}
