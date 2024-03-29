package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.model.Regione;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProvinciaServiceTest {
    @Test
    void testProvincia() {
        //Inizializzo i service
        RegioneService regioneService = new RegioneService();
        ProvinciaService provinciaService = new ProvinciaService();
        //Creo i model
        Regione r = new Regione(1, "BanditiziaTest", 1, "001006");
        regioneService.save(r);
        Provincia provincia = new Provincia(15, "Verona", 3, "001004", r);
        Provincia provincia2 = new Provincia(14, "Mantova", 3, "001005", r);
        //Salvo i model
        provinciaService.save(provincia);
        provinciaService.saveOrUpdate(provincia2);
        //Cerco i model
        Provincia findProvincia = provinciaService.findByNome(provincia.getNome());
        assertEquals(provincia, findProvincia);
        //Cerco tutti  i model
        List<Provincia> provinciaList = provinciaService.findAll();
        assertEquals(2, provinciaList.size());
        //Save della lista
        provinciaList.removeAll(provinciaService.findAll());
        provinciaList.add(provincia);
        provinciaList.add(provincia2);
        provinciaService.saveOrUpdate(provinciaList);
        //Aggiorno i model
        provincia.setSuperficie(198);
        provinciaService.update(provincia);
        findProvincia = provinciaService.findById(provincia.getId());
        assertEquals(provincia, findProvincia);
        //Elimino i model
        provinciaService.deleteById(provincia.getId());
        provinciaService.deleteById(provincia2.getId());
        regioneService.deleteById(r.getId());
        //Assert dei model
        provinciaList = provinciaService.findAll();
        assertEquals(0, provinciaList.size());
    }
}
