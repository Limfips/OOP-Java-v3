package rpis81.dudka.oop.model;

public class IllegalAccountNumber extends RuntimeException {

    public IllegalAccountNumber() {
        super();
    }

    public IllegalAccountNumber(String message) {
        super(message);
    }
}
