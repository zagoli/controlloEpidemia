package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Ruolo;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class RuoloDao implements RuoloDaoInterface {

    private static Session session = new Session();

    @Override
    public void save(Ruolo ruolo) {
        session.openCurrentSessionwithTransaction();
        session.getCurrentSession().save(ruolo);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        session.openCurrentSessionwithTransaction();
        Ruolo ruolo = session.getCurrentSession().get(Ruolo.class, id);
        session.getCurrentSession().delete(ruolo);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public Ruolo findById(Integer id) {
        session.openCurrentSession();
        Ruolo ruolo = session.getCurrentSession().get(Ruolo.class, id);
        session.closeCurrentSession();
        return ruolo;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Ruolo> findAll() {
        session.openCurrentSession();
        List<Ruolo> ruolo = session.getCurrentSession().createQuery("from Ruolo").list();
        session.closeCurrentSession();
        return ruolo;
    }
}
