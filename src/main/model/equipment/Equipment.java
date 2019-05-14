package model.equipment;

import model.Region;
import model.Status;
import model.exceptions.EquipmentStatusException;
import model.exceptions.NullArgumentException;
import model.location.Location;
import model.exceptions.EmptyStringException;

import java.util.Objects;

// Represents a piece of Equipment in the system with an: String equipID, type, region, status and VIN (or serial number)
// Equipment must always have:
//  - equipID (unique)
//  - type
//  - region
//  - status
// The VIN field can only be null for equipment that has been ordered but not received yet
//  - once an AFE has been approved, a record for the Equipment may be created
// Make and model are optional fields

public abstract class Equipment {
    protected final String equipID;
    protected String type;
    protected String make;
    protected String model;
    protected String vin;
    protected Status status;
    protected Region region;


    // MODIFIES: this
    // EFFECTS: sets the description using the given description

    public Equipment(String equipID) {
        if (equipID == null || equipID.length() == 0) {
            throw new EmptyStringException("Equipment must have ID");
        }
        status = Status.ON_ORDER;
        this.equipID = equipID;
    }

    public Equipment(String equipID, String VIN) {
        if (VIN == null || VIN.length() == 0 || equipID == null || equipID.length() == 0) {
            throw new EmptyStringException("Equipment must have ID and VIN Number");
        }
        status = Status.ON_ORDER;
        this.equipID = equipID;
        this.vin = VIN;

        // todo!! check that VIN number and equipID are valid and unique

    }

    public Equipment(String equipID, String VIN, String make, String model) {
        if (VIN == null || VIN.length() == 0 || equipID == null || equipID.length() == 0) {
            throw new EmptyStringException("Equipment must have ID and VIN Number");
        }
        if (make == null || make.length() == 0 || model == null || model.length() == 0) {
            throw new EmptyStringException("Equipment must have make and model");
        }
        status = Status.ON_ORDER;
        this.equipID = equipID;
        this.vin = VIN;
        this.make = make;
        this.model = model;

        // todo!! check that VIN number and equipID are valid and unique

    }


    public Equipment(String equipID, String VIN, String make, String model, Region region) {
        if (VIN == null || VIN.length() == 0 || equipID == null || equipID.length() == 0) {
            throw new EmptyStringException("Equipment must have ID and VIN Number");
        }
        if (make == null || make.length() == 0 || model == null || model.length() == 0) {
            throw new EmptyStringException("Equipment must have make and model");
        }
        status = Status.ON_ORDER;
        this.equipID = equipID;
        this.vin = VIN;
        this.make = make;
        this.model = model;
        this.region = region;

        // todo!! check that VIN number and equipID are valid and unique

    }

    // MODIFIES:
    // EFFECTS:
    // REQUIRES:
    private void validateVIN() {
        // todo!!!
    }

    // MODIFIES: this
    // EFFECTS: updates the equipment Status; throws exception if status set to ON_ORDER
    public void setStatus(Status status) {
        if (status == null) {
            throw new NullArgumentException("status cannot be null");
        }
        if (status == Status.ON_ORDER) {
            throw new EquipmentStatusException("Status cannot be set to ON_ORDER");
        }
        this.status = status;
        // todo prompt for more info depending on status
    }

//    // MODIFIES: this
//    // EFFECTS: updates the equipment Location
//    public void setLocation(Location loc) {
//        this.location = loc;
//    }


    // MODIFIES: this
    // EFFECTS: updates the Reporting Region the equip belongs to
    // todo! figure out if changeRegion can be null
    public void setRegion(Region region) {
        if (region == null) {
            throw new NullArgumentException("Region cannot be null");
        }
        this.region = region;
        region.addEquipment(this);
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Status getStatus() {
        return status;
    }

    public Region getRegion() {
        return region;
    }

//    //public Location getLocation() {
//        return location;
//    }

    public String getEquipID() {
        return equipID;
    }

    public String getType() {
        return type;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    // EFFECTS: Returns True if equipment status is set to AVAILABLE
    public boolean isAvailable() {
        return status == Status.AVAILABLE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return equipID.equals(equipment.equipID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipID);
    }

}
