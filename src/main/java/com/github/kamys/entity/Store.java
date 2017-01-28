package com.github.kamys.entity;

import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents store in which {@link Client} to buys {@link Product}.
 */
public class Store {
    private static final Logger LOGGER = Logger.getLogger(Store.class);
    /**
     * Store name.
     */
    private String name;
    /**
     * List production which to trades store.
     * Key - id Value - Product
     */
    private Map<Integer, Product> products;

    /**
     * Only for hibernate usage.
     */
    protected Store() {
        LOGGER.info("Created through protected constructor.");
    }

    /**
     * Creates the Store instance with required fields and empty products.
     *
     * @param name store name.
     */
    public Store(String name) {
        this(name, Collections.emptyMap());
        LOGGER.info("Created with an empty products." + toString());
    }

    /**
     * Creates the store instance with required fields.
     *
     * @param name     store name.
     * @param products list production which to trades store.
     */
    public Store(String name, Map<Integer, Product> products) {
        this.name = name;
        this.products = new HashMap<>(products);
        LOGGER.info("Created " + toString());
    }

    /**
     * To sell {@link Product}.
     * Removal from balance is {@link Client}.
     * Add {@link Product} in {@link Client}.
     *
     * @param id     unique id for production.
     * @param client Client which want to buy {@link Product}.
     */
    public void toSell(int id, Client client) throws FailedToSell {
        try {
            Product product = products.get(id);
            checkProduction(product);
            checkClient(client, product);

            client.withdraw(product.getCost());
            client.addProduction(product);

            products.remove(id);
        } catch (FailedToSell caught) {
            FailedToSell newFailedToSell = new FailedToSell(
                    "Failed to sell id = " + id + " for " + client,
                    caught);
            LOGGER.warn(newFailedToSell);
            throw newFailedToSell;
        }
    }

    private void checkProduction(Product product) throws FailedToSell {
        if (product == null) {
            final FailedToSell failedToSell =
                    new FailedToSell("Product missing in " + toString());
            LOGGER.warn(failedToSell);
            throw failedToSell;
        }
    }

    private void checkClient(Client client, Product product) throws FailedToSell {
        final int balance = client.getBalance();
        final int costProduct = product.getCost();
        if (balance < costProduct) {
            FailedToSell failedToSell = new FailedToSell(
                    "On the client's balance not enough money. Balance = " + balance + ". Need " + costProduct);
            LOGGER.warn(failedToSell);
            throw failedToSell;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
