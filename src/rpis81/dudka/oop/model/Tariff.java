package rpis81.dudka.oop.model;

import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public interface Tariff extends Iterable<Service>, Collection<Service> {
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
    default boolean contains(String serviceName) {
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
    Service[] toArray();
    default Collection<Service> toArray(ServiceTypes type) {
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
        return Arrays.asList(result);
    }
    default List<Service> sortedServicesByCost() {
        Service[] services = toArray();
        Arrays.sort(services);
        return Arrays.asList(services);
    }
    default double cost() {
        double cost = 0;
        long days;
        for (Service service : toArray()) {
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

    @Override
    default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    default boolean contains(Object o) {
        if (o == null) throw new NullPointerException();
        return contains(((Service) o).getName());
    }

    @Override
    default <T> T[] toArray(T[] a) {
        return (T[]) toArray();
    }

    @Override
    default boolean remove(Object o) {
        if (o == null) throw new NullPointerException();
        if (!(o instanceof Service)) throw new ClassCastException();
        return remove(((Service) o));
    }

    @Override
    default boolean containsAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        boolean result = true;
        for (Object it : c) {
            result = result && contains(it);
        }
        return result;
    }

    @Override
    default boolean addAll(Collection<? extends Service> c) {
        if (c == null) throw new NullPointerException();
        boolean result = true;
        for (Service it : c) {
            result = result && add(it);
        }
        return result;
    }

    @Override
    default boolean removeAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        boolean result = true;
        List<Service> services = new ArrayList<>();

        for (Object it : c) {
            result = result && add((Service) it);
        }
        return result;
    }

    @Override
    default boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    default void clear() {

    }
}
