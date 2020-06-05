package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.*;
import org.junit.jupiter.api.Test;

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
        TipoTerritorio tipoTerritorio = new TipoTerritorio("Pianeggiante");
        Regione r = new Regione("Banditizia",1,333333);
        regioneService.save(r);
        Provincia p = new Provincia(4,"Cuneo",3,777777,r);
        provinciaService.save(p);
        Comune comune = new Comune(333333,"Castelnuovo",1, new Date(), true, tipoTerritorio, p);
        DecessiAnnuali decessiAnnuali = new DecessiAnnuali(2019, 0, 0, 0, 0, p);
        DecessiAnnuali decessiAnnuali2 = new DecessiAnnuali(2020, 0, 0, 0, 0, p);
        //Salvo i model
        tipoTerritorioService.save(tipoTerritorio);
        comuneService.save(comune);
        provinciaService.save(p);
        decessiAnnualiService.save(decessiAnnuali);
        decessiAnnualiService.saveOrUpdate(decessiAnnuali2);
        //Cerco i model
        DecessiAnnuali findDecessiAnnuali = decessiAnnualiService.findById(decessiAnnuali.getId());
        assertEquals(decessiAnnuali, findDecessiAnnuali);
        //Cerco tutti  i model
        List<DecessiAnnuali> decessiAnnualiList = decessiAnnualiService.findAll();
        assertEquals(decessiAnnualiList.size(), 2);
        //Aggiorno i model
        decessiAnnuali.setAnno(2021);
        decessiAnnualiService.update(decessiAnnuali);
        findDecessiAnnuali = decessiAnnualiService.findById(decessiAnnuali.getId());
        assertEquals(decessiAnnuali, findDecessiAnnuali);
        //Elimino i model
        decessiAnnualiService.deleteById(decessiAnnuali.getId());
        decessiAnnualiService.deleteById(decessiAnnuali2.getId());
        provinciaService.deleteById(p.getId());
        comuneService.deleteByCodiceIstat(333333);
        regioneService.deleteById(r.getId());
        tipoTerritorioService.deleteById(tipoTerritorio.getId());
        //Assert dei model
        decessiAnnuali = decessiAnnualiService.findById(decessiAnnuali.getId());
        assertNull(decessiAnnuali);
        decessiAnnualiList = decessiAnnualiService.findAll();
        assertEquals(decessiAnnualiList.size(), 0);
    }
}
