package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Permessi;

import java.util.List;

public interface PermessiDaoInterface {

    void save(Permessi permessi);

    List<Permessi> findAll();
}
