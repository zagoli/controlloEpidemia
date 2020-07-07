package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;
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
        TipoTerritorio tipoTerritorio = new TipoTerritorio(1,"PianeggianteTest");
        Regione r = new Regione(1,"BanditiziaTest", 1, "333333");
        regioneService.save(r);
        Provincia p = new Provincia(1, "CuneoTest", 3, "777777", r);
        provinciaService.save(p);
        Comune comune = new Comune("333333", "Castelnuovo", 1, new Date(), true, tipoTerritorio, p);
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
        assertEquals(2, malattieSettimanaliList.size());
        //Save della lista
        malattieSettimanaliList.removeAll(malattieSettimanaliService.findAll());
        malattieSettimanaliList.add(malattieSettimanali);
        malattieSettimanaliList.add(malattieSettimanali2);
        malattieSettimanaliService.saveOrUpdate(malattieSettimanaliList);
        //Find by anno della lista
        malattieSettimanaliList.removeAll(malattieSettimanaliService.findAll());
        malattieSettimanaliList = malattieSettimanaliService.findByAnno(malattieSettimanali.getAnno());
        assertEquals(1, malattieSettimanaliList.size());
        //Find inserted years
        List<Integer> anni = malattieSettimanaliService.findInsertedYears();
        Collections.sort(anni);
        assertEquals(2019, anni.get(0));
        assertEquals(2020, anni.get(1));
        //Aggiorno i model
        malattieSettimanali.setAnno(2021);
        malattieSettimanaliService.update(malattieSettimanali);
        findMalattieSettimanali = malattieSettimanaliService.findById(malattieSettimanali.getId());
        assertEquals(malattieSettimanali, findMalattieSettimanali);
        //Elimino i model
        malattieSettimanaliService.deleteById(malattieSettimanali.getId());
        malattieSettimanaliService.deleteById(malattieSettimanali2.getId());
        comuneService.deleteByCodiceIstat("333333");
        provinciaService.deleteById(p.getId());
        regioneService.deleteById(r.getId());
        tipoTerritorioService.deleteById(tipoTerritorio.getId());
        //Assert dei model
        malattieSettimanaliList = malattieSettimanaliService.findAll();
        assertEquals(0, malattieSettimanaliList.size());
    }
}