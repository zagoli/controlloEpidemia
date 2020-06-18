package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Regione;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
public class RegioneDao implements RegioneDaoInterface {

    final private String FROM_REGIONE_WHERE_NOME = "FROM Regione where nome = :nome";
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
    public void save(Regione regione) {
        currentSession.save(regione);
    }

    @Override
    public void deleteById(Integer id) {
        currentSession.delete(currentSession.get(Regione.class, id));
    }

    @Override
    public void update(Regione regione) {
        currentSession.update(regione);
    }

    @Override
    public void saveOrUpdate(Regione regione) {
        Regione eRegione = findByNome(regione.getNome());
        if (eRegione == null) {
            save(regione);
        } else {
            eRegione.setNome(regione.getNome());
            eRegione.setSuperficie(regione.getSuperficie());
            eRegione.setCapoluogo(regione.getCapoluogo());
            update(eRegione);
        }
    }

    @Override
    public Regione findById(Integer id) {
        return currentSession.get(Regione.class, id);
    }

    @Override
    public Regione findByNome(String nome) {
        Query query = createQuery(FROM_REGIONE_WHERE_NOME);
        query.setParameter("nome", nome);
        Regione regione = null;
        try {
            regione = (Regione) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        return regione;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Regione> findAll() {
        return (List<Regione>) currentSession.createQuery("from Regione").list();
    }

}
