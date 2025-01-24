package app;

import classes.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ModernAppliancesApp {
    private static ArrayList<Appliance> appliances;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Load appliances from file using static method from Appliance class
        appliances = Appliance.loadAppliances("appliances.txt");

        // Main program loop
        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": checkOutAppliance(); break;
                case "2": findAppliancesByBrand(); break;
                case "3": displayAppliancesByType(); break;
                case "4": produceRandomApplianceList(); break;
                case "5": saveAndExit(); return;
                default: System.out.println("You didn't select a valid option. Please try again!");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nWelcome to Modern Appliances!");
        System.out.println("How may we assist you?");
        System.out.println("1 : Check out appliance");
        System.out.println("2 : Find appliances by brand");
        System.out.println("3 : Display appliances by type");
        System.out.println("4 : Produce random appliance list");
        System.out.println("5 : Save & exit");
        System.out.print("Enter option: ");
    }

    private static void checkOutAppliance() {
        System.out.println("Enter the item number of an appliance:");
        String itemNumber = scanner.nextLine().trim();

        boolean applianceExists = false;
        for (Appliance appliance : appliances) {
            if (appliance.getItemNumber().equals(itemNumber)) {
                applianceExists = true;
                if (appliance.getQuantity() > 0) {
                    appliance.checkOut();
                    System.out.printf("You checked out:\n%s", appliance);
                } else {
                    System.out.println("That appliance is not available to be checked out.");
                }
                break;
            }
        }

        if (!applianceExists) {
            System.out.println("No appliances found with that item number.");
        }
    }

    private static void findAppliancesByBrand() {
        System.out.println("Enter the brand to search for:");
        String brand = scanner.nextLine();
        System.out.println("Here are matching appliances:");

        boolean brandExists = false;
        for (Appliance appliance : appliances) {
            if (appliance.getBrand().toLowerCase().equals(brand.toLowerCase())) {
                brandExists = true;
                System.out.println(appliance);
            }
        }

        if (!brandExists) {
            System.out.println("Uh oh! We don't seem to carry that brand.");
        }
    }

    private static void displayAppliancesByType() {
        System.out.println("Enter the type of appliance you are looking for:");
        System.out.println("1 - Refrigerators\n2 - Vacuums\n3 - Microwaves\n4 - Dishwashers");
        String applianceType = scanner.nextLine().toLowerCase();

        switch (applianceType) {
            case "1":
                handleRefrigeratorType();
                break;
            case "2":
                handleVacuumType();
                break;
            case "3":
                handleMicrowaveType();
                break;
            case "4":
                handleDishwasherType();
                break;
            default:
                System.out.println("Invalid appliance type.");
        }
    }

    private static void handleRefrigeratorType() {
        System.out.println("Enter number of doors: 2 (double door), 3 (three doors) or 4 (four doors):");
        String numDoors = scanner.nextLine().toLowerCase();
        String[] doorList = {"2", "3", "4"};
        
        boolean exists = false;
        for (String validDoor : doorList) {
            if (validDoor.equals(numDoors)) {
                exists = true;
                break;
            }
        }
        
        if (!exists) {
            System.out.println("We don't have refrigerators with that number of doors.");
            return;
        }
        
        System.out.println("Matching refrigerators:");
        for (Appliance appliance : appliances) {
            if (appliance instanceof Refrigerator) {
                Refrigerator refrigerator = (Refrigerator) appliance;
                if (refrigerator.numDoorsIs(numDoors)) {
                    System.out.println(refrigerator);
                }
            }
        }
    }

    private static void handleVacuumType() {
        System.out.println("Enter battery voltage value. 18 V (low) or 24 V (high)");
        String voltage = scanner.nextLine().toLowerCase();
        
        System.out.println("Matching vacuums:");
        for (Appliance appliance : appliances) {
            if (appliance instanceof Vacuum) {
                Vacuum vacuum = (Vacuum) appliance;
                if (vacuum.isVoltage(voltage)) {
                    System.out.println(vacuum);
                }
            }
        }
    }

    private static void handleMicrowaveType() {
        System.out.println("Room where the microwave will be installed: K (kitchen) or W (work site):");
        String room = scanner.nextLine().toLowerCase();
        
        String[] roomList = {"k", "w"};
        boolean validRoom = false;
        for (String validRoomType : roomList) {
            if (validRoomType.equals(room)) {
                validRoom = true;
                break;
            }
        }
        
        if (!validRoom) {
            System.out.println("Invalid room type.");
            return;
        }
        
        for (Appliance appliance : appliances) {
            if (appliance instanceof Microwave) {
                Microwave microwave = (Microwave) appliance;
                if (microwave.isRoomType(room)) {
                    System.out.println(microwave);
                }
            }
        }
    }

    private static void handleDishwasherType() {
        System.out.println("Enter a sound rating: Qt (Quietest), Qr (Quieter), Qu (Quiet) or M (Moderate)");
        String rating = scanner.nextLine().toLowerCase();
        
        String[] ratingList = {"qt", "qr", "qu", "m"};
        boolean validRating = false;
        for (String validSoundRating : ratingList) {
            if (validSoundRating.equals(rating)) {
                validRating = true;
                break;
            }
        }
        
        if (!validRating) {
            System.out.println("Invalid sound rating.");
            return;
        }
        
        System.out.println("List of appliances:");
        for (Appliance appliance : appliances) {
            if (appliance instanceof Dishwasher) {
                Dishwasher dishwasher = (Dishwasher) appliance;
                if (dishwasher.isRating(rating)) {
                    System.out.println(dishwasher);
                }
            }
        }
    }

    private static void produceRandomApplianceList() {
        System.out.println("Enter number of appliances:");
        
        try {
            int numAppliances = Integer.parseInt(scanner.nextLine());
            
            if (numAppliances > appliances.size()) {
                System.out.println("We only have " + appliances.size() + " appliances.");
                return;
            }
            
            System.out.println("Random appliances:");
            Random rand = new Random();
            for (int i = 0; i < numAppliances; i++) {
                int index = rand.nextInt(appliances.size());
                System.out.println(appliances.get(index));
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid integer. Please try again.");
        }
    }

    private static void saveAndExit() {
        String applianceStringToWrite = "";
        
        for (Appliance appliance : appliances) {
            if (appliance instanceof Dishwasher) {
                Dishwasher dishwasher = (Dishwasher) appliance;
                applianceStringToWrite += dishwasher.toFileString();
            } else if (appliance instanceof Microwave) {
                Microwave microwave = (Microwave) appliance;
                applianceStringToWrite += microwave.toFileString();
            } else if (appliance instanceof Refrigerator) {
                Refrigerator refrigerator = (Refrigerator) appliance;
                applianceStringToWrite += refrigerator.toFileString();
            } else if (appliance instanceof Vacuum) {
                Vacuum vacuum = (Vacuum) appliance;
                applianceStringToWrite += vacuum.toFileString();
            }
        }

        File applianceFile = new File("appliances.txt");

        if (!applianceFile.exists()) {
            System.out.println("Uh oh... your appliance file is missing!");
            return;
        }

        try {
            FileWriter writer = new FileWriter(applianceFile, false);
            writer.write(applianceStringToWrite);
            writer.close();
            System.out.println("File successfully written! Goodbye!");
        } catch (IOException e) {
            System.out.println("Error writing to appliance file:\n" + e.getMessage());
        }
        System.exit(0);
    }
}