package model;

// Represents a reporting region with a list of equipment belonging to that region


import com.sun.javaws.exceptions.InvalidArgumentException;
import model.equipment.Equipment;
import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;
import model.exceptions.ProvinceException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Region {
    private String name;
    private Province province;
    private List<Equipment> equipment;  // todo maybe equipment should be stored in hashset instead of array


    // EFFECTS: Creates a Region with the given name and an empty equipment list
    public Region(String name) {
        if (name == null || name.length() == 0) {
            throw new EmptyStringException("Name cannot be null");
        }
        for (Region.Province p : Province.values()) {
            if (p.name() == name) {
                this.province = p;
            }
        }
        this.name = name;
        equipment = new ArrayList<>();
        // todo!!
    }

    // EFFECTS: Creates a Region from a province with given name and an empty equipment list
    public Region(Province prov) {
        if (prov == null) {
            throw new NullArgumentException("Province cannot be null");
        }
        boolean validProv = false;
        for (Province p : Province.values()) {
            if (prov.equals(p)) {
                validProv = true;
                break;
            }
        }
        if (!validProv) {
            throw new ProvinceException("Not a valid province");
        }
        this.province = prov;
        this.name = prov.toString();
        equipment = new ArrayList<>();

    }

    // EFFECTS: Returns the name of the Region
    public String getName() {
        return name;
    }

    // EFFECTS: Returns an UnmodifiableList of the region's equipment
    public List<Equipment> getEquipment() {
        return Collections.unmodifiableList(equipment);
    }

    // EFFECTS: Returns true if the region contains a piece of equipment
    public Boolean containsEquipment(Equipment equip) {
        if (equip == null) {
            throw new NullArgumentException("Equipment cannot be null");
        }

        return equipment.contains(equip);
    }

    // MODIFIES: this
    // EFFECTS: adds Equipment to region's equipment list, updates equipment

    public void addEquipment(Equipment equip) {
        if (equip == null) {
            throw new NullArgumentException("Equipment cannot be null");
        }
        if (!containsEquipment(equip)) {
            equipment.add(equip);
            equip.setRegion(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes Equipment from region
    private void removeEquipment(Equipment equip) {
        if (equip == null) {
            throw new NullArgumentException();
        }
        if (containsEquipment(equip)) {
            equipment.remove(equip);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes equipment from region and transfers to the new region
    public void transferEquipment(Equipment equip, Region receivingRegion) {
        if (equip == null || receivingRegion == null) {
            throw new NullArgumentException();
        }
        if (containsEquipment(equip)) {
            removeEquipment(equip);
            equip.setRegion(receivingRegion);
        }
    }

    // Represents the fixed set of Canadian provinces

    public Province getProvince() {
        return province;
    }

    /// EFFECTS: Sets the equipment region to this.
    // JSON Parsing of bidirectional relationship between equipment and region resulted in loop
    // this is a workaround, by updating the region for all Equipment in equipment
    public void correctRegion() {
        for (Equipment e : equipment) {
            e.setRegion(this);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public enum Province {

        AB("Alberta"),
        BC("British Columbia"),
        MB("Manitoba"),
        NB("New Brunswick"),
        NL("Newfoundland and Labrador"),
        NT("Northwest Territories"),
        NS("Nova Scotia"),
        NU("Nunavut"),
        ON("Ontario"),
        PE("Prince Edward Island"),
        QC("Quebec"),
        SK("Saskatchewan"),
        YK("Yukon");

        private String description;

        Province(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }
}