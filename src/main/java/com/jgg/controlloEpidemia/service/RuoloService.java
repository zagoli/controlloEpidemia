package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.RuoloDao;
import com.jgg.controlloEpidemia.model.Ruolo;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class RuoloService {

    private static final RuoloDao ruoloDao = new RuoloDao();

    public void save(Ruolo ruolo) {
        ruoloDao.save(ruolo);
    }

    public void deleteById(Integer id) {
        ruoloDao.deleteById(id);
    }

    public void update(Ruolo ruolo) {
        ruoloDao.update(ruolo);
    }

    public void saveOrUpdate(Ruolo ruolo) {
        ruoloDao.saveOrUpdate(ruolo);
    }

    public Ruolo findById(Integer id) {
        return ruoloDao.findById(id);
    }

    public Ruolo findByNome(String nome) {
        return ruoloDao.findByNome(nome);
    }

    public List<Ruolo> findAll() {
        return ruoloDao.findAll();
    }

}
