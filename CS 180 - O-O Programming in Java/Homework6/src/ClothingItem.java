/**
 * Created by Spencer on 7/4/2017.
 */
public interface ClothingItem {

    //Returns the id of the given clothing item
    int getID();

    double getCost();

    //Returns the type of the clothing item.
    String getType();

    //Returns the material of the clothing item.
    String getMaterial();

    //Returns the size of the clothing item.
    String getSize();

    //Increases the cost of the clothing item by the increaseValue
    void increaseCost(double increaseValue);

    //Decreases the cost of the clothing item by the decreaseValue
    void decreaseCost(double decreaseValue);

}
