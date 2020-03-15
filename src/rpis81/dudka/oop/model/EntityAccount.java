package rpis81.dudka.oop.model;

public class EntityAccount implements Account {

    public static final Tariff ENTITY_TARIFF_DEFAULT  = initTariff();

    private static Tariff initTariff() {
        Tariff tariff = new EntityTariff();
        tariff.add(new Service());
        return tariff;
    }

    private long number;
    private String name;
    private Tariff tariff;

    public EntityAccount(long number, String name) {
        this(number, name, ENTITY_TARIFF_DEFAULT);
    }

    public EntityAccount(long number, String name, Tariff tariff) {
        this.number = number;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
