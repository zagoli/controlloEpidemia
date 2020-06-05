package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.TipoTerritorio;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class TipoTerritorioDao implements TipoTerritorioDaoInterface {

    private static Session session = new Session();

    @Override
    public void save(TipoTerritorio tipoTerritorio) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().save(tipoTerritorio);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        session.openCurrentSessionWithTransaction();
        TipoTerritorio tipoTerritorio = session.getCurrentSession().get(TipoTerritorio.class, id);
        session.getCurrentSession().delete(tipoTerritorio);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(TipoTerritorio tipoTerritorio) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().update(tipoTerritorio);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void saveOrUpdate(TipoTerritorio tipoTerritorio) {
        session.openCurrentSession();
        TipoTerritorio eTipoTerritorio = findByNome(tipoTerritorio.getNome());
        if (eTipoTerritorio == null) {
            save(tipoTerritorio);
        }else{
            eTipoTerritorio.setNome(tipoTerritorio.getNome());
            update(eTipoTerritorio);
        }
        session.closeCurrentSession();
    }

    @Override
    public TipoTerritorio findById(Integer id) {
        session.openCurrentSession();
        TipoTerritorio tipoTerritorio = session.getCurrentSession().get(TipoTerritorio.class, id);
        session.closeCurrentSession();
        return tipoTerritorio;
    }

    @Override
    public TipoTerritorio findByNome(String nome) {
        session.openCurrentSession();
        String hql = "FROM TipoTerritorio where nome = :nome";
        Query query = session.createQuery(hql);
        query.setParameter("nome", nome);
        TipoTerritorio tipoTerritorio = null;
        try {
            tipoTerritorio = (TipoTerritorio) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        session.closeCurrentSession();
        return tipoTerritorio;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<TipoTerritorio> findAll() {
        session.openCurrentSession();
        List<TipoTerritorio> tipoTerritorio = session.getCurrentSession().createQuery("from TipoTerritorio").list();
        session.closeCurrentSession();
        return tipoTerritorio;
    }
}
