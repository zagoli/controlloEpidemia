package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Ruolo;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import lombok.NoArgsConstructor;
import org.hibernate.query.Query;

import java.util.List;

@NoArgsConstructor
public class RuoloDao implements RuoloDaoInterface {

    private static Session session = new Session();

    @Override
    public void save(Ruolo ruolo) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().save(ruolo);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        session.openCurrentSessionWithTransaction();
        Ruolo ruolo = session.getCurrentSession().get(Ruolo.class, id);
        session.getCurrentSession().delete(ruolo);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Ruolo ruolo) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().update(ruolo);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void saveOrUpdate(Ruolo ruolo) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().saveOrUpdate(ruolo);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public Ruolo findById(Integer id) {
        session.openCurrentSession();
        Ruolo ruolo = session.getCurrentSession().get(Ruolo.class, id);
        session.closeCurrentSession();
        return ruolo;
    }

    @Override
    public Ruolo findByNome(String nome) {
        session.openCurrentSession();
        String hql = "FROM Ruolo where nome = :nome";
        Query query = session.createQuery(hql);
        query.setParameter("nome", nome);
        Ruolo ruolo = (Ruolo) query.getSingleResult();
        session.closeCurrentSession();
        return ruolo;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Ruolo> findAll() {
        session.openCurrentSession();
        List<Ruolo> ruolo = session.getCurrentSession().createQuery("from Ruolo").list();
        session.closeCurrentSession();
        return ruolo;
    }
}
