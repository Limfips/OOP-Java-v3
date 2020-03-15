package rpis81.dudka.oop.model;

public interface Tariff {
    boolean add(Service service);
    boolean add(int index, Service service);
    Service get(int index);
    Service get(String serviceName);
    boolean hasService(String serviceName);
    Service set(int index, Service service);
    Service remove(int index);
    Service remove(String serviceName);
    int size();
    Service[] getServices();
    Service[] sortedServicesByBalance();
    double cost();
    int getIndex(String serviceName);
    Service[] getServices(ServiceTypes type);
}
