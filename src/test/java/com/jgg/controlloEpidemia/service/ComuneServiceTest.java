package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComuneServiceTest {

    @Test
    void testComune() {
        //Inizializzo i service
        ComuneService comuneService = new ComuneService();
        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
        RegioneService regioneService = new RegioneService();
        ProvinciaService provinciaService = new ProvinciaService();
        //Creo i model
        TipoTerritorio tipoTerritorio = new TipoTerritorio(1, "pianeggianteTest");
        Regione r = new Regione(1, "BanditiziaTest", 1, "001001");
        Provincia p = new Provincia(1, "CuneoTest", 3, "001001", r);
        Comune comune = new Comune("333333", "CastelnuovoTest", 1, new Date(), true, tipoTerritorio, p);
        Comune comune2 = new Comune("444444", "GelateriaTest", 4, new Date(), true, tipoTerritorio, p);
        //Salvo i model
        tipoTerritorioService.save(tipoTerritorio);
        regioneService.save(r);
        provinciaService.save(p);
        comuneService.save(comune);
        comuneService.saveOrUpdate(comune2);
        //Cerco i model
        Comune findComune = comuneService.findByCodiceIstat("333333");
        assertEquals(comune, findComune);
        findComune = comuneService.findByNome("GelateriaTest");
        assertEquals(comune2, findComune);
        //Cerco tutti  i model
        List<Comune> comuneList = comuneService.findAll();
        assertEquals(2, comuneList.size());
        //Save della lista
        comuneList.removeAll(comuneService.findAll());
        comuneList.add(comune);
        comuneList.add(comune2);
        comuneService.saveOrUpdate(comuneList);
        //Count comuni
        assertEquals(2, comuneService.countComuni());
        //Aggiorno i model
        comune.setSuperficie(158);
        comuneService.update(comune);
        findComune = comuneService.findByCodiceIstat("333333");
        assertEquals(comune, findComune);
        //Elimino i model
        comuneService.deleteByCodiceIstat("333333");
        comuneService.deleteByCodiceIstat("444444");
        provinciaService.deleteById(p.getId());
        regioneService.deleteById(r.getId());
        tipoTerritorioService.deleteById(tipoTerritorio.getId());
        //Assert dei model
        comuneList = comuneService.findAll();
        assertEquals(0, comuneList.size());
    }
}