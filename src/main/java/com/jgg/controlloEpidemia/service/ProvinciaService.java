package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.ProvinciaDao;
import com.jgg.controlloEpidemia.model.Provincia;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ProvinciaService {

    private static final ProvinciaDao provinciaDao = new ProvinciaDao();

    public void save(Provincia provincia) {
        provinciaDao.save(provincia);
    }

    public void deleteById(Integer id) {
        provinciaDao.deleteById(id);
    }

    public void update(Provincia provincia) {
        provinciaDao.update(provincia);
    }

    public void saveOrUpdate(Provincia provincia) {
        provinciaDao.saveOrUpdate(provincia);
    }

    public Provincia findById(Integer id) {
        return provinciaDao.findById(id);
    }


    public List<Provincia> findAll() {
        return provinciaDao.findAll();
    }
}
