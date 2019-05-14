package model.location;

import model.exceptions.EmptyStringException;

public class Yard extends Location{
    private String name;

    public Yard(String name) {
        super("Yard");
        if (name == null || name.length() == 0) {
            throw new EmptyStringException();
        }
        this.name = name;
    }

}
