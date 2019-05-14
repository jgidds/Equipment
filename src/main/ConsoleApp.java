// a Simple Equipment Tracking app
// todo - not finished yet
import model.Region;
import utility.JsonFileIO;

import java.util.*;

public class ConsoleApp {
    private static List<Region> regions;
    private static Scanner input;
    private static String userInput;
    private static boolean exit = false;


    public static void main(String[] args) {
	input = new Scanner(System.in);
	regions = utility.JsonFileIO.read();
	while (!exit) {
	    displayRegions();
	}
    }

    // EFFECTS: Shows top-level view of Regions
    public static void displayRegions() {
        System.out.println("\n");
        System.out.println("==============================");
        System.out.println("\tRegions");
        System.out.println("------------------------------");
        for (int i = 1; i < regions.size(); i++) {
            System.out.println(i + 1 + ". "+ regions.get(i).getName());
        }

        // todo!
    }

    private static void pressEnterToContinue() {
        System.out.print("\nPress enter to continue ... ");
        input.nextLine();
    }

    // EFFECTS: Displays equipment for selected region
    private static void displayEquipment(Region region) {
        System.out.println("\n");
        System.out.println("==============================");
        System.out.println("\tEquipment for " + region.getName() );
        System.out.println("------------------------------");
        //displayEquipmentList(region);
        displayMenu();
    }

    private static void displayMenu() {
        System.out.println("You can ...");
        System.out.println("\tEnter A to add a new task;");
        System.out.println("\tEnter D to show tasks' details;");
        System.out.println("\tEnter Q to quit.");
        System.out.print("> ");
    }


}
