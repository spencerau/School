/**
 * Created by Spencer on 7/18/2017.
 */
public class Arsenal {

    private Equipment equipment;
    private SuperPowers superPowers;

    public Arsenal(Equipment equipment, SuperPowers superPowers) {
        this.equipment = equipment;
        this.superPowers = superPowers;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public Equipment getEquips() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public SuperPowers getSuperPowers() {
        return superPowers;
    }

    public void setSuperPowers(SuperPowers superPowers) {
        this.superPowers = superPowers;
    }
}
