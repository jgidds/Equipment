package model;

import model.equipment.Centrifuge;
import model.equipment.Equipment;
import model.equipment.Excavator;
import model.exceptions.EmptyStringException;
import model.exceptions.ProvinceException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestRegion {
    private Region region;
    private Equipment equip1;

    // EFFECTS: tests constructor
    @Test
    public void testConstructor() {
        try {
            region = new Region("");
        } catch (EmptyStringException e) {
            System.out.println("Caught expected EmptyStringExcep");
        }
        Region sw = new Region("SW");
        assertEquals("SW", sw.getName());

        try {
            region = new Region(Region.Province.AB);
            assertEquals("Alberta", region.getName());
        } catch (ProvinceException e) {
            fail();
        }

    }

    // EFFECTS: test containsEquipment
    //      - equip is null, does not contain equipment and does contain equipment
    @Test
    public void testContainsEquipment() {
        region = new Region(Region.Province.BC);
        Equipment c = new Centrifuge("C1", "123456789");
        Equipment e = new Excavator("E1", "234567890");
        region.addEquipment(c);
        assertTrue(region.containsEquipment(c));
        assertFalse(region.containsEquipment(e));
        region.addEquipment(e);
        assertTrue(region.containsEquipment(e));
    }

    @Test
    void getEquipment() {
        region = new Region(Region.Province.BC);
        Equipment c = new Centrifuge("C1", "123456789");
        Equipment e = new Excavator("E1", "234567890");
        region.addEquipment(c);
        region.addEquipment(e);
        List<Equipment> listOfEquip = region.getEquipment();
        assertTrue(listOfEquip.contains(e));
        assertTrue(listOfEquip.contains(c));
    }


    @Test
    void transferEquipment() {
        region = new Region(Region.Province.BC);
        Region SK = new Region(Region.Province.SK);
        Equipment c = new Centrifuge("C1", "123456789");
        Equipment e = new Excavator("E1", "234567890");
        region.addEquipment(c);
        region.addEquipment(e);
        region.transferEquipment(c,SK);
        assertFalse(region.containsEquipment(c));
        assertTrue(SK.containsEquipment(c));
        assertEquals(SK, c.getRegion());
    }

    @Test
    void getProvince() {
        region = new Region(Region.Province.QC);
        Region NB = new Region(Region.Province.NB);
        assertEquals(Region.Province.QC, region.getProvince());
        assertEquals(Region.Province.NB, NB.getProvince());
    }


}
