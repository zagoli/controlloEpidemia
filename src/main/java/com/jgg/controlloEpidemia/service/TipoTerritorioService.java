package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.TipoTerritorioDao;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class TipoTerritorioService {

    private static final TipoTerritorioDao tipoTerritorioDao = new TipoTerritorioDao();

    public void save(TipoTerritorio tipoTerritorio) {
        tipoTerritorioDao.openCurrentSessionWithTransaction();
        tipoTerritorioDao.save(tipoTerritorio);
        tipoTerritorioDao.closeCurrentSessionWithTransaction();
    }

    public void deleteById(Integer id) {
        tipoTerritorioDao.openCurrentSessionWithTransaction();
        tipoTerritorioDao.deleteById(id);
        tipoTerritorioDao.closeCurrentSessionWithTransaction();
    }

    public void update(TipoTerritorio tipoTerritorio) {
        tipoTerritorioDao.openCurrentSessionWithTransaction();
        tipoTerritorioDao.update(tipoTerritorio);
        tipoTerritorioDao.closeCurrentSessionWithTransaction();
    }

    public void saveOrUpdate(TipoTerritorio tipoTerritorio) {
        tipoTerritorioDao.openCurrentSessionWithTransaction();
        tipoTerritorioDao.saveOrUpdate(tipoTerritorio);
        tipoTerritorioDao.closeCurrentSessionWithTransaction();
    }

    public void saveOrUpdate(List<TipoTerritorio> tipoTerritorioList) {
        tipoTerritorioDao.openCurrentSessionWithTransaction();
        for (TipoTerritorio tipoTerritorio : tipoTerritorioList) {
            tipoTerritorioDao.saveOrUpdate(tipoTerritorio);
        }
        tipoTerritorioDao.closeCurrentSessionWithTransaction();
    }

    public TipoTerritorio findById(Integer id) {
        tipoTerritorioDao.openCurrentSession();
        TipoTerritorio tipoTerritorioById = tipoTerritorioDao.findById(id);
        tipoTerritorioDao.closeCurrentSession();
        return tipoTerritorioById;
    }

    public TipoTerritorio findByNome(String nome) {
        tipoTerritorioDao.openCurrentSession();
        TipoTerritorio tipoTerritorioByNome = tipoTerritorioDao.findByNome(nome);
        tipoTerritorioDao.closeCurrentSession();
        return tipoTerritorioByNome;
    }

    public List<TipoTerritorio> findAll() {
        tipoTerritorioDao.openCurrentSession();
        List<TipoTerritorio> tipoTerritorioAll = tipoTerritorioDao.findAll();
        tipoTerritorioDao.closeCurrentSession();
        return tipoTerritorioAll;
    }

}
