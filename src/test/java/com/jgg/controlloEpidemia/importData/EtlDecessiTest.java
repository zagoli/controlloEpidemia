package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import com.jgg.controlloEpidemia.service.UtenteService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EtlDecessiTest {
    @Test
    void testEtlDecessi() {
        App.utenteCorrente = new UtenteService().findById(1);
        new EtlDecessi().load("src\\test\\resources\\csvToLoadTest\\decessi.csv");

        List<DecessiAnnuali> decessiAnnualiList = new DecessiAnnualiService().findAll();
        assertEquals(38, decessiAnnualiList.size());
    }
}
