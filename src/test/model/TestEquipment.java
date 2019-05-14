package model;

import model.equipment.Centrifuge;
import model.equipment.Equipment;
import model.exceptions.DuplicateException;
import model.exceptions.EquipmentStatusException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEquipment {
    private Equipment eq;
    private Equipment eq3;

    @BeforeEach
    public void setUp() {
        eq3 = new Centrifuge("3", "234567890", "Lynx", "1000", 200);
    }


    @Test
    public void testCentrifuge() {
        eq = new Centrifuge("1","123456789");
        assertEquals("123456789",eq.getVin());
        assertEquals("1",eq.getEquipID());

        try {
            Equipment eq2 = new Centrifuge("1", "123456789");
        } catch (DuplicateException e) {
            System.out.println("Caught expected DuplicateException");
        }


        Equipment eq4 = new Centrifuge("4", "4567890123", "Lynx", "1000");
        assertEquals("4", eq4.getEquipID());
        assertEquals("4567890123", eq4.getVin());
        assertEquals("Lynx", eq4.getMake());
        assertEquals("1000",eq4.getModel());
    }

    @Test
    void setStatus() {
        eq = new Centrifuge("c1", "1234567890");
        for (Status s : Status.values()) {
            if (s != Status.ON_ORDER) {
                eq.setStatus(s);
                assertEquals(s, eq.getStatus());
            }
        }
        try {
            eq.setStatus(Status.ON_ORDER);
        } catch (EquipmentStatusException e) {
            System.out.println("Caught expected exception");
        }
    }

    @Test
    void setRegion() {
        Region sw = new Region("SW");
        eq3.setRegion(sw);
        assertEquals(sw, eq3.getRegion());
        Region nw = new Region("NW");
        eq3.setRegion(nw);
        assertEquals(nw, eq3.getRegion());

    }

    @Test
    void getVin() {
        assertEquals("234567890", eq3.getVin());
    }


    @Test
    void getRegion() {
        Region sw = new Region("SW");
        eq3.setRegion(sw);
        assertEquals(sw, eq3.getRegion());
    }

    @Test
    void getEquipID() {
        assertEquals("3", eq3.getEquipID());
    }

    @Test
    public void getFeedRate() {
        assertEquals(200, ((Centrifuge) eq3).getFeedRate());
    }

    @Test
    void getType() {
        eq3.setStatus(Status.AVAILABLE);
        assertTrue(eq3.isAvailable());
    }

    @Test
    void getMake() {
        assertEquals("Lynx", eq3.getMake());
    }

    @Test
    void getModel() {
        assertEquals("1000",eq3.getModel());
    }

    @Test
    void isAvailable() {
        assertFalse(eq3.isAvailable());
        eq3.setStatus(Status.AVAILABLE);
        assertTrue(eq3.isAvailable());
    }



}
