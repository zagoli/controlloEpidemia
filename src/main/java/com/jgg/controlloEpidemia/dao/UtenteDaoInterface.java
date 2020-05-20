package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Utente;

import java.util.List;

public interface UtenteDaoInterface {

    void save(Utente u);

    List<Utente> findAll();
}
