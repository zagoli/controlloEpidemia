package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
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
    @SuppressWarnings("unchecked")
    public List<TipoTerritorio> findAll() {
        session.openCurrentSession();
        List<TipoTerritorio> tipoTerritorio = session.getCurrentSession().createQuery("from TipoTerritorio").list();
        session.closeCurrentSession();
        return tipoTerritorio;
    }
}
