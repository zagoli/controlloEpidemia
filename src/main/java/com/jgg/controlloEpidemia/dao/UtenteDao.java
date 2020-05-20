package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Utente;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UtenteDao implements UtenteDaoInterface {
    private static Session s = new Session();

    @Override
    public void save(Utente u) {
        s.openCurrentSessionwithTransaction();
        s.getCurrentSession().save(u);
        s.closeCurrentSessionwithTransaction();

    }
}
