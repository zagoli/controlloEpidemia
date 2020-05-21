package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ComuneServiceTest {

    @Test
    void testComune() {
        TipoTerritorio tp = new TipoTerritorio(1, "pianeggiante");
        Comune c = new Comune("a", "castelnuovo", new Date(), 139, true, tp);
        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
        ComuneService comuneService = new ComuneService();

        tipoTerritorioService.save(tp);
        comuneService.save(c);

        // trovo comune
        Comune a = comuneService.findByCodiceIstat("a");
        assertEquals(c,a);

        //find all
        c = new Comune("b", "babbalabba", new Date(), 139, true, tp);
        comuneService.save(c);
        List<Comune> lc = comuneService.findAll();
        assertEquals(lc.size(), 2);

        //elimino il comune
        comuneService.deleteByCodiceIstat("a");
        comuneService.deleteByCodiceIstat("b");
        a = comuneService.findByCodiceIstat("a");
        assertNull(a);
        lc = comuneService.findAll();
        assertEquals(lc.size(), 0);
    }
}