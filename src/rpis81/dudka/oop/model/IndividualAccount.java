package rpis81.dudka.oop.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IndividualAccount)) return false;
        if (!super.equals(o)) return false;
        IndividualAccount that = (IndividualAccount) o;
        return Objects.equals(person, that.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), person.hashCode());
    }

    @Override
    public String toString() {
        return String.format("“Entity account:\nholder: %s\n%s", person.toString(), super.toString());
    }
}
