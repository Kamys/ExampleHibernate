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
     * Unique id for client.
     */
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    /**
     * Client name.
     */

    @Column(name = "name", nullable = false)
    private String name;
    /**
     * Client balance for to buy {@link Product}.
     */
    @Column(name = "balance", nullable = false)
    private int balance;
    /**
     * List to bought{@link Product}
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "purchased_product",
            joinColumns = @JoinColumn(name = "id_client"),
            inverseJoinColumns = @JoinColumn(name = "id_product"))
    private List<Product> products = new ArrayList<>();
    /**
     * Only for hibernate usage.
     */
    protected Client() {
    }
    public Client(int id, String name, int balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void addProduction(Product product) {
        products.add(product);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", products=" + products +
                '}';
    }
}
