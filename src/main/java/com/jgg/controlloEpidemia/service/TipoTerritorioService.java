package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.TipoTerritorioDao;
import com.jgg.controlloEpidemia.model.TipoTerritorio;

public class TipoTerritorioService {

    private static TipoTerritorioDao tipoTerritorioDao;

    public TipoTerritorioService() {
        tipoTerritorioDao = new TipoTerritorioDao();
    }

    public void save(TipoTerritorio entity) {
        tipoTerritorioDao.openCurrentSessionWithTransaction();
        tipoTerritorioDao.save(entity);
        tipoTerritorioDao.closeCurrentSessionWithTransaction();
    }
}
