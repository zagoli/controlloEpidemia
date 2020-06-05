package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.*;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MalattieSettimanaliServiceTest {

    @Test
    void test() {
        //Inizializzo i service
        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
        ComuneService comuneService = new ComuneService();
        MalattieSettimanaliService malattieSettimanaliService = new MalattieSettimanaliService();
        RegioneService regioneService = new RegioneService();
        ProvinciaService provinciaService = new ProvinciaService();
        //Creo i model
        TipoTerritorio tipoTerritorio = new TipoTerritorio("Pianeggiante");
        Regione r = new Regione("Banditizia",1,333333);
        regioneService.save(r);
        Provincia p = new Provincia(4,"Cuneo",3,777777,r);
        provinciaService.save(p);
        Comune comune = new Comune(333333,"Castelnuovo",1, new Date(), true, tipoTerritorio, p);
        MalattieSettimanali malattieSettimanali = new MalattieSettimanali(2019, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, comune);
        MalattieSettimanali malattieSettimanali2 = new MalattieSettimanali(2020, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, comune);
        //Salvo i model
        tipoTerritorioService.save(tipoTerritorio);
        comuneService.save(comune);
        malattieSettimanaliService.save(malattieSettimanali);
        malattieSettimanaliService.saveOrUpdate(malattieSettimanali2);
        //Cerco i model
        MalattieSettimanali findMalattieSettimanali = malattieSettimanaliService.findById(malattieSettimanali.getId());
        assertEquals(malattieSettimanali, findMalattieSettimanali);
        //Cerco tutti  i model
        List<MalattieSettimanali> malattieSettimanaliList = malattieSettimanaliService.findAll();
        assertEquals(malattieSettimanaliList.size(), 2);
        //Aggiorno i model
        malattieSettimanali.setAnno(2021);
        malattieSettimanaliService.update(malattieSettimanali);
        findMalattieSettimanali = malattieSettimanaliService.findById(malattieSettimanali.getId());
        assertEquals(malattieSettimanali, findMalattieSettimanali);
        //Elimino i model
        malattieSettimanaliService.deleteById(malattieSettimanali.getId());
        malattieSettimanaliService.deleteById(malattieSettimanali2.getId());
        comuneService.deleteByCodiceIstat(333333);
        provinciaService.deleteById(4);
        regioneService.deleteById(r.getId());
        tipoTerritorioService.deleteById(tipoTerritorio.getId());
        //Assert dei model
        malattieSettimanali = malattieSettimanaliService.findById(malattieSettimanali.getId());
        assertNull(malattieSettimanali);
        malattieSettimanaliList = malattieSettimanaliService.findAll();
        assertEquals(malattieSettimanaliList.size(), 0);
    }
}