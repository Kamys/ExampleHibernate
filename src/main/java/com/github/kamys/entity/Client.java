package com.github.kamys.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents client which to buys {@link Product} of {@link Store}.
 * Contains list to bought{@link Product}.
 */
@Entity
@Table(schema = "public", name = "clients")
public class Client {
    /**
     * Client name.
     */
    @Id
    @Column(name = "name")
    private String name;
    /**
     * Client balance for to buy {@link Product}.
     */
    @Column(name = "balance")
    private int balance;
    /**
     * List to bought{@link Product}
     */
    @Transient
    private List<Product> boughtProducts = new ArrayList<>();

    /**
     * Only for hibernate usage.
     */
    protected Client() {
    }

    public Client(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public List<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
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

    public void withdraw(int amount) {
        balance-=amount;
    }

    public void deposit(int amount){
        balance+=amount;
    }

    public void addProduction(Product product) {
        boughtProducts.add(product);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                ", boughtProducts=" + boughtProducts +
                '}';
    }
}
