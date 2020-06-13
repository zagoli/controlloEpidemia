package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.MalattieSettimanaliDao;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class MalattieSettimanaliService {

    private static final MalattieSettimanaliDao malattieSettimanaliDao = new MalattieSettimanaliDao();

    public void save(MalattieSettimanali malattieSettimanali) {
        malattieSettimanaliDao.save(malattieSettimanali);
    }

    public void deleteById(Integer id) {
        malattieSettimanaliDao.deleteById(id);
    }

    public void update(MalattieSettimanali malattieSettimanali) {
        malattieSettimanaliDao.update(malattieSettimanali);
    }

    public void saveOrUpdate(MalattieSettimanali malattieSettimanali) {
        malattieSettimanaliDao.saveOrUpdate(malattieSettimanali);
    }

    public MalattieSettimanali findById(Integer id) {
        return malattieSettimanaliDao.findById(id);
    }

    public List<MalattieSettimanali> findByAnno(Integer anno) {
        return malattieSettimanaliDao.findByAnno(anno);
    }

    public List<Integer> findInsertedYears() {
        return malattieSettimanaliDao.findInsertedYears();
    }

    public List<MalattieSettimanali> findAll() {
        return malattieSettimanaliDao.findAll();
    }

}
