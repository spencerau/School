/**
 * Created by Spencer on 7/6/2017.
 */
public interface Transportation {

    double getMaxSpeed();
    String getDestination();
    int getMaxNumPassengers();
    void setDestination(String destination);
    default String getType() {
        return "Public Transportation";
    }

}
