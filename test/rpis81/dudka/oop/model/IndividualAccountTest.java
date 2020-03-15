package rpis81.dudka.oop.model;

import org.junit.Test;
import rpis81.dudka.oop.model.source.DataSource;

import static org.junit.Assert.*;

public class IndividualAccountTest {

    private IndividualAccount account;
    private DataSource source = new DataSource();

    @Test
    public void firstConstructor() {
        account = new IndividualAccount(source.testIndividualAccounts[0].getNumber(), source.people[0]);
        assertEquals(account.getNumber(), source.testIndividualAccounts[0].getNumber());
        assertEquals(account.getPerson(), source.people[0]);
        assertEquals(account.getTariff(), IndividualAccount.INDIVIDUALS_TARIFF_DEFAULT);
    }

    @Test
    public void secondConstructor() {
        account = new IndividualAccount(source.testIndividualAccounts[0].getNumber(), source.people[0], source.testTariffs[0]);
        assertEquals(account.getNumber(), source.testIndividualAccounts[0].getNumber());
        assertEquals(account.getPerson(), source.people[0]);
        assertEquals(account.getTariff(), source.testTariffs[0]);
    }

    @Test
    public void setPerson() {
        account = source.testIndividualAccounts[0];
        account.setPerson(source.testIndividualAccounts[1].getPerson());
        assertEquals(account.getPerson(), source.testIndividualAccounts[1].getPerson());
    }

    @Test
    public void setTariff() {
        account = source.testIndividualAccounts[0];
        account.setTariff(source.testIndividualAccounts[1].getTariff());
        assertEquals(account.getTariff(), source.testIndividualAccounts[1].getTariff());
    }
}