package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import lombok.NoArgsConstructor;
import org.hibernate.query.Query;

import java.util.List;

@NoArgsConstructor
public class RegioneDao implements RegioneDaoInterface {
    private static Session session = new Session();

    @Override
    public void save(Regione regione) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().save(regione);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        session.openCurrentSessionWithTransaction();
        Regione regione = session.getCurrentSession().get(Regione.class, id);
        session.getCurrentSession().delete(regione);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Regione regione) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().update(regione);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void saveOrUpdate(Regione regione) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().saveOrUpdate(regione);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public Regione findById(Integer id) {
        session.openCurrentSession();
        Regione regione = session.getCurrentSession().get(Regione.class, id);
        session.closeCurrentSession();
        return regione;
    }

    @Override
    public Regione findByNome(String nome) {
        session.openCurrentSession();
        String hql = "FROM Regione where nome = :nome";
        Query query = session.createQuery(hql);
        query.setParameter("nome", nome);
        Regione regione = (Regione) query.getSingleResult();
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
