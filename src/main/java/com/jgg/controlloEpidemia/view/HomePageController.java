package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.service.ComuneService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private Label welcomeLabel;
    @FXML
    private VBox mainPanel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeLabel.setText("Benvenuto, " + App.utenteCorrente.getNome() + " " + App.utenteCorrente.getCognome());
        String ruolo = App.utenteCorrente.getRuolo().getNome();
        if (ruolo.equals("Amministratore")) {
            Button territorioButton = new Button("GESTISCI DATI TERRITORIALI");
            territorioButton.setUserData(0);
            prepareButton(territorioButton);
            mainPanel.getChildren().add(territorioButton);
        }
        if (ruolo.equals("Personale dell'ente") || ruolo.equals("Amministratore")) {
            Button comunePersonaleContrattoButton = new Button("AUTORIZZA COMUNI PERSONALE A CONTRATTO");
            comunePersonaleContrattoButton.setUserData(1);
            prepareButton(comunePersonaleContrattoButton);
            if (new ComuneService().countComuni() == 0) {
                comunePersonaleContrattoButton.setDisable(true);
            }
            mainPanel.getChildren().add(comunePersonaleContrattoButton);
            Button decessiButton = new Button("GESTISCI DECESSI ANNUALI");
            decessiButton.setUserData(2);
            prepareButton(decessiButton);
            mainPanel.getChildren().add(decessiButton);
        }
        if (ruolo.equals("Personale a contratto") || ruolo.equals("Amministratore")) {
            Button malattieButton = new Button("GESTISCI MALATTIE SETTIMANALI");
            malattieButton.setUserData(3);
            prepareButton(malattieButton);
            mainPanel.getChildren().add(malattieButton);
        }
        if (ruolo.equals("Ricercatore Analista") || ruolo.equals("Amministratore")) {
            Button vediMalattieButton = new Button("ANALIZZA I DATI ACQUISITI");
            vediMalattieButton.setUserData(4);
            prepareButton(vediMalattieButton);
            mainPanel.getChildren().add(vediMalattieButton);
        }
        if (App.utenteCorrente.getPermesso().stream().anyMatch(o -> o.getNome().equals("vediDecessi"))) {
            Button vediDecessiButton = new Button("VISUALIZZA DATI DECESSI");
            vediDecessiButton.setUserData(5);
            prepareButton(vediDecessiButton);
            mainPanel.getChildren().add(vediDecessiButton);
        }
    }

    @FXML
    private void onLogoutButtonClicked(ActionEvent event) {
        App.utenteCorrente = null;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/loginPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ((Button) event.getSource()).getScene().setRoot(root);
    }

    private void prepareButton(Button button) {
        button.setFont(new Font("Segoe UI", 22));
        button.setOnAction(new EventHandler<>() {
            private Parent root;

            @Override
            public void handle(ActionEvent actionEvent) {
                switch ((Integer) ((Button) actionEvent.getSource()).getUserData()) {
                    case 0:
                        try {
                            root = FXMLLoader.load(HomePageController.this.getClass().getResource("/fxml/datiTerritoriali.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        button.getScene().setRoot(root);
                        break;
                    case 1:
                        try {
                            root = FXMLLoader.load(HomePageController.this.getClass().getResource("/fxml/autorizzaComuni.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        button.getScene().setRoot(root);
                        break;
                    case 2:
                        try {
                            root = FXMLLoader.load(HomePageController.this.getClass().getResource("/fxml/decessiAnnuali.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        button.getScene().setRoot(root);
                        break;
                    case 3:
                        try {
                            root = FXMLLoader.load(HomePageController.this.getClass().getResource("/fxml/malattieSettimanali.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        button.getScene().setRoot(root);
                        break;
                    case 4:
                        try {
                            root = FXMLLoader.load(HomePageController.this.getClass().getResource("/fxml/analisiDati.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        button.getScene().setRoot(root);
                        break;
                    case 5:
                        try {
                            root = FXMLLoader.load(HomePageController.this.getClass().getResource("/fxml/visualizzaDecessi.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        button.getScene().setRoot(root);
                        break;
                    default:
                }
            }
        });
    }

}
