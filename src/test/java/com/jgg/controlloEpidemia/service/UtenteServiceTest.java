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
        Ruolo ruolo = new Ruolo(1,"AmministratoreTest");
        Ruolo ruolo2 = new Ruolo(2,"ContrattoTest");
        Utente utente = new Utente("utente1", "password", "Utente", "Uno", ruolo);
        Utente utente2 = new Utente("utente2", "password2", "Utente", "Due", ruolo2);
        //Salvo i model
        ruoloService.save(ruolo);
        ruoloService.save(ruolo2);
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
        assertEquals(2, utenteList.size());
        //Save della lista
        utenteList.removeAll(utenteService.findAll());
        utenteList.add(utente);
        utenteList.add(utente2);
        utenteService.saveOrUpdate(utenteList);
        //Find personale a contratto
        List<Utente> utenti = utenteService.findAllPersonaleContratto();
        assertEquals(1, utenti.size());
        //Aggiorno i model
        utente.setCognome("DOS");
        utenteService.update(utente);
        findUtente = utenteService.findById(utente.getId());
        assertEquals(utente, findUtente);
        //Elimino i model
        utenteService.deleteById(utente.getId());
        utenteService.deleteById(utente2.getId());
        ruoloService.deleteById(ruolo.getId());
        ruoloService.deleteById(ruolo2.getId());
        //Assert dei model
        utente = utenteService.findById(utente.getId());
        assertNull(utente);
        utenteList = utenteService.findAll();
        assertEquals(0, utenteList.size());
    }

}
