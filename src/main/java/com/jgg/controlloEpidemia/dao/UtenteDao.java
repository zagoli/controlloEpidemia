package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Utente;
import lombok.NoArgsConstructor;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public void saveOrUpdate(Utente utente) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().saveOrUpdate(utente);
        session.closeCurrentSessionWithTransaction();
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
        CriteriaBuilder cb = session.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Utente> cq = cb.createQuery(Utente.class);
        Root<Utente> root = cq.from(Utente.class);
        cq.select(root).where(cb.equal(root.get("username"), username));
        Query<Utente> query = session.getCurrentSession().createQuery(cq);
        Utente utente;
        try {
            utente = query.getSingleResult();
        } catch (NoResultException ex) {
            utente = null;
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
