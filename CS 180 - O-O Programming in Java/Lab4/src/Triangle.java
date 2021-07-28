/**
 * CS180 - Lab 05
 *
 * This program is a Triangle.
 *
 * @author Spencer Au, sau@purdue.edu, Lab 03
 *
 * @version 6/29/17
 */

public class Triangle {

    private double base;

    public Triangle(double base) {
        this.base = base;
    }

    public double getPerimeter() {
        return 3*this.base;
    }

    public double getArea() {
        double height = this.base/2 * Math.sqrt(3);
        return (this.base*height)/2;
    }

}
