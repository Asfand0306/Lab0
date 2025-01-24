package appliances;

public abstract class Appliance {
    private String itemNumber;
    private String brand;
    private int quantity;
    private int wattage;
    private String color;
    private double price;

    // Constructor
    public Appliance(String itemNumber, String brand, int quantity, 
                     int wattage, String color, double price) {
        this.itemNumber = itemNumber;
        this.brand = brand;
        this.quantity = quantity;
        this.wattage = wattage;
        this.color = color;
        this.price = price;
    }

    // Getters and setters
    public String getItemNumber() { return itemNumber; }
    public void setItemNumber(String itemNumber) { this.itemNumber = itemNumber; }
    public String getBrand() { return brand; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    // toString method
    @Override
    public String toString() {
        return "Item Number: " + itemNumber + 
               "\nBrand: " + brand + 
               "\nQuantity: " + quantity + 
               "\nWattage: " + wattage + 
               "\nColor: " + color + 
               "\nPrice: " + price;
    }
}