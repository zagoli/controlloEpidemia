import com.jgg.controlloEpidemia.model.TipoTerritorio;
import com.jgg.controlloEpidemia.service.TipoTerritorioService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

public class Main {

    private static final SessionFactory ourSessionFactory;

    static {
        Configuration configuration = new Configuration().configure();
        ourSessionFactory = configuration.buildSessionFactory();
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) {
        try (Session session = getSession()) {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        }

        //Esempio salvataggio tipoterritorio
        TipoTerritorio tp = new TipoTerritorio(1, "pianeggiante");
        TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
        tipoTerritorioService.save(tp);
    }
}