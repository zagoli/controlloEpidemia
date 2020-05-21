package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.TipoTerritorio;

import java.util.List;

public interface TipoTerritorioInterface {

    void save(TipoTerritorio t);

    void deleteById(Integer id);

    void update(TipoTerritorio tipoTerritorio);

    TipoTerritorio findById(Integer id);

    List<TipoTerritorio> findAll();
}
