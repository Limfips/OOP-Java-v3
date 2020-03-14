package rpis81.dudka.oop.model;

import org.junit.Test;
import rpis81.dudka.oop.model.source.DataSource;

import static org.junit.Assert.*;

public class ServiceTest {

    private DataSource source = new DataSource();
    private Service service;

    @Test
    public void firstConstructor() {
        service = new Service();
        assertEquals(service.getName(), Service.NAME_DEFAULT);
        assertEquals(service.getCost(), Service.COST_DEFAULT, 0.0);
    }

    @Test
    public void secondConstructor() {
        service = new Service(source.fServiceName, source.fServiceCost);
        assertEquals(service.getName(), source.fServiceName);
        assertEquals(service.getCost(), source.fServiceCost, 0.0);
    }

    @Test
    public void setName() {
        service = new Service();
        service.setName(source.sServiceName);
        assertEquals(service.getName(), source.sServiceName);
    }

    @Test
    public void setCost() {
        service = new Service();
        service.setCost(source.sServiceCost);
        assertEquals(service.getCost(), source.sServiceCost, 0.0);
    }
}