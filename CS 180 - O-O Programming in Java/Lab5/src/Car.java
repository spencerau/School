/**
 * Created by Spencer on 7/6/2017.
 */
public class Car implements Transportation {

    private int seats;
    private double maxSpeed;
    private String destination;
    private String makeAndModel;

    public Car(int seats, double maxSpeed, String destination, String makeAndModel) {
        if (seats < 1) this.seats = 1;
        else this.seats = seats;
        if (maxSpeed < 0.0) this.maxSpeed = 0.0;
        else this.maxSpeed = maxSpeed;
        if (destination != null && !destination.isEmpty()) {
            this.destination = "";
        }
        else this.destination = destination;
        if (makeAndModel != null && !makeAndModel.isEmpty()) {
            this.makeAndModel = "";
        }
        else this.makeAndModel = makeAndModel;
    }

    @Override
    public double getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public String getDestination() {
        return this.destination;
    }

    @Override
    public int getMaxNumPassengers() {
        return this.seats;
    }

    @Override
    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String getType() {
        return "Private Transportation";
    }
}
