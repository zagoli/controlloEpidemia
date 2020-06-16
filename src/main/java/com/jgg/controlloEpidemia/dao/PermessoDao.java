package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Permesso;
import lombok.NoArgsConstructor;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
public class PermessoDao implements PermessoDaoInterface {

    private static final Session session = new Session();

    final private String FROM_PERMESSO_WHERE_NOME = "FROM Permesso where nome = :nome";

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
    public void saveOrUpdate(Permesso permesso) {
        session.openCurrentSession();
        Permesso ePermesso = findByNome(permesso.getNome());
        if (ePermesso == null) {
            save(permesso);
        } else {
            ePermesso.setNome(permesso.getNome());
            ePermesso.setDescrizione(permesso.getDescrizione());
            update(ePermesso);
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
        Query query = session.createQuery(FROM_PERMESSO_WHERE_NOME);
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
