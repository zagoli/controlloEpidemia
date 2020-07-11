package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Provincia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@SuppressWarnings("rawtypes")
@NoArgsConstructor
public class DecessiAnnualiDao implements DecessiAnnualiDaoInterface {

    final private String FROM_DECESSI_WHERE_ANNO_PROVINCIA = "from DecessiAnnuali where anno= :anno and provincia_id= :provincia";
    final private String SELECT_ALL_ANNI_DECESSI = "select distinct anno from DecessiAnnuali";
    final private String FROM_DECESSI_WHERE_ANNO = "from DecessiAnnuali where anno=:anno";

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
    public void save(DecessiAnnuali decessiAnnuali) {
        currentSession.save(decessiAnnuali);
    }

    @Override
    public void deleteById(Integer id) {
        currentSession.delete(currentSession.get(DecessiAnnuali.class, id));
    }

    @Override
    public void update(DecessiAnnuali decessiAnnuali) {
        currentSession.update(decessiAnnuali);
    }

    @Override
    public void saveOrUpdate(DecessiAnnuali decessiAnnuali) {
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
    }

    public DecessiAnnuali findByAnnoProvincia(Integer anno, Provincia provincia) {
        Query query = createQuery(FROM_DECESSI_WHERE_ANNO_PROVINCIA);
        query.setParameter("anno", anno);
        query.setParameter("provincia", provincia.getId());
        DecessiAnnuali decessiAnnuali;
        try {
            decessiAnnuali = (DecessiAnnuali) query.getSingleResult();
        } catch (NoResultException ignored) {
            decessiAnnuali = null;
        }
        return decessiAnnuali;
    }

    @Override
    public DecessiAnnuali findById(Integer id) {
        return currentSession.get(DecessiAnnuali.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DecessiAnnuali> findAll() {
        return (List<DecessiAnnuali>) currentSession.createQuery("from DecessiAnnuali").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DecessiAnnuali> findByAnno(Integer anno) {
        return (List<DecessiAnnuali>) currentSession.createQuery(FROM_DECESSI_WHERE_ANNO).setParameter("anno", anno).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Integer> findInsertedYears() {
        return (List<Integer>) currentSession.createQuery(SELECT_ALL_ANNI_DECESSI).list();
    }

}
