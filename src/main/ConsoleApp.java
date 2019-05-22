// a Simple Equipment Tracking app
// todo - not finished yet
import model.Region;
import model.Status;
import model.equipment.Equipment;
import utility.JsonFileIO;

import java.util.*;
// todo

public class ConsoleApp {
    private static List<Region> regions;
    private static Scanner input;
    private static String userInput;
    private static boolean exit = false;
    private static String doubleDash = "====================================================";
    private static String singleDash = "----------------------------------------------------";
    private static String columnFormat = "%-7s %-11s %-30s %n";


    public static void main(String[] args) {
        input = new Scanner(System.in);
        regions = utility.JsonFileIO.read();
        while (!exit) {
            displayRegions();
//        userInput = input.nextLine();
//        regionActionfromInput();
        }
        saveChanges();
    }

    // EFFECTS: Shows top-level view of Regions
    public static void displayRegions() {
        System.out.println("\n");
        System.out.println(doubleDash);
        System.out.println("Regions");
        System.out.println(singleDash);
        if (regions.isEmpty()) {
            System.out.println("There are no regions to display.");
            System.out.println("Enter A to add a new region.");
            System.out.println(">  ");
            userInput = input.nextLine();

        } else {
            System.out.println("Enter a number to choose region and view equipment");
            int i = 1;
            for (Region r : regions) {
                System.out.println(i + ". " + r.getName());
                i++;
            }
            System.out.println(">  ");
            try {int selection = input.nextInt();
                if (regions.get(selection-1) != null) {
                    displayRegionMenu(regions.get(selection-1));
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again");
                displayRegions();
            }

        }


        // todo!
    }

    private static void regionActionfromInput() {

    }

    // console for displaying
    private static void displayRegionMenu(Region region) {
        System.out.println("\n");
        System.out.println(doubleDash);
        System.out.println(region.getName());
        System.out.println(singleDash);
        List<Equipment> regEquip = region.getEquipment();
        int i = 1;
        //System.out.println(" ID   Type                Status");
        System.out.printf(columnFormat, "   ID", "Type", "Status");
        for (Equipment e : regEquip) {
            System.out.printf(columnFormat, i + ". " + e.getEquipID(), e.getType(), e.getStatus().getDescription());
            i++;
        }
        System.out.println("-------------------------------");
        System.out.println("     Enter equipment number to update status or transfer");
        System.out.println("     Enter 0 to return to Main Menu");
        System.out.println("> ");

        try {
            int userInputNum = input.nextInt();
            if (userInputNum == 0) {
                displayRegions();
            } else {
                if (regEquip.get(userInputNum-1) != null) {
                    displayEquipmentMenu(regEquip.get(userInputNum - 1), region);
                } else {
                    System.out.println("Invalid input, please try again");
                    displayRegionMenu(region);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, please try again");
            displayRegionMenu(region);
        }

    }



    private static void displayEquipmentMenu(Equipment e, Region r) {
        System.out.println(doubleDash);
        System.out.printf("%-9s %-11s %-20s %n", e.getEquipID(), e.getType(), e.getStatus());
        System.out.println("Region: " + e.getRegion().getName());
        System.out.println(singleDash);
        System.out.println("Enter A to update equipment status as Available");
        System.out.println("Enter B to update equipment status as Reserved as Backup");
        System.out.println("Enter C to update equipment status as Committed");
        System.out.println("Enter R to update equipment status as Being Repaired");
        System.out.println("Enter T to transfer to another region");
        System.out.println("Enter M to return to menu for " + r.getName());
        System.out.println("Enter 0 to return to main menu");
        System.out.println(">  ");
        userInput = input.next();
        switch (userInput.toUpperCase().charAt(0)) {
            case 'A':
                e.setStatus(Status.AVAILABLE);
                System.out.println(e.getEquipID() + " set to Available");
                saveChanges();
                displayRegionMenu(r);

                break;
            case 'B':
                e.setStatus(Status.RESERVE);
                saveChanges();
                System.out.println(e.getEquipID() + " set to Reserved");
                displayRegionMenu(r);
                break;
            case 'C': //
                e.setStatus(Status.COMMITTED);
                System.out.println(e.getEquipID() + " set to Committed");
                saveChanges();
                displayRegionMenu(r);
                break;
            case 'R':
                e.setStatus(Status.REPAIR);
                saveChanges();
                System.out.println(e.getEquipID() + " set to Being Repaired");
                displayRegionMenu(r);
                break;
            case 'T': transferEquipment(e, r);
                break;
            case 'M': displayRegionMenu(r);//
                break;
            case '0' : //
                break;
            default:
                System.out.println("Invalid input, please try again");
                displayEquipmentMenu(e, r);
        }

    }

    private static void transferEquipment(Equipment e, Region region) {
        System.out.println(singleDash);
        System.out.println("Transfer " + e.getType().toLowerCase() + " " + e.getEquipID() + " from " + region.getName());
        System.out.println(doubleDash);
        System.out.println();
        int listIndex = 0;
        int i = 1;
        System.out.println("Enter number to choose region to transfer equipment to");
        System.out.println("Enter 0 to return to equipment menu");
        for (Region r: regions) {
            if (r != region) {
                System.out.println(i + ". " + r.getName());
                i++;
            }
        }
            if (i == 1) {
                System.out.println("There are no other regions to transfer to");
                displayEquipmentMenu(e, region);
            } else {
                int userInputNum = input.nextInt();
                if (userInputNum == 0) {
                    displayEquipmentMenu(e, region);
                } else if (regions.get(userInputNum) != null) {
                    e.setRegion(regions.get(userInputNum));
                    saveChanges();
                    System.out.println(e.getEquipID() + " transferred to " + e.getRegion().getName());
                } else {
                    System.out.println("Invalid input! Please try again");
                    transferEquipment(e, region);


            }
        }
    }


    // console for adding a new region to the list
    private static void addNewRegion() {

    }





    private void takeActionFromInputRegionScreen() {
//        switch (userInput.toUpperCase().charAt(0)) {
//            case 'A': // addRegion;
//                break;
//                case  ''
//        }
    }

    private static void pressEnterToContinue() {
        System.out.print("\nPress enter to continue ... ");
        input.nextLine();
    }

    private static void saveChanges() {
        JsonFileIO.write(regions);
    }






}
