package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ComuneDao implements ComuneDaoInterface {
    private Session currentSession;

    private Transaction currentTransaction;

    public ComuneDao() {
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Comune.class);
        configuration.addAnnotatedClass(TipoTerritorio.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
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

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }


    @Override
    public void save(Comune c) {
        getCurrentSession().save(c);
    }

    @Override
    public void deleteByCodiceIstat(String codice) {
        Comune comune = (Comune) getCurrentSession().get(Comune.class, codice);
        getCurrentSession().delete(comune);
    }

    @Override
    public Comune findByCodiceIstat(String codice) {
        return getCurrentSession().get(Comune.class, codice);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comune> findAll() {
        return (List<Comune>) getCurrentSession().createQuery("from Comune").list();
    }
}

