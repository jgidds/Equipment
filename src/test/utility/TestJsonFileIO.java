package utility;

import model.Region;
import model.Status;
import model.equipment.Centrifuge;
import model.equipment.Equipment;
import model.equipment.Excavator;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import parsers.JSONParser;
import persistence.Jsonifier;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonFileIO {
    private static final File testJsonDataFile = new File("./resources/json/test.json");

        @Test
        void read() {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(testJsonDataFile));
                StringBuilder builder = new StringBuilder();
                String currentLine = reader.readLine();
                while (currentLine != null) {
                    builder.append(currentLine);
                    //builder.append("n");
                    currentLine = reader.readLine();
                }
                String fileText = builder.toString();
                reader.close();

                JSONParser jsonParser = new JSONParser();
                List<Region> regionList = jsonParser.parseRegionArray(fileText);
                for (Region r : regionList) {
                    System.out.println(r.toString());
                }

            } catch (FileNotFoundException e) {
                // nada
            } catch (IOException e) {
                /// nada
            }
        }

        @Test
        void write() {
            // setting up regions with lists of equipment
            Region AB = new Region(Region.Province.AB);
            Region BC = new Region(Region.Province.BC);
            Equipment c1 = new Centrifuge("c1", "1234567890");
            c1.setStatus(Status.AVAILABLE);
            ((Centrifuge) c1).setFeedRate(100);
            Equipment c2 = new Centrifuge("c2", "2345678901");
            c2.setStatus(Status.COMMITTED);
            Equipment e1 = new Excavator("e1", "3456789012");
            c2.setStatus(Status.RESERVE);
            Equipment e2 = new Excavator("e2", "4567890123");
            Equipment e3 = new Excavator("e3", "5678901234");
            AB.addEquipment(c1);
            AB.addEquipment(c2);
            AB.addEquipment(e1);
            BC.addEquipment(e2);
            BC.addEquipment(e3);
            List<Region> regions = new ArrayList<>();
            regions.add(AB);
            regions.add(BC);

            JSONArray jsonArray = Jsonifier.regionListToJSON(regions);
            String jsonArrToString = jsonArray.toString(2);

            try {
                FileWriter fileWriter = new FileWriter(testJsonDataFile);
                fileWriter.write(jsonArrToString);
                fileWriter.close();
            } catch (IOException e) {
                // yeah
            }

        }

}
