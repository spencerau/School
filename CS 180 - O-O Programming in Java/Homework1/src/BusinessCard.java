import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * Created by Spencer on 6/12/2017.
 */
public class BusinessCard {

    public static void main(String args[]) throws IOException {
        Scanner s = new Scanner(System.in);
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //s.useDelimiter("\\s*,\\s*");
        NumberFormat f = new DecimalFormat("#0.00");

        System.out.print("What is your name?");
        String name = s.nextLine();
        System.out.print("What is your age?");
        int age = s.nextInt();
        System.out.print("What is your GPA?");
        double GPA = s.nextDouble();
        //s.nextLine();
        System.out.print("What is your major?");
        //String major = br.readLine();
        s.nextLine();
        String major = s.nextLine();
        //s.nextLine();
        System.out.print("What is your email?");
        //String email = br.readLine();
        //s.nextLine();
        String email = s.nextLine();
        System.out.println();
        System.out.println("Name:  " + name);
        System.out.println("Age:   " + age);
        System.out.println("GPA:   " + f.format(GPA));
        System.out.println("Major: " + major);
        System.out.print("Email: " + email);

        //br.close();
    }
}
