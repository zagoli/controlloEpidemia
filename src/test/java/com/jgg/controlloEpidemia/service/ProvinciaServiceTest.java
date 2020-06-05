package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProvinciaServiceTest {
    @Test
    void testProvincia() {
        //Inizializzo i service
        RegioneService regioneService = new RegioneService();
        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
        ProvinciaService provinciaService = new ProvinciaService();
        //Creo i model
        TipoTerritorio tipoTerritorio = new TipoTerritorio("Pianeggiante");
        Regione r = new Regione("Banditizia", 1, 333333);
        regioneService.save(r);
        Provincia provincia = new Provincia(4, "Cuneo", 3, 777777, r);
        Provincia provincia2 = new Provincia(4, "Cuneo", 3, 777777, r);
        //Salvo i model
        tipoTerritorioService.save(tipoTerritorio);
        provinciaService.save(provincia);
        provinciaService.saveOrUpdate(provincia2);
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
        regioneService.deleteById(r.getId());
        tipoTerritorioService.deleteById(tipoTerritorio.getId());
        //Assert dei model
        provincia = provinciaService.findById(provincia.getId());
        assertNull(provincia);
        provinciaList = provinciaService.findAll();
        assertEquals(provinciaList.size(), 0);
    }
}
