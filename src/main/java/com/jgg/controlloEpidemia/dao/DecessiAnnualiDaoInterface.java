package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Ruolo;

import java.util.List;

public interface DecessiAnnualiDaoInterface {

    void save(DecessiAnnuali decessiAnnuali);

    void deleteById(Integer id);

    void update(DecessiAnnuali decessiAnnuali);

    void saveOrUpdate(DecessiAnnuali decessiAnnuali);

    DecessiAnnuali findById(Integer id);

    List<DecessiAnnuali> findAll();
}
