package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.MalattieSettimanali;

import java.util.List;

public interface MalattieSettimanaliDaoInterface {

    void save(MalattieSettimanali malattieSettimanali);

    void deleteById(Integer id);

    void update(MalattieSettimanali malattieSettimanali);

    MalattieSettimanali findById(Integer id);

    List<MalattieSettimanali> findAll();
}
