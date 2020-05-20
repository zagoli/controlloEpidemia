package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.MalattieSettimanali;

import java.util.List;

public interface MalattieSettimanaliDaoInterface {

    void save(MalattieSettimanali malattieSettimanali);

    List<MalattieSettimanali> findAll();
}
