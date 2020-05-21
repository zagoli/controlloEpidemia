package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.TipoTerritorioDao;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class TipoTerritorioService {

    private static TipoTerritorioDao tipoTerritorioDao = new TipoTerritorioDao();

    public void save(TipoTerritorio tipoTerritorio) {
        tipoTerritorioDao.save(tipoTerritorio);
    }

    public TipoTerritorio findById(Integer id) {
        return tipoTerritorioDao.findById(id);
    }

    public void deleteById(Integer id) {
        tipoTerritorioDao.deleteById(id);
    }

    public List<TipoTerritorio> findAll() {
        return tipoTerritorioDao.findAll();
    }
}
