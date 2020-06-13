package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        TipoTerritorio tipoTerritorio = new TipoTerritorio("PianeggianteTest");
        tipoTerritorioService.save(tipoTerritorio);
        Regione r = new Regione("Banditizia", 1, "001001");
        regioneService.save(r);
        Provincia p = new Provincia(16, "CuneoTest", 3, "777777", r);
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
        assertEquals(decessiAnnualiList.size(), 7);
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
        decessiAnnuali = decessiAnnualiService.findById(decessiAnnuali.getId());
        assertNull(decessiAnnuali);
        decessiAnnualiList = decessiAnnualiService.findAll();
        assertEquals(decessiAnnualiList.size(), 5);
    }

    // Dopo aver fatto partire EtlDecessiTest
    @Test
    void findInsertedYears() {
        DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
        List<Integer> anni = decessiAnnualiService.findInsertedYears();
        Collections.sort(anni);
        assertEquals(2011, anni.get(0));
        assertEquals(2019, anni.get(4));
    }
}
