package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.RegioneDao;
import com.jgg.controlloEpidemia.model.Regione;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class RegioneService {

    private static RegioneDao regioneDao = new RegioneDao();

    public void save(Regione regione) {
        regioneDao.save(regione);
    }

    public Regione findById(Integer id) {
        return regioneDao.findById(id);
    }

    public void deleteById(Integer id) {
        regioneDao.deleteById(id);
    }

    public List<Regione> findAll() {
        return regioneDao.findAll();
    }
}
