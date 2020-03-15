package rpis81.dudka.oop.model;

public class IndividualAccount extends AbstractAccount {

    public static final Tariff INDIVIDUALS_TARIFF_DEFAULT  = initTariff();

    private static Tariff initTariff() {
        Tariff tariff = new IndividualsTariff();
        tariff.add(new Service());
        return tariff;
    }
    private Person person;

    public IndividualAccount(long number, Person person) {
        this(number, person, INDIVIDUALS_TARIFF_DEFAULT);
    }

    public IndividualAccount(long number, Person person, Tariff tariff) {
        super(number, tariff);
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
