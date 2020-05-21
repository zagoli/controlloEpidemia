package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Permessi;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PermessiServiceTest {
    @Test
    void testPermessi() {
        //Inizializzo i service
        PermessiService permessiService = new PermessiService();
        //Creo i model
        Permessi permessi = new Permessi("Lettura", "può leggere");
        Permessi permessi2 = new Permessi("Scrittura", "può scrivere");
        //Salvo i model
        permessiService.save(permessi);
        permessiService.save(permessi2);
        //Cerco i model
        Permessi findRuolo = permessiService.findById(permessi.getId());
        assertEquals(permessi, findRuolo);
        //Cerco tutti  i model
        List<Permessi> permessiList = permessiService.findAll();
        assertEquals(permessiList.size(), 2);
        //Elimino i model
        permessiService.deleteById(permessi.getId());
        permessiService.deleteById(permessi2.getId());
        //Assert dei model
        permessi = permessiService.findById(permessi.getId());
        assertNull(permessi);
        permessiList = permessiService.findAll();
        assertEquals(permessiList.size(), 0);
    }
}
