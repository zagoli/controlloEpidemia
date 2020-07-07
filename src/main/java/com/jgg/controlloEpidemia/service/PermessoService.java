package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.PermessoDao;
import com.jgg.controlloEpidemia.model.Permesso;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class PermessoService {

    private static final PermessoDao permessoDao = new PermessoDao();

    public void save(Permesso permesso) {
        permessoDao.openCurrentSessionWithTransaction();
        permessoDao.save(permesso);
        permessoDao.closeCurrentSessionWithTransaction();
    }

    public void deleteById(Integer id) {
        permessoDao.openCurrentSessionWithTransaction();
        permessoDao.deleteById(id);
        permessoDao.closeCurrentSessionWithTransaction();
    }

    public void update(Permesso permesso) {
        permessoDao.openCurrentSessionWithTransaction();
        permessoDao.update(permesso);
        permessoDao.closeCurrentSessionWithTransaction();
    }

    public void saveOrUpdate(Permesso permesso) {
        permessoDao.openCurrentSessionWithTransaction();
        permessoDao.saveOrUpdate(permesso);
        permessoDao.closeCurrentSessionWithTransaction();
    }

    public void saveOrUpdate(List<Permesso> permessoList) {
        permessoDao.openCurrentSessionWithTransaction();
        for (Permesso permesso : permessoList) {
            permessoDao.saveOrUpdate(permesso);
        }
        permessoDao.closeCurrentSessionWithTransaction();
    }

    public Permesso findById(Integer id) {
        permessoDao.openCurrentSession();
        Permesso permessoById = permessoDao.findById(id);
        permessoDao.closeCurrentSession();
        return permessoById;
    }

    public Permesso findByNome(String nome) {
        permessoDao.openCurrentSession();
        Permesso permessoByNome = permessoDao.findByNome(nome);
        permessoDao.closeCurrentSession();
        return permessoByNome;
    }

    public List<Permesso> findAll() {
        permessoDao.openCurrentSession();
        List<Permesso> permessoAll = permessoDao.findAll();
        permessoDao.closeCurrentSession();
        return permessoAll;
    }

}
