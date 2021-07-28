import javax.swing.*;

/**
 * CS180 - Lab 05
 *
 * This program is a Shape that uses the Shape class.
 *
 * @author Spencer Au, sau@purdue.edu, Lab 03
 *
 * @version 6/29/17
 */

public class Driver {

    public static void main(String[] args) {
        Circle cir = null;
        Triangle tri = null;
        Rectangle rec = null;
        Shape sh = new Shape(cir, tri, rec);

        String shape = JOptionPane.showInputDialog(null,
                "What shape would you like to create?");
        if (shape.equalsIgnoreCase("circle")) {
            double radius = Double.parseDouble(JOptionPane.showInputDialog(null,
                    "Type in the Radius of the Circle"));
            cir = new Circle(radius);
            double p = sh.getPerimemeter(cir);
            double a = sh.getArea(cir);
            String output = String.format("Circumference: %.2f\nArea: %.2f\n", p, a);
            JOptionPane.showMessageDialog(null, output);
        }
        else if (shape.equalsIgnoreCase("triangle")) {
            double base = Double.parseDouble(JOptionPane.showInputDialog(null,
                    "Type in the Base of the Triangle"));
            tri = new Triangle(base);
            double p = sh.getPerimemeter(tri);
            double a = sh.getArea(tri);
            String output = String.format("Perimeter: %.2f\nArea: %.2f\n", p, a);
            JOptionPane.showMessageDialog(null, output);
        }
        else if (shape.equalsIgnoreCase("rectangle")) {
            double length = Double.parseDouble(JOptionPane.showInputDialog(null,
                    "Type in the Length of the Rectangle"));
            double width = Double.parseDouble(JOptionPane.showInputDialog(null,
                    "Type in the Width of the Rectangle"));
            rec = new Rectangle(length, width);
            double p = sh.getPerimemeter(rec);
            double a = sh.getArea(rec);
            String output = String.format("Perimeter: %.2f\nArea: %.2f\n", p, a);
            JOptionPane.showMessageDialog(null, output);
        }

    }

}
