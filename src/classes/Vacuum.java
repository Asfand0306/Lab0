package classes;

public class Vacuum extends Appliance {
  private String Grade;
  private int Voltage;

  private Vacuum(
      String itemNumber,
      String brand,
      int quantity,
      int wattage,
      String color,
      float price,
      String grade,
      int voltage) {
    super(itemNumber, brand, quantity, wattage, color, price);
    this.Grade = grade;
    this.Voltage = voltage;
  }

  public static Vacuum createVacuum(String[] appString) {
    Vacuum vacuum =
        new Vacuum(
            appString[0],
            appString[1],
            Integer.parseInt(appString[2]),
            Integer.parseInt(appString[3]),
            appString[4],
            Float.parseFloat(appString[5]),
            appString[6],
            Integer.parseInt(appString[7]));

    return vacuum;
  }

  public boolean is(String type) {
    return type.toLowerCase().equals("Vacuum");
  }

  public boolean isVoltage(String voltage) {
    return Integer.parseInt(voltage) == this.Voltage;
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
            + "\nGrade: "
            + Grade
            + "\nVoltage: "
            + Voltage);
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
            + Grade
            + ';'
            + Voltage
            + "\n");
  }
}