package model.exceptions;

public class DuplicateException extends IllegalArgumentException {
    public DuplicateException() {
        super();
    }

    public DuplicateException(String arg) {
        super(arg);
    }
}
