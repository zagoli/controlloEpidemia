package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Utente;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class UtenteDao implements UtenteDaoInterface {
    private static Session session = new Session();

    @Override
    public void save(Utente utente) {
        session.openCurrentSessionwithTransaction();
        session.getCurrentSession().save(utente);
        session.closeCurrentSessionwithTransaction();

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Utente> findAll() {
        session.openCurrentSession();
        List<Utente> utente = session.getCurrentSession().createQuery("from Utente").list();
        session.closeCurrentSession();
        return utente;
    }
}
