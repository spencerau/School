/**
 * Created by Spencer on 7/4/2017.
 */
public class Jacket implements ClothingItem {

    private int id;
    private double cost;
    private ClothingSize clothingSize;
    private String type;

    public Jacket(int id, double cost, ClothingSize clothingSize) {
        this.type = "Jacket";
        this.id = id;
        this.cost = cost;
        this.clothingSize = clothingSize;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String getType() {
        return "JACKET";
    }

    @Override
    public String getMaterial() {
        return "LEATHER";
    }

    @Override
    public String getSize() {
        return String.valueOf(clothingSize);
    }

    @Override
    public void increaseCost(double increaseValue) {
        cost += increaseValue;
    }

    @Override
    public void decreaseCost(double decreaseValue) {
        if (cost > decreaseValue) cost -= decreaseValue;
    }
}
