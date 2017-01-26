package com.github.kamys.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StoreTest {

    private final List<Production> productions = new ArrayList<Production>();
    private final int idProduction = 0;
    private Store store;
    @Mock
    private Client client;

    @Before
    public void setUp() throws Exception {
        productions.add(createProduction(0, "Bread", 50));
        productions.add(createProduction(1, "Cheese", 60));
        productions.add(createProduction(2, "Meat", 120));
        store = new Store("Store 1", productions);
        when(client.getBalance()).thenReturn(100);
    }

    private Production createProduction(int id, String bread, int cost) {
        Production mock = mock(Production.class);
        mock.setId(id);
        mock.setName(bread);
        mock.setCost(cost);
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