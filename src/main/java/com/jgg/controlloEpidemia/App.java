package com.jgg.controlloEpidemia;

import com.jgg.controlloEpidemia.importData.*;
import com.jgg.controlloEpidemia.model.Utente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class App extends Application {

    public static final Logger logger = LogManager.getLogger(App.class);
    public static Utente utenteCorrente;

    public static void main(String[] args) {
        launch(args);
    }

    private void initLoad(Logger logger) {
        new EtlProvincia().load("src\\main\\resources\\csvToLoad\\provincia.csv");
        logger.info("Caricate province");
        new EtlComune().load("src\\main\\resources\\csvToLoad\\comune.csv");
        logger.info("Caricati comuni");
        new EtlDecessi().load("src\\main\\resources\\csvToLoad\\decessi.csv");
        logger.info("Caricati decessi");
        new EtlMalattie().load("src\\main\\resources\\csvToLoad\\malattie.csv", true);
        logger.info("Caricate malattie");
    }

    @Override
    public void start(Stage stage) {
        logger.info("Logger inizializzato");

        new LoadRuolo().load();
        logger.info("Caricati ruoli");
        new LoadTipoTerritorio().load();
        logger.info("Caricati territori");
        new LoadPermesso().load();
        logger.info("Caricati permessi");
        new LoadUtente().load();
        logger.info("Caricati utenti");
        new EtlRegione().load("src\\main\\resources\\csvToLoad\\regione.csv");
        logger.info("Caricate regioni");

        //initLoad(logger);

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/loginPage.fxml"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        assert root != null;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Controllo Epidemia");
        stage.getIcons().add(new Image("images/icon.png"));
        stage.show();
        logger.info("Caricata grafica");
    }

}
