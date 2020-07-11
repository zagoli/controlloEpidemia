package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import com.jgg.controlloEpidemia.service.MalattieSettimanaliService;
import com.jgg.controlloEpidemia.service.UtenteService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EtlMalattieTest {
    @Test
    void testEtlMalattie() {
        App.utenteCorrente = new UtenteService().findById(1);
        new EtlMalattie().load("src\\test\\resources\\csvToLoadTest\\malattie.csv", true);

        List<MalattieSettimanali> malattieSettimanaliList = new MalattieSettimanaliService().findAll();
        assertEquals(99, malattieSettimanaliList.size());
    }
}
