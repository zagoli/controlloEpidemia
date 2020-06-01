package com.jgg.controlloEpidemia;

import com.jgg.controlloEpidemia.importData.*;
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
        LoadRuolo loadRuolo = new LoadRuolo();
        loadRuolo.load();
        LoadTipoTerritorio loadTipoTerritorio = new LoadTipoTerritorio();
        loadTipoTerritorio.load();
        LoadPermesso loadPermesso = new LoadPermesso();
        loadPermesso.load();
        LoadUtenti loadUtenti = new LoadUtenti();
        loadUtenti.load();
        // --------------------------------------------------------------

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginPage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Controllo Epidemia");
        primaryStage.show();

        //EtlRegione etlRegione=new EtlRegione();
        //etlRegione.load();


    }

}
