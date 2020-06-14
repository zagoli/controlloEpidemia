package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VisualizzaDecessiController implements Initializable {


    @FXML
    private TabPane decessiAnnualiTabPane;

    @FXML
    private Tab decessiAnnualiVisualizzazioneTab;

    @FXML
    private TableView<DecessiAnnuali> decessiTableView;

    @FXML
    private TableColumn<DecessiAnnuali,Integer> decessiIdColumn;

    @FXML
    private TableColumn<DecessiAnnuali, Provincia> decessiProvinciaColumn;

    @FXML
    private TableColumn<DecessiAnnuali,Integer> decessiAnnoColumn;

    @FXML
    private TableColumn<DecessiAnnuali,Integer> decessiIncidentiStradaliColumn;

    @FXML
    private TableColumn<DecessiAnnuali,Integer> decessiMalattieTumoraliColumn;

    @FXML
    private TableColumn<DecessiAnnuali,Integer> decessiMalattieCardiovascolariColumn;

    @FXML
    private TableColumn<DecessiAnnuali,Integer> decessiMalattieContagioseColumn;

    private final DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        decessiIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        decessiAnnoColumn.setCellValueFactory(new PropertyValueFactory<>("anno"));
        decessiProvinciaColumn.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        decessiProvinciaColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Provincia provincia, boolean empty) {
                super.updateItem(provincia, empty);
                if (empty || provincia == null) {
                    setText("");
                }
            else {
                    setText(provincia.getNome());
                 }
                }
            });
        decessiIncidentiStradaliColumn.setCellValueFactory(new PropertyValueFactory<>("incidentiStradali"));
        decessiMalattieTumoraliColumn.setCellValueFactory(new PropertyValueFactory<>("malattieTumorali"));
        decessiMalattieContagioseColumn.setCellValueFactory(new PropertyValueFactory<>("malattieContagiose"));
        decessiMalattieCardiovascolariColumn.setCellValueFactory(new PropertyValueFactory<>("malattieCardiovascolari"));



        List<DecessiAnnuali> decessiAnnualiList = decessiAnnualiService.findAll();
        for(DecessiAnnuali d : decessiAnnualiList){
            decessiTableView.getItems().add(d);

        }
    }





    @FXML
    private void homepageButtonOnClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) event.getSource()).getScene().setRoot(root);
    }


}
