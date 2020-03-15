package rpis81.dudka.oop.model;

public class IndividualAccount implements Account {

    public static final Tariff INDIVIDUALS_TARIFF_DEFAULT  = initTariff();

    private static Tariff initTariff() {
        Tariff tariff = new IndividualsTariff();
        tariff.add(new Service());
        return tariff;
    }

    private long number;
    private Person person;
    private Tariff tariff;

    public IndividualAccount(long number, Person person) {
        this(number, person, INDIVIDUALS_TARIFF_DEFAULT);
    }

    public IndividualAccount(long number, Person person, Tariff tariff) {
        this.number = number;
        this.person = person;
        this.tariff = tariff;
    }

    public long getNumber() {
        return number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("number=").append(number);
        sb.append(", person=").append(person);
        sb.append(", tariff=").append(tariff);
        sb.append('}');
        return sb.toString();
    }
}
