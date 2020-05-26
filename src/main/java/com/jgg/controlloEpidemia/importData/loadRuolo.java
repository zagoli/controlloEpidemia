package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Ruolo;
import com.jgg.controlloEpidemia.service.RuoloService;

public class loadRuolo {

    static final RuoloService ruoloService = new RuoloService();

    public void loadTipoterritorio() {
        Ruolo r = new Ruolo();

        ruoloService.saveOrUpdate(r);

    }
}
