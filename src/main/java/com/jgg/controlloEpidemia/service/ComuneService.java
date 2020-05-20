package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.ComuneDao;
import com.jgg.controlloEpidemia.model.Comune;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ComuneService {

    private static ComuneDao comuneDao = new ComuneDao();

    public void save(Comune entity) {
        comuneDao.save(entity);
    }

    public Comune findByCodiceIstat(String codice) {
        return comuneDao.findByCodiceIstat(codice);
    }

    public void deleteByCodiceIstat(String codice) {
        Comune comune = comuneDao.findByCodiceIstat(codice);
        comuneDao.deleteByCodiceIstat(codice);
    }

    public List<Comune> findAll() {
        return comuneDao.findAll();
    }

    public ComuneDao comuneDao() {
        return comuneDao;
    }
}
