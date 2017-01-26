package com.github.kamys.entity;

import java.util.List;

/**
 * Represents client which to buys {@link Production} of {@link Store}.
 * Contains list to bought{@link Production}.
 */
public class Client {
    /**
     * Client name.
     */
    private String name;
    /**
     * Client balance for to buy {@link Production}.
     */
    private int balance;
    /**
     * List to bought{@link Production}
     */
    private List<Production> boughtProductions;

    /**
     * Only for hibernate usage.
     */
    protected Client() {
    }

    public Client(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public List<Production> getBoughtProductions() {
        return boughtProductions;
    }

    public void setBoughtProductions(List<Production> boughtProductions) {
        this.boughtProductions = boughtProductions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    //TODO: Add test.
    public void withdraw(int amount) {
        balance-=amount;
    }

    public void addProduction(Production production) {
        boughtProductions.add(production);
    }
}
