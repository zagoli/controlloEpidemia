package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Permesso;
import com.jgg.controlloEpidemia.model.Ruolo;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class PermessoDao implements PermessoDaoInterface {
    private static Session session = new Session();

    @Override
    public void save(Permesso permesso) {
        session.openCurrentSessionwithTransaction();
        session.getCurrentSession().save(permesso);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        session.openCurrentSessionwithTransaction();
        Permesso permesso = session.getCurrentSession().get(Permesso.class, id);
        session.getCurrentSession().delete(permesso);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public void update(Permesso permesso) {
        session.openCurrentSessionwithTransaction();
        session.getCurrentSession().update(permesso);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public Permesso findById(Integer id) {
        session.openCurrentSession();
        Permesso permesso = session.getCurrentSession().get(Permesso.class, id);
        session.closeCurrentSession();
        return permesso;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Permesso> findAll() {
        session.openCurrentSession();
        List<Permesso> permesso = session.getCurrentSession().createQuery("from Permesso").list();
        session.closeCurrentSession();
        return permesso;
    }
}
