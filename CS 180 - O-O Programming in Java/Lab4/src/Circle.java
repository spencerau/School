/**
 * CS180 - Lab 05
 *
 * This program is a Circle.
 *
 * @author Spencer Au, sau@purdue.edu, Lab 03
 *
 * @version 6/29/17
 */

public class Circle {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getCircumfrence() {
        return (2*this.radius*Math.PI);
    }

    public double getArea() {
        return (Math.PI * radius * radius);
    }

}
