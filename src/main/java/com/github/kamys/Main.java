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

    public static void main(String[] args) {
        DataBaseHelper helper = new DataBaseHelper();
        final Client client = new Client(1,"Vasa", 300);
        client.addProduction(new Product(1,"Car",100));
        client.addProduction(new Product(2,"Milk",70));
        helper.saveClient(client);
    }


    private static void toSellCarSteve(Client client) {
        HashMap<Integer, Product> products = new HashMap<>();
        Product car = new Product(1, "Car", 300);
        products.put(1, car);

        Store store = new Store("Store 1", products);

        store.toSell(1, client);
    }
}
