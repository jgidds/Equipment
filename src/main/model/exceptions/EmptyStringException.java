package model.exceptions;

public class EmptyStringException extends IllegalArgumentException {
    public EmptyStringException() {
    }

    public EmptyStringException(String arg) {
        super(arg);
    }
}
