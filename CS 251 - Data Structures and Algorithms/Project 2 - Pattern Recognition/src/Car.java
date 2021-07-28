import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Car implements Comparable<Car> {

    private int price;
    public String make;

    public Car(String make, int price) {
        this.make = make;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                ", make='" + make + '\'' +
                '}';
    }

    @Override
    public int compareTo(Car other) {
        return other.price - this.price;
    }

    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car("BMW", 6));
        cars.add(new Car("Audi", 5));
        cars.add(new Car("Maserati", 10));
        cars.add(new Car("Honda", 1));

        System.out.println(cars);

        Collections.sort(cars, new CarComparator());

        System.out.println(cars);
    }

}
