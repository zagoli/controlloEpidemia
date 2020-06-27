package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Permesso;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
public class PermessoDao implements PermessoDaoInterface {

    final private String FROM_PERMESSO_WHERE_NOME = "FROM Permesso where nome = :nome";

    @Getter
    @Setter
    private org.hibernate.Session currentSession;

    @Getter
    @Setter
    private org.hibernate.Transaction currentTransaction;

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        return configuration.buildSessionFactory();
    }

    public void openCurrentSession() {
        currentSession = getSessionFactory().openSession();
    }

    public void openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public org.hibernate.query.Query createQuery(String hql) {
        return currentSession.createQuery(hql);
    }

    @Override
    public void save(Permesso permesso) {
        currentSession.save(permesso);
    }

    @Override
    public void deleteById(Integer id) {
        currentSession.delete(currentSession.get(Permesso.class, id));
    }

    @Override
    public void update(Permesso permesso) {
        currentSession.update(permesso);
    }

    @Override
    public void saveOrUpdate(Permesso permesso) {
        Permesso ePermesso = findByNome(permesso.getNome());
        if (ePermesso == null) {
            save(permesso);
        } else {
            ePermesso.setNome(permesso.getNome());
            ePermesso.setDescrizione(permesso.getDescrizione());
            update(ePermesso);
        }
    }

    @Override
    public Permesso findById(Integer id) {
        return currentSession.get(Permesso.class, id);
    }

    @Override
    public Permesso findByNome(String nome) {
        Query query = createQuery(FROM_PERMESSO_WHERE_NOME);
        query.setParameter("nome", nome);
        Permesso permesso = null;
        try {
            permesso = (Permesso) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        return permesso;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Permesso> findAll() {
        return (List<Permesso>) currentSession.createQuery("from Permesso").list();
    }

}
