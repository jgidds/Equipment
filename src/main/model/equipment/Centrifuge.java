package model.equipment;

// Represents a Centrifuge with a Feed rate (cubic metres/hr)

import model.Region;
import model.exceptions.NegativeInputException;

import java.util.Objects;

public class Centrifuge extends Equipment {
    private int feedRate;

    public Centrifuge(String equipID) {
        super(equipID);
        this.type = "Centrifuge";
    }

    public Centrifuge(String equipID, String VIN) {
        super(equipID, VIN);
        this.type = "Centrifuge";
    }

    public Centrifuge(String equipID, String VIN, Region region) {
        super(equipID, VIN, region);
        this.type = "Centrifuge";
    }

    public Centrifuge(String equipID, String VIN, String make, String model) {
        super(equipID, VIN, make, model);
        this.type = "Centrifuge";
    }

    public Centrifuge(String equipID, String VIN, String make, String model, int feedRate) {
        super(equipID, VIN, make, model);
        this.type = "Centrifuge";
        if (feedRate < 0) {
            throw new NegativeInputException();
        }
        this.feedRate = feedRate;
    }

    public void setFeedRate(int feedRate) {
        if (feedRate < 0) {
            throw new NegativeInputException();
        }
        this.feedRate = feedRate;
    }

    public int getFeedRate() {
        return feedRate;
    }


}
