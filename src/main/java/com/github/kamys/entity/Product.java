package com.github.kamys.entity;

/**
 * Represents the production which to buys {@link Client}.
 */
public class Product {
    /**
     * Unique id for production;
     */
    private int id;

    /**
     * Product name.
     */
    private String name;
    private int cost;

    /**
     * Only for hibernate usage.
     */

    protected Product() {
    }

    public Product(int id, String name, int cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
