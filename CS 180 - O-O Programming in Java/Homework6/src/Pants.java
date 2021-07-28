/**
 * Created by Spencer on 7/4/2017.
 */
public class Pants implements ClothingItem {

    private String type = "Pants";
    private int id;
    private double cost;
    private ClothingMaterial clothingMaterial;
    private int length;
    private int waist;

    public Pants(int id, double cost, ClothingMaterial clothingMaterial, int length, int waist) {
        this.type = "Pants";
        this.id = id;
        this.cost = cost;
        this.clothingMaterial = clothingMaterial;
        this.length = length;
        this.waist = waist;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public int getID() {
        return id;
    }

    public String getType() {
        return "PANTS";
    }

    public String getMaterial() {
        return String.valueOf(clothingMaterial);
    }

    public String getSize() {
        return length + "x" + waist;
    }

    @Override
    public void increaseCost(double increaseValue) {
        double newCost = cost + increaseValue;
        if (newCost <= 75) cost = newCost;
        else return;
    }

    @Override
    public void decreaseCost(double decreaseValue) {
        if (cost > decreaseValue) cost -= decreaseValue;
    }

}
