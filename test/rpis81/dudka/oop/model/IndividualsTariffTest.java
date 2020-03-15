package rpis81.dudka.oop.model;

import org.junit.Before;
import org.junit.Test;
import rpis81.dudka.oop.model.source.DataSource;

import java.util.Arrays;

import static org.junit.Assert.*;

public class IndividualsTariffTest {

    private IndividualsTariff individualsTariff;
    private DataSource source;

    @Before
    public void init() {
        source = new DataSource();
        individualsTariff = (IndividualsTariff) source.testTariffs[0];
    }

    @Test
    public void add() {
        assertTrue(individualsTariff.add(source.testServices[20]));
    }

    @Test
    public void add1() {
        assertTrue(individualsTariff.add(0, source.testServices[20]));
        assertEquals(individualsTariff.get(0), source.testServices[20]);
        assertEquals(individualsTariff.get(1), source.testServices[0]);
        assertEquals(individualsTariff.get(2), source.testServices[1]);
    }

    @Test
    public void get() {
        assertEquals(individualsTariff.get(source.testServices[0].getName()), source.testServices[0]);
    }

    @Test
    public void get1() {
        assertEquals(individualsTariff.get(0), source.testServices[0]);
    }

    @Test
    public void hasAccount() {
        assertTrue(individualsTariff.hasService(source.testServices[0].getName()));
    }

    @Test
    public void set() {
        assertEquals(individualsTariff.set(0, source.testServices[20]), source.testServices[0]);
        assertEquals(individualsTariff.get(0), source.testServices[20]);
    }

    @Test
    public void remove() {
        assertEquals(individualsTariff.remove(0), source.testServices[0]);
        assertEquals(individualsTariff.get(0), source.testServices[1]);
        assertEquals(individualsTariff.get(1), source.testServices[2]);
        assertEquals(individualsTariff.get(2), source.testServices[3]);
    }

    @Test
    public void size() {
        assertEquals(source.testTariffs[0].size(), individualsTariff.size());
    }

    @Test
    public void cost() {
        double cost = 0;
        for (Service it : source.testTariffs[0].getServices()) {
            cost += it.getCost();
        }
        assertEquals(cost, individualsTariff.cost(), 0.0);
    }

    @Test
    public void getServices1() {
        assertEquals(1, individualsTariff.getServices(ServiceTypes.INTERNET).length);
    }

    @Test
    public void iteratorBLAD() {
        int k = 0;
        for (Service it : individualsTariff) {
            assertEquals(it, source.testTariffs[0].get(k++));
        }
    }
}