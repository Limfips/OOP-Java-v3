package rpis81.dudka.oop.model;

import java.util.Objects;

public class Person {

    private String fName;
    private String sName;

    public Person(String fName, String sName) {
        if (fName == null || sName == null) throw new NullPointerException();
        this.fName = fName;
        this.sName = sName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(fName, person.fName) &&
                Objects.equals(sName, person.sName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fName, sName);
    }

    public String toString() {
        return String.format("%s %s", fName, sName);
    }
}
