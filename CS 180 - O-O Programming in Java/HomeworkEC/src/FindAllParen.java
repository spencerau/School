import java.util.ArrayList;
import java.util.Scanner;


public class FindAllParen {

    static int allPoss;

    /**
     * add all of the combinations to the list
     *
     * @param list list to store all of the combinations
     * @param leftParen number of remaining left parentheses
     * @param rightParen number of remaining right parentheses
     * @param str a character array to store one combination
     * @param i index of a particular character in str
     */

    public static void addParen(ArrayList<String> list, int leftParen, int rightParen, char[] str, int i) {
		// Todo: Follow the algorithm provided to implement the base cases and recursive steps

        if (rightParen == 0) {
            list.add(String.valueOf(str));
            //addParen(list, str.length/2, str.length/2, str, 0);
            return;
        }

        if (leftParen < rightParen) {
            str[i] = ')';
            addParen(list, leftParen, rightParen-1, str, i+1);
        }
        if (leftParen > 0) {
            str[i] = '(';
            addParen(list, leftParen-1, rightParen, str, i+1);
        }
    }

    /**
     * Prints all of the combinations of n pairs of parentheses
     *
     * @param n number of parentheses
     * @return a list containing all of the combinations of n pairs of parentheses
     */

    public static ArrayList<String> printParen(int n) {
        // Number of characters
        char[] str = new char[2 * n];

        allPoss = (int) Math.pow(2, n);

        // List to store the combinations
        ArrayList<String> list = new ArrayList<>();

        // Todo: Implement the method addParen
        addParen(list, n, n, str, 0);

        for (String i : list)
            System.out.println(i);
        return list;
    }

    public static void main(String[] args) {
        //printParen(3);
    }
}
