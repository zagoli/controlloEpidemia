package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Permessi;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PermessiDao implements PermessiDaoInterface {
    private static Session s = new Session();

    @Override
    public void save(Permessi p) {
        s.openCurrentSessionwithTransaction();
        s.getCurrentSession().save(p);
        s.closeCurrentSessionwithTransaction();

    }
}
