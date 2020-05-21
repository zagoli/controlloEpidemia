package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.TipoTerritorio;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TipoTerritorioServiceTest {
    @Test
    void testTipoTerritorio() {
        //Inizializzo i service
        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
        //Creo i model
        TipoTerritorio tipoterritorio = new TipoTerritorio("Personale dell'ente");
        TipoTerritorio tipoterritorio2 = new TipoTerritorio("Personale a contratto");
        //Salvo i model
        tipoTerritorioService.save(tipoterritorio);
        tipoTerritorioService.save(tipoterritorio2);
        //Cerco i model
        TipoTerritorio findTipoTerritorio = tipoTerritorioService.findById(tipoterritorio.getId());
        assertEquals(tipoterritorio, findTipoTerritorio);
        //Cerco tutti  i model
        List<TipoTerritorio> tipoTerritorioList = tipoTerritorioService.findAll();
        assertEquals(tipoTerritorioList.size(), 2);
        //Elimino i model
        tipoTerritorioService.deleteById(tipoterritorio.getId());
        tipoTerritorioService.deleteById(tipoterritorio2.getId());
        //Assert dei model
        tipoterritorio = tipoTerritorioService.findById(tipoterritorio.getId());
        assertNull(tipoterritorio);
        tipoTerritorioList = tipoTerritorioService.findAll();
        assertEquals(tipoTerritorioList.size(), 0);
    }
}
