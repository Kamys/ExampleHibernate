package com.github.kamys.entity;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(DataProviderRunner.class)
public class StoreTest {

    private final Map<Integer, Product> productions = new HashMap<>();
    private Store store;
    private Client client = mock(Client.class);

    @Before
    public void setUp() throws Exception {
        addProduction(0, "Bread", 50);
        addProduction(1, "Cheese", 60);
        addProduction(2, "Meat", 120);
        store = new Store("Store 1", productions);
        when(client.getBalance()).thenReturn(200);
    }

    @DataProvider
    public static Object[][] idProduction(){
        return new Object[][] {
                {0},
                {1},
                {2},
        };
    }

    private void addProduction(int id, String name, int cost) {
        Product product = createProduction(id, name, cost);
        productions.put(id, product);
    }

    private Product createProduction(int id, String name, int cost) {
        Product mock = mock(Product.class);
        when(mock.getCost()).thenReturn(cost);
        return mock;
    }

    @Test
    @UseDataProvider("idProduction")
    public void checkRemovalFromBalance(int idProduction) throws Exception {
        final int cost = productions.get(idProduction).getCost();
        store.toSell(idProduction, client);
        verify(client).withdraw(cost);
    }

    @Test
    @UseDataProvider("idProduction")
    public void checkAddProductionInClient(int idProduction) throws Exception {
        store.toSell(idProduction, client);
        verify(client).addProduction(productions.get(idProduction));
    }

    @Test(expected = FailedToSell.class)
    @UseDataProvider("idProduction")
    public void checkBalanceNotEnoughMoney(int idProduction) throws Exception {
        when(client.getBalance()).thenReturn(0);
        store.toSell(idProduction, client);
    }

    @Test
    @UseDataProvider("idProduction")
    public void checkRemoveProduct(int idProduction) throws Exception {
        store.toSell(idProduction, client);
        Product product = store.getProducts().get(idProduction);
        assertNull(product);
    }
}