package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Ruolo;
import com.jgg.controlloEpidemia.service.RuoloService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadRuoloTest {
    @Test
    void testLoadRuolo() {
        new LoadRuolo().load();

        List<Ruolo> ruoloList = new RuoloService().findAll();
        assertEquals(ruoloList.size(), 4);
    }
}
