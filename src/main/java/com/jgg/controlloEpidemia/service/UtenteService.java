package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.UtenteDao;
import com.jgg.controlloEpidemia.model.Utente;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class UtenteService {

    private static final UtenteDao utenteDao = new UtenteDao();

    public void save(Utente utente) {
        utenteDao.openCurrentSessionWithTransaction();
        utenteDao.save(utente);
        utenteDao.closeCurrentSessionWithTransaction();
    }

    public void deleteById(Integer id) {
        utenteDao.openCurrentSessionWithTransaction();
        utenteDao.deleteById(id);
        utenteDao.closeCurrentSessionWithTransaction();
    }

    public void update(Utente utente) {
        utenteDao.openCurrentSessionWithTransaction();
        utenteDao.update(utente);
        utenteDao.closeCurrentSessionWithTransaction();
    }

    public void saveOrUpdate(Utente utente) {
        utenteDao.openCurrentSessionWithTransaction();
        utenteDao.saveOrUpdate(utente);
        utenteDao.closeCurrentSessionWithTransaction();
    }

    public void saveOrUpdate(List<Utente> utenteList) {
        utenteDao.openCurrentSessionWithTransaction();
        for (Utente utente : utenteList) {
            utenteDao.saveOrUpdate(utente);
        }
        utenteDao.closeCurrentSessionWithTransaction();
    }

    public Utente findById(Integer id) {
        utenteDao.openCurrentSession();
        Utente utenteById = utenteDao.findById(id);
        utenteDao.closeCurrentSession();
        return utenteById;
    }

    public Utente findByUsername(String username) {
        utenteDao.openCurrentSession();
        Utente utenteByUsername = utenteDao.findByUsername(username);
        utenteDao.closeCurrentSession();
        return utenteByUsername;
    }

    public List<Utente> findAll() {
        utenteDao.openCurrentSession();
        List<Utente> utenteAll = utenteDao.findAll();
        utenteDao.closeCurrentSession();
        return utenteAll;
    }

    public List<Utente> findAllPersonaleContratto() {
        utenteDao.openCurrentSession();
        List<Utente> utenteAllPersonaleContratto = utenteDao.findAllPersonaleContratto();
        utenteDao.closeCurrentSession();
        return utenteAllPersonaleContratto;
    }

}
