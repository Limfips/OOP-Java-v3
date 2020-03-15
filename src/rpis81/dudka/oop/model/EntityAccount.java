package rpis81.dudka.oop.model;

public class EntityAccount extends AbstractAccount {

    public static final Tariff ENTITY_TARIFF_DEFAULT  = initTariff();

    private static Tariff initTariff() {
        Tariff tariff = new EntityTariff();
        tariff.add(new Service());
        return tariff;
    }
    private String name;

    public EntityAccount(long number, String name) {
        this(number, name, ENTITY_TARIFF_DEFAULT);
    }

    public EntityAccount(long number, String name, Tariff tariff) {
        super(number, tariff);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
