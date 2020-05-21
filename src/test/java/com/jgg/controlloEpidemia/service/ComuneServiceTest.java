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
        //Inizializzo i service
        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
        ComuneService comuneService = new ComuneService();
        //Creo i model
        TipoTerritorio tipoTerritorio = new TipoTerritorio(1, "pianeggiante");
        Comune comune = new Comune("a", "Castelnuovo", new Date(), 139, true, tipoTerritorio);
        Comune comune2 = new Comune("b", "Bussolengo", new Date(), 139, true, tipoTerritorio);
        //Salvo i model
        tipoTerritorioService.save(tipoTerritorio);
        comuneService.save(comune);
        comuneService.save(comune2);
        //Cerco i model
        Comune findComune = comuneService.findByCodiceIstat("a");
        assertEquals(comune,findComune);
        //Cerco tutti  i model
        List<Comune> comuneList = comuneService.findAll();
        assertEquals(comuneList.size(), 2);
        //Elimino i model
        comuneService.deleteByCodiceIstat("a");
        comuneService.deleteByCodiceIstat("b");
        //Assert dei model
        comune = comuneService.findByCodiceIstat("a");
        assertNull(comune);
        comuneList = comuneService.findAll();
        assertEquals(comuneList.size(), 0);
    }
}