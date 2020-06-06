package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.ComuneDao;
import com.jgg.controlloEpidemia.model.Comune;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ComuneService {

    private static final ComuneDao comuneDao = new ComuneDao();

    public void save(Comune comune) {
        comuneDao.save(comune);
    }

    public void deleteByCodiceIstat(Integer codiceIstat) {
        comuneDao.deleteByCodiceIstat(codiceIstat);
    }

    public void update(Comune comune) {
        comuneDao.update(comune);
    }

    public void saveOrUpdate(Comune comune) {
        comuneDao.saveOrUpdate(comune);
    }

    public Comune findByCodiceIstat(Integer codiceIstat) {
        return comuneDao.findByCodiceIstat(codiceIstat);
    }

    public Comune findByNome(String nome) {
        return comuneDao.findByNome(nome);
    }

    public List<Comune> findAll() {
        return comuneDao.findAll();
    }

    public Integer countComuni(){return comuneDao.countComuni();}

}
