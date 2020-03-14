package rpis81.dudka.oop.model;

import org.junit.Before;
import org.junit.Test;
import rpis81.dudka.oop.model.source.DataSource;

import static org.junit.Assert.*;

public class PersonTest {

    private DataSource source = new DataSource();
    private Person person;

    @Before
    public void firstConstructor() {
        person = new Person(source.people[0].getfName(), source.people[0].getsName());
        assertEquals(person.getfName(), source.people[0].getfName());
        assertEquals(person.getsName(), source.people[0].getsName());
    }

    @Test
    public void setfName() {
        person.setfName(source.people[1].getfName());
        assertEquals(person.getfName(), source.people[1].getfName());
    }

    @Test
    public void setsName() {
        person.setsName(source.people[1].getsName());
        assertEquals(person.getsName(), source.people[1].getsName());
    }
}