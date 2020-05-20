package com.jgg.controlloEpidemia.dao;

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
    public void deleteById(Integer id) {
        session.openCurrentSessionwithTransaction();
        MalattieSettimanali malattieSettimanali = session.getCurrentSession().get(MalattieSettimanali.class, id);
        session.getCurrentSession().delete(malattieSettimanali);
        session.closeCurrentSessionwithTransaction();
    }

    @Override
    public MalattieSettimanali findById(Integer id) {
        session.openCurrentSession();
        MalattieSettimanali malattieSettimanali = session.getCurrentSession().get(MalattieSettimanali.class, id);
        session.closeCurrentSession();
        return malattieSettimanali;
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
