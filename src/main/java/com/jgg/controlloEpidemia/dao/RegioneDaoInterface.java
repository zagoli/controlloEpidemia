package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Regione;

import java.util.List;

public interface RegioneDaoInterface {

    void save(Regione regione);

    void deleteById(Integer id);

    void update(Regione regione);

    Regione findById(Integer id);

    List<Regione> findAll();
}
