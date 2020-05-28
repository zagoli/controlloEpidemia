package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Permesso;
import lombok.NoArgsConstructor;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
public class PermessoDao implements PermessoDaoInterface {
    private static Session session = new Session();

    @Override
    public void save(Permesso permesso) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().save(permesso);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        session.openCurrentSessionWithTransaction();
        Permesso permesso = session.getCurrentSession().get(Permesso.class, id);
        session.getCurrentSession().delete(permesso);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Permesso permesso) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().update(permesso);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void saveIfNotPresent(Permesso permesso) {
        session.openCurrentSession();
        Permesso ePermesso = findByNome(permesso.getNome());
        if (ePermesso == null) {
            save(permesso);
        }
        session.closeCurrentSession();
    }

    @Override
    public Permesso findById(Integer id) {
        session.openCurrentSession();
        Permesso permesso = session.getCurrentSession().get(Permesso.class, id);
        session.closeCurrentSession();
        return permesso;
    }

    @Override
    public Permesso findByNome(String nome) {
        session.openCurrentSession();
        String hql = "FROM Permesso where nome = :nome";
        Query query = session.createQuery(hql);
        query.setParameter("nome", nome);
        Permesso permesso = null;
        try {
            permesso = (Permesso) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        session.closeCurrentSession();
        return permesso;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Permesso> findAll() {
        session.openCurrentSession();
        List<Permesso> permesso = session.getCurrentSession().createQuery("from Permesso").list();
        session.closeCurrentSession();
        return permesso;
    }
}
