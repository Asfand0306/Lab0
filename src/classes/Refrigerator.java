package classes;

public class Refrigerator extends Appliance {
  private int NumDoors;
  private int Height;
  private int Width;

  public Refrigerator(
      String itemNumber,
      String brand,
      int quantity,
      int wattage,
      String color,
      float price,
      int numDoors,
      int height,
      int width) {
    super(itemNumber, brand, quantity, wattage, color, price);
    this.NumDoors = numDoors;
    this.Height = height;
    this.Width = width;
  }

  public static Refrigerator createRefrigerator(String[] appString) {
    Refrigerator refrigerator =
        new Refrigerator(
            appString[0],
            appString[1],
            Integer.parseInt(appString[2]),
            Integer.parseInt(appString[3]),
            appString[4],
            Float.parseFloat(appString[5]),
            Integer.parseInt(appString[6]),
            Integer.parseInt(appString[7]),
            Integer.parseInt(appString[8]));

    return refrigerator;
  }

  public boolean is(String type) {
    return type.toLowerCase().equals("refrigerator");
  }

  public boolean numDoorsIs(String numDoors) {
    return Integer.parseInt(numDoors) == this.NumDoors;
  }

  @Override
  public String toString() {
    return String.format(
        "Item Number: "
            + ItemNumber
            + "\nBrand: "
            + Brand
            + "\nQuantity: "
            + Quantity
            + "\nWattage: "
            + Wattage
            + "\nColor: "
            + Color
            + "\nPrice: "
            + Price
            + "\nNumber of Doors: "
            + NumDoors
            + "\nHeight: "
            + Height
            + "\nWidth: "
            + Width);
  }

  public String toFileString() {
    return String.format(
        ItemNumber
            + ';'
            + Brand
            + ';'
            + Quantity
            + ';'
            + Wattage
            + ';'
            + Color
            + ';'
            + Price
            + ';'
            + NumDoors
            + ';'
            + Height
            + ';'
            + Width
            + "\n");
  }
}