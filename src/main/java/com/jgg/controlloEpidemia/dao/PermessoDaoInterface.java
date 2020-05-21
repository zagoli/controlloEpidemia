package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Permesso;

import java.util.List;

public interface PermessoDaoInterface {

    void save(Permesso permesso);

    void deleteById(Integer id);

    void update(Permesso permesso);

    Permesso findById(Integer id);

    List<Permesso> findAll();
}
