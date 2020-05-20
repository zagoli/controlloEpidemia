package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Comune;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ComuneDao implements ComuneDaoInterface {

    private static Session s = new Session();

    @Override
    public void save(Comune c) {
        s.openCurrentSessionwithTransaction();
        s.getCurrentSession().save(c);
        s.closeCurrentSessionwithTransaction();
    }

    @Override
    public void deleteByCodiceIstat(String codice) {
        s.openCurrentSessionwithTransaction();
        Comune comune = s.getCurrentSession().get(Comune.class, codice);
        s.getCurrentSession().delete(comune);
        s.closeCurrentSessionwithTransaction();
    }

    @Override
    public Comune findByCodiceIstat(String codice) {
        s.openCurrentSession();
        Comune comune = s.getCurrentSession().get(Comune.class, codice);
        s.closeCurrentSession();
        return comune;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comune> findAll() {
        s.openCurrentSession();
        List<Comune> comune = (List<Comune>) s.getCurrentSession().createQuery("from Comune").list();
        s.closeCurrentSession();
        return comune;
    }
}

