package rpis81.dudka.oop.model;

import java.util.Objects;

public final class Service implements Cloneable {

    public static final int COST_DEFAULT = 300;
    public static final String NAME_DEFAULT = "интернет 100мб\\сек";
    public static final ServiceTypes SERVICE_TYPES_DEFAULT = ServiceTypes.INTERNET;
    
    private final String name;
    private final double cost;
    private final ServiceTypes serviceType;

    public Service() {
        this(NAME_DEFAULT, COST_DEFAULT, SERVICE_TYPES_DEFAULT);
    }

    public Service(String name, double cost, ServiceTypes serviceType) {
        this.name = name;
        this.cost = cost;
        this.serviceType = serviceType;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public ServiceTypes getServiceType() {
        return serviceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        Service service = (Service) o;
        return Double.compare(service.cost, cost) == 0 &&
                Objects.equals(name, service.name) &&
                serviceType == service.serviceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost, serviceType);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
            return String.format("name %s \\ cost %fр.", name, cost);
    }
}
