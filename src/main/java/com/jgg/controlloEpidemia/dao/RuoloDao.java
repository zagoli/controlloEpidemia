package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Ruolo;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RuoloDao implements RuoloDaoInterface {

    private static Session s = new Session();

    @Override
    public void save(Ruolo r) {
        s.openCurrentSessionwithTransaction();
        s.getCurrentSession().save(r);
        s.closeCurrentSessionwithTransaction();
    }
}
