package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.TipoTerritorio;
import com.jgg.controlloEpidemia.service.TipoTerritorioService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadTipoTerritorioTest {
    @Test
    void testLoadTipoTerritorio() {
        new LoadTipoTerritorio().load();

        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();

        List<TipoTerritorio> tipoTerritorioList = tipoTerritorioService.findAll();
        assertEquals(tipoTerritorioList.size(), 3);
    }
}
