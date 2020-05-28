package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Permesso;

import java.util.List;

public interface PermessoDaoInterface {

    void save(Permesso permesso);

    void deleteById(Integer id);

    void update(Permesso permesso);

    void saveIfNotPresent(Permesso permesso);

    Permesso findById(Integer id);

    Permesso findByNome(String nome);

    List<Permesso> findAll();
}
