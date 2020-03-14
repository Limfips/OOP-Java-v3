package rpis81.dudka.oop.model;

import java.util.Objects;

public class Service {

    public static final int COST_DEFAULT = 300;
    public static final String NAME_DEFAULT = "интернет 100мб\\сек";
    
    private String name;
    private double cost;

    public Service() {
        this(NAME_DEFAULT, COST_DEFAULT);
    }

    public Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Service)) return false;
        Service service = (Service) object;
        return this.name.equals(service.name) && this.cost == service.cost;
    }


    public String toString() {
        final StringBuilder sb = new StringBuilder("Service{");
        sb.append("name='").append(name).append('\'');
        sb.append(", cost=").append(cost);
        sb.append('}');
        return sb.toString();
    }
}
