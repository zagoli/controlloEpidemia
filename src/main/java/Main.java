import com.jgg.controlloEpidemia.dao.ComuneDao;
import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import java.util.*;

import static java.lang.String.valueOf;

public class Main {

    private static final SessionFactory ourSessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
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

        Comune c = new Comune();
        c.setCodiceIstat("a");
        ComuneDao y = new ComuneDao();
        y.save(c);
        Optional<Comune> a = y.findByCodiceIstat("a");
        System.out.println(a.get());

    }
}