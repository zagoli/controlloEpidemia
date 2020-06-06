package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import lombok.NoArgsConstructor;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
public class MalattieSettimanaliDao implements MalattieSettimanaliDaoInterface {

    private static Session session = new Session();

    final private String FROM_COMUNE_WHERE_ANNO_SETTIMANA_COMUNE = "FROM MalattieSettimanali where ANNO= :anno and SETTIMANA= :settimana and COMUNE_CODICEISTAT= :comune";

    @Override
    public void save(MalattieSettimanali malattieSettimanali) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().save(malattieSettimanali);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        session.openCurrentSessionWithTransaction();
        MalattieSettimanali malattieSettimanali = session.getCurrentSession().get(MalattieSettimanali.class, id);
        session.getCurrentSession().delete(malattieSettimanali);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(MalattieSettimanali malattieSettimanali) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().update(malattieSettimanali);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void saveOrUpdate(MalattieSettimanali malattieSettimanali) {
        session.openCurrentSession();
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
        session.closeCurrentSession();
    }

    @Override
    public MalattieSettimanali findById(Integer id) {
        session.openCurrentSession();
        MalattieSettimanali malattieSettimanali = session.getCurrentSession().get(MalattieSettimanali.class, id);
        session.closeCurrentSession();
        return malattieSettimanali;
    }

    @Override
    public MalattieSettimanali findByAnnoSettimanaComune(Integer anno, Integer settimana, Comune comune) {
        session.openCurrentSession();
        Query query = session.createQuery(FROM_COMUNE_WHERE_ANNO_SETTIMANA_COMUNE);
        query.setParameter("anno", anno);
        query.setParameter("settimana", settimana);
        query.setParameter("comune", comune.getCodiceIstat());
        MalattieSettimanali malattieSettimanali = null;
        try {
            malattieSettimanali = (MalattieSettimanali) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        session.closeCurrentSession();
        return malattieSettimanali;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MalattieSettimanali> findAll() {
        session.openCurrentSession();
        List<MalattieSettimanali> malattieSettimanali = session.getCurrentSession().createQuery("from MalattieSettimanali").list();
        session.closeCurrentSession();
        return malattieSettimanali;
    }

}
