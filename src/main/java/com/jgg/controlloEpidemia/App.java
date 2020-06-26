package com.jgg.controlloEpidemia;

import com.jgg.controlloEpidemia.importData.*;
import com.jgg.controlloEpidemia.model.Utente;
import com.jgg.controlloEpidemia.view.AutorizzaComuniController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;

public class App extends Application {

    public static Utente utenteCorrente;
    public static Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        launch(args);
    }

    private void initLoad() throws IOException {
        new EtlProvincia().load("src\\main\\resources\\csvToLoad\\provincia.csv");
        new EtlComune().load("src\\main\\resources\\csvToLoad\\comune.csv");
        new EtlDecessi().load("src\\main\\resources\\csvToLoad\\decessi.csv");
        new EtlMalattie().load("src\\main\\resources\\csvToLoad\\malattie.csv");
    }

    @Override
    public void start(Stage stage) throws IOException {

        PropertyConfigurator.configure("src\\main\\resources\\log4j.properties");
        logger.info("Logger inizializzato");

        new LoadRuolo().load();
        new LoadTipoTerritorio().load();
        new LoadPermesso().load();
        new LoadUtente().load();
        new EtlRegione().load("src\\main\\resources\\csvToLoad\\regione.csv");
        //initLoad();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Controllo Epidemia");
        stage.getIcons().add(new Image("images/icon.png"));
        stage.show();
    }

}
