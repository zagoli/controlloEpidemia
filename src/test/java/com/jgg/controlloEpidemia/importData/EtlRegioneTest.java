package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.Ruolo;
import com.jgg.controlloEpidemia.model.Utente;
import com.jgg.controlloEpidemia.service.ProvinciaService;
import com.jgg.controlloEpidemia.service.RuoloService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class EtlRegioneTest {
    @Test
    void testLoadRegioni() throws IOException {
        Ruolo r= new Ruolo(1,"Amministratore");
        Utente u = new Utente("Admin", "aa", "Gianni", "Bolla", r);
        App.utenteCorrente=u;

        EtlRegione etlRegione = new EtlRegione();
        etlRegione.load("src\\main\\resources\\csvToLoad\\regione.csv");
    }

}