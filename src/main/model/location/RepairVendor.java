package model.location;

// Represents a Vendor shop where a piece of equipment may be repaired


import model.exceptions.EmptyStringException;

public class RepairVendor extends Location{
    private String vendorName;
    private String vendorID;
    private String description;

    public RepairVendor(String vendorName, String vendorID, String description) {
        super("Vendor");
        if (vendorName == null || vendorID == null || description == null || vendorName.length() == 0 ||
                vendorID.length() == 0 || description.length() == 0) {
            throw new EmptyStringException();
        }
        this.vendorName = vendorName;
        this.vendorID = vendorID;
        this.description = description;
    }


}
