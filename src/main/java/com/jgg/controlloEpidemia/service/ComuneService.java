package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.ComuneDao;
import com.jgg.controlloEpidemia.model.Comune;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ComuneService {

    private static final ComuneDao comuneDao = new ComuneDao();

    public void save(Comune comune) {
        comuneDao.openCurrentSessionWithTransaction();
        comuneDao.save(comune);
        comuneDao.closeCurrentSessionWithTransaction();
    }

    public void deleteByCodiceIstat(String codiceIstat) {
        comuneDao.openCurrentSessionWithTransaction();
        comuneDao.deleteByCodiceIstat(codiceIstat);
        comuneDao.closeCurrentSessionWithTransaction();
    }

    public void update(Comune comune) {
        comuneDao.openCurrentSessionWithTransaction();
        comuneDao.update(comune);
        comuneDao.closeCurrentSessionWithTransaction();
    }

    public void saveOrUpdate(Comune comune) {
        comuneDao.openCurrentSessionWithTransaction();
        comuneDao.saveOrUpdate(comune);
        comuneDao.closeCurrentSessionWithTransaction();
    }

    public void saveOrUpdate(List<Comune> comuneList) {
        comuneDao.openCurrentSessionWithTransaction();
        for (Comune comune : comuneList) {
            comuneDao.saveOrUpdate(comune);
        }
        comuneDao.closeCurrentSessionWithTransaction();
    }

    public Comune findByCodiceIstat(String codiceIstat) {
        comuneDao.openCurrentSession();
        Comune comuneByCodiceIstat = comuneDao.findByCodiceIstat(codiceIstat);
        comuneDao.closeCurrentSession();
        return comuneByCodiceIstat;
    }

    public Comune findByNome(String nome) {
        comuneDao.openCurrentSession();
        Comune comuneByNome = comuneDao.findByNome(nome);
        comuneDao.closeCurrentSession();
        return comuneByNome;
    }

    public List<Comune> findAll() {
        comuneDao.openCurrentSession();
        List<Comune> comuneAllList = comuneDao.findAll();
        comuneDao.closeCurrentSession();
        return comuneAllList;
    }

    public Integer countComuni() {
        comuneDao.openCurrentSession();
        Integer comuneCount = comuneDao.countComuni();
        comuneDao.closeCurrentSession();
        return comuneCount;
    }

}
