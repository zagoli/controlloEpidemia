package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.model.Regione;
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
        RegioneService regioneService = new RegioneService();
        ProvinciaService provinciaService = new ProvinciaService();
        //Creo i model
        TipoTerritorio tipoTerritorio = new TipoTerritorio(1, "pianeggiante");
        Regione r = new Regione("Banditizia", 1, 333333);
        regioneService.save(r);
        Provincia p = new Provincia(4, "Cuneo", 3, 777777, r);
        provinciaService.save(p);
        Comune comune = new Comune(333333, "Castelnuovo", 1, new Date(), true, tipoTerritorio, p);
        Comune comune2 = new Comune(444444, "Gelateria", 4, new Date(), true, tipoTerritorio, p);
        //Salvo i model
        tipoTerritorioService.save(tipoTerritorio);
        comuneService.save(comune);
        comuneService.saveOrUpdate(comune2);
        //Cerco i model
        Comune findComune = comuneService.findByCodiceIstat(333333);
        assertEquals(comune, findComune);
        //Cerco tutti  i model
        List<Comune> comuneList = comuneService.findAll();
        assertEquals(comuneList.size(), 2);
        //Aggiorno i model
        comune.setSuperficie(158);
        comuneService.update(comune);
        findComune = comuneService.findByCodiceIstat(333333);
        assertEquals(comune, findComune);
        //Elimino i model
        comuneService.deleteByCodiceIstat(333333);
        comuneService.deleteByCodiceIstat(444444);
        provinciaService.deleteById(4);
        regioneService.deleteById(r.getId());
        tipoTerritorioService.deleteById(tipoTerritorio.getId());
        //Assert dei model
        comune = comuneService.findByCodiceIstat(333333);
        assertNull(comune);

    }
}