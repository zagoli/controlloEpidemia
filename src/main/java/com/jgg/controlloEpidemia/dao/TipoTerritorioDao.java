package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.TipoTerritorio;

public class TipoTerritorioDao implements TipoTerritorioInterface {

    private static Session s = new Session();

    @Override
    public void save(TipoTerritorio t) {
        s.openCurrentSessionwithTransaction();
        s.getCurrentSession().save(t);
        s.closeCurrentSessionwithTransaction();
    }
}
