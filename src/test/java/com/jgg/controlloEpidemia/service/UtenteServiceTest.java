package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Ruolo;
import com.jgg.controlloEpidemia.model.Utente;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UtenteServiceTest {

    @Test
    void testUtente() {
        //Inizializzo i service
        RuoloService ruoloService = new RuoloService();
        UtenteService utenteService = new UtenteService();
        //Creo i model
        Ruolo ruolo = new Ruolo("AmministratoreTest");
        Utente utente = new Utente("utente1", "password", "Utente", "Uno", ruolo);
        Utente utente2 = new Utente("utente2", "password2", "Utente", "Due", ruolo);
        //Salvo i model
        ruoloService.save(ruolo);
        utenteService.save(utente);
        utenteService.saveOrUpdate(utente2);
        //Cerco i model
        Utente findUtente = utenteService.findById(utente.getId());
        assertEquals(utente, findUtente);
        //Cerco il model dallo username
        findUtente = utenteService.findByUsername("utente1");
        assertEquals(utente, findUtente);
        //Cerco tutti i model
        List<Utente> utenteList = utenteService.findAll();
        assertEquals(6, utenteList.size());
        //Aggiorno i model
        utente.setCognome("DOS");
        utenteService.update(utente);
        findUtente = utenteService.findById(utente.getId());
        assertEquals(utente, findUtente);
        //Elimino i model
        utenteService.deleteById(utente.getId());
        utenteService.deleteById(utente2.getId());
        ruoloService.deleteById(ruolo.getId());
        //Assert dei model
        utente = utenteService.findById(utente.getId());
        assertNull(utente);
        utenteList = utenteService.findAll();
        assertEquals(4, utenteList.size());
    }

    @Test
    void testFindPersonaleAContratto() {
        //Inizializzo i service
        RuoloService ruoloService = new RuoloService();
        Ruolo r = new Ruolo("Personale a contratto Test");
        ruoloService.save(r);
        UtenteService utenteService = new UtenteService();
        //Creo i model
        Utente utente = new Utente("utente1", "password", "Utente", "Uno", ruoloService.findById(1));
        // Salvo l'utente
        utenteService.save(utente);
        // Cerco gli utenti con ruolo personale a contratto
        List<Utente> utenti = utenteService.findAllPersonaleContratto();
        assertTrue(utenti.size() >= 1);

        utenteService.deleteById(utente.getId());
        ruoloService.deleteById(5);
    }
}
