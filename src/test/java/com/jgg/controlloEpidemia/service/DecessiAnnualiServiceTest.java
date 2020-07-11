package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecessiAnnualiServiceTest {
    @Test
    void testDecessiAnnuali() {
        //Inizializzo i service
        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
        ComuneService comuneService = new ComuneService();
        ProvinciaService provinciaService = new ProvinciaService();
        RegioneService regioneService = new RegioneService();
        DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
        //Creo i model
        TipoTerritorio tipoTerritorio = new TipoTerritorio(1, "PianeggianteTest");
        tipoTerritorioService.save(tipoTerritorio);
        Regione r = new Regione(1, "Banditizia", 1, "001001");
        regioneService.save(r);
        Provincia p = new Provincia(1, "CuneoTest", 3, "777777", r);
        provinciaService.save(p);
        Comune comune = new Comune("333333", "Castelnuovo", 1, new Date(), true, tipoTerritorio, p);
        DecessiAnnuali decessiAnnuali = new DecessiAnnuali(2019, 0, 0, 0, 0, p);
        DecessiAnnuali decessiAnnuali2 = new DecessiAnnuali(2020, 0, 0, 0, 0, p);
        //Salvo i model
        comuneService.save(comune);
        decessiAnnualiService.save(decessiAnnuali);
        decessiAnnualiService.saveOrUpdate(decessiAnnuali2);
        //Cerco i model
        DecessiAnnuali findDecessiAnnuali = decessiAnnualiService.findById(decessiAnnuali.getId());
        assertEquals(decessiAnnuali, findDecessiAnnuali);
        //Cerco tutti  i model
        List<DecessiAnnuali> decessiAnnualiList = decessiAnnualiService.findAll();
        assertEquals(2, decessiAnnualiList.size());
        //Save della lista
        decessiAnnualiList.removeAll(decessiAnnualiService.findAll());
        decessiAnnualiList.add(decessiAnnuali);
        decessiAnnualiList.add(decessiAnnuali2);
        decessiAnnualiService.saveOrUpdate(decessiAnnualiList);
        //Find by anno della lista
        decessiAnnualiList.removeAll(decessiAnnualiService.findAll());
        decessiAnnualiList = decessiAnnualiService.findByAnno(decessiAnnuali.getAnno());
        assertEquals(1, decessiAnnualiList.size());
        //Find inserted years
        List<Integer> anni = decessiAnnualiService.findInsertedYears();
        Collections.sort(anni);
        assertEquals(2019, anni.get(0));
        assertEquals(2020, anni.get(1));
        //Aggiorno i model
        decessiAnnuali.setAnno(2021);
        decessiAnnualiService.update(decessiAnnuali);
        findDecessiAnnuali = decessiAnnualiService.findById(decessiAnnuali.getId());
        assertEquals(decessiAnnuali, findDecessiAnnuali);
        //Elimino i model
        decessiAnnualiService.deleteById(decessiAnnuali.getId());
        decessiAnnualiService.deleteById(decessiAnnuali2.getId());
        comuneService.deleteByCodiceIstat("333333");
        provinciaService.deleteById(p.getId());
        regioneService.deleteById(r.getId());
        tipoTerritorioService.deleteById(tipoTerritorio.getId());
        //Assert dei model
        decessiAnnualiList = decessiAnnualiService.findAll();
        assertEquals(0, decessiAnnualiList.size());
    }

}
