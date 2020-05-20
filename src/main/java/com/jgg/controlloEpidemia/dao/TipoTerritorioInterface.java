package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.TipoTerritorio;

import java.util.List;

public interface TipoTerritorioInterface {

    void save(TipoTerritorio t);

    List<TipoTerritorio> findAll();
}
