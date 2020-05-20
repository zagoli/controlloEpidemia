package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Provincia;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProvinciaDao implements ProvinciaDaoInterface {
    private static Session s = new Session();

    @Override
    public void save(Provincia p) {
        s.openCurrentSessionwithTransaction();
        s.getCurrentSession().save(p);
        s.closeCurrentSessionwithTransaction();

    }
}
