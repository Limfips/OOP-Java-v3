package rpis81.dudka.oop.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public abstract class AbstractAccount implements Account {

    public static boolean isValidNumber(long number) {
        return number >= 1000000000001L && number <= 999999999999999L;
    }

    private long number;
    private Tariff tariff;
    private LocalDate registrationDate;

    protected AbstractAccount(long number, Tariff tariff, LocalDate registrationDate) {
        if (registrationDate.isAfter(LocalDate.now())) throw new IllegalArgumentException();
        if (!isValidNumber(number)) throw new IllegalAccountNumber();
        this.number = number;
        setTariff(tariff);
        this.registrationDate = registrationDate;
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
        if (tariff == null) throw new NullPointerException();
        this.tariff = tariff;
    }

    @Override
    public LocalDate getRegistrationDate() {
        return this.registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAccount)) return false;
        AbstractAccount that = (AbstractAccount) o;
        return number == that.number &&
                Objects.equals(tariff, that.tariff) &&
                Objects.equals(registrationDate, that.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, tariff, registrationDate);
    }

    @Override
    public String toString() {
        return String.format("number: %s\n%s", number, tariff);
    }
}
