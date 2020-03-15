package rpis81.dudka.oop.model;

import org.junit.Before;
import org.junit.Test;
import rpis81.dudka.oop.model.source.DataSource;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AccountManagerTest {

    private AccountManager accountManager;
    private DataSource source;

    @Before
    public void init() {
        source = new DataSource();
        accountManager = new AccountManager(source.testAccounts);
    }

    @Test
    public void add() {
        assertTrue(accountManager.add(source.testIndividualAccounts[7]));
    }

    @Test
    public void add1() {
        assertTrue(accountManager.add(0, source.testIndividualAccounts[7]));
        assertEquals(accountManager.get(0), source.testIndividualAccounts[7]);
        assertEquals(accountManager.get(1), source.testIndividualAccounts[0]);
        assertEquals(accountManager.get(2), source.testEntityAccounts[0]);
    }

    @Test
    public void set() {
        assertEquals(accountManager.set(0, source.testEntityAccounts[7]), source.testIndividualAccounts[0]);
        assertEquals(accountManager.get(0), source.testEntityAccounts[7]);
        assertEquals(accountManager.get(1), source.testEntityAccounts[0]);
        assertEquals(accountManager.get(2), source.testIndividualAccounts[1]);
    }

    @Test
    public void remove() {
        assertEquals(accountManager.remove(0), source.testIndividualAccounts[0]);
        assertEquals(accountManager.get(0), source.testEntityAccounts[0]);
        assertEquals(accountManager.get(1), source.testIndividualAccounts[1]);
    }

    @Test
    public void size() {
        assertEquals(10, accountManager.size());
    }

    @Test
    public void getAccounts() {
        for (Account account : accountManager.getAccounts()) {
            assertNotNull(account);
        }
    }

    @Test
    public void getTariff() {
        assertEquals(accountManager.getTariff(source.testIndividualAccounts[2].getNumber()),
                source.testIndividualAccounts[2].getTariff());
    }

    @Test
    public void setTariff() {
        Tariff constTariff = source.testIndividualAccounts[1].getTariff();
        assertEquals(accountManager.setTariff(source.testIndividualAccounts[1].getNumber(), source.testIndividualAccounts[9].getTariff()),
                constTariff);
    }

    @Test
    public void getAccounts1() {
        assertEquals(10, accountManager.getAccounts(ServiceTypes.MAIL).length);
    }

    @Test
    public void getIndividualAccounts() {
        assertEquals(5, accountManager.getIndividualAccounts().length);
    }

    @Test
    public void getEntityAccounts() {
        assertEquals(5, accountManager.getEntityAccounts().length);
    }
}