package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.PermessoDao;
import com.jgg.controlloEpidemia.model.Permesso;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class PermessoService {

    private static final PermessoDao PERMESSO_DAO = new PermessoDao();

    public void save(Permesso permesso) {
        PERMESSO_DAO.save(permesso);
    }

    public Permesso findById(Integer id) {
        return PERMESSO_DAO.findById(id);
    }

    public void update(Permesso permesso) {
        PERMESSO_DAO.update(permesso);
    }

    public void deleteById(Integer id) {
        PERMESSO_DAO.deleteById(id);
    }

    public List<Permesso> findAll() {
        return PERMESSO_DAO.findAll();
    }
}
