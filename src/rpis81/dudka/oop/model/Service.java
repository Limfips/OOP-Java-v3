package rpis81.dudka.oop.model;

import java.time.LocalDate;
import java.util.Objects;

public final class Service implements Cloneable, Comparable<Service> {

    public static final int COST_DEFAULT = 300;
    public static final String NAME_DEFAULT = "интернет 100мб\\сек";
    public static final ServiceTypes SERVICE_TYPES_DEFAULT = ServiceTypes.INTERNET;
    public static final LocalDate ACTIVATION_DATE_DEFAULT = LocalDate.now();
    
    private final String name;
    private final double cost;
    private final ServiceTypes serviceType;
    private final LocalDate activationDate;

    public Service() {
        this(NAME_DEFAULT, COST_DEFAULT, SERVICE_TYPES_DEFAULT, ACTIVATION_DATE_DEFAULT);
    }

    public Service(String name, double cost, ServiceTypes serviceType, LocalDate activationDate) {
        if (name == null || serviceType == null || activationDate == null) throw new NullPointerException();
        if (activationDate.isAfter(ACTIVATION_DATE_DEFAULT)) throw new IllegalArgumentException();
        this.name = name;
        this.cost = cost;
        this.serviceType = serviceType;
        this.activationDate = activationDate;
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

    public LocalDate getActivationDate() {
        return activationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        Service service = (Service) o;
        return Double.compare(service.cost, cost) == 0 &&
                Objects.equals(name, service.name) &&
                serviceType == service.serviceType &&
                Objects.equals(activationDate, service.activationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost, serviceType, activationDate);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
            return String.format("name %s \\ cost %fр.", name, cost);
    }

    @Override
    public int compareTo(Service o) {
        return ((int) (this.cost - o.getCost()));
    }
}
