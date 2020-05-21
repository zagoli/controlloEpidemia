package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Ruolo;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class DecessiAnnualiDao implements DecessiAnnualiDaoInterface {

    private static Session session = new Session();

    @Override
    public void save(DecessiAnnuali decessiAnnuali) {
        session.openCurrentSessionwithTransaction();
        session.getCurrentSession().save(decessiAnnuali);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        session.openCurrentSessionwithTransaction();
        DecessiAnnuali decessiAnnuali = session.getCurrentSession().get(DecessiAnnuali.class, id);
        session.getCurrentSession().delete(decessiAnnuali);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public void update(DecessiAnnuali decessiAnnuali) {
        session.openCurrentSessionwithTransaction();
        session.getCurrentSession().update(decessiAnnuali);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public DecessiAnnuali findById(Integer id) {
        session.openCurrentSession();
        DecessiAnnuali decessiAnnuali = session.getCurrentSession().get(DecessiAnnuali.class, id);
        session.closeCurrentSession();
        return decessiAnnuali;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DecessiAnnuali> findAll() {
        session.openCurrentSession();
        List<DecessiAnnuali> decessiAnnuali = session.getCurrentSession().createQuery("from DecessiAnnuali").list();
        session.closeCurrentSession();
        return decessiAnnuali;
    }
}
