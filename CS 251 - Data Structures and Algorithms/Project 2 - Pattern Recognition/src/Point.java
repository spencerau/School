/*************************************************************************
 * Compilation:  javac Point.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import static java.sql.Types.FLOAT;
import static java.sql.Types.NULL;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> BY_SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            float diff = (getSlope(o1) - getSlope(o2));
            if (diff > 0) {
                return 1;
            } else if (diff == 0) {
                return 0;
            } else {
                return -1;
            }
        }
    };

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    public float slopeWithP;

    // constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public float getSlope(Point point) {
        Point p = new Point(this.x, this.y);
        return getSlope(point, p);
    }

    /* x comp = index 0, y comp = index 1 */

    public static float getSlope(Point a, Point b) {
        float x1 = a.getX();
        float y1 = a.getY();
        float x2 = b.getX();
        float y2 = b.getY();

        if (x1 == x2) {
            return Float.POSITIVE_INFINITY;
        }
        else if (y1 == y2) {
            return 0;
        }
        else {
            return (y2 - y1)/(x2 - x1);
        }
    }

    // are the 3 points p, q, and r collinear?

    public static boolean areCollinear(Point p, Point q, Point r) {
        float slopePQ = getSlope(p, q);
        //System.out.println(slopePQ);
        float slopeQR = getSlope(q, r);
        //System.out.println(slopeQR);
        //float slopeRP = getSlope(r, p);
        //System.out.println(slopeRP);
        double epsilon = 0.00000001;
        if (Math.abs(slopePQ - slopeQR) < epsilon) { /*&& Math.abs(slopeQR - slopeRP) < epsilon
                && Math.abs(slopeRP - slopePQ) < epsilon) {*/
            return true;
        }
        return false;
    }

    // are the 4 points p, q, r, and s collinear?

    public static boolean areCollinear(Point p, Point q, Point r, Point s) {
        if (areCollinear(p, q, r) && areCollinear(p, q, s)) {
            return true;
        }
        return false;
    }

    // is this point lexicographically smaller than that one?

    public int compareTo(Point that) {
        if (that.getX() == this.x) {
            return this.y - that.getY();
        } else {
            return this.x - that.getX();
        }
    }

    /* Sorts the List of Collinear Coordinate sets so that the "smallest" sets appear first */

    public static ArrayList<ArrayList<Point>> sortColList(ArrayList<ArrayList<Point>> colPoints) throws FileNotFoundException {
        colPoints.sort(new Comparator<ArrayList<Point>>() {
            @Override
            public int compare(ArrayList<Point> list1, ArrayList<Point> list2) {
                for (int i = 0; i < list1.size(); i++) {
                    Point a = list1.get(i);
                    Point b = list2.get(i);
                    if (a.getX() < b.getX()) {
                        return -1;
                    } else if (a.getX() > b.getX()) {
                        return 1;
                    } else {
                        if (a.getY() < b.getY()) {
                            return -1;
                        } else if (a.getY() > b.getY()) {
                            return 1;
                        }
                    }
                }
                return 0;
            }
        });
        return colPoints;
    }

    /* Prints out the List of Collinear Coordinate Points, outputting to both the terminal the visualPoints.txt */

    public static void printColCoor(ArrayList<ArrayList<Point>> colPoints) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("visualPoints.txt");
        //System.out.println("Size: " + colPoints.size());
        //System.out.println();
        for (int i = 0; i < colPoints.size(); i++) {
            ArrayList<Point> array = colPoints.get(i);
            // take out the whitespace in the 2 lines below
            System.out.print(array.size() + ":");
            pw.print(array.size() + ":");
            for (int j = 0; j < array.size(); j++) {
                System.out.print("(" + array.get(j).getX() + ", " + array.get(j).getY() + ")");
                pw.print("(" + array.get(j).getX() + ", " + array.get(j).getY() + ")");
                if (j != array.size()-1) {
                    System.out.print(" -> ");
                    pw.print(" -> ");
                }
            }
            System.out.println();
            pw.println();
        }
        pw.flush();
        pw.close();
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
