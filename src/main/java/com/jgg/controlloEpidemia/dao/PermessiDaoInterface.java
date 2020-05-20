package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Permessi;

import java.util.List;

public interface PermessiDaoInterface {

    void save(Permessi permessi);

    void deleteById(Integer id);

    Permessi findById(Integer id);

    List<Permessi> findAll();
}
