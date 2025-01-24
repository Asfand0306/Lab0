package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Appliance {
  protected String ItemNumber;
  protected String Brand;
  protected int Quantity;
  protected int Wattage;
  protected String Color;
  protected float Price;

  Appliance(String itemNumber, String brand, int quantity, int wattage, String color, float price) {
    this.ItemNumber = itemNumber;
    this.Brand = brand;
    this.Quantity = quantity;
    this.Wattage = wattage;
    this.Color = color;
    this.Price = price;
  }

  public String getItemNumber() {
    return this.ItemNumber;
  }

  public int getQuantity() {
    return this.Quantity;
  }

  public void checkOut() {
    this.Quantity--;
  }

  public String getBrand() {
    return this.Brand;
  }

  public static ArrayList<Appliance> loadAppliances(String fileName) {
    ArrayList<Appliance> appliances = new ArrayList<Appliance>();

    try {
      File applianceFile = new File(fileName);
      Scanner fileReader = new Scanner(applianceFile);

      // iterate over lines in appliances.txt file
      while (fileReader.hasNextLine()) {
        String data = fileReader.nextLine();
        String[] appString = data.split(";");

        // creates appropriate appliance for each line in the appliances file
        switch (appString[0].charAt(0)) {
          case '1':
            appliances.add(Refrigerator.createRefrigerator(appString));
            break;
          case '2':
            appliances.add(Vacuum.createVacuum(appString));
            break;
          case '3':
            appliances.add(Microwave.createMicrowave(appString));
            break;
          case '4':
          case '5':
            appliances.add(Dishwasher.createDishwasher(appString));
            break;
          default:
            System.out.println("Unable to create appliance.");
            break;
        }
      }

      fileReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("Error occured reading from Appliance file: ");
      e.printStackTrace();
    }
    return appliances;
  }
}