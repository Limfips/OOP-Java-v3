package rpis81.dudka.oop.model;

public final class Service {

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

    public boolean equals(Object object) {
        if (!(object instanceof Service)) return false;
        Service service = (Service) object;
        return this.name.equals(service.name) && this.cost == service.cost;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Service{");
        sb.append("name='").append(name).append('\'');
        sb.append(", cost=").append(cost);
        sb.append(", serviceType=").append(serviceType);
        sb.append('}');
        return sb.toString();
    }
}
