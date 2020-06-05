package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Permesso;
import com.jgg.controlloEpidemia.service.PermessoService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadPermessoTest {
    @Test
    void testLoadPermesso() {
        new LoadPermesso().load();

        PermessoService permessoService = new PermessoService();

        List<Permesso> permessoList = permessoService.findAll();
        assertEquals(permessoList.size(), 1);
    }
}
