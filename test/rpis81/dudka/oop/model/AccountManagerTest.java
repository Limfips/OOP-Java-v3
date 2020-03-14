package rpis81.dudka.oop.model;

import org.junit.Before;
import org.junit.Test;
import rpis81.dudka.oop.model.source.DataSource;

import static org.junit.Assert.*;

public class AccountManagerTest {

    private AccountManager accountManager;
    private DataSource source;

    @Before
    public void init() {
        source = new DataSource();
        accountManager = new AccountManager(new Account[]{source.accounts[0],
                source.accounts[1], source.accounts[2]});
    }

    @Test
    public void add() {
        assertTrue(accountManager.add(source.accounts[3]));
    }

    @Test
    public void add1() {
        assertTrue(accountManager.add(0, source.accounts[3]));
        assertEquals(accountManager.get(0), source.accounts[3]);
        assertEquals(accountManager.get(1), source.accounts[0]);
        assertEquals(accountManager.get(2), source.accounts[1]);
    }

    @Test
    public void set() {
        assertEquals(accountManager.set(0, source.accounts[3]), source.accounts[0]);
        assertEquals(accountManager.get(0), source.accounts[3]);
        assertEquals(accountManager.get(1), source.accounts[1]);
        assertEquals(accountManager.get(2), source.accounts[2]);
    }

    @Test
    public void remove() {
        assertEquals(accountManager.remove(0), source.accounts[0]);
        assertEquals(accountManager.get(0), source.accounts[1]);
        assertEquals(accountManager.get(1), source.accounts[2]);
    }

    @Test
    public void size() {
        assertEquals(3, accountManager.size());
    }

    @Test
    public void getAccounts() {
        for (Account account : accountManager.getAccounts()) {
            assertNotNull(account);
        }
    }

    @Test
    public void getTariff() {
        assertEquals(accountManager.getTariff(source.accounts[2].getNumber()),
                source.accounts[2].getTariff());
    }

    @Test
    public void setTariff() {
        IndividualsTariff constTariff = source.accounts[1].getTariff();
        assertEquals(accountManager.setTariff(source.accounts[1].getNumber(), source.accounts[9].getTariff()),
                constTariff);
    }
}