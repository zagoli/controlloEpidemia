package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import com.jgg.controlloEpidemia.service.MalattieSettimanaliService;
import com.jgg.controlloEpidemia.service.RegioneService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
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
    private final RegioneService regioneService = new RegioneService();
    private final MalattieSettimanaliService malattieSettimanaliService = new MalattieSettimanaliService();
    private final ObservableList<PieChart.Data> datiGraficoDecessiCausa = FXCollections.observableArrayList();
    private final ObservableList<PieChart.Data> datiGraficoDecessiRegione = FXCollections.observableArrayList();
    private final XYChart.Series<String, Number> datiGraficoMalattieSettimanali = new XYChart.Series<>();
    @FXML
    private ComboBox<Integer> chooseAnnoDecessiCausaCombobox;
    @FXML
    private ComboBox<Integer> chooseAnnoMalattieCombobox;
    @FXML
    private ComboBox<Integer> chooseAnnoDecessiRegioneCombobox;
    @FXML
    private PieChart graficoDecessiCausa;
    @FXML
    private PieChart graficoDecessiRegione;
    @FXML
    private BarChart<String, Number> graficoMalattieSettimanali;
    @FXML
    private Label labelGraficoDecessiCausa;
    @FXML
    private Label labelGraficoDecessiRegione;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseAnnoDecessiCausaCombobox.setConverter(new AnnoToStringConverter());
        chooseAnnoMalattieCombobox.setConverter(new AnnoToStringConverter());
        chooseAnnoDecessiRegioneCombobox.setConverter(new AnnoToStringConverter());
        ObservableList<Integer> anniDecessiObsList = FXCollections.observableList(decessiAnnualiService.findInsertedYears());
        anniDecessiObsList.add(-1);
        Collections.sort(anniDecessiObsList);
        chooseAnnoDecessiCausaCombobox.setItems(anniDecessiObsList);
        chooseAnnoDecessiRegioneCombobox.setItems(anniDecessiObsList);
        ObservableList<Integer> anniMalattieObsList = FXCollections.observableList(malattieSettimanaliService.findInsertedYears());
        anniMalattieObsList.add(-1);
        Collections.sort(anniMalattieObsList);
        chooseAnnoMalattieCombobox.setItems(anniMalattieObsList);
        graficoDecessiCausa.setData(datiGraficoDecessiCausa);
        graficoDecessiRegione.setData(datiGraficoDecessiRegione);
        graficoMalattieSettimanali.getData().add(datiGraficoMalattieSettimanali);
        labelGraficoDecessiCausa.visibleProperty().bind(Bindings.isEmpty(datiGraficoDecessiCausa));
        labelGraficoDecessiRegione.visibleProperty().bind(Bindings.isEmpty(datiGraficoDecessiRegione));
    }

    @FXML
    private void homePageButtonOnClicked() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        graficoDecessiCausa.getScene().setRoot(root);
    }


    @FXML
    private void indietroButtonClicked() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/analisiDati.fxml"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        graficoDecessiCausa.getScene().setRoot(root);
    }

    @FXML
    private void annoDecessiSelezionaCausa() {
        Integer anno = chooseAnnoDecessiCausaCombobox.getValue();
        List<DecessiAnnuali> decessiAnnuali;
        if (anno == -1) {
            decessiAnnuali = decessiAnnualiService.findAll();
        } else {
            decessiAnnuali = decessiAnnualiService.findByAnno(anno);
        }
        datiGraficoDecessiCausa.clear();
        datiGraficoDecessiCausa.add(new PieChart.Data("Incidenti stradali", decessiAnnuali.stream().mapToInt(DecessiAnnuali::getIncidentiStradali).sum()));
        datiGraficoDecessiCausa.add(new PieChart.Data("Malattie tumorali", decessiAnnuali.stream().mapToInt(DecessiAnnuali::getMalattieTumorali).sum()));
        datiGraficoDecessiCausa.add(new PieChart.Data("Malattie cardiovascolari", decessiAnnuali.stream().mapToInt(DecessiAnnuali::getMalattieCardiovascolari).sum()));
        datiGraficoDecessiCausa.add(new PieChart.Data("Malattie contagiose", decessiAnnuali.stream().mapToInt(DecessiAnnuali::getMalattieContagiose).sum()));
    }

    @FXML
    private void annoDecessiSelezionaRegione() {
        Integer anno = chooseAnnoDecessiRegioneCombobox.getValue();
        List<DecessiAnnuali> decessiAnnuali;
        if (anno == -1) {
            decessiAnnuali = decessiAnnualiService.findAll();
        } else {
            decessiAnnuali = decessiAnnualiService.findByAnno(anno);
        }
        datiGraficoDecessiRegione.clear();
        for (Regione r : regioneService.findAll()) {
            int decPerRegione = decessiAnnuali.stream().
                    filter(d -> d.getProvincia().getRegione().equals(r)).
                    mapToInt(d -> d.getIncidentiStradali() + d.getMalattieCardiovascolari() + d.getMalattieContagiose() + d.getMalattieTumorali())
                    .sum();
            if (decPerRegione > 0) {
                datiGraficoDecessiRegione.add(new PieChart.Data(r.getNome(), decPerRegione));
            }
        }
    }

    @FXML
    private void annoMalattieSelezione() {
        Integer anno = chooseAnnoMalattieCombobox.getValue();
        List<MalattieSettimanali> malattieSettimanali;
        if (anno == -1) {
            malattieSettimanali = malattieSettimanaliService.findAll();
        } else {
            malattieSettimanali = malattieSettimanaliService.findByAnno(anno);
        }
        datiGraficoMalattieSettimanali.getData().clear();
        datiGraficoMalattieSettimanali.getData().add(new XYChart.Data<>("Ricoverati influenza", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getRicoveratiInfluenza).sum()));
        datiGraficoMalattieSettimanali.getData().add(new XYChart.Data<>("In cura influenza", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getInCuraInfluenza).sum()));
        datiGraficoMalattieSettimanali.getData().add(new XYChart.Data<>("Ricoverati polmonite", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getRicoveratiPolmonite).sum()));
        datiGraficoMalattieSettimanali.getData().add(new XYChart.Data<>("In cura polmonite", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getInCuraPolmonite).sum()));
        datiGraficoMalattieSettimanali.getData().add(new XYChart.Data<>("Ricoverati meningite", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getRicoveratiMeningite).sum()));
        datiGraficoMalattieSettimanali.getData().add(new XYChart.Data<>("In cura meningite", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getInCuraMeningite).sum()));
        datiGraficoMalattieSettimanali.getData().add(new XYChart.Data<>("Ricoverati epatite", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getRicoveratiEpatite).sum()));
        datiGraficoMalattieSettimanali.getData().add(new XYChart.Data<>("In cura epatite", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getInCuraEpatite).sum()));
        datiGraficoMalattieSettimanali.getData().add(new XYChart.Data<>("Ricoverati morbillo", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getRicoveratiMorbillo).sum()));
        datiGraficoMalattieSettimanali.getData().add(new XYChart.Data<>("In cura morbillo", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getInCuraMorbillo).sum()));
        datiGraficoMalattieSettimanali.getData().add(new XYChart.Data<>("Ricoverati tubercolosi", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getRicoveratiTubercolosi).sum()));
        datiGraficoMalattieSettimanali.getData().add(new XYChart.Data<>("In cura tubercolosi", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getInCuraTubercolosi).sum()));
        datiGraficoMalattieSettimanali.getData().add(new XYChart.Data<>("Ricoverati gastroenterite", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getRicoveratiGastroenterite).sum()));
        datiGraficoMalattieSettimanali.getData().add(new XYChart.Data<>("In cura gastroenterite", malattieSettimanali.stream().mapToInt(MalattieSettimanali::getInCuraGastroenterite).sum()));
    }

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
