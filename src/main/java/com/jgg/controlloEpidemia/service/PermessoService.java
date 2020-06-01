package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.PermessoDao;
import com.jgg.controlloEpidemia.model.Permesso;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class PermessoService {

    private static final PermessoDao permessoDao = new PermessoDao();

    public void save(Permesso permesso) {
        permessoDao.save(permesso);
    }

    public void deleteById(Integer id) {
        permessoDao.deleteById(id);
    }

    public void update(Permesso permesso) {
        permessoDao.update(permesso);
    }

    public void saveIfNotPresent(Permesso permesso) {
        permessoDao.saveIfNotPresent(permesso);
    }

    public Permesso findById(Integer id) {
        return permessoDao.findById(id);
    }

    public List<Permesso> findAll() {
        return permessoDao.findAll();
    }
}