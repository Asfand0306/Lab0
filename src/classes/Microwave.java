package classes;

public class Microwave extends Appliance {
  private float Capacity;
  private String RoomType;

  public Microwave(
      String itemNumber,
      String brand,
      int quantity,
      int wattage,
      String color,
      float price,
      float capacity,
      String roomType) {
    super(itemNumber, brand, quantity, wattage, color, price);
    this.Capacity = capacity;
    this.RoomType = roomType;
  }

  public static Microwave createMicrowave(String[] appString) {
    Microwave microwave =
        new Microwave(
            appString[0],
            appString[1],
            Integer.parseInt(appString[2]),
            Integer.parseInt(appString[3]),
            appString[4],
            Float.parseFloat(appString[5]),
            Float.parseFloat(appString[6]),
            appString[7]);

    return microwave;
  }

  public boolean is(String type) {
    return type.toLowerCase().equals("microwave");
  }

  public boolean isRoomType(String type) {
    return type.toLowerCase().equals(this.RoomType.toLowerCase());
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
            + "\nCapacity: "
            + Capacity
            + "\nRoom Type: "
            + RoomType);
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
            + Capacity
            + ';'
            + RoomType
            + "\n");
  }
}