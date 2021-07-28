import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Spencer on 2/7/2017.
 */
public class Percolation {

    int[][] grid;
    int row;
    int col;
    int size;
    WeightedQuickUnionUF QUF;

    /* Create a new n by n grid where all cells are initially blocked
    * 0 = blocked
    * 1 = open
    * 2 = full of water */

    public Percolation(int n) {
        this.grid = new int[n][n];
        this.row = n;
        this.col = n;
        this.size = n;
        this.QUF = new WeightedQuickUnionUF(n*n);
    }

    public void printFile(PrintWriter pW) {
        for (int i = row-1; i >= 0; i--) {
            for (int j = 0; j < col; j++) {
                if (isOpen(i,j)) {
                    if (grid[i][j] == 2) {
                        pW.write("2");
                    }
                    else if (grid[i][j] == 1) {
                        pW.write("1");
                    }
                }
                else {
                    pW.write("0");
                }
                pW.write(" ");
            }
            pW.println();
        }
        pW.println();
        /*
        for (int k = 0; k < col; k++) {
            pW.write("-");
            pW.write("-");
        }
        */
        //pW.println();
    }

    public void printGrid() {
        for (int i = row-1; i >= 0; i--) {
            for (int j = 0; j < col; j++) {
                if (isOpen(i,j)) {
                    if (grid[i][j] == 2) {
                        System.out.print(2);
                    }
                    else if (grid[i][j] == 1) {
                        System.out.print(1);
                    }
                }
                else {
                    System.out.print(0);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
        /*
        for (int k = 0; k < col; k++) {
            System.out.print("-");
            System.out.print("-");
        }
        */
        //System.out.println();
    }

    /* Creates a main method that reads a description of a grid from standard input and validates if the system
    described percolates or not, printing to standard output a simple "Yes" or "No" answer. */

    public static void main(String[] args) throws IOException {

        //File file = new File("C:\\Users\\Spencer\\IdeaProjects\\CS 251\\Project 1 - Percolation\\src\\DemoYes.txt");
        //File file = new File("C:\\Users\\Spencer\\IdeaProjects\\CS 251\\Project 1 - Percolation\\src\\DemoNo.txt");
        File file = new File(args[0]);
        //Scanner s = new Scanner(System.in); // should be this
        Scanner s = new Scanner(file);
        Percolation p = new Percolation(s.nextInt());
        while (s.hasNextInt()) {
            p.open(s.nextInt(),s.nextInt());
        }
        if (p.percolates()) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
    }

    /* WRITE A FUNCTION THAT CONVERTS NEGATIVE INDEXES TO POSITIVE ONES */

    public int absoluteValue(int x) {
        if (x < 0) {
            return x * -1;
        }
        else {
            return x;
        }
    }

    /* Open the site at coordinate (x,y), where x represents the row number and y the column  number. For consistency
    purposes, (0,0) will be the bottom-left cell of the grid and (n-1, n-1) will be on the top-right. The graphical
    capabilities discussed later assume a similar convention. */

    public void open(int x, int y) {
        if (x < 0 || x > row - 1 || y < 0 || y > col - 1) {
            throw new IndexOutOfBoundsException("Index is not valid");
        }
        //int X = absoluteValue(x-size+1);
        this.grid[x][y] = 1;
        propogate(x,y);
    }

    public void propogate(int x, int y) {
        if (x < 0 || x > row - 1 || y < 0 || y > col - 1) {
            throw new IndexOutOfBoundsException("Index is not valid");
        }
        //int X = absoluteValue(x - size + 1);

        if (x == row - 1) {
            this.grid[x][y] = 2;
        }

        // left
        if (y > 0) {
            //left = this.grid[x][y - 1];
            if (this.grid[x][y - 1] == 2) {
                this.grid[x][y] = 2;
            }
        }
        // right
        if (y < col - 1) {
            //right = this.grid[x][y + 1];
            if (this.grid[x][y + 1] == 2) {
                this.grid[x][y] = 2;
            }
        }
        //top
        if (x < row-1) {
            //top = this.grid[x + 1][y];
            if (this.grid[x + 1][y] == 2) {
                this.grid[x][y] = 2;
            }
        }
        // bottom
        if (x > 0) {
            //bot = this.grid[x - 1][y];
            if (this.grid[x - 1][y] == 2) {
                this.grid[x][y] = 2;
            }
        }
        //int cent = this.grid[x][y];

        // checking if bottom is open and if center has water
        if (x > 0) {
            /*
            bot = this.grid[x-1][y];
            botVal = y+((x-1)*size);
            */

            if (this.grid[x-1][y] == 1 && this.grid[x][y] == 2) {
                QUF.union(y+(x*size),y+((x-1)*size));
                propogate(x - 1, y);
            }
        }

        // checking if left is open and center has water
        if (y > 0) {
            /*
            left = this.grid[x][y - 1];
            leftVal = (y - 1) + (x * size);
            */
            if (this.grid[x][y - 1] == 1 && this.grid[x][y] == 2) {
                QUF.union(y+(x*size),(y - 1) + (x * size));
                propogate(x, y - 1);
            }
        }
        // checking if right is open and if center has water
        if (y < col-1) {
            /*
            right = this.grid[x][y + 1];
            rightVal = (y + 1) + (x * size);
            */
            if (this.grid[x][y + 1] == 1 && this.grid[x][y] == 2) {
                QUF.union(y+(x*size),(y + 1) + (x * size));
                propogate(x, y + 1);
            }
        }
        // checking if top is open and if center has water
        if (x < row-1) {
            /*
            top = this.grid[x+1][y];
            topVal = y+((x+1)*size);
            */
            if (size <= 250) {
                if (this.grid[x + 1][y] == 1 && this.grid[x][y] == 2) {
                    QUF.union(y + (x * size), y + ((x + 1) * size));
                    propogate(x + 1, y);
                }
            }
        }
    }

    public boolean isOpen(int x, int y) {
        //int X = absoluteValue(x-size+1);
        if (grid[x][y] == 1 || grid[x][y] == 2) {
            return true;
        }
        else {
            return false;
        }
    }

    /* Returns true if there is a path from cell (x,y) to the surface (i.e. there is percolation up to this cell) */

    public boolean isFull(int x, int y) {
        if (isOpen(x,y)) {
            //int X = absoluteValue(x-row+1);
            if (grid[x][y] == 2) {
                for (int i = 0; i < col; i++) {
                    if (grid[0][i] == 2) {
                        return true;
                    }
                }
            }
        }
        else {
            return false;
        }
        return false;
    }

    /* Analyzes the entire grid and returns true if the whole system percolates */

    public boolean percolates() {
        for (int i = 0; i < col; i++) {
            if (this.grid[0][i] == 2) {
                return true;
            }
            //if (isFull(row-1, i)) {
            //    return true;
            //}
        }
        return false;
    }
}
