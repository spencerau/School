/**
 * Created by Spencer on 7/6/2017.
 */
public class Bus implements Transportation {

    private int next;
    private int rows;
    private double maxSpeed;
    private String[] destinations;

    public Bus(int next, int rows, double maxSpeed, String[] destinations) {
        if (destinations == null) {
            this.destinations = new String[1];
            this.destinations[0] = "";
        }
        else this.destinations = destinations;
        this.next = next;
        if (rows < 1) this.rows = 1;
        else this.rows = rows;
        if (maxSpeed < 0.0) this.maxSpeed = 0.0;
        else this.maxSpeed = maxSpeed;
    }

    public void stop() {
        if (this.next >= this.destinations.length) this.next = 0;
        else this.next++;
    }

    @Override
    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    @Override
    public String getDestination() {
        if (this.next >= this.destinations.length) {
            this.next = 0;
            return destinations[0];
        }
        else return destinations[next];
    }

    @Override
    public int getMaxNumPassengers() {
        return this.rows * 4;
    }

    @Override
    public void setDestination(String destination) {
        if (destination != null && !destination.isEmpty()) {
            int index = destination.indexOf(destination);
            if (index != -1) this.next = index;
            else destinations[next] = destination;
        }
    }
}
