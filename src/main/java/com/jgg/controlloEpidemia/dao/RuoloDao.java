package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Ruolo;
import lombok.NoArgsConstructor;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
public class RuoloDao implements RuoloDaoInterface {

    private static Session session = new Session();

    final private String FROM_RUOLO_WHERE_NOME = "FROM Ruolo where nome = :nome";

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
        session.openCurrentSession();
        Ruolo eRuolo = findByNome(ruolo.getNome());
        if (eRuolo == null) {
            save(ruolo);
        } else {
            eRuolo.setNome(ruolo.getNome());
            update(eRuolo);
        }
        session.closeCurrentSession();
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
        Query query = session.createQuery(FROM_RUOLO_WHERE_NOME);
        query.setParameter("nome", nome);
        Ruolo ruolo = null;
        try {
            ruolo = (Ruolo) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
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
