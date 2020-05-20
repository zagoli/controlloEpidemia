package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.DecessiAnnualiDao;
import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class DecessiAnnualiService {

    private static DecessiAnnualiDao decessiAnnualiDao = new DecessiAnnualiDao();

    public void save(DecessiAnnuali decessiAnnuali) {
        decessiAnnualiDao.save(decessiAnnuali);
    }

    public List<DecessiAnnuali> findAll() {
        return decessiAnnualiDao.findAll();
    }

}
