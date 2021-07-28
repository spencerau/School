import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Spencer on 2/14/2017.
 */

public class Brute {

    private static ArrayList<Point> pointList;
    private static int length;
    private static int numCol;
    private static ArrayList<ArrayList<Point>> colPoints;

    public Brute() {
        numCol = 0;
        colPoints = new ArrayList<>();
    }

    /* Iterates through the points array, checking if any 4 points are collinear. If yes, then it adds the sorted array
    * to the global colPoints Array List */

    private void checkCollinear() {
        for (int i = 0; i < length-3; i++) {
            Point point = pointList.get(i);
            for (int a = i+1; a < length-2; a++) {
                Point pointA = pointList.get(a);
                for (int b = a+1; b < length-1; b++) {
                    Point pointB = pointList.get(b);
                    if (point.areCollinear(point, pointA, pointB)) {
                        for (int c = b+1; c < length; c++) {
                            Point pointC = pointList.get(c);
                            if (point.areCollinear(point, pointA, pointB, pointC)) {
                                numCol++;
                                ArrayList<Point> line = new ArrayList<>();
                                line.add(point);
                                line.add(pointA);
                                line.add(pointB);
                                line.add(pointC);
                                Collections.sort(line);
                                if (!colPoints.contains(line) && line.size() == 4) {
                                    colPoints.add(line);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String args[]) throws FileNotFoundException {

        //File file = new File("C:\\Users\\Spencer\\IdeaProjects\\CS 251\\Project 2 - Pattern Recognition\\src\\"+ "input6.txt");
        //File file = new File("C:\\Users\\Spencer\\IdeaProjects\\CS 251\\Project 2 - Pattern Recognition\\src\\" + "input8.txt");
        //File file = new File("C:\\Users\\Spencer\\IdeaProjects\\CS 251\\Project 2 - Pattern Recognition\\src\\" + "input50.txt");
        //File file = new File("C:\\Users\\Spencer\\IdeaProjects\\CS 251\\Project 2 - " +
          //      "Pattern Recognition\\src\\testcases\\input6.txt");
        Brute b = new Brute();
        Scanner s = new Scanner(System.in);
        //Scanner s = new Scanner(file);

        //Random r = new Random();

        pointList = new ArrayList<>();
        length = s.nextInt();
        //double start = System.nanoTime();

        for (int i = 0; i < length; i++) {
            int x = s.nextInt();
            int y = s.nextInt();
            //int x = r.nextInt(32767);
            //int y = r.nextInt(32767);
            pointList.add(new Point(x, y));
        }
        b.checkCollinear();
        colPoints = Point.sortColList(colPoints);
        Point.printColCoor(colPoints);

        //double end = System.nanoTime();
        //double runTime = (end - start)/1000000000;
        //System.out.println(runTime + " seconds");
    }

}
