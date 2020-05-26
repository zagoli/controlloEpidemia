package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Comune;

import java.util.List;

public interface ComuneDaoInterface {

    void save(Comune comune);

    void deleteByCodiceIstat(Integer codiceIstat);

    void update(Comune comune);

    void saveOrUpdate(Comune comune);

    Comune findByCodiceIstat(Integer codiceIstat);

    List<Comune> findAll();
}
