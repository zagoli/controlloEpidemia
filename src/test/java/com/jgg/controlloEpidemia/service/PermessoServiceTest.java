package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.Permesso;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PermessoServiceTest {
    @Test
    void testPermessi() {
        //Inizializzo i service
        PermessoService permessoService = new PermessoService();
        //Creo i model
        Permesso permesso = new Permesso("Lettura", "può leggere");
        Permesso permesso2 = new Permesso("Scrittura", "può scrivere");
        //Salvo i model
        permessoService.save(permesso);
        permessoService.save(permesso2);
        //Cerco i model
        Permesso findRuolo = permessoService.findById(permesso.getId());
        assertEquals(permesso, findRuolo);
        //Cerco tutti  i model
        List<Permesso> permessoList = permessoService.findAll();
        assertEquals(permessoList.size(), 2);
        //Aggiorno i model
        permesso.setNome("Aggiornamento utenti");
        permessoService.update(permesso);
        findRuolo = permessoService.findById(permesso.getId());
        assertEquals(permesso, findRuolo);
        //Elimino i model
        permessoService.deleteById(permesso.getId());
        permessoService.deleteById(permesso2.getId());
        //Assert dei model
        permesso = permessoService.findById(permesso.getId());
        assertNull(permesso);
        permessoList = permessoService.findAll();
        assertEquals(permessoList.size(), 0);
    }
}
