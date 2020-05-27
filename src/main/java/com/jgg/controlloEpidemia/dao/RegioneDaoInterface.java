package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Regione;

import java.util.List;

public interface RegioneDaoInterface {

    void save(Regione regione);

    void deleteById(Integer id);

    void update(Regione regione);

    void saveOrUpdate(Regione regione);

    Regione findById(Integer id);

    Regione findByNome(String nome);

    List<Regione> findAll();
}
