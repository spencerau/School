/**
 * CS180 - Lab 05
 *
 * This program is a Shape that implements the Circle, Triangle, and Rectangle shapes.
 *
 * @author Spencer Au, sau@purdue.edu, Lab 03
 *
 * @version 6/29/17
 */

public class Shape {

    private Circle cir;
    private Triangle tri;
    private Rectangle rec;

    public Shape(Circle cir, Triangle tri, Rectangle rec) {
        this.cir = cir;
        this.tri = tri;
        this.rec = rec;
    }

    public double getPerimemeter(Object o) {
        if (o instanceof Circle) return ((Circle) o).getCircumfrence();
        else if (o instanceof Triangle) return ((Triangle) o).getPerimeter();
        else if (o instanceof Rectangle) return ((Rectangle) o).getPerimeter();
        else return -1;
    }

    public double getArea(Object o) {
        if (o instanceof Circle) return ((Circle) o).getArea();
        else if (o instanceof Triangle) return ((Triangle) o).getArea();
        else if (o instanceof Rectangle) return ((Rectangle) o).getArea();
        else return -1;
    }

}
