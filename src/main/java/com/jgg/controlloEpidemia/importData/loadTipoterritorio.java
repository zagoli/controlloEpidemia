package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.TipoTerritorio;
import com.jgg.controlloEpidemia.service.TipoTerritorioService;

public class loadTipoterritorio {

    static final TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();

    public loadTipoterritorio() {
        TipoTerritorio montano = new TipoTerritorio("montano");
        TipoTerritorio collinare = new TipoTerritorio("collinare");
        TipoTerritorio pianeggiante = new TipoTerritorio("pianeggiante");

        tipoTerritorioService.saveOrUpdate(montano);
        tipoTerritorioService.saveOrUpdate(collinare);
        tipoTerritorioService.saveOrUpdate(pianeggiante);

    }
}
