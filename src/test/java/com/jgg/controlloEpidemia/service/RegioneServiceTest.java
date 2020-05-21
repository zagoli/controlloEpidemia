package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RegioneServiceTest {
    @Test
    void testRegione() {
        //Inizializzo i service
        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
        ComuneService comuneService = new ComuneService();
        RegioneService regioneService = new RegioneService();
        //Creo i model
        TipoTerritorio tipoTerritorio = new TipoTerritorio("Pianeggiante");
        Comune comune = new Comune("a", "Castelnuovo", new Date(), 139, true, tipoTerritorio);
        Regione regione = new Regione("Veneto", 147, comune);
        Regione regione2 = new Regione("Lombardia", 143, comune);
        //Salvo i model
        tipoTerritorioService.save(tipoTerritorio);
        comuneService.save(comune);
        regioneService.save(regione);
        regioneService.save(regione2);
        //Cerco i model
        Regione findRegione = regioneService.findById(regione.getId());
        assertEquals(regione, findRegione);
        //Cerco tutti  i model
        List<Regione> regioneList = regioneService.findAll();
        assertEquals(regioneList.size(), 2);
        //Elimino i model
        regioneService.deleteById(regione.getId());
        regioneService.deleteById(regione2.getId());
        comuneService.deleteByCodiceIstat(comune.getCodiceIstat());
        tipoTerritorioService.deleteById(tipoTerritorio.getId());
        //Assert dei model
        regione = regioneService.findById(regione.getId());
        assertNull(regione);
        regioneList = regioneService.findAll();
        assertEquals(regioneList.size(), 0);
    }
}
