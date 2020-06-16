package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Permesso;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PermessoServiceTest {
    @Test
    void testPermessi() {
        //Inizializzo i service
        PermessoService permessoService = new PermessoService();
        //Creo i model
        Permesso permesso = new Permesso("LetturaTest", "può leggere");
        Permesso permesso2 = new Permesso("ScritturaTest", "può scrivere");
        //Salvo i model
        permessoService.save(permesso);
        permessoService.saveOrUpdate(permesso2);
        //Cerco i model
        Permesso findRuolo = permessoService.findById(permesso.getId());
        assertEquals(permesso, findRuolo);
        //Cerco tutti  i model
        List<Permesso> permessoList = permessoService.findAll();
        assertEquals(3, permessoList.size());
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
        permesso2 = permessoService.findByNome(permesso2.getNome());
        assertNull(permesso2);
        permessoList = permessoService.findAll();
        assertEquals(1, permessoList.size());
    }
}
