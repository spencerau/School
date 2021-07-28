import java.util.Comparator;

/**
 * Created by Spencer on 2/20/2017.
 */
public class CarComparator implements Comparator<Car> {
    @Override
    public int compare(Car car1, Car other) {
        return car1.make.compareTo(other.make);
    }
}
