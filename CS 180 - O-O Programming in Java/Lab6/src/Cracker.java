import java.util.ArrayList;

/**
 * Created by Spencer on 7/16/2017.
 */
public class Cracker implements Runnable{

    private int id;
    private int start;
    private int end;
    //public static String guess;
    //public static String guess2;
    //public static String guess3;
    private String possible;
    private static ArrayList<Character> password;

    public Cracker(int id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
        possible = "abcdefghijklmnopqrstuvwxyz0123456789";
        password = new ArrayList<>();
    }

    public void run() {
        System.out.println("Thread(" + id + ") is running!");

        for (int i = start; i < end; i++) {
            for (int j = 0; j < possible.length(); j++) {
                if (Password.guess(String.valueOf(possible.charAt(j)), i)) {
                    password.add(i, possible.charAt(j));
                }
            }
        }
        System.out.println("Thread(" + id + ") got: " + password.toString());
    }

    public static String smartCrack(int length) {
        int start1 = 0;
        int end1 = length/3;
        int start2 = end1 + 1;
        int end2 = length/3 * 2;
        int start3 = end2 + 1;
        int end3 = length;
        String password = "";

        Thread t1 = new Thread(new Cracker(1, start1, end1));
        Thread t2 = new Thread(new Cracker(2, start2, end2));
        Thread t3 = new Thread(new Cracker(3, start3, end3));
        t1.start();
        t2.start();
        t3.start();
        try {
            t3.join();
            t2.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return password;
    }

    public static void main(String args) throws InterruptedException {
        Password.generatePass(50);
        //Thread thread1 = new Thread(new Cracker(1, 0, 50));
        //thread1.start();
        //thread1.join();
        smartCrack(50);
        System.out.println("The Final Password is:" + password.toString());

        /*

        if (Password.guess(guess)) {
            System.out.println("The Final password is: " + guess);
        }
        else {
            System.out.println("We failed...");
        }
        */
    }

}
