package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.UtenteService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EtlComuneTest {

    @Test
    void testEtlComune() throws IOException {
        UtenteService utenteService = new UtenteService();
        EtlComune etlComune = new EtlComune();
        ComuneService comuneService = new ComuneService();

        App.utenteCorrente = utenteService.findById(1);
        etlComune.load("src\\main\\resources\\csvToLoad\\comune.csv");

        List<Comune> comuneList = comuneService.findAll();
        assertEquals(comuneList.size(), 8092);
    }

}