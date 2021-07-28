import java.util.Scanner;


/**
 * Created by Spencer on 6/20/2017.
 */
public class CollegeFeeCalculator {

    private static String name;
    private static int credHours;
    private static int residency;
    private static boolean onCampus;
    private static int tuition;
    private static int housing;

    private static boolean calcTuition() {
        Scanner s = new Scanner(System.in);
        //int credHours;
        boolean partTime;
        System.out.println("How many credit hours are you taking?");
        credHours = s.nextInt();
        if (credHours <= 0 || credHours > 21) return false;
        else {
            if (credHours > 0 && credHours < 9) partTime = true;
            else partTime = false;
        }
        System.out.println("Please select the appropriate residency\n1. In-state\n2. Out-of-state\n3. International");
        residency = s.nextInt();
        if (!partTime) {
            switch (residency) {
                case 1: tuition = 4996;
                        break;
                case 2: tuition = 14397;
                        break;
                case 3: tuition = 15397;
                        break;
                default: return false;
            }
        }
        else {
            switch (residency) {
                case 1: tuition = 350 * credHours;
                        break;
                case 2: tuition = 950 * credHours;
                        break;
                case 3: tuition = 1020 * credHours;
                        break;
                default: return false;
            }
        }
        return true;
    }

    private static boolean calcHousing() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please select your housing\n1. ON-Campus\n2. OFF-Campus");
        int campus = s.nextInt();
        if (campus == 1) {
            onCampus = true;
            System.out.println("Please select the residence hall\n1. Earhart\n2. Hillenbrand\n3. Owen\n4. Windsor");
            int dorm = s.nextInt();
            switch (dorm) {
                case 1:
                    housing = 4745;
                    break;
                case 2:
                    housing = 4150;
                    break;
                case 3:
                    housing = 5307;
                    break;
                case 4:
                    housing = 4130;
                    break;
                default:
                    return false;
            }
        } else if (campus == 2) {
            onCampus = false;
            System.out.println("Please input your rent:");
            int rent = s.nextInt();
            if (rent > 0) housing = rent;
            else return false;
        }
        else return false;

        System.out.println("Are you purchasing a meal plan?:\n1. Yes\n2. No");
        int mealPlan = s.nextInt();
        if (mealPlan == 1) housing += 1500;
        else if (mealPlan == 2) return true;
        else return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to CollegeFeeCalculator!");
        System.out.println("Please enter your name");
        name = s.next();
        if (!calcTuition()) return;
        if (!calcHousing()) return;

        String res = "";
        if (residency == 1) res = "In-state";
        else if (residency == 2) res = "Out-of-state";
        else if (residency == 3) res = "International";

        String onCamp = "";
        if (onCampus) onCamp = "On-Campus";
        else onCamp = "Off-Campus";

        int total = tuition + housing;

        System.out.println("\nName: " + name +
                "\nCredit Hours: " + credHours +
                "\nResidency: " + res +
                "\nHousing: " + onCamp +
                "\nTuition fee: " + tuition +
                "\nHousing Expense: " + housing +
                "\nTotal Sem. Fee: " + total);
    }

}
