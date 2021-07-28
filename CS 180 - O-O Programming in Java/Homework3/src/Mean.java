import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Spencer on 6/19/2017.
 */
public class Mean {
    /*
        private static int sum;
        private static int num;

        //private static boolean cont;

        public Mean() {
            sum = 0;
            num = 0;
            //cont = true;
        }
    /*
        private static void askNumber(Scanner s) {
            //Scanner s = new Scanner(System.in);
            System.out.println("Please enter your number:");
            try {
                sum += s.nextInt();
                num += 1;
            } catch (InputMismatchException e){
                System.out.println("Numbers only!");
                askNumber(s);
            }
        }

        private static void askContinue(Scanner s) {
            //Scanner s = new Scanner(System.in);
            System.out.println("Do you wish to enter another number?(y/n):");
            String bool = s.next();
            if (bool.equals("y")) {
                cont = true;
            }
            else if (bool.equals("n")) cont = false;
            else {
                System.out.println("Please enter y or n!");
                askContinue(s);
            }
        }
        */
    public static void main(String[] args) {
        int sum = 0;
        int num = 0;
        Scanner s = new Scanner(System.in);
        boolean cont = true;
        //boolean isNum = false;
        System.out.println("Welcome to the Mean Calculator!");
        while (cont) {
            //boolean isNum = false;
            while (true) {
                System.out.println("Please enter your number:");
                if (s.hasNextInt()) {
                    sum += s.nextInt();
                    num += 1;
                    break;
                } else {
                    System.out.println("Numbers only!");
                    s.next();
                }
            }
            while (true) {
                System.out.println("Do you wish to enter another number?(y/n):");
                if (s.hasNext()) {
                    String bool = s.next();
                    if (bool.equals("y")) {
                        break;
                    } else if (bool.equals("n")) {
                        cont = false;
                        break;
                    } else {
                        System.out.println("Please enter y or n!");
                    }
                }
            }
        }
        System.out.println("Sum: " + sum + " Num: " + num);
        System.out.println("Mean: " + (double) sum / num);
        System.out.println("Thank you for using Mean Calculator!");
    }
}