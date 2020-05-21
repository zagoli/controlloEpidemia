package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.ProvinciaDao;
import com.jgg.controlloEpidemia.model.Provincia;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ProvinciaService {

    private static ProvinciaDao provinciaDao = new ProvinciaDao();

    public void save(Provincia provincia) {
        provinciaDao.save(provincia);
    }

    public Provincia findById(Integer id) {
        return provinciaDao.findById(id);
    }

    public void deleteByCodiceIstat(Integer id) {
        Provincia provincia = provinciaDao.findById(id);
        provinciaDao.deleteById(id);
    }

    public List<Provincia> findAll() {
        return provinciaDao.findAll();
    }
}