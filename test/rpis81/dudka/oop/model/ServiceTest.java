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
        assertEquals(service.getServiceType(), Service.SERVICE_TYPES_DEFAULT);
    }

    @Test
    public void secondConstructor() {
        service = new Service(source.fServiceName, source.fServiceCost, ServiceTypes.ADDITIONAL_SERVICE);
        assertEquals(service.getName(), source.fServiceName);
        assertEquals(service.getCost(), source.fServiceCost, 0.0);
        assertEquals(service.getServiceType(), ServiceTypes.ADDITIONAL_SERVICE);
    }
}