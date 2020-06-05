package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.Ruolo;
import com.jgg.controlloEpidemia.model.Utente;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class EtlComuneTest {
    @Test
    void testLoadComune() throws IOException {
        Ruolo r= new Ruolo(1,"Amministratore");
        Utente u = new Utente("Admin", "aa", "Gianni", "Bolla", r);
        App.utenteCorrente=u;

        LoadTipoTerritorio loadtt = new LoadTipoTerritorio();
        loadtt.load();

        EtlComune etlComune = new EtlComune();
        etlComune.load("src\\main\\resources\\csvToLoad\\comune.csv");
    }

}