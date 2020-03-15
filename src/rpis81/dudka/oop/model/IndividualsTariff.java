package rpis81.dudka.oop.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class IndividualsTariff implements Tariff, Cloneable {

    public static final int SIZE_DEFAULT = 16;

    private Service[] services;
    private int size;

    public IndividualsTariff() {
        this(SIZE_DEFAULT);
    }

    public IndividualsTariff(int size) {
        this(new Service[size]);
    }

    public IndividualsTariff(Service[] services) {
        this.services = new Service[services.length];
        toFill(services);
    }

    //Метод для заполнения списка счетов из источника
    private void toFill(Service[] sourceArray) {
        int i = 0;
        for (Service it : sourceArray) {
            if (it != null) {
                this.services[i++] = it;
            }
        }
        size = i;
    }

    public boolean add(Service service) {
        if (service == null) throw new NullPointerException();
        checkQuantity();
        this.services[size++] = service;
        return true;
    }

    public boolean add(int index, Service service) {
        if (!isValidIndex(index)) throw new IndexOutOfBoundsException();
        if (service == null) throw new NullPointerException();
        if (index > -1 && index < this.services.length){
            if (size + 1 > this.services.length) {
                increaseArray();
            }
            if (index < size) {
                int length = (size - index);
                for (int i = 0, j = size; i < length; i++) {
                    Service tmp = this.services[j];
                    this.services[j] = this.services[j-1];
                    this.services[j-1] = tmp;
                    j--;
                }
                this.services[index] = service;
                size += 1;
            } else {
                add(service);
            }
            return true;
        }
        return false;
    }

    public Service get(int index) {
        if (!isValidIndex(index)) throw new IndexOutOfBoundsException();
        return this.services[index];
    }

    public Service set(int index, Service service) {
        if (!isValidIndex(index)) throw new IndexOutOfBoundsException();
        if (service == null) throw new NullPointerException();
            Service oldService = this.services[index];
            this.services[index] = service;
            return oldService;
    }

    public Service remove(int index) {
        if (!isValidIndex(index)) throw new IndexOutOfBoundsException();
        return toRemove(index);
    }

    public Service remove(String serviceName) {
        if (serviceName == null) throw new NullPointerException();
        for (int i = 0; i < size; i++) {
            if (services[i].getName().equals(serviceName)) {
                return toRemove(i);
            }
        }
        throw new NoSuchElementException();
    }


    private Service toRemove(int index) {
        Service oldService = this.services[index];
        this.services[index] = null;
        size--;
        shiftValues(index);
        return oldService;
    }

    public int size() {
        return size;
    }

    public Service[] toArray() {
        Service[] newService = new Service[size];
        System.arraycopy(this.services, 0, newService, 0, size);
        return newService;
    }

    @Override
    public boolean remove(Service service) {
        if (service == null) throw new NullPointerException();
        return remove(service.getName()) != null;
    }

    //Проверка, что если места нет)))
    private void checkQuantity(){
        if (size == this.services.length) {
            increaseArray();
        }
    }

    //Метод, увеличивающий объем
    private void increaseArray() {
        Service[] tmp = this.services;
        this.services = new Service[size * 2];
        toFill(tmp);
    }

    //Сдвиг всех элементов влево, с перемещением элемента по индексу в самый конец
    private void shiftValues(int index){
        int length = this.services.length - 1;
        if (length - index >= 0) {
            System.arraycopy(this.services, index + 1, this.services, index, length - index);
        }
        this.services[length] = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IndividualsTariff)) return false;
        IndividualsTariff tariff = (IndividualsTariff) o;
        return size == tariff.size &&
                Arrays.equals(services, tariff.services);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(services);
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

        private int count = 0;

        @Override
        public boolean hasNext() {
            return count != size;
        }

        @Override
        public Service next() {
            if (!hasNext()) throw new NoSuchElementException();
            return get(count++);
        }
    }
}
