package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Ruolo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
public class RuoloDao implements RuoloDaoInterface {

    final private String FROM_RUOLO_WHERE_NOME = "FROM Ruolo where nome = :nome";
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
    public void save(Ruolo ruolo) {
        currentSession.save(ruolo);
    }

    @Override
    public void deleteById(Integer id) {
        currentSession.delete(currentSession.get(Ruolo.class, id));
    }

    @Override
    public void update(Ruolo ruolo) {
        currentSession.update(ruolo);
    }

    @Override
    public void saveOrUpdate(Ruolo ruolo) {
        Ruolo eRuolo = findByNome(ruolo.getNome());
        if (eRuolo == null) {
            save(ruolo);
        } else {
            eRuolo.setNome(ruolo.getNome());
            update(eRuolo);
        }
    }

    @Override
    public Ruolo findById(Integer id) {
        return currentSession.get(Ruolo.class, id);
    }

    @Override
    public Ruolo findByNome(String nome) {
        Query query = createQuery(FROM_RUOLO_WHERE_NOME);
        query.setParameter("nome", nome);
        Ruolo ruolo = null;
        try {
            ruolo = (Ruolo) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        return ruolo;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Ruolo> findAll() {
        return (List<Ruolo>) currentSession.createQuery("from Ruolo").list();
    }

}
