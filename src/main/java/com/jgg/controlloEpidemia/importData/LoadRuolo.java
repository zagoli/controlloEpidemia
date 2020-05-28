package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Ruolo;
import com.jgg.controlloEpidemia.service.RuoloService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoadRuolo {

    static final RuoloService ruoloService = new RuoloService();

    public void load() {
        Ruolo r = new Ruolo("Amministratore");
        Ruolo r2 = new Ruolo("Personale a contratto");
        Ruolo r3 = new Ruolo("Personale dell'ente");
        Ruolo r4 = new Ruolo("Ricercatore Analista");

        ruoloService.saveIfNotPresent(r);
        ruoloService.saveIfNotPresent(r2);
        ruoloService.saveIfNotPresent(r3);
        ruoloService.saveIfNotPresent(r4);

    }
}
