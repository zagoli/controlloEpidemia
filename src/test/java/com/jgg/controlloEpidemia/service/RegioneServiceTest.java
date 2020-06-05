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
        RegioneService regioneService = new RegioneService();
        //Creo i model
        TipoTerritorio tipoTerritorio = new TipoTerritorio("Pianeggiante");
        Regione regione = new Regione("Banditizia",1,333333);
        Regione regione2 = new Regione("Lombardia", 143, 123456);
        //Salvo i model
        tipoTerritorioService.save(tipoTerritorio);
        regioneService.save(regione);
        regioneService.saveOrUpdate(regione2);
        //Cerco i model
        Regione findRegione = regioneService.findById(regione.getId());
        assertEquals(regione, findRegione);
        //Cerco tutti i model
        List<Regione> regioneList = regioneService.findAll();
        assertEquals(regioneList.size(), 2);
        //Aggiorno i model
        regione.setSuperficie(987);
        regioneService.update(regione);
        findRegione = regioneService.findById(regione.getId());
        assertEquals(regione, findRegione);
        //Elimino i model
        regioneService.deleteById(regione.getId());
        regioneService.deleteById(regione2.getId());
        tipoTerritorioService.deleteById(tipoTerritorio.getId());
        //Assert dei model
        regione = regioneService.findById(regione.getId());
        assertNull(regione);
        regioneList = regioneService.findAll();
        assertEquals(regioneList.size(), 0);
    }
}
