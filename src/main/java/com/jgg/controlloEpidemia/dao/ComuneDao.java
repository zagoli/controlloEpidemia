package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Comune;
import lombok.NoArgsConstructor;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
public class ComuneDao implements ComuneDaoInterface {

    private static final Session session = new Session();

    final private String FROM_COMUNE_WHERE_NOME = "FROM Comune where nome = :nome";
    final private String COUNT_COMUNE = "select count(*) FROM Comune";

    @Override
    public void save(Comune c) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().save(c);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteByCodiceIstat(String codiceIstat) {
        session.openCurrentSessionWithTransaction();
        Comune comune = session.getCurrentSession().get(Comune.class, codiceIstat);
        session.getCurrentSession().delete(comune);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Comune comune) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().update(comune);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void saveOrUpdate(Comune comune) {
        session.openCurrentSession();
        Comune eComune = findByNome(comune.getNome());
        if (eComune == null) {
            save(comune);
        } else {
            eComune.setNome(comune.getNome());
            eComune.setSuperficie(comune.getSuperficie());
            eComune.setDataIstituzione(comune.getDataIstituzione());
            eComune.setSiAffacciaSulMare(comune.getSiAffacciaSulMare());
            eComune.setTipoTerritorio(comune.getTipoTerritorio());
            eComune.setProvincia(comune.getProvincia());
            update(eComune);
        }
        session.closeCurrentSession();
    }

    @Override
    public Comune findByCodiceIstat(String codiceIstat) {
        session.openCurrentSession();
        Comune comune = session.getCurrentSession().get(Comune.class, codiceIstat);
        session.closeCurrentSession();
        return comune;
    }

    @Override
    public Comune findByNome(String nome) {
        session.openCurrentSession();
        Query query = session.createQuery(FROM_COMUNE_WHERE_NOME);
        query.setParameter("nome", nome);
        Comune comune = null;
        try {
            comune = (Comune) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        session.closeCurrentSession();
        return comune;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comune> findAll() {
        session.openCurrentSession();
        List<Comune> comune = session.getCurrentSession().createQuery("from Comune").list();
        session.closeCurrentSession();
        return comune;
    }

    @Override
    public Integer countComuni() {
        session.openCurrentSession();
        Integer count = Math.toIntExact((Long) session.getCurrentSession().createQuery(COUNT_COMUNE).getSingleResult());
        session.closeCurrentSession();
        return count;
    }

}

