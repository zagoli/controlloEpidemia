package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.model.Utente;
import lombok.NoArgsConstructor;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
public class RegioneDao implements RegioneDaoInterface {

    private static Session session = new Session();

    final private String FROM_REGIONE_WHERE_NOME = "FROM Regione where nome = :nome";

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
        session.openCurrentSession();
        Regione eRegione = findByNome(regione.getNome());
        if (eRegione == null) {
            save(regione);
        } else {
            eRegione.setNome(regione.getNome());
            eRegione.setSuperficie(regione.getSuperficie());
            eRegione.setCapoluogo(regione.getCapoluogo());
            update(eRegione);
        }
        session.closeCurrentSession();
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
        Query query = session.createQuery(FROM_REGIONE_WHERE_NOME);
        query.setParameter("nome", nome);
        Regione regione = null;
        try {
            regione = (Regione) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
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
