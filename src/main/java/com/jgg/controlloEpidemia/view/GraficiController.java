package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import com.jgg.controlloEpidemia.service.MalattieSettimanaliService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class GraficiController implements Initializable {

    private final DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
    private final MalattieSettimanaliService malattieSettimanaliService = new MalattieSettimanaliService();
    // Lista di supporto che contiene i dati del grafico dei decessi annuali
    private final ObservableList<PieChart.Data> datiGraficoDecessiAnnuali = FXCollections.observableArrayList();
    // Lista di supporto che contiene i dati del grafico delle malattie settimanali
    private final ObservableList<PieChart.Data> datiGraficoMalattieSettimanali = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Integer> chooseAnnoDecessiCombobox;
    @FXML
    private ComboBox<Integer> chooseAnnoMalattieCombobox;
    @FXML
    private PieChart graficoDecessiAnnuali;
    @FXML
    private PieChart graficoMalattieSettimanali;
    @FXML
    private Label labelGraficoDecessiAnnuali;
    @FXML
    private Label labelGraficoMalattieSettimanali;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Visualizzazione anni per combobox
        chooseAnnoDecessiCombobox.setConverter(new AnnoToStringConverter());
        chooseAnnoMalattieCombobox.setConverter(new AnnoToStringConverter());
        // Inserimento dati chooseAnnoDecessiCombobox
        ObservableList<Integer> anniDecessiObsList = FXCollections.observableList(decessiAnnualiService.findInsertedYears());
        anniDecessiObsList.add(-1);
        Collections.sort(anniDecessiObsList);
        chooseAnnoDecessiCombobox.setItems(anniDecessiObsList);
        // Inserimento dati chooseAnnoMalattieCombobox
        ObservableList<Integer> anniMalattieObsList = FXCollections.observableList(malattieSettimanaliService.findInsertedYears());
        anniMalattieObsList.add(-1);
        Collections.sort(anniMalattieObsList);
        chooseAnnoMalattieCombobox.setItems(anniMalattieObsList);
        // Impostazione dati grafici
        graficoDecessiAnnuali.setData(datiGraficoDecessiAnnuali);
        graficoMalattieSettimanali.setData(datiGraficoMalattieSettimanali);
        // Visualizzazione label di aiuto grafici
        labelGraficoDecessiAnnuali.visibleProperty().bind(Bindings.isEmpty(datiGraficoDecessiAnnuali));
        labelGraficoMalattieSettimanali.visibleProperty().bind(Bindings.isEmpty(datiGraficoMalattieSettimanali));
    }

    @FXML
    private void onHomePageButtonClicked(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) e.getSource()).getScene().setRoot(root);
    }

    @FXML
    private void annoDecessiSelected() {
        Integer anno = chooseAnnoDecessiCombobox.getValue();
        List<DecessiAnnuali> decessiAnnuali;
        if (anno == -1) {
            // Visualizza anni complessivi
            decessiAnnuali = decessiAnnualiService.findAll();
        } else {
            // Visualizza anno selezionato
            decessiAnnuali = decessiAnnualiService.findByAnno(anno);
        }
        // Trasformo la lista nei dati del grafico
        datiGraficoDecessiAnnuali.clear();
        datiGraficoDecessiAnnuali.add(new PieChart.Data("Incidenti stradali", decessiAnnuali.stream().mapToInt(DecessiAnnuali::getIncidentiStradali).sum()));
        datiGraficoDecessiAnnuali.add(new PieChart.Data("Malattie tumorali", decessiAnnuali.stream().mapToInt(DecessiAnnuali::getMalattieTumorali).sum()));
        datiGraficoDecessiAnnuali.add(new PieChart.Data("Malattie cardiovascolari", decessiAnnuali.stream().mapToInt(DecessiAnnuali::getMalattieCardiovascolari).sum()));
        datiGraficoDecessiAnnuali.add(new PieChart.Data("Malattie contagiose", decessiAnnuali.stream().mapToInt(DecessiAnnuali::getMalattieContagiose).sum()));
    }

    @FXML
    private void annoMalattieSelected() {
        Integer anno = chooseAnnoMalattieCombobox.getValue();
        List<MalattieSettimanali> malattieSettimanali;
        if (anno == -1) {
            // Visualizza per tutti gli anni
            malattieSettimanali = malattieSettimanaliService.findAll();
        } else {
            // Visualizza solo l'anno selezionato
            malattieSettimanali = malattieSettimanaliService.findByAnno(anno);
        }
        // Trasformo la lista nei dati del grafico
        datiGraficoMalattieSettimanali.clear();
        datiGraficoMalattieSettimanali.add(new PieChart.Data("ricoverati influenza", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getRicoveratiInfluenza).sum()));
        datiGraficoMalattieSettimanali.add(new PieChart.Data("in cura influenza", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getInCuraInfluenza).sum()));
        datiGraficoMalattieSettimanali.add(new PieChart.Data("ricoverati polmonite", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getRicoveratiPolmonite).sum()));
        datiGraficoMalattieSettimanali.add(new PieChart.Data("in cura polmonite", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getInCuraPolmonite).sum()));
        datiGraficoMalattieSettimanali.add(new PieChart.Data("ricoverati meningite", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getRicoveratiMeningite).sum()));
        datiGraficoMalattieSettimanali.add(new PieChart.Data("in cura meningite", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getInCuraMeningite).sum()));
        datiGraficoMalattieSettimanali.add(new PieChart.Data("ricoverati epatite", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getRicoveratiEpatite).sum()));
        datiGraficoMalattieSettimanali.add(new PieChart.Data("in cura epatite", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getInCuraEpatite).sum()));
        datiGraficoMalattieSettimanali.add(new PieChart.Data("ricoverati morbillo", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getRicoveratiMorbillo).sum()));
        datiGraficoMalattieSettimanali.add(new PieChart.Data("in cura morbillo", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getInCuraMorbillo).sum()));
        datiGraficoMalattieSettimanali.add(new PieChart.Data("ricoverati tubercolosi", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getRicoveratiTubercolosi).sum()));
        datiGraficoMalattieSettimanali.add(new PieChart.Data("in cura tubercolosi", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getInCuraTubercolosi).sum()));
        datiGraficoMalattieSettimanali.add(new PieChart.Data("ricoverati gastroenterite", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getRicoveratiGastroenterite).sum()));
        datiGraficoMalattieSettimanali.add(new PieChart.Data("in cura gastroenterite", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getInCuraGastroenterite).sum()));
    }

    // Convertitore anno in stringa per combobox
    private static class AnnoToStringConverter extends StringConverter<Integer> {
        @Override
        public String toString(Integer integer) {
            return integer > -1 ? integer.toString() : "Tutti gli anni";
        }

        @Override
        public Integer fromString(String s) {
            return s.equals("Tutti gli anni?") ? -1 : Integer.parseInt(s);
        }
    }
}
