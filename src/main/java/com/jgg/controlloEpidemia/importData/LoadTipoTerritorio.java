package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.TipoTerritorio;
import com.jgg.controlloEpidemia.service.TipoTerritorioService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoadTipoTerritorio {

    final TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();

    public void load() {
        TipoTerritorio montano = new TipoTerritorio(1,"montano");
        TipoTerritorio collinare = new TipoTerritorio(2,"collinare");
        TipoTerritorio pianeggiante = new TipoTerritorio(3,"pianeggiante");

        tipoTerritorioService.saveOrUpdate(montano);
        tipoTerritorioService.saveOrUpdate(collinare);
        tipoTerritorioService.saveOrUpdate(pianeggiante);
    }
}
