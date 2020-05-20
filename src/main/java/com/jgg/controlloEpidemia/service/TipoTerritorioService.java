package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.TipoTerritorioDao;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TipoTerritorioService {

    private static TipoTerritorioDao tipoTerritorioDao = new TipoTerritorioDao();

    public void save(TipoTerritorio entity) {
        tipoTerritorioDao.save(entity);
    }
}
