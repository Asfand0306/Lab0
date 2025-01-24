package appliances;

public class Refrigerator extends Appliance {
    private int numberOfDoors;
    private int height;
    private int width;

    public Refrigerator(String itemNumber, String brand, int quantity, 
                        int wattage, String color, double price, 
                        int numberOfDoors, int height, int width) {
        super(itemNumber, brand, quantity, wattage, color, price);
        this.numberOfDoors = numberOfDoors;
        this.height = height;
        this.width = width;
    }

    @Override
    public String toString() {
        return super.toString() + 
               "\nNumber of Doors: " + (numberOfDoors == 2 ? "Double Doors" : 
                                        numberOfDoors == 3 ? "Three Doors" : 
                                        "Four Doors") +
               "\nHeight: " + height +
               "\nWidth: " + width;
    }

}
