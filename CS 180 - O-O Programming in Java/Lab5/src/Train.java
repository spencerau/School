/**
 * Created by Spencer on 7/6/2017.
 */
public class Train implements Transportation {

    private int passengerCars;
    private double maxSpeed;
    private String destination;
    private int passengers;

    public Train(int passengerCars, double maxSpeed, String destination) {
        if (passengerCars < 1) this.passengerCars = 1;
        else this.passengerCars = passengerCars;
        if (maxSpeed < 0.0) this.maxSpeed = 0.0;
        else this.maxSpeed = maxSpeed;
        if (destination != null && !destination.isEmpty()) {
            this.destination = "";
        }
        else this.destination = destination;
        this.passengers = this.passengerCars * 40;
    }

    public void changeNumPassengerCars(int byNum) {
        this.passengerCars += byNum;
        this.passengers = this.passengerCars*40;
        if (this.passengerCars > 20 && this.maxSpeed > 80) this.maxSpeed = 80;
        else if (this.passengerCars > 40 && this.maxSpeed > 60) this.maxSpeed = 60;
        else if (this.passengerCars > 60 && this.maxSpeed > 40) this.maxSpeed = 40;
    }

    @Override
    public double getMaxSpeed() {
        changeNumPassengerCars(0);
        return this.maxSpeed;
    }

    @Override
    public String getDestination() {
        return this.destination;
    }

    @Override
    public int getMaxNumPassengers() {
        return this.passengers;
    }

    @Override
    public void setDestination(String destination) {
        this.destination = destination;
    }

}
