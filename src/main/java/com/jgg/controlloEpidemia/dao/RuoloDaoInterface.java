package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Ruolo;

import java.util.List;

public interface RuoloDaoInterface {

    void save(Ruolo ruolo);

    void deleteById(Integer id);

    Ruolo findById(Integer id);

    List<Ruolo> findAll();
}
