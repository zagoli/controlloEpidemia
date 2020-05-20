package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.PermessiDao;
import com.jgg.controlloEpidemia.model.Permessi;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class PermessiService {

    private static PermessiDao permessiDao = new PermessiDao();

    public void save(Permessi permessi) {
        permessiDao.save(permessi);
    }

    public Permessi findById(Integer id) {
        return permessiDao.findById(id);
    }

    public void deleteByCodiceIstat(Integer id) {
        Permessi permessi = permessiDao.findById(id);
        permessiDao.deleteById(id);
    }

    public List<Permessi> findAll() {
        return permessiDao.findAll();
    }
}
