package parsers;

import model.Region;
import model.Status;
import model.equipment.Centrifuge;
import model.equipment.Equipment;
import model.equipment.Excavator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import persistence.Jsonifier;

import java.util.ArrayList;
import java.util.List;

// Represents a parser


public class JSONParser {
    private List<String> regionFields;
    private List<String> equipFields;

    public JSONParser() {
        equipFields = new ArrayList<String>();
        equipFields.add(Jsonifier.STATUS_KEY);
        equipFields.add(Jsonifier.EQUIP_ID_KEY);
        equipFields.add(Jsonifier.TYPE_KEY);
        equipFields.add(Jsonifier.VIN_KEY);
        equipFields.add(Jsonifier.REGION_KEY);

        regionFields = new ArrayList<>();
        regionFields.add(Jsonifier.NAME);
        regionFields.add(Jsonifier.PROVINCE_KEY);
        regionFields.add(Jsonifier.EQUIP_KEY);
    }

    public List<Equipment> parseEquipArray(String input) {
        if (input == null || input.isEmpty()) {
            throw new JSONException("String representing JSONArray is empty or null");
        }
        List<Equipment> listOfEquip = new ArrayList<>();
        JSONArray jsonEquipArray = new JSONArray(input);
        for (Object object: jsonEquipArray) {
            JSONObject jsonObject = (JSONObject) object;
            try {
                Equipment equip = parseEquip(object);
                listOfEquip.add(equip);
            } catch (JSONException e) {
                System.out.println("The following JSONObject was not parsed");
                System.out.println(jsonObject.toString(2));
            }
        }
        return listOfEquip;
    }

    private Equipment parseEquip(Object object) throws JSONException {
        JSONObject jsonEquip = (JSONObject) object;
        int i = 1;
        for (String s : equipFields) {
            if (!jsonEquip.has(s)) {
                throw new JSONException("JSON Object missing " + s + " field");
            }
        }
        if (jsonEquip.get("Type") == "Centrifuge") {
            return parseCentrifuge(object);
        } else {
            return parseExcavator(object);
        }
    }

    private Centrifuge parseCentrifuge(Object object) {
        JSONObject jsonEquip = (JSONObject) object;
        String VIN = (String) jsonEquip.get(Jsonifier.VIN_KEY);
        String equipID = (String) jsonEquip.get(Jsonifier.EQUIP_ID_KEY);
        Centrifuge c = new Centrifuge(VIN, equipID);
        Region region = (Region) jsonEquip.get(Jsonifier.REGION_KEY);
        Status status = (Status) jsonEquip.get(Jsonifier.STATUS_KEY);
        c.setRegion(region);
        c.setStatus(status);

        return c;
    }

    private Excavator parseExcavator(Object object) {
        JSONObject jsonEquip = (JSONObject) object;
        String VIN = (String) jsonEquip.get(Jsonifier.VIN_KEY);
        String equipID = (String) jsonEquip.get(Jsonifier.EQUIP_ID_KEY);
        Excavator e = new Excavator(VIN, equipID);
        Region region = (Region) jsonEquip.get(Jsonifier.REGION_KEY);
        Status status = (Status) jsonEquip.get(Jsonifier.STATUS_KEY);
        e.setRegion(region);
        e.setStatus(status);

        return e;
    }

    public List<Region> parseRegionArray(String input) {
        if (input == null || input.isEmpty()) {
            throw new JSONException("String representing JSONArray is empty or null");
        }
        List<Region> listofRegions = new ArrayList<>();
        JSONArray jsonRegionArray = new JSONArray(input);
        for (Object object: jsonRegionArray) {
            JSONObject jsonObject = (JSONObject) object;
            try {
                Region region = parseRegion(object);
                listofRegions.add(region);
            } catch (JSONException e) {
                System.out.println("The following JSONObject was not parsed");
                System.out.println(jsonObject.toString(2));
            }
        }
        return listofRegions;
    }

    private Region parseRegion(Object object) throws JSONException {
        JSONObject jsonObject = (JSONObject) object;
        if (!jsonObject.has("Name") || !jsonObject.has("Equipment")) {
            throw new JSONException("JSON Object is missing field");
        }
        Region region;
        if (jsonObject.has("Province") && jsonObject.get("Province") != null) {
            region = new Region(jsonObject.get("Province").toString());
        } else {
            region = new Region(jsonObject.get("Name").toString());
        }

        for (Object equipObject : jsonObject.getJSONArray(Jsonifier.EQUIP_KEY)) {
            JSONObject jsonEquip = (JSONObject) equipObject;
            region.addEquipment(parseEquip(jsonEquip));
        }
        region.correctRegion();

        return region;
    }



}
