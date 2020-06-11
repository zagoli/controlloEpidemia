package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VisualizzaDecessiController implements Initializable {

    private final DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();

    @FXML
    private TabPane decessiAnnualiTabPane;

    @FXML
    private Tab decessiAnnualiVisualizzazioneTab;

    @FXML
    private ListView<String> visualizzaDecessiListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateList();
        visualizzaDecessiListView.getSelectionModel().select(0);
    }

    @FXML
    private void homepageButtonOnClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) event.getSource()).getScene().setRoot(root);
    }

    private void updateList() {
        visualizzaDecessiListView.getItems().clear();
        List<DecessiAnnuali> decessiAnnualiList = decessiAnnualiService.findAll();
        visualizzaDecessiListView.getItems().add("ID ANNO INCIDENTI STRADALI MALATTIE TUMORALI MALATTIE CARDIOVSCOLARI MALATTIE CONTAGIOSE PROVINCIA");
        for (DecessiAnnuali d : decessiAnnualiList) {
            visualizzaDecessiListView.getItems().add(
                    d.getId()
                            + " " + d.getAnno()
                            + " " + d.getIncidentiStradali()
                            + " " + d.getMalattieTumorali()
                            + " " + d.getMalattieCardiovascolari()
                            + " " + d.getMalattieContagiose()
                            + " " + d.getProvincia().getNome());
        }
    }

}
