package rpis81.dudka.oop.model;

import java.time.LocalDate;
import java.util.Objects;

public class EntityAccount extends AbstractAccount {

    public static final Tariff ENTITY_TARIFF_DEFAULT  = initTariff();

    private static Tariff initTariff() {
        Tariff tariff = new EntityTariff();
        tariff.add(new Service());
        return tariff;
    }
    private String name;

    public EntityAccount(long number, String name) {
        this(number, name, ENTITY_TARIFF_DEFAULT, LocalDate.now());
    }

    public EntityAccount(long number, String name, Tariff tariff, LocalDate registrationDate) {
        super(number, tariff, registrationDate);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) throw new NullPointerException();
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityAccount)) return false;
        if (!super.equals(o)) return false;
        EntityAccount that = (EntityAccount) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return 53 * Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return String.format("“Entity account:\nentity: %s\n%s", name, super.toString());
    }
}
