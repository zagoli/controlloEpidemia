package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.TipoTerritorio;
import com.jgg.controlloEpidemia.service.TipoTerritorioService;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class LoadTipoTerritorio {

    List<TipoTerritorio> tipoTerritorioList = new ArrayList<>();

    public void load() {
        tipoTerritorioList.add(new TipoTerritorio(1, "Montano"));
        tipoTerritorioList.add(new TipoTerritorio(2, "Collinare"));
        tipoTerritorioList.add(new TipoTerritorio(3, "Pianeggiante"));

        new TipoTerritorioService().saveOrUpdate(tipoTerritorioList);
    }

}
