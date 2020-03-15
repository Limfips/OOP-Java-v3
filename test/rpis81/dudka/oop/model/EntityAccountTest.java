package rpis81.dudka.oop.model;

import org.junit.Test;
import rpis81.dudka.oop.model.source.DataSource;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class EntityAccountTest {

    private EntityAccount account;
    private DataSource source = new DataSource();

    @Test
    public void firstConstructor() {
        account = new EntityAccount(source.testEntityAccounts[0].getNumber(), source.names[0]);
        assertEquals(account.getNumber(), source.testEntityAccounts[0].getNumber());
        assertEquals(account.getName(), source.names[0]);
        assertEquals(account.getTariff(), EntityAccount.ENTITY_TARIFF_DEFAULT);
        assertEquals(account.getRegistrationDate(), LocalDate.now());
    }

    @Test
    public void secondConstructor() {
        account = new EntityAccount(source.testEntityAccounts[0].getNumber(),
                source.names[0], source.testTariffs[0], LocalDate.now());
        assertEquals(account.getNumber(), source.testEntityAccounts[0].getNumber());
        assertEquals(account.getName(), source.names[0]);
        assertEquals(account.getTariff(), source.testTariffs[0]);
        assertEquals(account.getRegistrationDate(), LocalDate.now());
    }
}