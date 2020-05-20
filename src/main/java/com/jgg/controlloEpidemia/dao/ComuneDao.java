package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Comune;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ComuneDao implements ComuneDaoInterface {

    private static Session session = new Session();

    @Override
    public void save(Comune c) {
        session.openCurrentSessionwithTransaction();
        session.getCurrentSession().save(c);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public void deleteByCodiceIstat(String codiceIstat) {
        session.openCurrentSessionwithTransaction();
        Comune comune = session.getCurrentSession().get(Comune.class, codiceIstat);
        session.getCurrentSession().delete(comune);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public Comune findByCodiceIstat(String codiceIstat) {
        session.openCurrentSession();
        Comune comune = session.getCurrentSession().get(Comune.class, codiceIstat);
        session.closeCurrentSession();
        return comune;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comune> findAll() {
        session.openCurrentSession();
        List<Comune> comune = session.getCurrentSession().createQuery("from Comune").list();
        session.closeCurrentSession();
        return comune;
    }
}

