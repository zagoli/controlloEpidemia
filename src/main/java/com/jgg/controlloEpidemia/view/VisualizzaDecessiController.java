package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class VisualizzaDecessiController implements Initializable {


    @FXML
    private TabPane decessiAnnualiTabPane;

    @FXML
    private Tab decessiAnnualiVisualizzazioneTab;

    @FXML
    private ListView<DecessiAnnuali> visualizzaDecessiListView;

    private final DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
    private final List<DecessiAnnuali> decessiAnnualiList = decessiAnnualiService.findAll();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        visualizzaDecessiListView.setCellFactory(decessiAnnualiListView -> new DecessiAnnualiFormatCell());
        ObservableList<DecessiAnnuali> decessiAnnualiObservableList = FXCollections.observableArrayList(decessiAnnualiList);
        visualizzaDecessiListView.setItems(decessiAnnualiObservableList);
    }


    private static class DecessiAnnualiFormatCell extends ListCell<DecessiAnnuali> {
        public DecessiAnnualiFormatCell() {
        }

        @Override
        protected void updateItem(DecessiAnnuali item, boolean empty) {
            super.updateItem(item, empty);
            setText(item == null ? "" : item.getId()+" "
                    +item.getAnno()+" "
                    +item.getProvincia().getNome()+" "
                    +item.getIncidentiStradali()+" "
                    +item.getMalattieTumorali()+" "
                    +item.getMalattieContagiose()+" "
                    +item.getMalattieCardiovascolari());
        }
    }


    @FXML
    private void homepageButtonOnClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) event.getSource()).getScene().setRoot(root);
    }


}
