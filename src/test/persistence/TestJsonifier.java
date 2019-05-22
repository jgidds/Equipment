package persistence;

import model.Region;
import model.Status;
import model.equipment.Centrifuge;
import model.equipment.Equipment;
import model.equipment.Excavator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestJsonifier {
    private Jsonifier jsonifier;
    private Jsonifier jf;
    private JSONObject jsonOb;
    private JSONArray jsonAr;
    private Equipment eq1;
    private Equipment eq2;
    private Region region;

    @BeforeEach
    void setUp() {
        region = new Region(Region.Province.QC);
        eq1 = new Centrifuge("c1", "12345");
        eq1.setRegion(region);
        eq1.setStatus(Status.AVAILABLE);
        eq2 = new Excavator("e1", "45678");
        eq2.setStatus(Status.REPAIR);
        eq2.setRegion(region);
        jsonOb = new JSONObject();
        jsonAr = new JSONArray();
    }


//    @Test
//    void equipIdToJSON() {
//        String eq1ID = eq1.getEquipID();
//        jsonOb = Jsonifier.equipIdToJSON(eq1ID);
//        assertTrue(jsonOb.has("EquipmentID"));
//        System.out.println(jsonOb.toString(2));
//    }

//    @Test
//    void typetoJSON() {
//        String string = eq1.getType();
//        jsonOb = Jsonifier.typetoJSON(string);
//        assertTrue(jsonOb.has("Type"));
//    }
//
//    @Test
//    void statusToJSON() {
//        jsonOb = Jsonifier.statusToJSON(eq1.getStatus());
//        assertTrue(jsonOb.has("Status"));
//    }

    @Test
    void regionToJSON() {
        jsonOb = Jsonifier.regionToJSON(region);
        //assertTrue(jsonOb.has("Province"));
        System.out.println(jsonOb.toString(2));
    }


    @Test
    void equipToJson() {
        jsonOb = Jsonifier.equipToJson(eq1);
        assertTrue(jsonOb.has("Status"));
        assertTrue(jsonOb.has("Type"));
        assertTrue(jsonOb.has("EquipmentID"));
        assertTrue(jsonOb.has("Region"));
        System.out.println(jsonOb.toString(2));
    }

    @Test
    void equipListToJSON() {
        List<Equipment> listOfEquipment = region.getEquipment();
        jsonAr = Jsonifier.equipListToJSON(listOfEquipment);
        assertEquals(2, jsonAr.length());

    }

    @Test
    void regionListToJSON() {
        Region region2 = new Region(Region.Province.BC);
        Equipment e3 = new Centrifuge("3","12345567");
        e3.setStatus(Status.RESERVE);
        e3.setRegion(region2);
        Equipment e4 = new Excavator("e3", "adfafAdf");
        e4.setStatus(Status.AVAILABLE);
        Equipment e5 = new Excavator("Adf","13424");
        e5.setStatus(Status.COMMITTED);
        region2.addEquipment(e4);
        region2.addEquipment(e5);
        List<Region> regions = new ArrayList<>();
        regions.add(region);
        regions.add(region2);
        jsonAr = Jsonifier.regionListToJSON(regions);

    }


}