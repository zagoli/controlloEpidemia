package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Ruolo;
import com.jgg.controlloEpidemia.model.TipoTerritorio;

import java.util.List;

public class TipoTerritorioDao implements TipoTerritorioInterface {

    private static Session session = new Session();

    @Override
    public void save(TipoTerritorio tipoTerritorio) {
        session.openCurrentSessionwithTransaction();
        session.getCurrentSession().save(tipoTerritorio);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        session.openCurrentSessionwithTransaction();
        TipoTerritorio tipoTerritorio = session.getCurrentSession().get(TipoTerritorio.class, id);
        session.getCurrentSession().delete(tipoTerritorio);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public void update(TipoTerritorio tipoTerritorio) {
        session.openCurrentSessionwithTransaction();
        session.getCurrentSession().update(tipoTerritorio);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public TipoTerritorio findById(Integer id) {
        session.openCurrentSession();
        TipoTerritorio tipoTerritorio = session.getCurrentSession().get(TipoTerritorio.class, id);
        session.closeCurrentSession();
        return tipoTerritorio;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TipoTerritorio> findAll() {
        session.openCurrentSession();
        List<TipoTerritorio> tipoTerritorio = session.getCurrentSession().createQuery("from TipoTerritorio").list();
        session.closeCurrentSession();
        return tipoTerritorio;
    }
}
