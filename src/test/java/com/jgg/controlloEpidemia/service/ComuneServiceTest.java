package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ComuneServiceTest {

    @Test
    void testComune() {
        //Inizializzo i service
        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
        ComuneService comuneService = new ComuneService();
        //Creo i model
        TipoTerritorio tipoTerritorio = new TipoTerritorio(1, "pianeggiante");
        Comune comune = new Comune(023015, "Castelnuovo", new Date(), 139, true, tipoTerritorio);
        Comune comune2 = new Comune(023022, "Bussolengo", new Date(), 140, true, tipoTerritorio);
        //Salvo i model
        tipoTerritorioService.save(tipoTerritorio);
        comuneService.save(comune);
        comuneService.save(comune2);
        //Cerco i model
        Comune findComune = comuneService.findByCodiceIstat(023015);
        assertEquals(comune, findComune);
        //Cerco tutti  i model
        List<Comune> comuneList = comuneService.findAll();
        assertEquals(comuneList.size(), 2);
        //Aggiorno i model
        comune.setSuperficie(158);
        comuneService.update(comune);
        findComune = comuneService.findByCodiceIstat(023015);
        assertEquals(comune, findComune);
        //Elimino i model
        comuneService.deleteByCodiceIstat(023015);
        comuneService.deleteByCodiceIstat(023022);
        tipoTerritorioService.deleteById(tipoTerritorio.getId());
        //Assert dei model
        comune = comuneService.findByCodiceIstat(023015);
        assertNull(comune);
        comuneList = comuneService.findAll();
        assertEquals(comuneList.size(), 0);
    }
}