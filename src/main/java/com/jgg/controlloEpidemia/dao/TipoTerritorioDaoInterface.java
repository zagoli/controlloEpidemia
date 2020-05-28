package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.TipoTerritorio;

import java.util.List;

public interface TipoTerritorioDaoInterface {

    void save(TipoTerritorio t);

    void deleteById(Integer id);

    void update(TipoTerritorio tipoTerritorio);

    void saveIfNotPresent(TipoTerritorio tipoTerritorio);

    TipoTerritorio findById(Integer id);

    TipoTerritorio findByNome(String nome);

    List<TipoTerritorio> findAll();
}
