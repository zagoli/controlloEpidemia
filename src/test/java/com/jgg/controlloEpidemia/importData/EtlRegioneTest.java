package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.service.RegioneService;
import com.jgg.controlloEpidemia.service.UtenteService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EtlRegioneTest {

    @Test
    void testEtlRegioni() {
        App.utenteCorrente = new UtenteService().findById(1);
        new EtlRegione().load("src\\test\\resources\\csvToLoadTest\\regione.csv");

        List<Regione> regioneList = new RegioneService().findAll();
        assertEquals(20, regioneList.size());
    }

}