package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Utente;

import java.util.List;

public interface UtenteDaoInterface {

    void save(Utente u);

    void deleteById(Integer id);

    Utente findById(Integer id);

    List<Utente> findAll();
}
