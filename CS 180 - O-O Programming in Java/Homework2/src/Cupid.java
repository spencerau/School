import java.util.Scanner;

/**
 * Created by Spencer on 6/19/2017.
 */
public class Cupid {

    private static Person createPerson(Scanner s) {
        Person person = new Person();
        //Scanner s = new Scanner(System.in);
        System.out.println("What is the height?");
        person.setHeight(s.nextInt());
        System.out.println("What is the weight?");
        person.setWeight(s.nextInt());
        System.out.println("What is the age?");
        person.setAge(s.nextInt());
        System.out.println("What is the favorite color?");
        person.setColor(s.next());
        return person;
    }

    private static int heightScore(int h1, int h2) {
        int diff = h1 - h2;
        diff = Math.abs(diff);
        return diff % 6;
    }

    private static int weightScore(int s1, int s2) {
        int diff = s1 - s2;
        diff = Math.abs(diff);
        int score = 5 - (diff/10);
        if (score < 0) return 0;
        else return score;
    }

    private static int ageScore(int a1, int a2) {
        int diff = a1 - a2;
        diff = Math.abs(diff);
        int score = 5 - (diff/3);
        if (score < 0) return 0;
        else return score;
    }

    private static int colorScore(String c1, String c2) {
        if (c1.toLowerCase().equals(c2.toLowerCase())) {
            return 5;
        }
        else return 2;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("User 1! It's your turn");
        Person person1 = createPerson(s);
        System.out.println("User 2! It's your turn");
        Person person2 = createPerson(s);


        //Person person1 = new Person(63, 60, 21, "Fushia");
        //Person person2 = new Person(65, 100, 20, "Periwinkle");

        int sum = 0;
        sum += heightScore(person1.getHeight(), person2.getHeight());
        sum += weightScore(person1.getWeight(), person2.getWeight());
        sum += ageScore(person1.getAge(), person2.getAge());
        sum += colorScore(person1.getColor(), person2.getColor());

        double score = sum/20.0;
        if (score == 1) {
            System.out.println("Looks like you found the 1!");
        }
        else {
            System.out.println("You two are: " + score + " compatible!");
        }
    }



}
