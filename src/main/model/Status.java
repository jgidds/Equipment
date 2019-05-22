package model;

// Represents the status of a unit of Equipment
//      status can be one of:
//              AVAILABLE: ready to be rented to customer
//              REPAIR: being maintained or repaired; not available
//              COMMITTED: committed to customer (either with customer, in transit to customer or reserved)
//              RESERVE: reserved as backup for emergency (e.g. replacement for broken equip)
//              ON_ORDER: ordered from the dealer, but not yet received

public enum Status {
    AVAILABLE("Available"),
    REPAIR("Being repaired/maintained"),
    COMMITTED("Committed to customer"),
    RESERVE("Reserved as backup"),
    ON_ORDER("Ordered, not yet received");

    private String description;

    // EFFECTS: sets description of Equipment status
    Status(String description) {
        this.description = description;
    }



    // EFFECTS: returns description of status
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns description of status
    @Override
    public String toString() {
        return description;
    }



}
