package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Ruolo;
import com.jgg.controlloEpidemia.model.Utente;

import java.util.List;

public interface UtenteDaoInterface {

    void save(Utente u);

    void deleteById(Integer id);

    void update(Utente utente);

    Utente findById(Integer id);

    Utente findByUsername(String username);

    List<Utente> findAll();
}
