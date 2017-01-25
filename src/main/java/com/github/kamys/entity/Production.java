package com.github.kamys.entity;

/**
 * Represents the production which to buys {@link Client}.
 */
public class Production {
    /**
     * Unique id for production;
     */
    private int id;

    /**
     * Production name.
     */
    private String name;

    /**
     * Only for hibernate usage.
     */

    protected Production() {
    }

    public Production(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
