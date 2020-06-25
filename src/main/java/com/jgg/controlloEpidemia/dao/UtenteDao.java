package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Utente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
public class UtenteDao implements UtenteDaoInterface {

    final private String FIND_ALL_PERSONALE_CONTRATTO = "FROM Utente where ruolo.id = 2"; // Personale a contratto ha id ruolo = 2
    final private String FROM_UTENTE_WHERE_USERNAME = "FROM Utente where username = :username";

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
    public void save(Utente utente) {
        currentSession.save(utente);
    }

    @Override
    public void deleteById(Integer id) {
        currentSession.delete(currentSession.get(Utente.class, id));
    }

    @Override
    public void update(Utente utente) {
        currentSession.update(utente);
    }

    @Override
    public void saveOrUpdate(Utente utente) {
        Utente eUtente = findByUsername(utente.getUsername());
        if (eUtente == null) {
            save(utente);
        } else {
            eUtente.setUsername(utente.getUsername());
            eUtente.setPassword(utente.getPassword());
            eUtente.setNome(utente.getNome());
            eUtente.setCognome(utente.getCognome());
            eUtente.setRuolo(utente.getRuolo());
            update(eUtente);
        }
    }

    @Override
    public Utente findById(Integer id) {
        return currentSession.get(Utente.class, id);
    }

    @Override
    public Utente findByUsername(String username) {
        Query query = createQuery(FROM_UTENTE_WHERE_USERNAME);
        query.setParameter("username", username);
        Utente utente = null;
        try {
            utente = (Utente) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        return utente;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Utente> findAll() {
        return (List<Utente>) currentSession.createQuery("from Utente").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Utente> findAllPersonaleContratto() {
        return (List<Utente>) currentSession.createQuery(FIND_ALL_PERSONALE_CONTRATTO).list();
    }

}
