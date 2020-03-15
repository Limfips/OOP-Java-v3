package rpis81.dudka.oop.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAccount)) return false;
        AbstractAccount that = (AbstractAccount) o;
        return number == that.number &&
                Objects.equals(tariff, that.tariff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, tariff);
    }

    @Override
    public String toString() {
        return String.format("number: %s\n%s", number, tariff);
    }
}
