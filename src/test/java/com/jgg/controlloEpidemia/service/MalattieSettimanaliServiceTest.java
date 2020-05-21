package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
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
        //Creo i model
        TipoTerritorio tipoTerritorio = new TipoTerritorio("Pianeggiante");
        Comune comune = new Comune("a", "Castelnuovo", new Date(), 139, true, tipoTerritorio);
        MalattieSettimanali malattieSettimanali = new MalattieSettimanali(2019, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, comune);
        MalattieSettimanali malattieSettimanali2 = new MalattieSettimanali(2020, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, comune);
        //Salvo i model
        tipoTerritorioService.save(tipoTerritorio);
        comuneService.save(comune);
        malattieSettimanaliService.save(malattieSettimanali);
        malattieSettimanaliService.save(malattieSettimanali2);
        //Cerco i model
        MalattieSettimanali findMalattieSettimanali = malattieSettimanaliService.findById(malattieSettimanali.getId());
        assertEquals(malattieSettimanali, findMalattieSettimanali);
        //Cerco tutti  i model
        List<MalattieSettimanali> malattieSettimanaliList = malattieSettimanaliService.findAll();
        assertEquals(malattieSettimanaliList.size(), 2);
        //Elimino i model
        malattieSettimanaliService.deleteById(malattieSettimanali.getId());
        malattieSettimanaliService.deleteById(malattieSettimanali2.getId());
        comuneService.deleteByCodiceIstat(comune.getCodiceIstat());
        tipoTerritorioService.deleteById(tipoTerritorio.getId());
        //Assert dei model
        malattieSettimanali = malattieSettimanaliService.findById(malattieSettimanali.getId());
        assertNull(malattieSettimanali);
        malattieSettimanaliList = malattieSettimanaliService.findAll();
        assertEquals(malattieSettimanaliList.size(), 0);
    }
}