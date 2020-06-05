package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.service.RegioneService;
import com.jgg.controlloEpidemia.service.UtenteService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EtlRegioneTest {

    @Test
    void testEtlRegioni() throws IOException {
        UtenteService utenteService = new UtenteService();
        EtlRegione etlRegione = new EtlRegione();
        RegioneService regioneService = new RegioneService();

        App.utenteCorrente = utenteService.findById(1);
        etlRegione.load("src\\main\\resources\\csvToLoad\\regione.csv");

        List<Regione> regioneList = regioneService.findAll();
        assertEquals(regioneList.size(), 20);
    }

}