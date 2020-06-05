package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import com.jgg.controlloEpidemia.service.UtenteService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EtlDecessiTest {
    @Test
    void testEtlMalattie() throws IOException {
        UtenteService utenteService = new UtenteService();
        EtlDecessi etlDecessi = new EtlDecessi();
        DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();

        App.utenteCorrente = utenteService.findById(1);
        etlDecessi.load("src\\main\\resources\\csvToLoad\\decessi.csv");

        List<DecessiAnnuali> decessiAnnualiList = decessiAnnualiService.findAll();
        assertEquals(decessiAnnualiList.size(), 5);
    }
}
