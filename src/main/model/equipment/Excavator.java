package model.equipment;

// Represents an Excavator with a maximum Dig Depth (m)

import model.Region;
import model.exceptions.NegativeInputException;

public class Excavator extends Equipment{
    private static final float STANDARD_DIG_DEPTH = 3; // in meters
    private float maxDigDepth;

    public Excavator(String equipID, String VIN, String make, String model, float maxDepth) {
        super(equipID, VIN, make, model);
        this.type = "Excavator";
        if (maxDigDepth < 0) {
            throw new NegativeInputException();
        }
        this.maxDigDepth = maxDepth;
    }
    public Excavator(String equipID, String VIN) {
        super(equipID, VIN);
        this.type = "Excavator";
        if (maxDigDepth < 0) {
            throw new NegativeInputException();
        }
        this.maxDigDepth = STANDARD_DIG_DEPTH;
    }

    public Excavator(String equipID, String VIN, Region region) {
        super(equipID, VIN, region);
        this.type = "Excavator";
        if (maxDigDepth < 0) {
            throw new NegativeInputException();
        }

        this.maxDigDepth = STANDARD_DIG_DEPTH;
    }


    public Excavator(String equipID, String VIN, String make, String model, Region region, Float maxDigDepth) {
        super(equipID, VIN, make, model, region);
        this.maxDigDepth = maxDigDepth;
    }

    public Excavator(String equipID, Float maxDigDepth) {
        super(equipID);
        this.maxDigDepth = maxDigDepth;
    }

    public Excavator(String equipID, String VIN, Float maxDigDepth) {
        super(equipID, VIN);
        this.maxDigDepth = maxDigDepth;
    }

    public Float getMaxDigDepth() {
        return maxDigDepth;
    }

    public void setMaxDigDepth(Float maxDigDepth) {
        this.maxDigDepth = maxDigDepth;
    }


}
