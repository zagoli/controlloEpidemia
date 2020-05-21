package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MalattieSettimanaliServiceTest {

    @Test
    void test() {
        // Inserisco un nuovo comune
        TipoTerritorio tp = new TipoTerritorio(1, "pianeggiante");
        Comune c = new Comune("a", "castelnuovo", new Date(), 139, true, tp);
        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
        ComuneService comuneService = new ComuneService();
        tipoTerritorioService.save(tp);
        comuneService.save(c);

        // Creo malattia settimanale
        MalattieSettimanaliService malattieSettimanaliService= new MalattieSettimanaliService();
        MalattieSettimanali m = new MalattieSettimanali(2019,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,c);
        malattieSettimanaliService.save(m);

        Comune test = comuneService.findByCodiceIstat("a");
        assertEquals(test.getMalattieSettimanali().size(), 1);
        assertEquals(test.getMalattieSettimanali().get(0).getAnno(), 2019);

        //Cancella tutto
        malattieSettimanaliService.delete(m);
        comuneService.deleteByCodiceIstat("a");
    }
}