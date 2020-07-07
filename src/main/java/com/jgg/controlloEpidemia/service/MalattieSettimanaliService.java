package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.MalattieSettimanaliDao;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class MalattieSettimanaliService {

    private static final MalattieSettimanaliDao malattieSettimanaliDao = new MalattieSettimanaliDao();

    public void save(MalattieSettimanali malattieSettimanali) {
        malattieSettimanaliDao.openCurrentSessionWithTransaction();
        malattieSettimanaliDao.save(malattieSettimanali);
        malattieSettimanaliDao.closeCurrentSessionWithTransaction();
    }

    public void deleteById(Integer id) {
        malattieSettimanaliDao.openCurrentSessionWithTransaction();
        malattieSettimanaliDao.deleteById(id);
        malattieSettimanaliDao.closeCurrentSessionWithTransaction();
    }

    public void update(MalattieSettimanali malattieSettimanali) {
        malattieSettimanaliDao.openCurrentSessionWithTransaction();
        malattieSettimanaliDao.update(malattieSettimanali);
        malattieSettimanaliDao.closeCurrentSessionWithTransaction();
    }

    public void saveOrUpdate(MalattieSettimanali malattieSettimanali) {
        malattieSettimanaliDao.openCurrentSessionWithTransaction();
        malattieSettimanaliDao.saveOrUpdate(malattieSettimanali);
        malattieSettimanaliDao.closeCurrentSessionWithTransaction();
    }

    public void saveOrUpdate(List<MalattieSettimanali> malattieSettimanaliList) {
        malattieSettimanaliDao.openCurrentSessionWithTransaction();
        for (MalattieSettimanali malattieSettimanali : malattieSettimanaliList) {
            malattieSettimanaliDao.saveOrUpdate(malattieSettimanali);
        }
        malattieSettimanaliDao.closeCurrentSessionWithTransaction();
    }

    public MalattieSettimanali findById(Integer id) {
        malattieSettimanaliDao.openCurrentSession();
        MalattieSettimanali malattieSettimanaliById = malattieSettimanaliDao.findById(id);
        malattieSettimanaliDao.closeCurrentSession();
        return malattieSettimanaliById;
    }

    public List<MalattieSettimanali> findByAnno(Integer anno) {
        malattieSettimanaliDao.openCurrentSession();
        List<MalattieSettimanali> malattieSettimanaliByAnno = malattieSettimanaliDao.findByAnno(anno);
        malattieSettimanaliDao.closeCurrentSession();
        return malattieSettimanaliByAnno;
    }

    public List<MalattieSettimanali> findAll() {
        malattieSettimanaliDao.openCurrentSession();
        List<MalattieSettimanali> malattieSettimanaliAll = malattieSettimanaliDao.findAll();
        malattieSettimanaliDao.closeCurrentSession();
        return malattieSettimanaliAll;
    }

    public List<Integer> findInsertedYears() {
        malattieSettimanaliDao.openCurrentSession();
        List<Integer> malattieSettimanaliInsertedYears = malattieSettimanaliDao.findInsertedYears();
        malattieSettimanaliDao.closeCurrentSession();
        return malattieSettimanaliInsertedYears;
    }

}
