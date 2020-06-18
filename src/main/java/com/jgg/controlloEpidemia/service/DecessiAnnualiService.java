package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.DecessiAnnualiDao;
import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class DecessiAnnualiService {

    private static final DecessiAnnualiDao decessiAnnualiDao = new DecessiAnnualiDao();

    public void save(DecessiAnnuali decessiAnnuali) {
        decessiAnnualiDao.openCurrentSessionWithTransaction();
        decessiAnnualiDao.save(decessiAnnuali);
        decessiAnnualiDao.closeCurrentSessionWithTransaction();
    }

    public void deleteById(Integer id) {
        decessiAnnualiDao.openCurrentSessionWithTransaction();
        decessiAnnualiDao.deleteById(id);
        decessiAnnualiDao.closeCurrentSessionWithTransaction();
    }

    public void update(DecessiAnnuali decessiAnnuali) {
        decessiAnnualiDao.openCurrentSessionWithTransaction();
        decessiAnnualiDao.update(decessiAnnuali);
        decessiAnnualiDao.closeCurrentSessionWithTransaction();
    }

    public void saveOrUpdate(DecessiAnnuali decessiAnnuali) {
        decessiAnnualiDao.openCurrentSessionWithTransaction();
        decessiAnnualiDao.saveOrUpdate(decessiAnnuali);
        decessiAnnualiDao.closeCurrentSessionWithTransaction();
    }

    public void saveOrUpdate(List<DecessiAnnuali> decessiAnnualiList) {
        decessiAnnualiDao.openCurrentSessionWithTransaction();
        for (DecessiAnnuali decessiAnnuali : decessiAnnualiList) {
            decessiAnnualiDao.saveOrUpdate(decessiAnnuali);
        }
        decessiAnnualiDao.closeCurrentSessionWithTransaction();
    }

    public DecessiAnnuali findById(Integer id) {
        decessiAnnualiDao.openCurrentSession();
        DecessiAnnuali decessiAnnualiById = decessiAnnualiDao.findById(id);
        decessiAnnualiDao.closeCurrentSession();
        return decessiAnnualiById;
    }

    public List<DecessiAnnuali> findAll() {
        decessiAnnualiDao.openCurrentSession();
        List<DecessiAnnuali> decessiAnnualiAll = decessiAnnualiDao.findAll();
        decessiAnnualiDao.closeCurrentSession();
        return decessiAnnualiAll;
    }

    public List<DecessiAnnuali> findByAnno(Integer anno) {
        decessiAnnualiDao.openCurrentSession();
        List<DecessiAnnuali> decessiAnnualiByAnno = decessiAnnualiDao.findByAnno(anno);
        decessiAnnualiDao.closeCurrentSession();
        return decessiAnnualiByAnno;
    }

    public List<Integer> findInsertedYears() {
        decessiAnnualiDao.openCurrentSession();
        List<Integer> decessiAnnualiInsertedYears = decessiAnnualiDao.findInsertedYears();
        decessiAnnualiDao.closeCurrentSession();
        return decessiAnnualiInsertedYears;
    }

}
