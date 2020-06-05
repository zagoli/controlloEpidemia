package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Comune;
import lombok.NoArgsConstructor;
import org.hibernate.query.Query;

import java.util.List;

@NoArgsConstructor
public class ComuneDao implements ComuneDaoInterface {

    private static final Session session = new Session();

    final private String FROM_COMUNE_WHERE_NOME = "FROM Comune where nome = :nome";

    @Override
    public void save(Comune c) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().save(c);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteByCodiceIstat(Integer codiceIstat) {
        session.openCurrentSessionWithTransaction();
        Comune comune = session.getCurrentSession().get(Comune.class, codiceIstat);
        session.getCurrentSession().delete(comune);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Comune comune) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().update(comune);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void saveOrUpdate(Comune comune) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().saveOrUpdate(comune);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public Comune findByCodiceIstat(Integer codiceIstat) {
        session.openCurrentSession();
        Comune comune = session.getCurrentSession().get(Comune.class, codiceIstat);
        session.closeCurrentSession();
        return comune;
    }

    @Override
    public Comune findByNome(String nome) {
        session.openCurrentSession();
        Query query = session.createQuery(FROM_COMUNE_WHERE_NOME);
        query.setParameter("nome", nome);
        Comune comune = (Comune) query.getSingleResult();
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

