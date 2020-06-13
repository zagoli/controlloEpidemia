package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;

import java.util.List;

public interface MalattieSettimanaliDaoInterface {

    void save(MalattieSettimanali malattieSettimanali);

    void deleteById(Integer id);

    void update(MalattieSettimanali malattieSettimanali);

    void saveOrUpdate(MalattieSettimanali malattieSettimanali);

    MalattieSettimanali findById(Integer id);

    MalattieSettimanali findByAnnoSettimanaComune(Integer anno, Integer settimana, Comune comune);

    List<MalattieSettimanali> findAll();

    List<MalattieSettimanali> findByAnno(Integer anno);

    List<Integer> findInsertedYears();
}
