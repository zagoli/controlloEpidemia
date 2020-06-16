package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Provincia;
import lombok.NoArgsConstructor;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
public class DecessiAnnualiDao implements DecessiAnnualiDaoInterface {

    private static final Session session = new Session();

    final private String FROM_DECESSI_WHERE_ANNO_PROVINCIA = "from DecessiAnnuali where anno= :anno and provincia_id= :provincia";
    final private String SELECT_ALL_ANNI_DECESSI = "select distinct anno from DecessiAnnuali";
    final private String FROM_DECESSI_WHERE_ANNO = "from DecessiAnnuali where anno=:anno";

    @Override
    public void save(DecessiAnnuali decessiAnnuali) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().save(decessiAnnuali);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        session.openCurrentSessionWithTransaction();
        DecessiAnnuali decessiAnnuali = session.getCurrentSession().get(DecessiAnnuali.class, id);
        session.getCurrentSession().delete(decessiAnnuali);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(DecessiAnnuali decessiAnnuali) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().update(decessiAnnuali);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void saveOrUpdate(DecessiAnnuali decessiAnnuali) {
        session.openCurrentSession();
        DecessiAnnuali eDecessiAnnuali = findByAnnoProvincia(decessiAnnuali.getAnno(), decessiAnnuali.getProvincia());
        if (eDecessiAnnuali == null) {
            save(decessiAnnuali);
        } else {
            eDecessiAnnuali.setAnno(decessiAnnuali.getAnno());
            eDecessiAnnuali.setIncidentiStradali(decessiAnnuali.getIncidentiStradali());
            eDecessiAnnuali.setMalattieTumorali(decessiAnnuali.getMalattieTumorali());
            eDecessiAnnuali.setMalattieCardiovascolari(decessiAnnuali.getMalattieCardiovascolari());
            eDecessiAnnuali.setMalattieContagiose(decessiAnnuali.getMalattieContagiose());
            eDecessiAnnuali.setProvincia(decessiAnnuali.getProvincia());
            update(eDecessiAnnuali);
        }
        session.closeCurrentSession();
    }

    @Override
    public DecessiAnnuali findById(Integer id) {
        session.openCurrentSession();
        DecessiAnnuali decessiAnnuali = session.getCurrentSession().get(DecessiAnnuali.class, id);
        session.closeCurrentSession();
        return decessiAnnuali;
    }

    @Override
    public DecessiAnnuali findByAnnoProvincia(Integer anno, Provincia provincia) {
        session.openCurrentSession();
        Query query = session.createQuery(FROM_DECESSI_WHERE_ANNO_PROVINCIA);
        query.setParameter("anno", anno);
        query.setParameter("provincia", provincia.getId());
        DecessiAnnuali decessiAnnuali;
        try {
            decessiAnnuali = (DecessiAnnuali) query.getSingleResult();
        } catch (NoResultException ignored) {
            decessiAnnuali = null;
        }
        session.closeCurrentSession();
        return decessiAnnuali;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DecessiAnnuali> findAll() {
        session.openCurrentSession();
        List<DecessiAnnuali> decessiAnnuali = session.getCurrentSession().createQuery("from DecessiAnnuali").list();
        session.closeCurrentSession();
        return decessiAnnuali;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DecessiAnnuali> findByAnno(Integer anno) {
        session.openCurrentSession();
        List<DecessiAnnuali> decessi = session.getCurrentSession().createQuery(FROM_DECESSI_WHERE_ANNO).setParameter("anno", anno).list();
        session.closeCurrentSession();
        return decessi;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Integer> findInsertedYears() {
        session.openCurrentSession();
        List<Integer> anni = session.getCurrentSession().createQuery(SELECT_ALL_ANNI_DECESSI).list();
        session.closeCurrentSession();
        return anni;
    }

}
