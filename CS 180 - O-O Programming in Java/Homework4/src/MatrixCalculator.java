import java.util.Scanner;

/**
 * Created by Spencer on 6/26/2017.
 */
public class MatrixCalculator {

    private static int row1;
    private static int row2;
    private static int col1;
    private static int col2;
    private static int[][] matrix1;
    private static int[][] matrix2;

    private static void initializeMatrix() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the size of the first Matrix(format ROW x COL)");
        System.out.println("Enter row");
        row1 = s.nextInt();
        System.out.println("Enter column");
        col1 = s.nextInt();
        matrix1 = new int[row1][col1];
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                System.out.println("Enter the number for row " + i + " and column " + j);
                matrix1[i][j] = s.nextInt();
            }
        }
        System.out.println("Enter the size of the second Matrix(format ROW x COL)");
        System.out.println("Enter row");
        row2 = s.nextInt();
        System.out.println("Enter column");
        col2 = s.nextInt();
        matrix2 = new int[row2][col2];
        for (int i = 0; i < row2; i++) {
            for (int j = 0; j < col2; j++) {
                System.out.println("Enter the number for row " + i + " and column " + j);
                matrix2[i][j] = s.nextInt();
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void addMatrix() {
        if (row1 != row2 || col1 != col2) System.out.println("Matrices size are not the same!");
        else {
            int sumMatrix[][] = new int[row1][col1];
            for (int i = 0; i < row1; i++) {
                for (int j = 0; j < col1; j++) {
                    sumMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            }
            printMatrix(sumMatrix);
        }
    }
    /*
    private static void multiMatrix() {
        if (col1 != row2) System.out.println("Matrix size doesn't fit requirement!");
        else {
            int[][] prodMatrix = new int[row1][col2];
            //int row = 0;
            //int col = 0;
            for (int[] row : matrix1) {

            }
            for (int i = 0; i < prodMatrix.length; i++) {
                int count = 0;
                int product = matrix1[count][i] * matrix2[i][count];
                int j;
                for (j = 0; j < prodMatrix[0].length; j++) {
                    product += matrix1[i][j] * matrix2[j][i];
                }
                prodMatrix[row][col] = product;
                row++;
                col++;
            }
        }
    }*/

    public static void main(String[] args) {
        //initializeMatrix();
        matrix1 = new int[][]{
                {1, 2},
                {3, 4}
        };
        matrix2 = new int[][] {
                {1},
                {2}
        };
        System.out.println("Matrix 1 is:");
        printMatrix(matrix1);
        System.out.println("Matrix 2 is:");
        printMatrix(matrix2);
        System.out.println("Result after addition");
        addMatrix();
        System.out.println("Result after multiplication");
        //multiMatrix();
    }

}
