package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.service.ProvinciaService;
import com.jgg.controlloEpidemia.service.UtenteService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EtlProvinciaTest {

    @Test
    void testEtlProvincia() {
        App.utenteCorrente = new UtenteService().findById(1);
        new EtlProvincia().load("src\\test\\resources\\csvToLoadTest\\provincia.csv");

        List<Provincia> provinciaList = new ProvinciaService().findAll();
        assertEquals(110, provinciaList.size());
    }

}