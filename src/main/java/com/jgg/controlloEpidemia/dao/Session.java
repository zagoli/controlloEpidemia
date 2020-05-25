package com.jgg.controlloEpidemia.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@NoArgsConstructor
public class Session {

    @Getter
    @Setter
    private org.hibernate.Session currentSession;

    @Getter
    @Setter
    private Transaction currentTransaction;

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
      /*  configuration.addAnnotatedClass(Ruolo.class);
        configuration.
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());*/
        //versione 2 sotto uguale a main
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

}
