/**
 * CS180 - Lab 05
 *
 * This program is a Rectangle.
 *
 * @author Spencer Au, sau@purdue.edu, Lab 03
 *
 * @version 6/29/17
 */

public class Rectangle {

    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getPerimeter() {
        return (2*this.length + 2*this.width);
    }

    public double getArea() {
        return this.length*this.width;
    }

}
