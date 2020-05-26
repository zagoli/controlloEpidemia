package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
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
        DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
        //Creo i model
        TipoTerritorio tipoTerritorio = new TipoTerritorio("Pianeggiante");
        Comune comune = new Comune(023015, "Castelnuovo", new Date(), 139, true, tipoTerritorio);
        Provincia provincia = new Provincia("Verona", 143, comune);
        DecessiAnnuali decessiAnnuali = new DecessiAnnuali(2019, 0, 0, 0, 0, provincia);
        DecessiAnnuali decessiAnnuali2 = new DecessiAnnuali(2020, 0, 0, 0, 0, provincia);
        //Salvo i model
        tipoTerritorioService.save(tipoTerritorio);
        comuneService.save(comune);
        provinciaService.save(provincia);
        decessiAnnualiService.save(decessiAnnuali);
        decessiAnnualiService.save(decessiAnnuali2);
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
        provinciaService.deleteById(provincia.getId());
        comuneService.deleteByCodiceIstat(comune.getCodiceIstat());
        tipoTerritorioService.deleteById(tipoTerritorio.getId());
        //Assert dei model
        decessiAnnuali = decessiAnnualiService.findById(decessiAnnuali.getId());
        assertNull(decessiAnnuali);
        decessiAnnualiList = decessiAnnualiService.findAll();
        assertEquals(decessiAnnualiList.size(), 0);
    }
}
