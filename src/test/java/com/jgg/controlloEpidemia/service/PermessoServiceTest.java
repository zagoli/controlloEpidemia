package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Permesso;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PermessoServiceTest {
    @Test
    void testPermesso() {
        //Inizializzo i service
        PermessoService permessoService = new PermessoService();
        //Creo i model
        Permesso permesso = new Permesso("LetturaTest", "può leggere");
        Permesso permesso2 = new Permesso("ScritturaTest", "può scrivere");
        //Salvo i model
        permessoService.save(permesso);
        permessoService.saveOrUpdate(permesso2);
        //Cerco i model
        Permesso findRuolo = permessoService.findByNome(permesso.getNome());
        assertEquals(permesso, findRuolo);
        //Cerco tutti  i model
        List<Permesso> permessoList = permessoService.findAll();
        assertEquals(2, permessoList.size());
        //Save della lista
        permessoList.removeAll(permessoService.findAll());
        permessoList.add(permesso);
        permessoList.add(permesso2);
        permessoService.saveOrUpdate(permessoList);
        //Aggiorno i model
        permesso.setNome("Aggiornamento utenti");
        permessoService.update(permesso);
        findRuolo = permessoService.findById(permesso.getId());
        assertEquals(permesso, findRuolo);
        //Elimino i model
        permessoService.deleteById(permesso.getId());
        permessoService.deleteById(permesso2.getId());
        //Assert dei model
        permessoList = permessoService.findAll();
        assertEquals(0, permessoList.size());
    }
}
