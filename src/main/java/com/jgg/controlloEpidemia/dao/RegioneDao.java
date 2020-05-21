package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.model.Ruolo;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class RegioneDao implements RegioneDaoInterface {
    private static Session session = new Session();

    @Override
    public void save(Regione regione) {
        session.openCurrentSessionwithTransaction();
        session.getCurrentSession().save(regione);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        session.openCurrentSessionwithTransaction();
        Regione regione = session.getCurrentSession().get(Regione.class, id);
        session.getCurrentSession().delete(regione);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public void update(Regione regione) {
        session.openCurrentSessionwithTransaction();
        session.getCurrentSession().update(regione);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public Regione findById(Integer id) {
        session.openCurrentSession();
        Regione regione = session.getCurrentSession().get(Regione.class, id);
        session.closeCurrentSession();
        return regione;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Regione> findAll() {
        session.openCurrentSession();
        List<Regione> regione = session.getCurrentSession().createQuery("from Regione").list();
        session.closeCurrentSession();
        return regione;
    }
}
