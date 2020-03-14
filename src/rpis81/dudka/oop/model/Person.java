package rpis81.dudka.oop.model;

import java.util.Objects;

public class Person {

    private String fName;
    private String sName;

    public Person(String fName, String sName) {
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

    public boolean equals(Object object) {
        if (!(object instanceof Person)) return false;
        Person person = (Person) object;
        return this.fName.equals(person.fName) && this.sName.equals(person.sName);
    }


    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("fName='").append(fName).append('\'');
        sb.append(", sName='").append(sName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
