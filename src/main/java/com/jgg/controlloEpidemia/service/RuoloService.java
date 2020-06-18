package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.RuoloDao;
import com.jgg.controlloEpidemia.model.Ruolo;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class RuoloService {

    private static final RuoloDao ruoloDao = new RuoloDao();

    public void save(Ruolo ruolo) {
        ruoloDao.openCurrentSessionWithTransaction();
        ruoloDao.save(ruolo);
        ruoloDao.closeCurrentSessionWithTransaction();
    }

    public void deleteById(Integer id) {
        ruoloDao.openCurrentSessionWithTransaction();
        ruoloDao.deleteById(id);
        ruoloDao.closeCurrentSessionWithTransaction();
    }

    public void update(Ruolo ruolo) {
        ruoloDao.openCurrentSessionWithTransaction();
        ruoloDao.update(ruolo);
        ruoloDao.closeCurrentSessionWithTransaction();
    }

    public void saveOrUpdate(Ruolo ruolo) {
        ruoloDao.openCurrentSession();
        ruoloDao.saveOrUpdate(ruolo);
        ruoloDao.closeCurrentSession();
    }

    public void saveOrUpdate(List<Ruolo> ruoloList) {
        ruoloDao.openCurrentSessionWithTransaction();
        for (Ruolo ruolo : ruoloList) {
            ruoloDao.saveOrUpdate(ruolo);
        }
        ruoloDao.closeCurrentSessionWithTransaction();
    }

    public Ruolo findById(Integer id) {
        ruoloDao.openCurrentSession();
        Ruolo ruoloById = ruoloDao.findById(id);
        ruoloDao.closeCurrentSession();
        return ruoloById;
    }

    public Ruolo findByNome(String nome) {
        ruoloDao.openCurrentSession();
        Ruolo ruoloByNome = ruoloDao.findByNome(nome);
        ruoloDao.closeCurrentSession();
        return ruoloByNome;
    }

    public List<Ruolo> findAll() {
        ruoloDao.openCurrentSession();
        List<Ruolo> ruoloAll = ruoloDao.findAll();
        ruoloDao.closeCurrentSession();
        return ruoloAll;
    }

}
