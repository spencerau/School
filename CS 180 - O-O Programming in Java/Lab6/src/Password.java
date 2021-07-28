import java.util.Random;
import java.util.UUID;

/**
 * Created by Spencer on 7/16/2017.
 */
public class Password {

    private static String password;

    public static boolean guess(String guess) {
        return (guess.equals(password));
    }

    public static boolean guess(String guess, int index) {
        return (guess.charAt(index) == password.charAt(index));
    }

    public static void generatePass(int length) {
        Random r = new Random();
        String choices = "abcdefghijklmnopqrstuvwxyz0123456789";
        password = "";
        int index;
        for (int i = 0; i < length; i++) {
            index = r.nextInt(36);
            password += choices.charAt(index);
        }
    }
    /*
    public static void main(String[] args) {
        generatePass(9);
        System.out.println(password);
    }
    */

}
