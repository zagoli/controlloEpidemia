package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import com.jgg.controlloEpidemia.service.MalattieSettimanaliService;
import com.jgg.controlloEpidemia.service.UtenteService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EtlMalattieTest {
    @Test
    void testEtlMalattie() throws IOException {
        UtenteService utenteService = new UtenteService();
        EtlMalattie etlMalattie = new EtlMalattie();
        MalattieSettimanaliService malattieSettimanaliService = new MalattieSettimanaliService();

        App.utenteCorrente = utenteService.findById(1);
        etlMalattie.load("src\\main\\resources\\csvToLoad\\malattiesettimanali.csv");

        List<MalattieSettimanali> malattieSettimanaliList = malattieSettimanaliService.findAll();
        assertEquals(malattieSettimanaliList.size(), 11);
    }
}
