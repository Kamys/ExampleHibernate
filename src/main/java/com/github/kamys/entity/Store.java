package com.github.kamys.entity;

import java.util.Collections;
import java.util.Map;

/**
 * Represents store in which {@link Client} to buys {@link Production}.
 */
public class Store {
    /**
     * Store name.
     */
    private String name;
    /**
     * List production which to trades store.
     * Key - id Value - Production
     */
    private Map<Integer, Production> productions;

    /**
     * Only for hibernate usage.
     */
    protected Store() {
    }

    /**
     * Creates the Store instance with required fields and empty productions.
     *
     * @param name store name.
     */
    public Store(String name) {
        this(name, Collections.emptyMap());
    }

    /**
     * Creates the store instance with required fields.
     *
     * @param name        store name.
     * @param productions list production which to trades store.
     */
    public Store(String name, Map<Integer, Production> productions) {
        this.name = name;
        this.productions = productions;
    }

    /**
     * To sell {@link Production}.
     * Removal from balance is {@link Client}.
     * Add {@link Production} in {@link Client}.
     *
     * @param id     unique id for production.
     * @param client Client which want to buy {@link Production}.
     */
    public void toSell(int id, Client client) throws FailedToSell {
        Production production = productions.get(id);
        checkProduction(production);
        checkClient(client, production);

        client.withdraw(production.getCost());
        client.addProduction(production);
    }

    private void checkProduction(Production production) throws FailedToSell {
        if (production == null)
            throw new FailedToSell("Production missing.");
    }

    private void checkClient(Client client, Production production) throws FailedToSell {
        int balance = client.getBalance();
        if (balance < production.getCost())
            throw new FailedToSell("On the client's balance not enough money.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Production> getProductions() {
        return productions;
    }

    public void setProductions(Map<Integer, Production> productions) {
        this.productions = productions;
    }
}
