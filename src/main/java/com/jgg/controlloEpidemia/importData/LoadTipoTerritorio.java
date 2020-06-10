package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.TipoTerritorio;
import com.jgg.controlloEpidemia.service.TipoTerritorioService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoadTipoTerritorio {

    final TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();

    public void load() {
        TipoTerritorio montano = new TipoTerritorio("Montano");
        TipoTerritorio collinare = new TipoTerritorio("Collinare");
        TipoTerritorio pianeggiante = new TipoTerritorio("Pianeggiante");

        tipoTerritorioService.saveOrUpdate(montano);
        tipoTerritorioService.saveOrUpdate(collinare);
        tipoTerritorioService.saveOrUpdate(pianeggiante);
    }
}
