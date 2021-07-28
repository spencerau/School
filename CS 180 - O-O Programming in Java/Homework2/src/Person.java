/**
 * Created by Spencer on 6/19/2017.
 */
public class Person {

    public Person() {
        height = 0;
        weight = 0;
        age = 0;
        color = "";
    }

    public Person(int height, int weight, int age, String color) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.color = color;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private int height;
    private int weight;
    private int age;
    private String color;

}
