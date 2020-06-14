package com.jgg.controlloEpidemia;

import com.jgg.controlloEpidemia.importData.LoadPermesso;
import com.jgg.controlloEpidemia.importData.LoadRuolo;
import com.jgg.controlloEpidemia.importData.LoadTipoTerritorio;
import com.jgg.controlloEpidemia.importData.LoadUtente;
import com.jgg.controlloEpidemia.model.Utente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static Utente utenteCorrente;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        // Inizializzazione dati per prototipo -------------------------
        new LoadRuolo().load();
        new LoadTipoTerritorio().load();
        new LoadPermesso().load();
        new LoadUtente().load();
        // --------------------------------------------------------------

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginPage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Controllo Epidemia");
        primaryStage.show();
    }

}
