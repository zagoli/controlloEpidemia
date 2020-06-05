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
        App.utenteCorrente = new UtenteService().findById(1);
        new EtlDecessi().load("src\\main\\resources\\csvToLoad\\decessi.csv");

        List<DecessiAnnuali> decessiAnnualiList = new DecessiAnnualiService().findAll();
        assertEquals(decessiAnnualiList.size(), 5);
    }
}
