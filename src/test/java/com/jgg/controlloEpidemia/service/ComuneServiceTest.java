package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ComuneServiceTest {

    @Test
    void save() {
        TipoTerritorio tp = new TipoTerritorio(1, "pianeggiante");
        Comune c = new Comune("a", "castelnuovo", new Date(), 139, true, tp);
        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
        ComuneService comuneService = new ComuneService();

        tipoTerritorioService.save(tp);
        comuneService.save(c);

        Comune a = comuneService.findByCodiceIstat("a");
        assertEquals(c,a);
    }

    @Test
    void findByCodiceIstat() {
    }

    @Test
    void deleteByCodiceIstat() {
    }

    @Test
    void findAll() {
    }
}