package classes;

public class Dishwasher extends Appliance {
  private String SoundRating;
  private String Feature;

  public Dishwasher(
      String itemNumber,
      String brand,
      int quantity,
      int wattage,
      String color,
      float price,
      String soundRating,
      String feature) {
    super(itemNumber, brand, quantity, wattage, color, price);
    this.SoundRating = soundRating;
    this.Feature = feature;
  }

  public static Dishwasher createDishwasher(String[] appString) {
    // create new dishwasher with appropriate fields in appString
    Dishwasher dishwasher =
        new Dishwasher(
            appString[0],
            appString[1],
            Integer.parseInt(appString[2]),
            Integer.parseInt(appString[3]),
            appString[4],
            Float.parseFloat(appString[5]),
            appString[6],
            appString[7]);
    return dishwasher;
  }

  public boolean is(String type) {
    return type.toLowerCase().equals("dishwasher");
  }

  public boolean isRating(String rating) {
    return rating.toLowerCase().equals(this.SoundRating.toLowerCase());
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
            + "\nSound Rating: "
            + SoundRating
            + "\nFeature: "
            + Feature);
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
            + SoundRating
            + ';'
            + Feature
            + "\n");
  }
}