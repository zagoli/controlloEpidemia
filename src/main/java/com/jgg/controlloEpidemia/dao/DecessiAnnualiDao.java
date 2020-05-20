package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DecessiAnnualiDao implements DecessiAnnualiDaoInterface {
    private static Session s = new Session();

    @Override
    public void save(DecessiAnnuali d) {
        s.openCurrentSessionwithTransaction();
        s.getCurrentSession().save(d);
        s.closeCurrentSessionwithTransaction();
    }
}
