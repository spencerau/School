/**
 * Created by Spencer on 7/6/2017.
 */
public class Test {

    public static void main(String[] args) {
        Transportation[] t = new Transportation[3];
        t[0] = new Car(2, 120, "Purdue", "Honda");
        System.out.println("Car Max Speed: " + t[0].getMaxSpeed());
        t[0].setDestination("Lafayette");
        System.out.println("Car Max Pass: " + t[0].getMaxNumPassengers());
        System.out.println("Car Type: " + t[0].getType());
        System.out.println("Car Dest: " + t[0].getDestination());

        System.out.println();

        Train a = new Train(20, 60, "Boston");
        a.changeNumPassengerCars(30);
        t[1] = a;
        t[1].setDestination("Lafayette");
        System.out.println("Train Max Speed: " + String.valueOf(t[1].getMaxSpeed()));
        System.out.println("Train Max Pass: " + String.valueOf(t[1].getMaxNumPassengers()));
        System.out.println("Train Type: " + t[1].getType());
        System.out.println("Train Dest: " + t[1].getDestination());

        System.out.println();

        String[] s = {"Boston", "Lafayette"};
        Bus b = new Bus(20, 60, 5, s);
        t[2] = b;
        t[2].setDestination("Lafayette");
        System.out.println("Bus Max Speed: " + String.valueOf(t[2].getMaxSpeed()));
        System.out.println("Bus Max Pass: " + String.valueOf(t[2].getMaxNumPassengers()));
        System.out.println("Bus Type: " + t[2].getType());
        System.out.println("Bus Dest: " + t[2].getDestination());
    }
}
