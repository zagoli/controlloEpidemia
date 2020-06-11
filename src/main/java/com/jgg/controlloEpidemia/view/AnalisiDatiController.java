package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.MalattieSettimanaliService;
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

public class AnalisiDatiController implements Initializable {

    private final MalattieSettimanaliService malattieSettimanaliService = new MalattieSettimanaliService();

    @FXML
    private Tab malattieSettimanaliVisualizzazioneTab;

    @FXML
    private TabPane malattieSettimanaliTabPane;

    @FXML
    private Tab decessiAnnualiTabPane;

    @FXML
    private ListView<String> malattieSettimanaliListView;

    @FXML
    private ListView<String> decessiAnnualiListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateList();
        malattieSettimanaliListView.getSelectionModel().select(0);
    }

    @FXML
    void homepageButtonOnClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) event.getSource()).getScene().setRoot(root);
    }

    private void updateList() {
        malattieSettimanaliListView.getItems().clear();
        List<MalattieSettimanali> malattieSettimanaliList = malattieSettimanaliService.findAll();
        malattieSettimanaliListView.getItems().add("ID  COMUNE  ANNO  SETTIMANA  R.INFLUENZA  C.INFLUENZA  CP.RESPIRATORIE  R.POLMONITE  C.POLMONITE  R.MENINGITE  C.MENINGITE  R.EPATITE  C.EPATITE  R.MORBILLO  C.MORBILLO  R.TUBERCOLOSI  C.TUBERCOLOSI  R.GASTROENTERITE  C.GASTROENTERITE");
        for (MalattieSettimanali m : malattieSettimanaliList) {
            malattieSettimanaliListView.getItems().add(
                    m.getId()
                            + "  " + m.getComune().getNome()
                            + "  " + m.getAnno()
                            + "  " + m.getSettimana()
                            + "  " + m.getRicoveratiInfluenza()
                            + "  " + m.getInCuraInfluenza()
                            + "  " + m.getComplicanzeRespiratorie()
                            + "  " + m.getRicoveratiPolmonite()
                            + "  " + m.getInCuraPolmonite()
                            + "  " + m.getRicoveratiMeningite()
                            + "  " + m.getInCuraMeningite()
                            + "  " + m.getRicoveratiEpatite()
                            + "  " + m.getInCuraEpatite()
                            + "  " + m.getRicoveratiMorbillo()
                            + "  " + m.getInCuraMorbillo()
                            + "  " + m.getRicoveratiTubercolosi()
                            + "  " + m.getInCuraTubercolosi()
                            + "  " + m.getRicoveratiGastroenterite()
                            + "  " + m.getInCuraGastroenterite());
        }
    }

}
