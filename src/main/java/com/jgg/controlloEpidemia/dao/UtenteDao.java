package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Utente;
import lombok.NoArgsConstructor;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
public class UtenteDao implements UtenteDaoInterface {
    private static Session session = new Session();

    @Override
    public void save(Utente utente) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().save(utente);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        session.openCurrentSessionWithTransaction();
        Utente utente = session.getCurrentSession().get(Utente.class, id);
        session.getCurrentSession().delete(utente);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Utente utente) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().update(utente);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void saveIfNotPresent(Utente utente) {
        session.openCurrentSession();
        Utente eUtente = findByUsername(utente.getUsername());
        if (eUtente == null) {
            save(utente);
        }
        session.closeCurrentSession();
    }

    @Override
    public Utente findById(Integer id) {
        session.openCurrentSession();
        Utente utente = session.getCurrentSession().get(Utente.class, id);
        session.closeCurrentSession();
        return utente;
    }

    @Override
    public Utente findByUsername(String username) {
        session.openCurrentSession();
        String hql = "FROM Utente where username = :username";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        Utente utente = null;
        try {
            utente = (Utente) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        session.closeCurrentSession();
        return utente;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Utente> findAll() {
        session.openCurrentSession();
        List<Utente> utente = session.getCurrentSession().createQuery("from Utente").list();
        session.closeCurrentSession();
        return utente;
    }
}
