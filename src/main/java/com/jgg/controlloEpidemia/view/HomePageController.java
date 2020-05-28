package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private Label welcomeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeLabel.setText("Benvenuto, "+App.utenteCorrente.getNome()+" "+App.utenteCorrente.getCognome());
    }

    @FXML
    private void onLogoutButtonClicked(ActionEvent e) throws IOException {
        App.utenteCorrente = null;
        // vai alla pagina di login
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginPage.fxml"));
        ((Button)e.getSource()).getScene().setRoot(root);
    }

}
