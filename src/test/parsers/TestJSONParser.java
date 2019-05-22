package parsers;

import model.Region;
import model.equipment.Centrifuge;
import model.equipment.Equipment;
import model.equipment.Excavator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Jsonifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


class TestJSONParser {
    private JSONParser jsonParser;
    private Region BC;
    private Region region;
    //private Equipment e;
    private Region bc;

    @BeforeEach
    void setUp(){
        jsonParser = new JSONParser();
        BC = new Region(Region.Province.BC);
    }

    // test parseEquipArray for an array of size = 1
    @Test
    public void testParseEquip() {
        // Equipment objects parsed from JsonArray of Equip (no Region) will have
        // null Region field, which is fine for testing since won't occur in program
        Equipment c1 = new Centrifuge("c1","1234567890", BC);
        List<Equipment> equipments = new ArrayList<>();
        equipments.add(c1);

        JSONArray jsonEquipList = Jsonifier.equipListToJSON(equipments);
        String jsonEquipListAsString = jsonEquipList.toString(2);
        List<Equipment> equipmentsParsed = jsonParser.parseEquipArray(jsonEquipListAsString);
        assertEquals(c1, equipmentsParsed.get(0));

    }


    @Test
    void parseEquipArray() {
        try {
            jsonParser.parseEquipArray("");
            fail("should have caused exception");
        } catch (JSONException e) {
            System.out.println("Caught expected exception");
        }

        // Equipment objects parsed from JsonArray of Equip (no Region) will have
        // null Region field, which is fine for testing since won't occur in program
        Equipment c1 = new Centrifuge("c1","1234567890", BC);
        Equipment c2 = new Centrifuge("c2","2345678901", BC);
        Equipment e1 = new Excavator("e1","3456789012", BC);
        Equipment e2 = new Excavator("e2","4567890123", BC);
        List<Equipment> equipments = new ArrayList<>();
        equipments.add(c1);
        equipments.add(c2);
        equipments.add(e1);
        equipments.add(e2);

        JSONArray jsonEquipList = Jsonifier.equipListToJSON(equipments);
        String jsonEquipListAsString = jsonEquipList.toString(2);
        List<Equipment> equipmentsParsed = jsonParser.parseEquipArray(jsonEquipListAsString);
        assertEquals(4, equipmentsParsed.size());
        assertTrue(equipmentsParsed.contains(c1));
        assertTrue(equipmentsParsed.contains(c2));
        assertTrue(equipmentsParsed.contains(e1));
        assertTrue(equipmentsParsed.contains(e2));

    }


    @Test
    void parseRegionArray() {
        BC = new Region(Region.Province.BC);
        Equipment e1 = new Excavator("12","234354");
        e1.setRegion(BC);
        region = new Region("Canada");
        JSONObject jsonBC = new JSONObject();
        jsonBC.put("Name", BC.getName());
        jsonBC.put("Province", BC.getProvince());
        jsonBC.put("Equipment", Jsonifier.equipListToJSON(BC.getEquipment()));
        List<Region> listOfRegions = new ArrayList<>();
        listOfRegions.add(BC);
        JSONArray jsonArray = Jsonifier.regionListToJSON(listOfRegions);

        List<Region> regionParsedFromJsonBC = jsonParser.parseRegionArray(jsonArray.toString(2));
        for (Region r : regionParsedFromJsonBC) {
            System.out.println(r.toString());
        }


        JSONObject jsonCanada = new JSONObject();
        jsonCanada.put("Name", region.getName());
        jsonCanada.put("Province", region.getProvince());
        jsonCanada.put("Equipment", Jsonifier.equipListToJSON(region.getEquipment()));

        //todo

    }
}