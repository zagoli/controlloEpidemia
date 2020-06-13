package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Ruolo;
import com.jgg.controlloEpidemia.service.RuoloService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoadRuolo {

    final RuoloService ruoloService = new RuoloService();

    public void load() {
        Ruolo r = new Ruolo(1, "Amministratore");
        Ruolo r2 = new Ruolo(2, "Personale a contratto");
        Ruolo r3 = new Ruolo(3, "Personale dell'ente");
        Ruolo r4 = new Ruolo(4, "Ricercatore Analista");

        ruoloService.saveOrUpdate(r);
        ruoloService.saveOrUpdate(r2);
        ruoloService.saveOrUpdate(r3);
        ruoloService.saveOrUpdate(r4);

    }
}
