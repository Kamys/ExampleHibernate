package com.github.kamys.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StoreTest {

    private final Map<Integer, Production> productions = new HashMap<>();
    private final int idProduction = 0;
    private Store store;
    @Mock
    private Client client;

    @Before
    public void setUp() throws Exception {
        addProduction(0, "Bread", 50);
        addProduction(1, "Cheese", 60);
        addProduction(2, "Meat", 120);
        store = new Store("Store 1", productions);
        when(client.getBalance()).thenReturn(100);
    }

    private void addProduction(int id, String name, int cost) {
        Production production = createProduction(id, name, cost);
        productions.put(id, production);
    }

    private Production createProduction(int id, String name, int cost) {
        Production mock = mock(Production.class);
        when(mock.getCost()).thenReturn(cost);
        return mock;
    }

    @Test
    public void checkRemovalFromBalance() throws Exception {
        final int cost = productions.get(idProduction).getCost();
        store.toSell(idProduction, client);
        verify(client).withdraw(cost);
    }

    @Test
    public void checkAddProductionInClient() throws Exception {
        store.toSell(idProduction, client);
        verify(client).addProduction(productions.get(idProduction));
    }

    @Test(expected = FailedToSell.class)
    public void checkBalanceNotEnoughMoney() throws Exception {
        when(client.getBalance()).thenReturn(0);
        store.toSell(idProduction, client);
    }
}