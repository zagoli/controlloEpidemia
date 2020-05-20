package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;

import java.util.List;

public interface DecessiAnnualiDaoInterface {

    void save(DecessiAnnuali decessiAnnuali);

    List<DecessiAnnuali> findAll();
}
