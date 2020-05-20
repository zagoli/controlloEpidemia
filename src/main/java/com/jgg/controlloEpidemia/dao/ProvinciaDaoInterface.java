package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Provincia;

import java.util.List;

public interface ProvinciaDaoInterface {

    void save(Provincia provincia);

    void deleteById(Integer id);

    Provincia findById(Integer id);

    List<Provincia> findAll();
}
