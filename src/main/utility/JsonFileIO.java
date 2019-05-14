package utility;

import model.Region;
import model.equipment.Equipment;
import org.json.JSONArray;
import parsers.JSONParser;
import persistence.Jsonifier;

import java.io.*;
import java.util.List;

// File input/output operations
public class JsonFileIO {
    public static final File jsonDataFile = new File("./resources/json/regions.json");

    // EFFECTS: attempts to read jsonDataFile and parse it
    //           returns a list of tasks from the content of jsonDataFile
    public static List<Region> read() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(jsonDataFile));
            StringBuilder builder = new StringBuilder();
            String currentLine = reader.readLine();
            while (currentLine != null) {
                builder.append(currentLine);
                currentLine = reader.readLine();
            }
            String fileText = builder.toString();
            reader.close();
            JSONParser parser = new JSONParser();
            List<Region> regionList = parser.parseRegionArray(fileText);
            return regionList;

        } catch (FileNotFoundException e) {
            // nada
        } catch (IOException e) {
            /// nada
        }
        return null; // stub
    }

//    // EFFECTS: saves the equipment to jsonDataFile
    public static void write(List<Equipment> equipment) {
        JSONArray jsonArray = Jsonifier.equipListToJSON(equipment);
        String jsonEquipString = jsonArray.toString(2);
        try {
            FileWriter fileWriter = new FileWriter(JsonFileIO.jsonDataFile);
            fileWriter.write(jsonEquipString);
            fileWriter.close();
        } catch (IOException e) {
            // should this exception be thrown!??
        }
    }
}
