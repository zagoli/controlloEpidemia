package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Comune;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
public class ComuneDao implements ComuneDaoInterface {

    final private String FROM_COMUNE_WHERE_NOME = "FROM Comune where nome = :nome";
    final private String COUNT_COMUNE = "select count(*) FROM Comune";

    @Getter
    @Setter
    private org.hibernate.Session currentSession;

    @Getter
    @Setter
    private org.hibernate.Transaction currentTransaction;

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        return configuration.buildSessionFactory();
    }

    public void openCurrentSession() {
        currentSession = getSessionFactory().openSession();
    }

    public void openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public org.hibernate.query.Query createQuery(String hql) {
        return currentSession.createQuery(hql);
    }

    @Override
    public void save(Comune comune) {
        currentSession.save(comune);
    }

    @Override
    public void deleteByCodiceIstat(String codiceIstat) {
        currentSession.delete(currentSession.get(Comune.class, codiceIstat));
    }

    @Override
    public void update(Comune comune) {
        currentSession.update(comune);
    }

    @Override
    public void saveOrUpdate(Comune comune) {
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
    }

    @Override
    public Comune findByCodiceIstat(String codiceIstat) {
        return currentSession.get(Comune.class, codiceIstat);
    }

    @Override
    public Comune findByNome(String nome) {
        Query query = createQuery(FROM_COMUNE_WHERE_NOME);
        query.setParameter("nome", nome);
        Comune comune = null;
        try {
            comune = (Comune) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        return comune;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comune> findAll() {
        return (List<Comune>) currentSession.createQuery("from Comune").list();
    }

    @Override
    public Integer countComuni() {
        return Math.toIntExact((Long) currentSession.createQuery(COUNT_COMUNE).getSingleResult());
    }

}

