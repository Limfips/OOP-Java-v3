package rpis81.dudka.oop.model;

import org.junit.Test;
import rpis81.dudka.oop.model.source.DataSource;

import static org.junit.Assert.*;

public class AccountTest {

    private Account account;
    private DataSource source = new DataSource();

    @Test
    public void firstConstructor() {
        account = new Account(source.accounts[0].getNumber(), source.people[0]);
        assertEquals(account.getNumber(), source.accounts[0].getNumber());
        assertEquals(account.getPerson(), source.people[0]);
        assertEquals(account.getTariff(), Account.INDIVIDUALS_TARIFF_DEFAULT);
    }

    @Test
    public void secondConstructor() {
        account = new Account(source.accounts[0].getNumber(), source.people[0], source.testIndividualsTariffs[0]);
        assertEquals(account.getNumber(), source.accounts[0].getNumber());
        assertEquals(account.getPerson(), source.people[0]);
        assertEquals(account.getTariff(), source.testIndividualsTariffs[0]);
    }

    @Test
    public void setPerson() {
        account = source.accounts[0];
        account.setPerson(source.accounts[1].getPerson());
        assertEquals(account.getPerson(), source.accounts[1].getPerson());
    }

    @Test
    public void setTariff() {
        account = source.accounts[0];
        account.setTariff(source.accounts[1].getTariff());
        assertEquals(account.getTariff(), source.accounts[1].getTariff());
    }
}