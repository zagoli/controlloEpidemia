package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Utente;

import java.util.List;

public interface UtenteDaoInterface {

    void save(Utente u);

    void deleteById(Integer id);

    void update(Utente utente);

    void saveOrUpdate(Utente utente);

    Utente findById(Integer id);

    Utente findByUsername(String username);

    List<Utente> findAll();

    List<Utente> findAllPersonaleContratto();

}
