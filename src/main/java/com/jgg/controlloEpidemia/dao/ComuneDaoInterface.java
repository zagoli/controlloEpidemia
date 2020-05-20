package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Comune;

import java.util.List;

public interface ComuneDaoInterface {

    void save(Comune comune);

    void deleteByCodiceIstat(String codiceIstat);

    Comune findByCodiceIstat(String codiceIstat);

    List<Comune> findAll();
}
