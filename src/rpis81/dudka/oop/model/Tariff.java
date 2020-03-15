package rpis81.dudka.oop.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.time.temporal.ChronoUnit.DAYS;

public interface Tariff extends Iterable<Service> {
    boolean add(Service service);
    boolean add(int index, Service service);
    Service get(int index);
    default Service get(String serviceName) {
        if (serviceName == null) throw new NullPointerException();
        for (Service it: this) {
            if (it != null) {
                if (it.getName().equals(serviceName)){
                    return it;
                }
            }
        }
        throw new NoSuchElementException();
    }
    default boolean hasService(String serviceName) {
        if (serviceName == null) throw new NullPointerException();
        try {
            get(serviceName);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
    Service set(int index, Service service);
    Service remove(int index);
    Service remove(String serviceName);
    int size();
    Service[] getServices();
    default Service[] getServices(ServiceTypes type) {
        if (type == null) throw new NullPointerException();
        Service[] services = new Service[size()];
        int newSize = 0;
        for (Service it : this) {
            if (it.getServiceType().equals(type)) {
                services[newSize++] = it;
            }
        }
        Service[] result = new Service[newSize];
        System.arraycopy(services, 0, result, 0, newSize);
        return result;
    }
    default Service[] sortedServicesByCost() {
        Service[] accounts = getServices();
        Arrays.sort(accounts);
        return accounts;
    }
    default double cost() {
        double cost = 0;
        long days;
        for (Service service : getServices()) {
            cost += service.getCost();
            days = DAYS.between(service.getActivationDate(), LocalDate.now());
            if (days < 30) {
                cost += (days * cost) / 30;
            }
        }
        return cost;
    }
    boolean remove(Service service);
    default int indexOf(String serviceName) {
        if (serviceName == null) throw new NullPointerException();
        int k = 0;
        for (Service it : this) {
            if (it.getName().equals(serviceName)) {
                return k;
            }
            k += 1;
        }
        return -1;
    }
    default int lastIndexOf(Service service) {
        if (service == null) throw new NullPointerException();
        int k = -1;
        Iterator<Service> iterator = this.iterator();
        for (int i = 0; i < size(); i++) {
            if (iterator.next().equals(service)) {
                k = i;
            }
        }
        return k;
    }

    default boolean isValidIndex(int index) {
        return index > -1 && index < size();
    }
}
