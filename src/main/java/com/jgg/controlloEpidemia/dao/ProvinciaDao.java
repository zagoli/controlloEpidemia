package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Provincia;
import lombok.NoArgsConstructor;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
public class ProvinciaDao implements ProvinciaDaoInterface {

    private static Session session = new Session();

    final private String FROM_PROVINCIA_WHERE_NOME = "FROM Provincia where nome = :nome";

    @Override
    public void save(Provincia provincia) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().save(provincia);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        session.openCurrentSessionWithTransaction();
        Provincia provincia = session.getCurrentSession().get(Provincia.class, id);
        session.getCurrentSession().delete(provincia);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Provincia provincia) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().update(provincia);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void saveOrUpdate(Provincia provincia) {
        session.openCurrentSession();
        Provincia eProvincia = findByNome(provincia.getNome());
        if (eProvincia == null) {
            save(provincia);
        } else {
            eProvincia.setNome(provincia.getNome());
            eProvincia.setSuperficie(provincia.getSuperficie());
            eProvincia.setCapoluogo(provincia.getCapoluogo());
            eProvincia.setRegione(provincia.getRegione());
            update(eProvincia);
        }
        session.closeCurrentSession();
    }

    @Override
    public Provincia findById(Integer id) {
        session.openCurrentSession();
        Provincia provincia = session.getCurrentSession().get(Provincia.class, id);
        session.closeCurrentSession();
        return provincia;
    }

    @Override
    public Provincia findByNome(String nome) {
        session.openCurrentSession();
        Query query = session.createQuery(FROM_PROVINCIA_WHERE_NOME);
        query.setParameter("nome", nome);
        Provincia provincia = null;
        try {
            provincia = (Provincia) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        session.closeCurrentSession();
        return provincia;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Provincia> findAll() {
        session.openCurrentSession();
        List<Provincia> provincia = session.getCurrentSession().createQuery("from Provincia").list();
        session.closeCurrentSession();
        return provincia;
    }
}
