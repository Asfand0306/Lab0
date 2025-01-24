package appliances;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ModernAppliancesApp {
    private static List<Appliance> appliances = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadAppliancesFromFile("appliances.txt");
        
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: checkOutAppliance(); break;
                case 2: findAppliancesByBrand(); break;
                case 3: displayAppliancesByType(); break;
                case 4: produceRandomApplianceList(); break;
                case 5: saveAndExit(); return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private static void loadAppliancesFromFile(String filename) {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(";");
                String itemNumber = data[0];
                
                // Determine appliance type by first digit
                char type = itemNumber.charAt(0);
                switch (type) {
                    case '1': // Refrigerator
                        appliances.add(new Refrigerator(
                            itemNumber, data[1], Integer.parseInt(data[2]), 
                            Integer.parseInt(data[3]), data[4], 
                            Double.parseDouble(data[5]), 
                            Integer.parseInt(data[6]), 
                            Integer.parseInt(data[7]), 
                            Integer.parseInt(data[8])
                        ));
                        break;
                    case '2': // Vacuum
                        appliances.add(new Vacuum(
                            itemNumber, data[1], Integer.parseInt(data[2]), 
                            Integer.parseInt(data[3]), data[4], 
                            Double.parseDouble(data[5]), data[6], 
                            Integer.parseInt(data[7])
                        ));
                        break;
                    case '3': // Microwave
                        appliances.add(new Microwave(
                            itemNumber, data[1], Integer.parseInt(data[2]), 
                            Integer.parseInt(data[3]), data[4], 
                            Double.parseDouble(data[5]), 
                            Double.parseDouble(data[6]), data[7]
                        ));
                        break;
                    case '4':
                    case '5': // Dishwasher
                        appliances.add(new Dishwasher(
                            itemNumber, data[1], Integer.parseInt(data[2]), 
                            Integer.parseInt(data[3]), data[4], 
                            Double.parseDouble(data[5]), data[6], data[7]
                        ));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    private static void displayMenu() {
        System.out.println("\nWelcome to Modern Appliances!");
        System.out.println("How may we assist you?");
        System.out.println("1 – Check out appliance");
        System.out.println("2 – Find appliances by brand");
        System.out.println("3 – Display appliances by type");
        System.out.println("4 – Produce random appliance list");
        System.out.println("5 – Save & exit");
        System.out.print("Enter option: ");
    }

    private static void checkOutAppliance() {
        System.out.print("Enter the item number of an appliance: ");
        String itemNumber = scanner.nextLine();
        
        Optional<Appliance> appliance = appliances.stream()
            .filter(a -> a.getItemNumber().equals(itemNumber))
            .findFirst();
        
        if (appliance.isPresent()) {
            if (appliance.get().getQuantity() > 0) {
                appliance.get().setQuantity(appliance.get().getQuantity() - 1);
                System.out.println("Appliance \"" + itemNumber + "\" has been checked out.");
            } else {
                System.out.println("The appliance is not available to be checked out.");
            }
        } else {
            System.out.println("No appliances found with that item number.");
        }
    }

    private static void findAppliancesByBrand() {
        System.out.print("Enter brand to search for: ");
        String brand = scanner.nextLine();
        
        List<Appliance> matchingAppliances = appliances.stream()
            .filter(a -> a.getBrand().equalsIgnoreCase(brand))
            .collect(Collectors.toList());
        
        System.out.println("Matching Appliances:");
        matchingAppliances.forEach(System.out::println);
    }

    private static void displayAppliancesByType() {
        System.out.println("Appliance Types");
        System.out.println("1 – Refrigerators");
        System.out.println("2 – Vacuums");
        System.out.println("3 – Microwaves");
        System.out.println("4 – Dishwashers");
        System.out.print("Enter type of appliance: ");
        
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        switch (type) {
            case 1: // Refrigerators
                System.out.println("Enter number of doors: 2 (double door), 3 (three doors) or 4 (four doors):");
                int doors = scanner.nextInt();
                appliances.stream()
                    .filter(a -> a instanceof Refrigerator && 
                                 ((Refrigerator)a).toString().contains(
                                     doors == 2 ? "Double Doors" : 
                                     doors == 3 ? "Three Doors" : 
                                     "Four Doors"))
                    .forEach(System.out::println);
                break;
            case 2: // Vacuums
                System.out.println("Enter battery voltage value. 18 V (low) or 24 V (high)");
                int voltage = scanner.nextInt();
                appliances.stream()
                    .filter(a -> a instanceof Vacuum && 
                                 ((Vacuum)a).toString().contains(voltage + " V"))
                    .forEach(System.out::println);
                break;
            case 3: // Microwaves
                System.out.println("Room where the microwave will be installed: K (kitchen) or W (work site):");
                String room = scanner.nextLine();
                appliances.stream()
                    .filter(a -> a instanceof Microwave && 
                                 ((Microwave)a).toString().contains(
                                     room.equals("K") ? "Kitchen" : "Work Site"))
                    .forEach(System.out::println);
                break;
            case 4: // Dishwashers
                System.out.println("Enter the sound rating of the dishwasher: Qt (Quietest), Qr (Quieter), Qu(Quiet) or M (Moderate):");
                String rating = scanner.nextLine();
                appliances.stream()
                    .filter(a -> a instanceof Dishwasher && 
                                 ((Dishwasher)a).toString().contains(
                                     rating.equals("Qt") ? "Quietest" :
                                     rating.equals("Qr") ? "Quieter" :
                                     rating.equals("Qu") ? "Quiet" : "Moderate"))
                    .forEach(System.out::println);
                break;
        }
    }

    private static void produceRandomApplianceList() {
        System.out.print("Enter number of appliances: ");
        int count = scanner.nextInt();
        
        System.out.println("Random appliances:");
        new Random().ints(0, appliances.size())
            .distinct()
            .limit(count)
            .mapToObj(appliances::get)
            .forEach(System.out::println);
    }

    private static void saveAndExit() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("appliances.txt"))) {
            for (Appliance appliance : appliances) {
                if (appliance instanceof Refrigerator) {
                    Refrigerator r = (Refrigerator) appliance;
                    writer.printf("%s;%s;%d;%d;%s;%.2f;%d;%d;%d\n",
                        r.getItemNumber(), r.getBrand(), r.getQuantity(), 
                        0, "", r.getPrice(), 0, 0, 0);
                }
                // Add similar logic for other appliance types
            }
            System.out.println("Appliances saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving appliances: " + e.getMessage());
        }
    }
}