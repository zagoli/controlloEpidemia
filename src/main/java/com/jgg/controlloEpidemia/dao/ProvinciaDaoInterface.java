package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Provincia;

import java.util.List;

public interface ProvinciaDaoInterface {

    void save(Provincia provincia);

    List<Provincia> findAll();
}
