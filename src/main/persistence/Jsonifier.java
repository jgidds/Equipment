package persistence;

import model.Region;
import model.Status;
import model.equipment.Equipment;
import model.Region;
import model.location.Location;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

// Converts model elements to JSON objects
public class Jsonifier {
    // Equipment object Field names
    public static String EQUIP_ID_KEY = "EquipmentID";
    public static String TYPE_KEY = "Type";
    public static String STATUS_KEY = "Status";
    public static String REGION_KEY = "Region";
    public static String LOCATION_KEY = "Location";
    public static String VIN_KEY = "VIN";
    // Region object field names
    public static String PROVINCE_KEY = "Province";
    public static String EQUIP_KEY = "Equipment";
    public static String NAME = "Name";
    public static List<String> objectFields;
    public static List<String> regionFields;


    // EFFECTS: returns JSON representation of Equip ID
    public static JSONObject equipIdToJSON(String equipID) {
        JSONObject equipIDJson = new JSONObject();
        equipIDJson.put(EQUIP_ID_KEY, equipID);
        return equipIDJson;
    }

    // EFFECTS: returns JSON representation of Equip Type
    public static JSONObject typetoJSON(String equipType) {
        JSONObject equipTypeJson = new JSONObject();
        equipTypeJson.put(TYPE_KEY, equipType);
        return equipTypeJson;
    }

    // EFFECTS: returns JSON representation of Status
    public static JSONObject statusToJSON(Status status) {
        JSONObject statusJson = new JSONObject();
        statusJson.put(STATUS_KEY, status);
        return statusJson;
    }

    // EFFECTS: returns JSON representation of Location
    public static JSONObject locationToJSON(Location location) {
        JSONObject locationJson = new JSONObject();
        locationJson.put(LOCATION_KEY, location);
        return locationJson;
    }


    // EFFECTS: returns JSON representation of an Equip object
    public static JSONObject equipToJson(Equipment equipment) {
        JSONObject equipJson = new JSONObject();
        equipJson.put(EQUIP_ID_KEY, equipment.getEquipID());
        equipJson.put(TYPE_KEY, equipment.getType());
        equipJson.put(STATUS_KEY, equipment.getStatus());
        equipJson.put(REGION_KEY, equipment.getRegion().getName());
        equipJson.put(VIN_KEY, equipment.getVin());

        return equipJson;
    }

    // EFFECTS: returns JSON array representing list of equip objects
    public static JSONArray equipListToJSON(List<Equipment> equips) {
        JSONArray equipArray = new JSONArray();
        for (Equipment e: equips) {
            equipArray.put(equipToJson(e));
        }

        return equipArray;
    }

    // EFFECTS: returns JSON array representing list of equip objects
    public static JSONArray regionListToJSON(List<Region> regions) {
        JSONArray jsonArray = new JSONArray();
        for (Region r: regions) {
            jsonArray.put(regionToJSON(r));
        }

        return jsonArray;
    }
    // EFFECTS: returns JSON representation of Equipment's Region
    public static JSONObject regionToJSON(Region region) {
        JSONObject jsonObject = new JSONObject();
        if (region.getProvince() != null) {
            jsonObject.put(PROVINCE_KEY, region.getProvince());
        }
        jsonObject.put(NAME, region.getName());
        JSONArray equipArray = equipListToJSON(region.getEquipment());
        jsonObject.put(EQUIP_KEY, equipArray);

        return jsonObject;
    }
}
