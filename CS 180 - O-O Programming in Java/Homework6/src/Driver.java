import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
/**
 * Created by Spencer on 7/4/2017.
 */
public class Driver {

    public ArrayList<ClothingItem> clothingItems;

    public Driver() {
        clothingItems = new ArrayList<>();
    }

    public String getInfo(ClothingItem clothingItem) {
        NumberFormat f = new DecimalFormat("#0.00");
        return clothingItem.getID() + ". " + clothingItem.getSize() + " " + clothingItem.getType() + " made of " +
                clothingItem.getMaterial() + " that costs " + f.format(clothingItem.getCost());
    }

    public void changeCostOfItem(int id, double changeValue) {
        ClothingItem item = getClothingItem(id);
        if (changeValue >= 0) item.increaseCost(changeValue);
        else item.decreaseCost(changeValue);
    }

    public ClothingItem getClothingItem(int id) {
        for (ClothingItem item : clothingItems) {
            if (item.getID() == id) return item;
        }
        return clothingItems.get(0);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public ArrayList<ClothingItem> listByItemType(ClothingType clothingType) {

        ArrayList<ClothingItem> typeList = new ArrayList<>();

        if (clothingType.equals(ClothingType.FLANNEL)) {
            for (ClothingItem item : clothingItems) {
                if (item.getType().equals("FLANNEL")) typeList.add(item);
            }
        }
        if (clothingType.equals(ClothingType.SHIRT)) {
            for (ClothingItem item : clothingItems) {
                if (item.getType().equals("SHIRT")) typeList.add(item);
            }
        }
        if (clothingType.equals(ClothingType.PANTS)) {
            for (ClothingItem item : clothingItems) {
                if (item.getType().equals("PANTS")) typeList.add(item);
            }
        }
        if (clothingType.equals(ClothingType.JACKET)) {
            for (ClothingItem item : clothingItems) {
                if (item.getType().equals("JACKET")) typeList.add(item);
            }
        }
        return typeList;
    }
}