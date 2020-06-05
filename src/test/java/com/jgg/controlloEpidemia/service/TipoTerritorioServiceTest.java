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
        TipoTerritorio tipoterritorio = new TipoTerritorio("PianeggianteTest");
        TipoTerritorio tipoterritorio2 = new TipoTerritorio("MontanoTest");
        //Salvo i model
        tipoTerritorioService.save(tipoterritorio);
        tipoTerritorioService.saveOrUpdate(tipoterritorio2);
        //Cerco i model
        TipoTerritorio findTipoTerritorio = tipoTerritorioService.findById(tipoterritorio.getId());
        assertEquals(tipoterritorio, findTipoTerritorio);
        //Cerco tutti i model
        List<TipoTerritorio> tipoTerritorioList = tipoTerritorioService.findAll();
        assertEquals(tipoTerritorioList.size(), 5);
        //Aggiorno i model
        tipoterritorio.setNome("Personale dell'ente2");
        tipoTerritorioService.update(tipoterritorio);
        findTipoTerritorio = tipoTerritorioService.findById(tipoterritorio.getId());
        assertEquals(tipoterritorio, findTipoTerritorio);
        //Elimino i model
        tipoTerritorioService.deleteById(tipoterritorio.getId());
        tipoTerritorioService.deleteById(tipoterritorio2.getId());
        //Assert dei model
        tipoterritorio = tipoTerritorioService.findById(tipoterritorio.getId());
        assertNull(tipoterritorio);
        tipoTerritorioList = tipoTerritorioService.findAll();
        assertEquals(tipoTerritorioList.size(), 3);
    }
}
