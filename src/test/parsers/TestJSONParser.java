package parsers;

import model.Region;
import model.equipment.Equipment;
import model.equipment.Excavator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Jsonifier;

import java.util.ArrayList;
import java.util.List;

class TestJSONParser {
    private JSONParser jsonParser;
    private Region BC;
    private Region region;
    private Equipment e;
    private Region bc;

    @BeforeEach
    void setUp(){
        jsonParser = new JSONParser();
    }

    @Test
    void parseEquipArray() {
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
        System.out.println(jsonCanada.toString(2));
        System.out.println(region.getProvince() == null);





    }

    @Test
    void parseRegionArray() {
    }
}