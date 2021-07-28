import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Spencer on 2/14/2017.
 */

public class Fast {

    private static ArrayList<Point> pointList;
    private static int length;
    private static ArrayList<ArrayList<Point>> colPoints;

    public Fast() {
        length = 0;
        colPoints = new ArrayList<>();
    }

    private ArrayList<Point> createAngleList(Point p) {
        ArrayList<Point> list = new ArrayList<>();
        for (int y = 0; y < pointList.size(); y++) {
            Point compPoint = pointList.get(y);
            if (compPoint != p) {
                compPoint.slopeWithP = p.getSlope(compPoint);
                list.add(compPoint);
            }
        }
        return list;
    }

    private ArrayList<ArrayList<Point>> findSameAngle(ArrayList<Point> pointList) {
        ArrayList<ArrayList<Point>> colPoints = new ArrayList<>();

        for (int i = 0; i < pointList.size(); i++) {
            Point p = pointList.get(i);
            ArrayList<Point> list = createAngleList(p);
            //System.out.println("Non Sorted");
            //printArray(list);
            list.sort(p.BY_SLOPE_ORDER);
            //System.out.println("Sorted");
            //printArray(list);

            ArrayList<Point> colinearPoints = new ArrayList<>();

            colinearPoints.add(p);
            colinearPoints.add(list.get(0));
            float sameSlope = list.get(0).slopeWithP;

            for (int j = 1; j < list.size(); j++) {
                Point sortedPoint = list.get(j);
                if (sortedPoint.slopeWithP == sameSlope) {
                    colinearPoints.add(sortedPoint);
                } else {
                    sameSlope = sortedPoint.slopeWithP;

                    if (colinearPoints.size() >= 4) {
                        Collections.sort(colinearPoints);
                        if (!colPoints.contains(colinearPoints)) {
                            colPoints.add(colinearPoints);
                        }
                    }

                    colinearPoints = new ArrayList<>();
                    colinearPoints.add(p);
                    colinearPoints.add(sortedPoint);
                }
            }
            if (colinearPoints.size() >= 4) {
                Collections.sort(colinearPoints);
                if (!colPoints.contains(colinearPoints)) {
                    colPoints.add(colinearPoints);
                }
            }
        }
        return colPoints;
    }

    private void sortColPoints() {
        for (int i = 0; i < colPoints.size(); i++) {
            Collections.sort(colPoints.get(i));
        }
    }

    public static void printArray(ArrayList<Point> list) {
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).slopeWithP);
            if (i != list.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
        System.out.println();
    }

    /* parses through the input list, removing all the respective elements from list of points - pointList */

    private void remove(ArrayList<Point> list) {

    }

    public static void main(String args[]) throws FileNotFoundException {

        //File file = new File("C:\\Users\\Spencer\\IdeaProjects\\CS 251\\Project 2 - Pattern Recognition\\src\\"+ "input6.txt");
        //File file = new File("C:\\Users\\Spencer\\IdeaProjects\\CS 251\\Project 2 - Pattern Recognition\\src\\" + "input8.txt");
        //File file = new File("C:\\Users\\Spencer\\IdeaProjects\\CS 251\\Project 2 - Pattern Recognition\\src\\" + "input50.txt");
        //File file = new File(System.in);
        //File file = new File("C:\\Users\\Spencer\\IdeaProjects\\CS 251\\Project 2 - " +
                //"Pattern Recognition\\src\\testcases\\input10.txt");
        Fast f = new Fast();
        Scanner s = new Scanner(System.in);
        //Scanner s = new Scanner(file);

        //Random r = new Random();

        length = s.nextInt();
        //double start = System.nanoTime();
        pointList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int x = s.nextInt();
            int y = s.nextInt();
            //int x = r.nextInt(32767);
            //int y = r.nextInt(32767);
            pointList.add(new Point(x, y));
        }
        colPoints = f.findSameAngle(pointList);
        f.sortColPoints();
        colPoints = Point.sortColList(colPoints);
        Point.printColCoor(colPoints);

        //double end = System.nanoTime();
        //double runTime = (end - start)/1000000000;
        //System.out.println(runTime + " seconds");
    }

}
