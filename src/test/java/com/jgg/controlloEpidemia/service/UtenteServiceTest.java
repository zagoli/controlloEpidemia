package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Ruolo;
import com.jgg.controlloEpidemia.model.Utente;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UtenteServiceTest {

    @Test
    void testUtente() {
        //Inizializzo i service
        RuoloService ruoloService = new RuoloService();
        UtenteService utenteService = new UtenteService();
        //Creo i model
        Ruolo ruolo = new Ruolo("Pianeggiante");
        Utente utente = new Utente("utente1", "password", "Utente", "Uno", ruolo);
        Utente utente2 = new Utente("utente2", "password2", "Utente", "Due", ruolo);
        //Salvo i model
        ruoloService.save(ruolo);
        utenteService.save(utente);
        utenteService.save(utente2);
        //Cerco i model
        Utente findUtente = utenteService.findById(utente.getId());
        assertEquals(utente, findUtente);
        //Cerco tutti i model
        List<Utente> utenteList = utenteService.findAll();
        assertEquals(utenteList.size(), 2);
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
        assertEquals(utenteList.size(), 0);
    }

    @Test
    void findById(){
        //Inizializzo i service
        RuoloService ruoloService = new RuoloService();
        UtenteService utenteService = new UtenteService();
        //Creo i model
        Ruolo ruolo = new Ruolo("amministratore");
        Utente utente = new Utente("gianni rivera", "password", "Gianni", "Rivera", ruolo);
        // Salvo i dati
        ruoloService.save(ruolo);
        utenteService.save(utente);
        //Cerco utente
        Utente found = utenteService.findByUsername(utente.getUsername());
        Utente notfound = utenteService.findByUsername("caciotta");
        // Sono uguali?
        assertEquals(utente,found);
        // Utente non trovato
        assertNull(notfound);
        // Elimino i test
        utenteService.deleteById(utente.getId());
        ruoloService.deleteById(ruolo.getId());

    }
}
