
public class Matrix {
    public static void main(String[] args) {
        /* ---------------------------------------------------
        *
        * Part1: isSymmetric
        * TODO: Debug
        * m1 is not symmetric
        * m2 is symmetric matrix
        *
        * ---------------------------------------------------*/
        System.out.println("Part 1");
        Matrix m = new Matrix();
        Boolean isSymmetric = true;
        Boolean isDiagonal = true;
        Boolean isTridiagonal = true;
        int[][] m1 = {
                {0, 1, 3},
                {1, 0, 1},
                {2, 1, 0}
        };
        int[][] m2 = {
                {0, 1, 2, 3},
                {1, 0, 1, 2},
                {2, 1, 0, 1},
                {3, 2, 1, 0}
        };
        if (m1.length != m1[0].length)
            isSymmetric = false;
        else {
            for (int i = 0; i < m1.length; i++) {
                for (int j = 0; j < m1[0].length; j++) {
                    if (m1[i][j] != m1[j][i])
                        isSymmetric = false;
                }
            }
        }
        System.out.println("false - " + isSymmetric);

        // reset isSymmetric to true;
        isSymmetric = true;
        if (m2.length != m2[0].length)
            isSymmetric = false;
        for(int i = 0; i < m2.length; i++) {
            for(int j = 0; j < m2[0].length; j++) {
                if (m2[i][j] != m2[j][i])
                    isSymmetric = false;
            }
        }
        System.out.println("true - " + isSymmetric);


        /* ---------------------------------------------------
        *
        * Part2: isDiagonal
        * TODO: Debug
        * both m1 and m2 are Diagonal matrices
		*
        * ---------------------------------------------------*/
        System.out.println("Part 2");
        m1 = new int[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        m2 = new int[][]{
                {1, 0, 0, 0},
                {0, 3, 0, 0},
                {0, 0, -2, 0}
        };

        if (m1.length != m1[0].length)
            isDiagonal = false;
        for(int i = 0; i < m1.length; i++) {
            for(int j = 0; j < m1[0].length; j++) {
                if(i != j && m1[i][j] != 0)
                    isDiagonal = false;
            }
        }
        System.out.println("true - " + isDiagonal);

        // reset isDiagonal to True
        isDiagonal = true;
        /*
        if (m2.length != m2[0].length)
            isDiagonal = false;
        */
        for(int i = 0; i < m2.length; i++) {
            for(int j = 0; j < m2[0].length; j++) {
                if(i != j && m2[i][j] != 0)
                    isDiagonal = false;
            }
        }
        System.out.println("true - " + isDiagonal);

        /* ---------------------------------------------------
        *
        * Part3: isTridiagonal
        * TODO: Debug
        *
        * ---------------------------------------------------*/
        System.out.println("Part 3");
        m1 = new int[][] {
                {3, 5, 0},
                {10, 2, 5},
                {0, 3, 1}
        };

        m2 =new int[][] {
                {0, 1, 2, 3},
                {1, 0, 1, 2},
                {2, 1, 0, 1}
        };

        int[][] m3 = {
                {3, 3, 0, 0},
                {-4, 2, 53, 0},
                {0, 34, 1, 23},
                {0, 0, -3, 312414}
        };

        isTridiagonal = isTriadiagonal(m1);
        System.out.println("true - " + isTridiagonal);

        // reset isTridiagonal to True
        isTridiagonal = isTriadiagonal(m2);
        System.out.println("false - " + isTridiagonal);

        // reset isTridiagonal to True
        //isTridiagonal = false;
        isTridiagonal = isTriadiagonal(m3);
        System.out.println("true - " + isTridiagonal);

        /* ---------------------------------------------------
        *
        * TODO: implement isIdentity
        * Write your code outside the block
        *
        * --------------------------------------------------- */

        /* ---------------------------------------------------
        *
        * TODO: implement isUpperTriangular
        * Write your code outside the block
        *
        * --------------------------------------------------- */

        System.out.println("Part 4A - IsIdentity");

        int[][] i = new int[][] {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        boolean isIdentity = isIdentity(i);
        System.out.println("true - " + isIdentity(i));

        isIdentity = isIdentity(m1);
        System.out.println("false - " + isIdentity(m1));

        isIdentity = isIdentity(m2);
        System.out.println("false - " + isIdentity(m2));

        isIdentity = isIdentity(m3);
        System.out.println("false - " + isIdentity(m3));



        System.out.println("Part 4B - Is UpperTriangular");
        boolean isUT = isUpperTriangular(i);
        System.out.println("true - " + isIdentity(i));

        isUT = isUpperTriangular(m1);
        System.out.println("false - " + isIdentity(m1));

        isUT = isUpperTriangular(m2);
        System.out.println("false - " + isIdentity(m2));

        isUT = isUpperTriangular(m3);
        System.out.println("false - " + isIdentity(m3));

    }

    private static boolean isTriadiagonal(int[][] matrix) {
        if (matrix.length != matrix[0].length) return false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i != j && Math.abs(i - j) > 1) {
                    if (matrix[i][j] != 0) return false;
                }
            }
        }
        return true;
    }

    private static boolean isIdentity(int[][] matrix) {
        if (matrix.length != matrix[0].length) return false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == j) {
                    if (matrix[i][j] != 1) return false;
                }
                else {
                    if (matrix[i][j] != 0) return false;
                }
            }
        }
        return true;
    }

    private static boolean isUpperTriangular(int[][] matrix) {
        if (matrix.length != matrix[0].length) return false;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i != j && i > j) {
                    if (matrix[i][j] != 0) return false;
                }
            }
        }
        return true;
    }

}