package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.TipoTerritorio;
import com.jgg.controlloEpidemia.service.TipoTerritorioService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoadTipoTerritorio {

    static final TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();

    public void load() {
        TipoTerritorio montano = new TipoTerritorio("montano");
        TipoTerritorio collinare = new TipoTerritorio("collinare");
        TipoTerritorio pianeggiante = new TipoTerritorio("pianeggiante");

        tipoTerritorioService.saveIfNotPresent(montano);
        tipoTerritorioService.saveIfNotPresent(collinare);
        tipoTerritorioService.saveIfNotPresent(pianeggiante);
    }
}
