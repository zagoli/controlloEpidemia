package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Regione;

import java.util.List;

public interface RegioneDaoInterface {

    void save(Regione regione);

    void deleteById(Integer id);

    Regione findById(Integer id);

    List<Regione> findAll();
}
