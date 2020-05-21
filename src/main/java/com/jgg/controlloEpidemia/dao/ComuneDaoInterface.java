package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Comune;

import java.util.List;

public interface ComuneDaoInterface {

    void save(Comune comune);

    void deleteByCodiceIstat(String codiceIstat);

    void update(Comune comune);

    Comune findByCodiceIstat(String codiceIstat);

    List<Comune> findAll();
}
