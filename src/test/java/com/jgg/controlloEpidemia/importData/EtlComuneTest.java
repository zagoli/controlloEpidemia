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
        App.utenteCorrente = new UtenteService().findById(1);
        new EtlComune().load("src\\test\\resources\\csvToLoadTest\\comune.csv");

        List<Comune> comuneList = new ComuneService().findAll();
        assertEquals(1206, comuneList.size());
    }

}