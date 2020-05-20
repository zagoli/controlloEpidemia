package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.RuoloDao;
import com.jgg.controlloEpidemia.model.Ruolo;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RuoloService {

    private static RuoloDao ruoloDao = new RuoloDao();

    public void save(Ruolo r) {
        ruoloDao.save(r);
    }
}
