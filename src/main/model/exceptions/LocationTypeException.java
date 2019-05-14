package model.exceptions;

public class LocationTypeException extends IllegalArgumentException {

    public LocationTypeException() {
        super();
    }

    public LocationTypeException(String arg) {
        super(arg);
    }
}
