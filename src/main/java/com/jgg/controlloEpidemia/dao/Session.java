package com.jgg.controlloEpidemia.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

@NoArgsConstructor
public class Session {

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

    protected void openCurrentSession() {
        currentSession = getSessionFactory().openSession();
    }

    protected void openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
    }

    protected void closeCurrentSession() {
        currentSession.close();
    }

    protected void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    protected org.hibernate.query.Query createQuery(String hql) {
        return currentSession.createQuery(hql);
    }

}
