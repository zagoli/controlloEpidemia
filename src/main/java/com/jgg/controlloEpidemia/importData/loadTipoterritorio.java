package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.TipoTerritorio;
import com.jgg.controlloEpidemia.service.TipoTerritorioService;

public class loadTipoterritorio {


    static final TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();

    public void loadTipoterritorio() {
        TipoTerritorio montano = new TipoTerritorio();
        TipoTerritorio collinare = new TipoTerritorio();
        TipoTerritorio pianeggiante = new TipoTerritorio();

        //usare merge o saveOrUpdate?
        tipoTerritorioService.save(montano);
        tipoTerritorioService.save(collinare);
        tipoTerritorioService.save(pianeggiante);

    }
}
