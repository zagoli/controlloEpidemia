package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Comune;

import java.util.List;

public interface ComuneDaoInterface {

    void save(Comune c);

    void deleteByCodiceIstat(String codice);

    Comune findByCodiceIstat(String codice);

    List<Comune> findAll();
}
