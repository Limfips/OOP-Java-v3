package rpis81.dudka.oop.model;

import org.junit.Before;
import org.junit.Test;
import rpis81.dudka.oop.model.source.DataSource;

import java.util.Arrays;

import static org.junit.Assert.*;

public class EntityTariffTest {

    private EntityTariff entity;
    private DataSource source;
    private int startIndex;

    @Before
    public void init() {
        source = new DataSource();
        startIndex = source.testSizeIndividualsTariffs * 5;
        entity = (EntityTariff) source.entityTariffs[0];
    }

    @Test
    public void add() {
        assertTrue(entity.add(source.testServices[20]));
        assertEquals(source.entityTariffs[0].getServices()[0], entity.getFirst());
        assertEquals(source.testServices[20], entity.getLast());
    }

    @Test
    public void add1() {
        assertTrue(entity.add(0, source.testServices[20]));
        assertEquals(entity.get(0), source.testServices[20]);
        assertEquals(entity.get(1), source.testServices[startIndex]);
        assertEquals(entity.get(2), source.testServices[startIndex + 1]);
    }

    @Test
    public void get() {
        assertEquals(entity.get(source.testServices[startIndex + 2].getName()), source.testServices[startIndex + 2]);
    }

    @Test
    public void get1() {
        assertEquals(entity.get(0), source.testServices[startIndex]);
    }

    @Test
    public void hasService() {
        assertTrue(entity.hasService(source.testServices[startIndex].getName()));
    }

    @Test
    public void set() {
        assertEquals(entity.set(0, source.testServices[20]), source.testServices[startIndex]);
        assertEquals(entity.get(0), source.testServices[20]);
    }

    @Test
    public void remove() {
        assertEquals(entity.remove(0), source.testServices[startIndex]);
        assertEquals(entity.get(0), source.testServices[startIndex + 1]);
        assertEquals(entity.get(1), source.testServices[startIndex + 2]);
        assertEquals(entity.get(2), source.testServices[startIndex + 3]);
    }

    @Test
    public void remove1() {
        assertEquals(entity.remove(source.testServices[startIndex].getName()), source.testServices[startIndex]);
        assertEquals(entity.get(0), source.testServices[startIndex + 1]);
        assertEquals(entity.get(1), source.testServices[startIndex + 2]);
        assertEquals(entity.get(2), source.testServices[startIndex + 3]);
    }

    @Test
    public void size() {
        assertEquals(source.entityTariffs[1].size(), entity.size());
    }

    @Test
    public void getServices1() {
        assertEquals(1, entity.getServices(ServiceTypes.INTERNET).length);
    }
}