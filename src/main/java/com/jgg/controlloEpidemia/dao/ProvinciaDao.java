package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Provincia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
public class ProvinciaDao implements ProvinciaDaoInterface {

    final private String FROM_PROVINCIA_WHERE_NOME = "FROM Provincia where nome = :nome";
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
    public void save(Provincia provincia) {
        currentSession.save(provincia);
    }

    @Override
    public void deleteById(Integer id) {
        currentSession.delete(currentSession.get(Provincia.class, id));
    }

    @Override
    public void update(Provincia provincia) {
        currentSession.update(provincia);
    }

    @Override
    public void saveOrUpdate(Provincia provincia) {
        Provincia eProvincia = findByNome(provincia.getNome());
        if (eProvincia == null) {
            save(provincia);
        } else {
            eProvincia.setNome(provincia.getNome());
            eProvincia.setSuperficie(provincia.getSuperficie());
            eProvincia.setCapoluogo(provincia.getCapoluogo());
            eProvincia.setRegione(provincia.getRegione());
            update(eProvincia);
        }
    }

    @Override
    public Provincia findById(Integer id) {
        return currentSession.get(Provincia.class, id);
    }

    @Override
    public Provincia findByNome(String nome) {
        Query query = createQuery(FROM_PROVINCIA_WHERE_NOME);
        query.setParameter("nome", nome);
        Provincia provincia = null;
        try {
            provincia = (Provincia) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        return provincia;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Provincia> findAll() {
        return (List<Provincia>) currentSession.createQuery("from Provincia").list();
    }

}
