package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.UtenteDao;
import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Utente;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class UtenteService {
    private static UtenteDao utenteDao = new UtenteDao();

    public void save(Utente utente) {
        utenteDao.save(utente);
    }

    public List<Utente> findAll() {
        return utenteDao.findAll();
    }
}
