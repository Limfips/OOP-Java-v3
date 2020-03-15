package rpis81.dudka.oop.model;

public abstract class AbstractAccount implements Account {

    private long number;
    private Tariff tariff;

    protected AbstractAccount(long number, Tariff tariff) {
        this.number = number;
        this.tariff = tariff;
    }

    @Override
    public long getNumber() {
        return this.number;
    }

    @Override
    public Tariff getTariff() {
        return this.tariff;
    }

    @Override
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }
}
