package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
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
    @SuppressWarnings("unchecked")
    public List<DecessiAnnuali> findAll() {
        session.openCurrentSession();
        List<DecessiAnnuali> decessiAnnuali = session.getCurrentSession().createQuery("from DecessiAnnuali").list();
        session.closeCurrentSession();
        return decessiAnnuali;
    }
}
