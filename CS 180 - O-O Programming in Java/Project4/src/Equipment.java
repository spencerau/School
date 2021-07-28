/**
 * Created by Spencer on 7/18/2017.
 */
public class Equipment {

    private String weapon;
    private String vehicle;

    public Equipment(String weapon, String vehicle) {
        this.weapon = weapon;
        this.vehicle = vehicle;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

}
