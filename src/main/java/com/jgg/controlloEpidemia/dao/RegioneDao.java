package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Regione;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RegioneDao implements RegioneDaoInterface {
    private static Session s = new Session();

    @Override
    public void save(Regione r) {
        s.openCurrentSessionwithTransaction();
        s.getCurrentSession().save(r);
        s.closeCurrentSessionwithTransaction();

    }
}
