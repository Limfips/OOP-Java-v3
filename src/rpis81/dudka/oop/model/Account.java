package rpis81.dudka.oop.model;

public interface Account {
    long getNumber();
    Tariff getTariff();
    void setTariff(Tariff tariff);
}
