package com.github.kamys.entity;

import java.util.Collections;
import java.util.List;

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
     */
    private List<Production> productions;

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
        this(name, Collections.<Production>emptyList());
    }

    /**
     * Creates the store instance with required fields.
     *
     * @param name        store name.
     * @param productions list production which to trades store.
     */
    public Store(String name, List<Production> productions) {
        this.name = name;
        this.productions = productions;
    }

    public void toSell(int id, Client client) {
        //TODO: To finish this and add test.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Production> getProductions() {
        return productions;
    }

    public void setProductions(List<Production> productions) {
        this.productions = productions;
    }
}
