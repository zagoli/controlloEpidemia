package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProvinciaServiceTest {
    @Test
    void testProvincia() {
        //Inizializzo i service
        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
        ComuneService comuneService = new ComuneService();
        ProvinciaService provinciaService = new ProvinciaService();
        //Creo i model
        TipoTerritorio tipoTerritorio = new TipoTerritorio("Pianeggiante");
        Comune comune = new Comune(023015, "Castelnuovo", new Date(), 139, true, tipoTerritorio);
        Provincia provincia = new Provincia("Verona", 143, comune);
        Provincia provincia2 = new Provincia("Vicenza", 156, comune);
        //Salvo i model
        tipoTerritorioService.save(tipoTerritorio);
        comuneService.save(comune);
        provinciaService.save(provincia);
        provinciaService.save(provincia2);
        //Cerco i model
        Provincia findProvincia = provinciaService.findById(provincia.getId());
        assertEquals(provincia, findProvincia);
        //Cerco tutti  i model
        List<Provincia> provinciaList = provinciaService.findAll();
        assertEquals(provinciaList.size(), 2);
        //Aggiorno i model
        provincia.setSuperficie(198);
        provinciaService.update(provincia);
        findProvincia = provinciaService.findById(provincia.getId());
        assertEquals(provincia, findProvincia);
        //Elimino i model
        provinciaService.deleteById(provincia.getId());
        provinciaService.deleteById(provincia2.getId());
        comuneService.deleteByCodiceIstat(comune.getCodiceIstat());
        tipoTerritorioService.deleteById(tipoTerritorio.getId());
        //Assert dei model
        provincia = provinciaService.findById(provincia.getId());
        assertNull(provincia);
        provinciaList = provinciaService.findAll();
        assertEquals(provinciaList.size(), 0);
    }
}
