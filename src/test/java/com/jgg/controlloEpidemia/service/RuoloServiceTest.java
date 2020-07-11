package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Ruolo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuoloServiceTest {

    @Test
    void testRuolo() {
        //Inizializzo i service
        RuoloService ruoloService = new RuoloService();
        //Creo i model
        Ruolo ruolo = new Ruolo(1, "AmministratoreTest");
        Ruolo ruolo2 = new Ruolo(2, "AmministratoreTest2");
        //Salvo i model
        ruoloService.save(ruolo);
        ruoloService.saveOrUpdate(ruolo2);
        //Cerco i model
        Ruolo findRuolo = ruoloService.findByNome(ruolo.getNome());
        assertEquals(ruolo, findRuolo);
        //Cerco tutti  i model
        List<Ruolo> ruoloList = ruoloService.findAll();
        assertEquals(2, ruoloList.size());
        //Save della lista
        ruoloList.removeAll(ruoloService.findAll());
        ruoloList.add(ruolo);
        ruoloList.add(ruolo2);
        ruoloService.saveOrUpdate(ruoloList);
        //Aggiorno i model
        ruolo.setNome("Collinare");
        ruoloService.update(ruolo);
        findRuolo = ruoloService.findById(ruolo.getId());
        assertEquals(ruolo, findRuolo);
        //Elimino i model
        ruoloService.deleteById(ruolo.getId());
        ruoloService.deleteById(ruolo2.getId());
        //Assert dei model
        ruoloList = ruoloService.findAll();
        assertEquals(0, ruoloList.size());
    }
}
