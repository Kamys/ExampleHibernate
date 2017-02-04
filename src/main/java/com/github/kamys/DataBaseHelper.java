package com.github.kamys;

import com.github.kamys.entity.Client;
import com.github.kamys.entity.Product;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;

/**
 * This class helping to work with DataBase.
 */
public class DataBaseHelper {
    private static final Logger LOGGER = Logger.getLogger(DataBaseHelper.class);

    /**
     * Save {@link Client} in data base.
     * At first save all {@link Product} of {@link Client}.
     * Before save {@link Client}.
     *
     * @param client which need save in data base.
     */
    public void saveClient(Client client) {
        for (Product product : client.getProducts()) {
            saveObject(product);
        }
        saveObject(client);
    }

    /**
     * Save object with hibernate annotation in data base.
     *
     * @param o which need save in data base.
     */
    private void saveObject(Object o) {
        Transaction tr = null;
        SessionFactory factory = createSessionFactory();
        Session session = factory.openSession();
        try {
            tr = session.beginTransaction();
            Serializable save = session.save(o);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) tr.rollback();
            LOGGER.warn(e);
        } finally {
            session.close();
            factory.close();
        }
    }

    private SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Product.class)
                .configure();

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        return configuration.buildSessionFactory(builder.build());
    }

}
