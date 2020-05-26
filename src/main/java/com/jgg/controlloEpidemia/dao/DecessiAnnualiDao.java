package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class DecessiAnnualiDao implements DecessiAnnualiDaoInterface {

    private static Session session = new Session();

    @Override
    public void save(DecessiAnnuali decessiAnnuali) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().save(decessiAnnuali);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        session.openCurrentSessionWithTransaction();
        DecessiAnnuali decessiAnnuali = session.getCurrentSession().get(DecessiAnnuali.class, id);
        session.getCurrentSession().delete(decessiAnnuali);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(DecessiAnnuali decessiAnnuali) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().update(decessiAnnuali);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void saveOrUpdate(DecessiAnnuali decessiAnnuali) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().saveOrUpdate(decessiAnnuali);
        session.closeCurrentSessionWithTransaction();
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
