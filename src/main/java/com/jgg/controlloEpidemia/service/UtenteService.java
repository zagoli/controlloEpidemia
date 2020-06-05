package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.UtenteDao;
import com.jgg.controlloEpidemia.model.Utente;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class UtenteService {
    private static final UtenteDao utenteDao = new UtenteDao();

    public void save(Utente utente) {
        utenteDao.save(utente);
    }

    public void deleteById(Integer id) {
        utenteDao.deleteById(id);
    }

    public void update(Utente utente) {
        utenteDao.update(utente);
    }

    public void saveIfNotPresent(Utente utente) {
        utenteDao.saveIfNotPresent(utente);
    }

    public Utente findById(Integer id) {
        return utenteDao.findById(id);
    }

    public Utente findByUsername(String username) {
        return utenteDao.findByUsername(username);
    }

    public List<Utente> findAll() {
        return utenteDao.findAll();
    }

    public List<Utente> findAllPersonaleContratto() {
        return utenteDao.findAllPersonaleContratto();
    }
}
