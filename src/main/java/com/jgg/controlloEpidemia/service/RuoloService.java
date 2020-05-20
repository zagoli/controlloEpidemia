package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.RuoloDao;
import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Ruolo;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class RuoloService {

    private static RuoloDao ruoloDao = new RuoloDao();

    public void save(Ruolo ruolo) {
        ruoloDao.save(ruolo);
    }

    public List<Ruolo> findAll() {
        return ruoloDao.findAll();
    }
}
