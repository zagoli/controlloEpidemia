package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Ruolo;

import java.util.List;

public interface RuoloDaoInterface {

    void save(Ruolo ruolo);

    List<Ruolo> findAll();
}
