package com.github.kamys;

import com.github.kamys.entity.Client;
import com.github.kamys.entity.Product;
import com.github.kamys.entity.Store;

import java.util.HashMap;

/**
 * This main.
 */
public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Product> products = new HashMap<>();
        Product car = new Product(1, "Car", 300);
        products.put(1, car);

        Store store = new Store("Store 1", products);
        Client steve = new Client("Steve", 300);

        store.toSell(1,steve);
    }
}
