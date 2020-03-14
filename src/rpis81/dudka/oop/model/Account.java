package rpis81.dudka.oop.model;

public class Account {

    public static final IndividualsTariff INDIVIDUALS_TARIFF_DEFAULT  = initTariff();

    private static IndividualsTariff initTariff() {
        IndividualsTariff tariff = new IndividualsTariff();
        tariff.add(new Service());
        return tariff;
    }

    private long number;
    private Person person;
    private IndividualsTariff tariff;

    public Account(long number, Person person) {
        this(number, person, INDIVIDUALS_TARIFF_DEFAULT);
    }

    public Account(long number, Person person, IndividualsTariff tariff) {
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

    public IndividualsTariff getTariff() {
        return tariff;
    }

    public void setTariff(IndividualsTariff tariff) {
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
