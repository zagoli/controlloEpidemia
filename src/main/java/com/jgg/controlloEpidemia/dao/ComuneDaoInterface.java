package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.TipoTerritorio;

import java.util.List;

public interface ComuneDaoInterface {

    void save(Comune comune);

    void deleteByCodiceIstat(Integer codiceIstat);

    void update(Comune comune);

    void saveOrUpdate(Comune comune);

    Comune findByCodiceIstat(Integer codiceIstat);

    Comune findByNome(String nome);

    List<Comune> findAll();
}
