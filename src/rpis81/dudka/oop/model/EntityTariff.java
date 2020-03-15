package rpis81.dudka.oop.model;

import java.util.Arrays;
import java.util.Objects;

public class EntityTariff implements Tariff {

    private static final int SIZE_DEFAULT = 8;

    private Node head;
    private Node tail;
    private int size;

    public EntityTariff() {
        init();
    }

    public EntityTariff(Service[] services) {
        this();
        addAll(services);
    }

    private void init() {
        this.head = new Node(null, null, null);
        this.tail = new Node(null, null, null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        size = 0;
    }

    private boolean addAll(Service[] services) {
        boolean result = true;
        int numNew = services.length;
        if (numNew == 0) return true;
        for (Service it: services) {
            result = result && add(it);
        }
        return result;
    }

    @Override
    public boolean add(Service service) {
        return addLast(service);
    }

    @Override
    public boolean add(int index, Service service) {
        return addNodeByIndex(index, new Node(service, null, null));
    }

    @Override
    public Service get(int index) {
        if (checkIndex(index)) {
            Node node = getNodeByIndex(index);
            if (node != null) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public Service get(String serviceName) {
        for (Node node = this.head; node != null; node = node.next) {
            if (node.value.getName().equals(serviceName)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public boolean hasService(String serviceName) {
        return get(serviceName) != null;
    }

    @Override
    public Service set(int index, Service service) {
        if (checkIndex(index) && service != null) {
            return setNodeByIndex(index, service);
        }
        return null;
    }

    @Override
    public Service remove(int index) {
        return removeNodeByIndex(index);
    }

    @Override
    public Service remove(String serviceName) {
        return removeNodeByIndex(indexOf(serviceName));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Service[] getServices() {
        Service[] services = new Service[this.size];
        int index = 0;
        for (Node node = this.head; node != null; node = node.next ) {
            services[index++] = node.value;
        }
        return services;
    }

    @Override
    public Service[] sortedServicesByCost() {
        Service[] services = getServices();
        for (int i = size-1; i > 0; i--){
            for (int j = 0; j < i; j++){
                if( services[j].getCost() > services[j+1].getCost() ){
                    Service tmp = services[j];
                    services[j] = services[j+1];
                    services[j+1] = tmp;
                }
            }
        }
        return services;
    }

    @Override
    public double cost() {
        double cost = 0;
        for (Service service : getServices()) {
            cost += service.getCost();
        }
        return cost;
    }

    @Override
    public boolean remove(Service service) {
        return remove(service.getName()) != null;
    }

    @Override
    public int indexOf(String serviceName) {
        Service[] services = getServices();
        for (int i = 0; i < size; i++) {
            if (services[i] != null) {
                if (services[i].getName().equals(serviceName)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Service service) {
        Service[] services = getServices();
        int k = -1;
        for (int i = 0; i < size; i++) {
            if (services[i].equals(service)) {
                k = i;
            }
        }
        return k;
    }

    @Override
    public Service[] getServices(ServiceTypes type) {
        Service[] services = new Service[size];
        int newSize = 0;
        for (Service it : getServices()) {
            if (it.getServiceType().equals(type)) {
                services[newSize++] = it;
            }
        }
        Service[] result = new Service[newSize];
        System.arraycopy(services, 0, result, 0, newSize);
        return result;
    }

    // По Заданию
    private boolean addLast(Service service) {
        if (service != null) {
            Node newNode = new Node(service, null, null);
            if (this.head.value == null) {
                this.head = newNode;
                this.tail = newNode;
                this.head.next = this.tail;
                this.tail.prev = this.head;
                size += 1;
                return true;
            }
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
            size += 1;
            return true;
        }
        return false;
    }

    // По Заданию
    private Node getNodeByIndex(int index) {
//        Node resultNode = null;
//        int count = 0;
//        for (Node node = this.head.next; node != this.head; node = node.next) {
//            if (count++ == index) {
//                resultNode = node;
//                break;
//            }
//        }
//        return resultNode;
        int count = 0;
        for (Node node = this.head; node != null; node = node.next ) {
            if (index == count++) {
                return node;
            }
        }
        return null;
    }

    // По Заданию
    private boolean addNodeByIndex(int index, Node addNode) {
        if (checkIndex(index)) {
            if (index == 0) {
                addNode.prev = null;
                addNode.next = this.head;
                this.head = addNode;
                size += 1;
                return true;
            }
            if (index == size - 1) {
                addNode.next = null;
                addNode.prev = this.tail;
                this.tail = addNode;
                size += 1;
                return true;
            }
            getNodeByIndex(index).prev = addNode;
            getNodeByIndex(index - 1).next = addNode;
            return true;
        }
        return false;
    }

    // По Заданию
    private Service removeNodeByIndex(int index) {
        if (checkIndex(index)) {
            Node removeNode = getNodeByIndex(index);
            if (removeNode != null) {
                if (removeNode.value.equals(getFirst())) {
                    this.head = this.head.next;
                    this.head.prev = null;
                    size -= 1;
                    return removeNode.value;
                }
                if (removeNode.value.equals(getLast())) {
                    removeNode.prev.next = null;
                    this.tail = removeNode;
                    size -= 1;
                    return removeNode.value;
                }
                removeNode.next.prev = removeNode.prev;
                removeNode.prev.next = removeNode.next;
                size -= 1;
                return  removeNode.value;
            }
        }
        return null;
    }

    // По Заданию
    private Service setNodeByIndex(int index, Service service) {
        Service oldValue = null;
        Node node = getNodeByIndex(index);
        if (node != null) {
            oldValue = node.value;
            node.value = service;
        }
        return oldValue;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private boolean checkIndex(int index) {
        return (index > -1 && index < this.size);
    }

    public Service getFirst() {
        return this.head.value;
    }

    public Service getLast() {
        return this.tail.value;
    }

    private class Node {
        private Node next;
        private Node prev;
        private Service value;

        Node(Service value, Node prev, Node next) {
            this.next = next;
            this.value = value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IndividualsTariff)) return false;
        IndividualsTariff tariff = (IndividualsTariff) o;
        return size == tariff.size() &&
                Arrays.equals(getServices(), tariff.getServices());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 71 * result + Arrays.hashCode(getServices());
        return result;
    }

    @Override
    public Tariff clone() throws CloneNotSupportedException {
        return (Tariff) super.clone();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("services:\n");
        for (Service it : getServices()) {
            sb.append(it).append('\n');
        }
        return sb.toString();
    }
}
