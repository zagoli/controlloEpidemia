package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Comune;

import java.util.List;
import java.util.Optional;

public interface ComuneDaoInterface {

    void save(Comune c);
    void deleteByCodiceIstat(String codice);
    Optional<Comune> findByCodiceIstat(String codice);
    List<Comune> findAll();
}
