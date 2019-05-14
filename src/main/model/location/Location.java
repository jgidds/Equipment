package model.location;

// Represents a location where equipment may be
// type can be one of:
//      - "Yard" Equipment Yard
//      - "Customer" Customer Site
//      - "Vendor" Repair shop (vendor)


import model.exceptions.EmptyStringException;
import model.exceptions.LocationTypeException;

public abstract class Location {
    protected String type;

    public Location(String type) {
        if (type == null || type.length() == 0) {
            throw new EmptyStringException("type cannot be blank");
        }
        if (type == "Yard" || type == "Customer" || type == "Vendor") {
            this.type = type;
        } else {
            throw new LocationTypeException();
        }
    }

    public String getType() {
        return type;
    }
}
