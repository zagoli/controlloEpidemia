package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Regione;
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
    @SuppressWarnings("unchecked")
    public List<Regione> findAll() {
        session.openCurrentSession();
        List<Regione> regione = session.getCurrentSession().createQuery("from Regione").list();
        session.closeCurrentSession();
        return regione;
    }
}
