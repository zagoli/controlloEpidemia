package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Comune;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class ComuneDao implements ComuneDaoInterface{

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }


    @Override
    public void save(Comune c) {
        Session s = getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.save(c);
        t.commit();
        s.close();
    }

    @Override
    public void deleteByCodiceIstat(String codice) {
        Session s = getSessionFactory().openSession();
        Comune c = s.get(Comune.class, codice);
        Transaction t = s.beginTransaction();
        s.delete(c);
        t.commit();
        s.close();
    }

    @Override
    public Optional<Comune> findByCodiceIstat(String codice) {
        Session s = getSessionFactory().openSession();
        Comune c = s.get(Comune.class, codice);
        Optional<Comune> o = Optional.ofNullable(c);
        s.close();
        return o;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comune> findAll() {
        Session s = getSessionFactory().openSession();
        List<Comune> l = s.createQuery("from Comune").list();
        s.close();
        return l;
    }

}
