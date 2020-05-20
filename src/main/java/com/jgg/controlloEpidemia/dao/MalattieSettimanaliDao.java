package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class MalattieSettimanaliDao implements MalattieSettimanaliDaoInterface {
    private static Session session = new Session();

    @Override
    public void save(MalattieSettimanali malattieSettimanali) {
        session.openCurrentSessionwithTransaction();
        session.getCurrentSession().save(malattieSettimanali);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MalattieSettimanali> findAll() {
        session.openCurrentSession();
        List<MalattieSettimanali> malattieSettimanali = session.getCurrentSession().createQuery("from MalattieSettimanali").list();
        session.closeCurrentSession();
        return malattieSettimanali;
    }

}
