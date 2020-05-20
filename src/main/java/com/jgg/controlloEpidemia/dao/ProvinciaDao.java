package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Provincia;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ProvinciaDao implements ProvinciaDaoInterface {
    private static Session session = new Session();

    @Override
    public void save(Provincia provincia) {
        session.openCurrentSessionwithTransaction();
        session.getCurrentSession().save(provincia);
        session.closeCurrentSessionwithTransaction();

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Provincia> findAll() {
        session.openCurrentSession();
        List<Provincia> provincia = session.getCurrentSession().createQuery("from Provincia").list();
        session.closeCurrentSession();
        return provincia;
    }
}
