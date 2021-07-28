/**
 * Created by Spencer on 7/18/2017.
 */
public class SuperHero {

    public Arsenal getArsenal() {
        return arsenal;
    }

    public void setArsenal(Arsenal arsenal) {
        this.arsenal = arsenal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Arsenal arsenal;
    private String name;

    public SuperHero(Arsenal arsenal, String name) {
        this.arsenal = arsenal;
        this.name = name;
    }
}
