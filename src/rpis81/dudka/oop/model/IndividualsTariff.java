package rpis81.dudka.oop.model;

import java.util.Arrays;

public class IndividualsTariff implements Tariff {

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
        checkQuantity();
        this.services[size++] = service;
        return true;
    }

    public boolean add(int index, Service service) {
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
        if (index > -1 && index < size){
            return this.services[index];
        }
        return null;
    }

    public Service get(String serviceName) {
        Service service;
        for (int i = 0; i < size; i++){
            service = this.services[i];
            if (service.getName().equals(serviceName)){
                return service;
            }
        }
        return null;
    }

    public boolean hasService(String serviceName) {
        Service service = get(serviceName);
        return service != null;
    }

    public Service set(int index, Service service) {
        if (index > -1 && index < size){
            Service oldService = this.services[index];
            this.services[index] = service;
            return oldService;
        }
        return null;
    }

    public Service remove(int index) {
        if (index > -1 && index < size){
            return toRemove(index);
        }
        return null;
    }

    public Service remove(String serviceName) {
        for (int i = 0; i < size; i++) {
            if (services[i].getName().equals(serviceName)) {
                return toRemove(i);
            }
        }
        return null;
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

    public Service[] getServices() {
        Service[] newService = new Service[size];
        System.arraycopy(this.services, 0, newService, 0, size);
        return newService;
    }

    public Service[] sortedServicesByBalance() {
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

    public double cost() {
        double cost = 0;
        for (Service service : getServices()) {
            cost += service.getCost();
        }
        return cost;
    }

    public int getIndex(String serviceName) {
        for (int i = 0; i < size; i++) {
            if (this.services[i].getName().equals(serviceName)) {
                return i;
            }
        }
        return -1;
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
    public String toString() {
        final StringBuilder sb = new StringBuilder("IndividualsTariff{");
        sb.append("services=").append(Arrays.toString(services));
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}
