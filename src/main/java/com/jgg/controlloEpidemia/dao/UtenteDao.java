package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Utente;
import lombok.NoArgsConstructor;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
public class UtenteDao implements UtenteDaoInterface {

    private final Session session = new Session();

    final private String FIND_ALL_PERSONALE_CONTRATTO = "FROM Utente where ruolo.id = 2"; // Personale a contratto ha id ruolo = 2
    final private String FROM_UTENTE_WHERE_USERNAME = "FROM Utente where username = :username";

    @Override
    public void save(Utente utente) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().save(utente);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        session.openCurrentSessionWithTransaction();
        Utente utente = session.getCurrentSession().get(Utente.class, id);
        session.getCurrentSession().delete(utente);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Utente utente) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().update(utente);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void saveOrUpdate(Utente utente) {
        session.openCurrentSession();
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
        session.closeCurrentSession();
    }

    @Override
    public Utente findById(Integer id) {
        session.openCurrentSession();
        Utente utente = session.getCurrentSession().get(Utente.class, id);
        session.closeCurrentSession();
        return utente;
    }

    @Override
    public Utente findByUsername(String username) {
        session.openCurrentSession();
        Query query = session.createQuery(FROM_UTENTE_WHERE_USERNAME);
        query.setParameter("username", username);
        Utente utente = null;
        try {
            utente = (Utente) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        session.closeCurrentSession();
        return utente;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Utente> findAll() {
        session.openCurrentSession();
        List<Utente> utente = session.getCurrentSession().createQuery("from Utente").list();
        session.closeCurrentSession();
        return utente;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Utente> findAllPersonaleContratto() {
        session.openCurrentSession();
        List<Utente> utenti = session.getCurrentSession().createQuery(FIND_ALL_PERSONALE_CONTRATTO).list();
        session.closeCurrentSession();
        return utenti;
    }

}
