package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
public class MalattieSettimanaliDao implements MalattieSettimanaliDaoInterface {

    final private String FROM_MALATTIE_WHERE_ANNO_SETTIMANA_COMUNE = "FROM MalattieSettimanali where ANNO= :anno and SETTIMANA= :settimana and COMUNE_CODICEISTAT= :comune";
    final private String FROM_MALATTIE_WHERE_ANNO = "from MalattieSettimanali where anno=:anno";
    final private String SELECT_ALL_ANNI_MALATTIE = "select distinct anno from MalattieSettimanali";

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

    @SuppressWarnings("rawtypes")
    public org.hibernate.query.Query createQuery(String hql) {
        return currentSession.createQuery(hql);
    }

    @Override
    public void save(MalattieSettimanali malattieSettimanali) {
        currentSession.save(malattieSettimanali);
    }

    @Override
    public void deleteById(Integer id) {
        currentSession.delete(currentSession.get(MalattieSettimanali.class, id));
    }

    @Override
    public void update(MalattieSettimanali malattieSettimanali) {
        currentSession.update(malattieSettimanali);
    }

    @Override
    public void saveOrUpdate(MalattieSettimanali malattieSettimanali) {
        MalattieSettimanali eMalattieSettimanali = findByAnnoSettimanaComune(malattieSettimanali.getAnno(), malattieSettimanali.getSettimana(), malattieSettimanali.getComune());
        if (eMalattieSettimanali == null) {
            save(malattieSettimanali);
        } else {
            eMalattieSettimanali.setAnno(malattieSettimanali.getAnno());
            eMalattieSettimanali.setSettimana(malattieSettimanali.getSettimana());
            eMalattieSettimanali.setRicoveratiInfluenza(malattieSettimanali.getRicoveratiInfluenza());
            eMalattieSettimanali.setInCuraInfluenza(malattieSettimanali.getInCuraInfluenza());
            eMalattieSettimanali.setComplicanzeRespiratorie(malattieSettimanali.getComplicanzeRespiratorie());
            eMalattieSettimanali.setRicoveratiPolmonite(malattieSettimanali.getRicoveratiPolmonite());
            eMalattieSettimanali.setInCuraPolmonite(malattieSettimanali.getInCuraPolmonite());
            eMalattieSettimanali.setRicoveratiMeningite(malattieSettimanali.getRicoveratiMeningite());
            eMalattieSettimanali.setInCuraMeningite(malattieSettimanali.getInCuraMeningite());
            eMalattieSettimanali.setRicoveratiEpatite(malattieSettimanali.getRicoveratiEpatite());
            eMalattieSettimanali.setInCuraEpatite(malattieSettimanali.getInCuraEpatite());
            eMalattieSettimanali.setRicoveratiMorbillo(malattieSettimanali.getRicoveratiMorbillo());
            eMalattieSettimanali.setInCuraMorbillo(malattieSettimanali.getInCuraMorbillo());
            eMalattieSettimanali.setRicoveratiTubercolosi(malattieSettimanali.getRicoveratiTubercolosi());
            eMalattieSettimanali.setInCuraTubercolosi(malattieSettimanali.getInCuraTubercolosi());
            eMalattieSettimanali.setRicoveratiGastroenterite(malattieSettimanali.getRicoveratiGastroenterite());
            eMalattieSettimanali.setInCuraGastroenterite(malattieSettimanali.getRicoveratiGastroenterite());
            eMalattieSettimanali.setComune(malattieSettimanali.getComune());
            update(eMalattieSettimanali);
        }
    }

    @Override
    public MalattieSettimanali findById(Integer id) {
        return currentSession.get(MalattieSettimanali.class, id);
    }

    @SuppressWarnings("rawtypes")
    public MalattieSettimanali findByAnnoSettimanaComune(Integer anno, Integer settimana, Comune comune) {
        Query query = createQuery(FROM_MALATTIE_WHERE_ANNO_SETTIMANA_COMUNE);
        query.setParameter("anno", anno);
        query.setParameter("settimana", settimana);
        query.setParameter("comune", comune.getCodiceIstat());
        MalattieSettimanali malattieSettimanali = null;
        try {
            malattieSettimanali = (MalattieSettimanali) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        return malattieSettimanali;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MalattieSettimanali> findAll() {
        return (List<MalattieSettimanali>) currentSession.createQuery("from MalattieSettimanali").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MalattieSettimanali> findByAnno(Integer anno) {
        return (List<MalattieSettimanali>) currentSession.createQuery(FROM_MALATTIE_WHERE_ANNO).setParameter("anno", anno).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Integer> findInsertedYears() {
        return (List<Integer>) currentSession.createQuery(SELECT_ALL_ANNI_MALATTIE).list();
    }

}
