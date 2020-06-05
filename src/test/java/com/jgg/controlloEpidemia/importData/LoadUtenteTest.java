package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Utente;
import com.jgg.controlloEpidemia.service.UtenteService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadUtenteTest {
    @Test
    void testLoadUtente() {
        new LoadUtente().load();

        UtenteService utenteService = new UtenteService();

        List<Utente> utenteList = utenteService.findAll();
        assertEquals(utenteList.size(), 4);
    }
}