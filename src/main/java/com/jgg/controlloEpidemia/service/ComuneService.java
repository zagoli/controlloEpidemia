package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.ComuneDao;
import com.jgg.controlloEpidemia.model.Comune;

import java.util.List;

public class ComuneService {

    private static ComuneDao comuneDao;

    public ComuneService() {
        comuneDao = new ComuneDao();
    }

    public void save(Comune entity) {
        comuneDao.openCurrentSessionWithTransaction();
        comuneDao.save(entity);
        comuneDao.closeCurrentSessionWithTransaction();
    }

    public Comune findByCodiceIstat(String codice) {
        comuneDao.openCurrentSession();
        Comune comune = comuneDao.findByCodiceIstat(codice);
        comuneDao.closeCurrentSession();
        return comune;
    }

    public void deleteByCodiceIstat(String codice) {
        comuneDao.openCurrentSessionWithTransaction();
        comuneDao.deleteByCodiceIstat(codice);
        comuneDao.closeCurrentSessionWithTransaction();
    }

    public List<Comune> findAll() {
        comuneDao.openCurrentSession();
        List<Comune> comune = comuneDao.findAll();
        comuneDao.closeCurrentSession();
        return comune;
    }

    public ComuneDao comuneDao() {
        return comuneDao;
    }
}
