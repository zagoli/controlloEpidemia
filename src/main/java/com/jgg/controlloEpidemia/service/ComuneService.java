package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.ComuneDao;
import com.jgg.controlloEpidemia.model.Comune;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ComuneService {

    private static ComuneDao comuneDao = new ComuneDao();

    public void save(Comune comune) {
        comuneDao.save(comune);
    }

    public Comune findByCodiceIstat(String codiceIstat) {
        return comuneDao.findByCodiceIstat(codiceIstat);
    }

    public void deleteByCodiceIstat(String codiceIstat) {
        comuneDao.deleteByCodiceIstat(codiceIstat);
    }

    public List<Comune> findAll() {
        return comuneDao.findAll();
    }

}
