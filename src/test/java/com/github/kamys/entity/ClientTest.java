package com.github.kamys.entity;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(DataProviderRunner.class)
public class ClientTest {

    private final Client client = new Client(1,"Client 1", 100);

    @DataProvider
    public static Object[][] dataForWithdraw(){
        return new Object[][]{
                {100,0},
                {50,50},
                {200,-100}
        };
    }

    @DataProvider
    public static Object[][] dataForDeposit(){
        return new Object[][]{
                {100,200},
                {50,150},
                {0,100}
        };
    }

    @Test
    @UseDataProvider("dataForWithdraw")
    public void withdraw(int amount,int result) throws Exception {
        client.withdraw(amount);
        int balance = client.getBalance();
        assertThat(balance,is(result));
    }

    @Test
    @UseDataProvider("dataForDeposit")
    public void deposit(int amount,int result) throws Exception {
        client.deposit(amount);
        int balance = client.getBalance();
        assertThat(balance,is(result));
    }
}