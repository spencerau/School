/**
 * Created by Spencer on 7/4/2017.
 */
public class Shirt implements ClothingItem {

    private String type;
    private int id;
    private double cost;
    private ClothingSize clothingSize;
    private ClothingMaterial clothingMaterial;

    public Shirt(int id, double cost, ClothingSize clothingSize, ClothingMaterial clothingMaterial) {
        this.type = "Shirt";
        this.id = id;
        this.cost = cost;
        this.clothingSize = clothingSize;
        this.clothingMaterial = clothingMaterial;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public int getID() {
        return id;
    }

    public String getType() {
        return "SHIRT";
    }

    public String getSize() {
        return String.valueOf(clothingSize);
    }

    public String getMaterial() {
        return String.valueOf(clothingMaterial);
    }

    public void increaseCost(double increaseValue) {
        double newCost = cost + increaseValue;
        if (newCost <= 50) cost = newCost;
        else return;
    }

    public void decreaseCost(double decreaseValue) {
        if (cost > decreaseValue) cost -= decreaseValue;
    }

}
