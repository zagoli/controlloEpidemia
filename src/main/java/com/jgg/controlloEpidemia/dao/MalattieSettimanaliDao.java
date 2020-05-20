package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MalattieSettimanaliDao implements MalattieSettimanaliDaoInterface {
    private static Session s = new Session();

    @Override
    public void save(MalattieSettimanali m) {
        s.openCurrentSessionwithTransaction();
        s.getCurrentSession().save(m);
        s.closeCurrentSessionwithTransaction();
    }
}
