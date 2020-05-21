package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.model.Ruolo;
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
    public void deleteById(Integer id) {
        session.openCurrentSessionwithTransaction();
        Provincia provincia = session.getCurrentSession().get(Provincia.class, id);
        session.getCurrentSession().delete(provincia);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public void update(Provincia provincia) {
        session.openCurrentSessionwithTransaction();
        session.getCurrentSession().update(provincia);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public Provincia findById(Integer id) {
        session.openCurrentSession();
        Provincia provincia = session.getCurrentSession().get(Provincia.class, id);
        session.closeCurrentSession();
        return provincia;
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
