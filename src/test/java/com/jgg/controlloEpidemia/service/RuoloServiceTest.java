package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.Ruolo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RuoloServiceTest {

    @Test
    void testRuolo() {
        //Inizializzo i service
        RuoloService ruoloService = new RuoloService();
        //Creo i model
        Ruolo ruolo = new Ruolo("Pianeggiante");
        Ruolo ruolo2 = new Ruolo("Marittimo");
        //Salvo i model
        ruoloService.save(ruolo);
        ruoloService.save(ruolo2);
        //Cerco i model
        Ruolo findRuolo = ruoloService.findById(ruolo.getId());
        assertEquals(ruolo, findRuolo);
        //Cerco tutti  i model
        List<Ruolo> ruoloList = ruoloService.findAll();
        assertEquals(ruoloList.size(), 2);
        //Elimino i model
        ruoloService.deleteById(ruolo.getId());
        ruoloService.deleteById(ruolo2.getId());
        //Assert dei model
        ruolo = ruoloService.findById(ruolo.getId());
        assertNull(ruolo);
        ruoloList = ruoloService.findAll();
        assertEquals(ruoloList.size(), 0);
    }
}
