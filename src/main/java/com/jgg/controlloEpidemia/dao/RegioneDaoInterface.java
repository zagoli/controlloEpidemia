package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Regione;

import java.util.List;

public interface RegioneDaoInterface {

    void save(Regione regione);

    List<Regione> findAll();
}
