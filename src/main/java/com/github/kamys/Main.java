package com.github.kamys;

import com.github.kamys.entity.Client;
import com.github.kamys.entity.Product;
import com.github.kamys.entity.Store;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This main.
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    private static final Client steve = new Client("Steve", 300);

    public static void main(String[] args) {
        SessionFactory factory = createSessionFactory();
        Transaction tr = null;
        try (
                Session session = factory.openSession()
        ) {
            tr = session.beginTransaction();
            Serializable save = session.save(steve);
            LOGGER.debug("Serializable = " + save);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) tr.rollback();
            LOGGER.warn(e);
        }

    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Client.class)
                .configure();

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        return configuration.buildSessionFactory(builder.build());
    }

    private static void toSellCarSteve() {
        HashMap<Integer, Product> products = new HashMap<>();
        Product car = new Product(1, "Car", 300);
        products.put(1, car);

        Store store = new Store("Store 1", products);

        store.toSell(1, steve);
    }
}
