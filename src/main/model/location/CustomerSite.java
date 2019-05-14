package model.location;

// Represents a Customer Site
// Must include customerName and siteDescription; customerID and siteID are optional

import model.exceptions.EmptyStringException;

public class CustomerSite extends Location {
    private String customerID;
    private String customerName;
    private String siteDescription;
    private String siteID;

    public CustomerSite(String customerName, String siteDescription) {
        super("Customer");
        if (customerName == null || siteDescription == null || customerName.length() == 0 ||
                siteDescription.length()== 0) {
            throw new EmptyStringException();
        }
        this.customerName = customerName;
        this.siteDescription = siteDescription;
    }

    // EFFECTS: updates customerID
    //          throws EmptyStringException if input null
    public void addCustomerID(String customerID) {
        if (customerID == null || customerID.length() == 0) {
            throw new EmptyStringException();
        }
        this.customerID = customerID;
    }

    // EFFECTS: updates siteID
    //          throws EmptyStringException if input null
    public void addSiteID(String siteID) {
        if (siteID == null || siteID.length() == 0) {
            throw new EmptyStringException();
        }
        this.siteID = siteID;
    }


}
