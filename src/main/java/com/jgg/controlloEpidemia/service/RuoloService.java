package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.RuoloDao;
import com.jgg.controlloEpidemia.model.Ruolo;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class RuoloService {

    private static RuoloDao ruoloDao = new RuoloDao();

    public void save(Ruolo ruolo) {
        ruoloDao.save(ruolo);
    }

    public Ruolo findById(Integer id) {
        return ruoloDao.findById(id);
    }

    public void deleteByCodiceIstat(Integer id) {
       ruoloDao.deleteById(id);
    }

    public List<Ruolo> findAll() {
        return ruoloDao.findAll();
    }
}
