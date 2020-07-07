package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.RegioneDao;
import com.jgg.controlloEpidemia.model.Regione;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class RegioneService {

    private static final RegioneDao regioneDao = new RegioneDao();

    public void save(Regione regione) {
        regioneDao.openCurrentSessionWithTransaction();
        regioneDao.save(regione);
        regioneDao.closeCurrentSessionWithTransaction();
    }

    public void deleteById(Integer id) {
        regioneDao.openCurrentSessionWithTransaction();
        regioneDao.deleteById(id);
        regioneDao.closeCurrentSessionWithTransaction();
    }

    public void update(Regione regione) {
        regioneDao.openCurrentSessionWithTransaction();
        regioneDao.update(regione);
        regioneDao.closeCurrentSessionWithTransaction();
    }

    public void saveOrUpdate(Regione regione) {
        regioneDao.openCurrentSessionWithTransaction();
        regioneDao.saveOrUpdate(regione);
        regioneDao.closeCurrentSessionWithTransaction();
    }

    public void saveOrUpdate(List<Regione> regioneList) {
        regioneDao.openCurrentSessionWithTransaction();
        for (Regione regione : regioneList) {
            regioneDao.saveOrUpdate(regione);
        }
        regioneDao.closeCurrentSessionWithTransaction();
    }

    public Regione findById(Integer id) {
        regioneDao.openCurrentSession();
        Regione regioneById = regioneDao.findById(id);
        regioneDao.closeCurrentSession();
        return regioneById;
    }

    public Regione findByNome(String nome) {
        regioneDao.openCurrentSession();
        Regione regioneByNome = regioneDao.findByNome(nome);
        regioneDao.closeCurrentSession();
        return regioneByNome;
    }

    public List<Regione> findAll() {
        regioneDao.openCurrentSession();
        List<Regione> regioneAll = regioneDao.findAll();
        regioneDao.closeCurrentSession();
        return regioneAll;
    }

}
