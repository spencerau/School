import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Spencer on 2/3/2017.
 */

public class PercolationVisualization {

    public static void main(String args[]) throws IOException {

        //File file = new File("C:\\Users\\Spencer\\IdeaProjects\\CS 251\\Project 1 - Percolation\\src\\DemoYes.txt");
        //File file = new File("C:\\Users\\Spencer\\IdeaProjects\\CS 251\\Project 1 - Percolation\\src\\DemoNo.txt");
        //File file = new File(args[0]);
        File file = new File("C:\\Users\\Spencer\\IdeaProjects\\CS 251\\Project 1 - Percolation\\src\\testCase6.txt");
        PrintWriter pW = new PrintWriter("visualMatrix.txt");

        Scanner s = new Scanner(file);
        //Scanner s = new Scanner(System.in); // should be this
        int size = s.nextInt();
        String N = Integer.toString(size);
        Percolation p = new Percolation(size);
        System.out.print(N);
        pW.print(size);
        System.out.println();
        pW.println();
        System.out.println();
        pW.println();
        //p.printGrid();
        //p.printFile(pW);

        while (s.hasNextInt()) {
            int x = s.nextInt();
            int y = s.nextInt();
            p.open(x, y);
            p.printGrid();
            p.printFile(pW);
        }

        pW.flush();
        pW.close();
    }
}
