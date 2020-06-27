package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.TipoTerritorio;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class TipoTerritorioDao implements TipoTerritorioDaoInterface {

    final private String FROM_TIPOTERRITORIO_WHERE_NOME = "FROM TipoTerritorio where nome = :nome";

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
    public void save(TipoTerritorio tipoTerritorio) {
        currentSession.save(tipoTerritorio);
    }

    @Override
    public void deleteById(Integer id) {
        currentSession.delete(currentSession.get(TipoTerritorio.class, id));
    }

    @Override
    public void update(TipoTerritorio tipoTerritorio) {
        currentSession.update(tipoTerritorio);
    }

    @Override
    public void saveOrUpdate(TipoTerritorio tipoTerritorio) {
        TipoTerritorio eTipoTerritorio = findByNome(tipoTerritorio.getNome());
        if (eTipoTerritorio == null) {
            save(tipoTerritorio);
        } else {
            eTipoTerritorio.setNome(tipoTerritorio.getNome());
            update(eTipoTerritorio);
        }
    }

    @Override
    public TipoTerritorio findById(Integer id) {
        return currentSession.get(TipoTerritorio.class, id);
    }

    @Override
    public TipoTerritorio findByNome(String nome) {
        Query query = createQuery(FROM_TIPOTERRITORIO_WHERE_NOME);
        query.setParameter("nome", nome);
        TipoTerritorio tipoTerritorio = null;
        try {
            tipoTerritorio = (TipoTerritorio) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        return tipoTerritorio;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TipoTerritorio> findAll() {
        return (List<TipoTerritorio>) currentSession.createQuery("from TipoTerritorio").list();
    }

}
