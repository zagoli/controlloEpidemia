package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.RegioneDao;
import com.jgg.controlloEpidemia.model.Regione;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class RegioneService {

    private static final RegioneDao regioneDao = new RegioneDao();

    public void save(Regione regione) {
        regioneDao.save(regione);
    }

    public void deleteById(Integer id) {
        regioneDao.deleteById(id);
    }

    public void update(Regione regione) {
        regioneDao.update(regione);
    }

    public void saveOrUpdate(Regione regione) {
        regioneDao.saveOrUpdate(regione);
    }

    public Regione findById(Integer id) {
        return regioneDao.findById(id);
    }

    public Regione findByNome(String nome) {
        return regioneDao.findByNome(nome);
    }

    public List<Regione> findAll() {
        return regioneDao.findAll();
    }

}
