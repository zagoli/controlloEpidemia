package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.ProvinciaDao;
import com.jgg.controlloEpidemia.model.Provincia;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ProvinciaService {

    private static final ProvinciaDao provinciaDao = new ProvinciaDao();

    public void save(Provincia provincia) {
        provinciaDao.openCurrentSessionWithTransaction();
        provinciaDao.save(provincia);
        provinciaDao.closeCurrentSessionWithTransaction();
    }

    public void deleteById(Integer id) {
        provinciaDao.openCurrentSessionWithTransaction();
        provinciaDao.deleteById(id);
        provinciaDao.closeCurrentSessionWithTransaction();
    }

    public void update(Provincia provincia) {
        provinciaDao.openCurrentSessionWithTransaction();
        provinciaDao.update(provincia);
        provinciaDao.closeCurrentSessionWithTransaction();
    }

    public void saveOrUpdate(Provincia provincia) {
        provinciaDao.openCurrentSession();
        provinciaDao.saveOrUpdate(provincia);
        provinciaDao.closeCurrentSession();
    }

    public void saveOrUpdate(List<Provincia> provinciaList) {
        provinciaDao.openCurrentSessionWithTransaction();
        for (Provincia provincia : provinciaList) {
            provinciaDao.saveOrUpdate(provincia);
        }
        provinciaDao.closeCurrentSessionWithTransaction();
    }

    public Provincia findById(Integer id) {
        provinciaDao.openCurrentSession();
        Provincia provinciaById = provinciaDao.findById(id);
        provinciaDao.closeCurrentSession();
        return provinciaById;
    }

    public Provincia findByNome(String nome) {
        provinciaDao.openCurrentSession();
        Provincia provinciaByNome = provinciaDao.findByNome(nome);
        provinciaDao.closeCurrentSession();
        return provinciaByNome;
    }

    public List<Provincia> findAll() {
        provinciaDao.openCurrentSession();
        List<Provincia> provinciaAll = provinciaDao.findAll();
        provinciaDao.closeCurrentSession();
        return provinciaAll;
    }

}
