package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.TipoTerritorioDao;
import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class TipoTerritorioService {

    private static TipoTerritorioDao tipoTerritorioDao = new TipoTerritorioDao();

    public void save(TipoTerritorio tipoTerritorio) {
        tipoTerritorioDao.save(tipoTerritorio);
    }

    public List<TipoTerritorio> findAll() {
        return tipoTerritorioDao.findAll();
    }
}
