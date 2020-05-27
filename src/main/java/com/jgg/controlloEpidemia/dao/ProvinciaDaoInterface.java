package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.model.TipoTerritorio;

import java.util.List;

public interface ProvinciaDaoInterface {

    void save(Provincia provincia);

    void deleteById(Integer id);

    void update(Provincia provincia);

    void saveOrUpdate(Provincia provincia);

    Provincia findById(Integer id);

    Provincia findByNome(String nome);

    List<Provincia> findAll();
}
