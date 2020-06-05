package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.Ruolo;
import com.jgg.controlloEpidemia.model.Utente;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class EtlProvinciaTest {
    @Test
    void testLoadProvincia() throws IOException {
        Ruolo r= new Ruolo(1,"Amministratore");
        Utente u = new Utente("Admin", "aa", "Gianni", "Bolla", r);
        App.utenteCorrente=u;

        EtlProvincia etlProvincia = new EtlProvincia();
        etlProvincia.load("src\\main\\resources\\csvToLoad\\provincia.csv");
    }

}