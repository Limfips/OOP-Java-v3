package rpis81.dudka.oop.model;

import java.io.IOException;

public class DublicateAccountNumberException extends IOException {
    public DublicateAccountNumberException() {
        super();
    }
    public DublicateAccountNumberException(String message) {
        super(message);
    }
}
