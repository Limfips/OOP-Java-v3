package rpis81.dudka.oop.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
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
        if (service == null) throw new NullPointerException();
        return addLast(service);
    }

    @Override
    public boolean add(int index, Service service) {
        if (service == null) throw new NullPointerException();
        if (!isValidIndex(index)) throw new IndexOutOfBoundsException();
        return addNodeByIndex(index, new Node(service, null, null));
    }

    @Override
    public Service get(int index) {
        if (!isValidIndex(index)) throw new IndexOutOfBoundsException();
            Node node = getNodeByIndex(index);
            if (node != null) {
                return node.value;
            }
            throw new NoSuchElementException();
    }

    @Override
    public Service set(int index, Service service) {
        if (service == null) throw new NullPointerException();
        if (!isValidIndex(index)) throw new IndexOutOfBoundsException();
        return setNodeByIndex(index, service);
    }

    @Override
    public Service remove(int index) {
        if (!isValidIndex(index)) throw new IndexOutOfBoundsException();
        return removeNodeByIndex(index);
    }

    @Override
    public Service remove(String serviceName) {
        if (serviceName == null) throw new NullPointerException();
        return removeNodeByIndex(indexOf(serviceName));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Service[] toArray() {
        Service[] services = new Service[this.size];
        int index = 0;
        for (Node node = this.head; node != null; node = node.next ) {
            services[index++] = node.value;
        }
        return services;
    }

    @Override
    public boolean remove(Service service) {
        if (service == null) throw new NullPointerException();
        return remove(service.getName()) != null;
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
        if (!isValidIndex(index)) throw new IndexOutOfBoundsException();
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

    // По Заданию
    private Service removeNodeByIndex(int index) {
        if (!isValidIndex(index)) throw new IndexOutOfBoundsException();
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
        return null;
    }

    // По Заданию
    private Service setNodeByIndex(int index, Service service) {
        if (!isValidIndex(index)) throw new IndexOutOfBoundsException();
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
                Arrays.equals(toArray(), tariff.toArray());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 71 * result + Arrays.hashCode(toArray());
        return result;
    }

    @Override
    public Tariff clone() throws CloneNotSupportedException {
        return (Tariff) super.clone();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("services:\n");
        for (Service it : this) {
            sb.append(it).append('\n');
        }
        return sb.toString();
    }

    @Override
    public Iterator<Service> iterator() {
        return new ServiceIterator();
    }

    private class ServiceIterator implements Iterator<Service> {

        private Node cursor = head;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public Service next() {
            if (!hasNext()) throw new NoSuchElementException();
            Service value = cursor.value;
            cursor = cursor.next;
            return value;
        }
    }
}
