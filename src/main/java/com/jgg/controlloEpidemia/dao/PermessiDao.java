package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Permessi;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class PermessiDao implements PermessiDaoInterface {
    private static Session session = new Session();

    @Override
    public void save(Permessi permessi) {
        session.openCurrentSessionwithTransaction();
        session.getCurrentSession().save(permessi);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Permessi> findAll() {
        session.openCurrentSession();
        List<Permessi> permessi = session.getCurrentSession().createQuery("from Permessi").list();
        session.closeCurrentSession();
        return permessi;
    }
}
