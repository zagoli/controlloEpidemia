package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import org.junit.jupiter.api.Test;

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
        TipoTerritorio tipoTerritorio = new TipoTerritorio(1,"PianeggianteTest");
        Regione regione = new Regione(1,"Banditizia", 1, "333333");
        Regione regione2 = new Regione(2,"Lombardia", 143, "123456");
        //Salvo i model
        tipoTerritorioService.save(tipoTerritorio);
        regioneService.save(regione);
        regioneService.saveOrUpdate(regione2);
        //Cerco i model
        Regione findRegione = regioneService.findByNome(regione.getNome());
        assertEquals(regione, findRegione);
        //Cerco tutti i model
        List<Regione> regioneList = regioneService.findAll();
        assertEquals(2, regioneList.size());
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
        regioneList = regioneService.findAll();
        assertEquals(0, regioneList.size());
    }
}
