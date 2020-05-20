package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.MalattieSettimanaliDao;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class MalattieSettimanaliService {

    private static MalattieSettimanaliDao malattieSettimanaliDao = new MalattieSettimanaliDao();

    public void save(MalattieSettimanali malattieSettimanali) {
        malattieSettimanaliDao.save(malattieSettimanali);
    }

    public List<MalattieSettimanali> findAll() {
        return malattieSettimanaliDao.findAll();
    }

}
