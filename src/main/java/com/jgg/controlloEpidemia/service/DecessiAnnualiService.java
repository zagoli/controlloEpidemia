package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.DecessiAnnualiDao;
import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class DecessiAnnualiService {

    private static final DecessiAnnualiDao decessiAnnualiDao = new DecessiAnnualiDao();

    public void save(DecessiAnnuali decessiAnnuali) {
        decessiAnnualiDao.save(decessiAnnuali);
    }

    public DecessiAnnuali findById(Integer id) {
        return decessiAnnualiDao.findById(id);
    }

    public void deleteById(Integer id) {
        decessiAnnualiDao.deleteById(id);
    }

    public List<DecessiAnnuali> findAll() {
        return decessiAnnualiDao.findAll();
    }

}
