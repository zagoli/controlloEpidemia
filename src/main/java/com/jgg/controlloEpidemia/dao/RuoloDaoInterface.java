package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Ruolo;
import com.jgg.controlloEpidemia.model.TipoTerritorio;

import java.util.List;

public interface RuoloDaoInterface {

    void save(Ruolo ruolo);

    void deleteById(Integer id);

    void update(Ruolo ruolo);

    void saveOrUpdate(Ruolo ruolo);

    Ruolo findById(Integer id);

    Ruolo findByNome(String nome);

    List<Ruolo> findAll();
}
