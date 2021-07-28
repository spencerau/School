import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;

/**
 * A program that allows a user to add, edit, delete, and view snacks in an inventory.
 *
 * <p>Lab -- File IO</p>
 *
 * @author Logan Kulinski
 * @version June 12, 2017
 */
public class SnackInventory {
    /**
     * The list of snacks in the inventory.
     */
    private List<Snack> snackList;

    /**
     * The next ID of the snack in the inventory.
     */
    private static int nextId;

    /**
     * The scanner used to read user input.
     */
    private final Scanner input;

    static {
        nextId = 0;
    } //static

    /**
     * Constructs a newly allocated {@code SnackInventory} object.
     */
    private SnackInventory() {
        this.snackList = new ArrayList<>();
        this.input = new Scanner(System.in);

        this.readInSavedSnacks();

        this.runProgram();

        this.writeOutSavedSnacks();
    } //SnackInventory

    /**
     * Reads in the saved snacks from the file.
     */
    private void readInSavedSnacks() {
        try {
            //Scanner s = new Scanner(new File("C:\\Users\\Spencer\\IdeaProjects\\CS 180 - Summer\\Lab8_Actual\\src\\savedSnacks.txt"));
            Scanner s = new Scanner(new File("savedSnacks.txt"));
            int i = 0;
            while (s.hasNextLine()) {
                String snackLine = s.nextLine();
                String[] snackInfo = snackLine.split("#");
                snackList.add(new Snack(i, snackInfo[0], Double.valueOf(snackInfo[1]), Integer.parseInt(snackInfo[2])));
                i++;
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //
    } //readInSavedSnacks

    /**
     * Displays the menu to the user.
     */
    private void displayMenu() {
        System.out.println("Snack Inventory Program");
        System.out.println("----------------------------------------");
        System.out.println("(1)  Add");
        System.out.println("(2)  Edit name");
        System.out.println("(3)  Edit price");
        System.out.println("(4)  Edit calories");
        System.out.println("(5)  Delete");
        System.out.println("(6)  Find by ID");
        System.out.println("(7)  Find by name");
        System.out.println("(8)  Find by price");
        System.out.println("(9)  Find by number of calories");
        System.out.println("(10) Exit");
        System.out.println("----------------------------------------");
        System.out.print("Enter your choice: ");
    } //displayMenu

    /**
     * Gets the menu choice from the user.
     *
     * @return the menu choice from the user
     */
    private int getMenuChoice() {
        int menuChoice = 0;

        try {
            menuChoice = Integer.parseInt(this.input.nextLine());

            while (menuChoice < 1 || menuChoice > 10) {
                System.out.println("\nError: invalid menu choice! Please enter a valid one!\n");

                System.out.print("Enter your choice: ");

                menuChoice = Integer.parseInt(this.input.nextLine());
            } //end while
        } catch (NumberFormatException e) {
            System.out.println("\nError: invalid menu choice! Please enter a valid one!\n");

            getMenuChoice();
        } //end try catch

        return menuChoice;
    } //getMenuChoice

    /**
     * Gets the ID of the snack from the user.
     *
     * @return the ID of the snack from the user
     */
    private int getSnackId() {
        int snackId = 0;

        System.out.print("Enter the ID of the snack: ");

        try {
            snackId = Integer.parseInt(this.input.nextLine());

            while (snackId < 0) {
                System.out.println("\nError: invalid snack ID! Please enter a valid one!\n");

                System.out.print("Enter the ID of the snack: ");

                snackId = Integer.parseInt(this.input.nextLine());
            } //end while
        } catch (NumberFormatException e) {
            System.out.println("\nError: invalid snack ID! Please enter a valid one!\n");

            getMenuChoice();
        } //end try catch

        return snackId;
    } //getSnackId

    /**
     * Gets the name of the snack from the user.
     *
     * @return the name of the snack from the user
     */
    private String getSnackName() {
        String snackName;

        System.out.print("Enter the name of the snack: ");

        snackName = this.input.nextLine();

        return snackName;
    } //getSnackName

    /**
     * Determines whether or not the specified {@code String} is a valid number.
     *
     * @param numberStr the {@code String} to be tested
     * @return true, if the specified {@code String} is a valid number
     */
    private boolean isValidNumber(String numberStr) {
        try {
            Double.parseDouble(numberStr);

            return true;
        } catch (NumberFormatException e) {
            return false;
        } //end try catch
    } //isValidNumber

    /**
     * Gets the price of the snack from the user.
     *
     * @return the price of the snack from the user
     */
    private double getSnackPrice() {
        String snackPrice;

        System.out.print("Enter the price of the snack: ");

        snackPrice = this.input.nextLine();

        while (!this.isValidNumber(snackPrice) || Double.compare(Double.parseDouble(snackPrice), 0.0) < 0) {
            System.out.println("\nError: invalid snack price! Please re-enter it!\n");

            System.out.print("Enter the price of the snack: ");

            snackPrice = this.input.nextLine();
        } //end while

        return Double.parseDouble(snackPrice);
    } //getSnackPrice

    /**
     * Gets the number of calories in the snack from the user.
     *
     * @return the number of calories in the snack from the user
     */
    private int getSnackNumCalories() {
        String snackNumCalories;

        System.out.print("Enter the number of calories in the snack: ");

        snackNumCalories = this.input.nextLine();

        while (!this.isValidNumber(snackNumCalories) || Integer.parseInt(snackNumCalories) < 0) {
            System.out.println("\nError: invalid snack calories! Please re-enter it!\n");

            System.out.print("Enter the number of calories in the snack: ");

            snackNumCalories = this.input.nextLine();
        } //end while

        return Integer.parseInt(snackNumCalories);
    } //getSnackNumCalories

    /**
     * Attempts to edit the name of the snack with the specified ID.
     *
     * @param id the ID of the snack to be edited
     * @param newName the new name of the snack
     * @return true, if the snack was found, and the name was successfully changed
     */
    private boolean editSnackName(int id, String newName) {
        for (int i = 0; i < snackList.size(); i++) {
            if (snackList.get(i).getId() == id) {
                snackList.get(i).setName(newName);
                return true;
            }
        }
        return false;
        //
    } //editSnackName

    /**
     * Attempts to edit the price of the snack with the specified ID.
     *
     * @param id the ID of the snack to be edited
     * @param newPrice the new price of the snack
     * @return true, if the snack was found, and the price was successfully changed
     */
    private boolean editSnackPrice(int id, double newPrice) {
        for (int i = 0; i < snackList.size(); i++) {
            if (snackList.get(i).getId() == id) {
                NumberFormat f = new DecimalFormat("#0.00");
                snackList.get(i).setPrice(Double.parseDouble(f.format(newPrice)));
                return true;
            }
        }
        return false;
        //
    } //editSnackPrice

    /**
     * Attempts to edit the number of calories in the snack with the specified ID.
     *
     * @param id the ID of the snack to be edited
     * @param newNumCalories the new calories in the snack
     * @return true, if the snack was found, and the number of calories was successfully changed
     */
    private boolean editSnackNumCalories(int id, int newNumCalories) {
        for (int i = 0; i < snackList.size(); i++) {
            if (snackList.get(i).getId() == id) {
                snackList.get(i).setPrice(newNumCalories);
                return true;
            }
        }
        return false;
        //
    } //editSnackNumCalories

    /**
     * Attempts to delete the snack with the specified ID.
     *
     * @param id the ID of the snack to be deleted
     * @return true, if the snack with the specified ID was found and deleted
     */
    private boolean deleteSnack(int id) {
        for (int i = 0; i < snackList.size(); i++) {
            if (snackList.get(i).getId() == id) {
                snackList.remove(i);
                return true;
            }
        }
        return false;
        //
    } //deleteSnack

    /**
     * Attempts to find a snack with the specified ID.
     *
     * @param id the id of the snack to be searched for
     * @return a {@code Optional} possibly containing a found snack
     */
    private Snack findSnackById(int id) {
        for (int i = 0; i < snackList.size(); i++) {
            if (snackList.get(i).getId() == id) {
                return snackList.get(i);
            }
        }
        return null;
        //
    } //findSnackById

    /**
     * Attempts to find snacks with the specified name.
     *
     * @param name the name of the snack to be searched for
     * @return a {@code List} containing all of the found snacks
     */
    private List<Snack> findSnacksByName(String name) {
        List<Snack> newList = new ArrayList<>();
        for (int i = 0; i < snackList.size(); i++) {
            if (snackList.get(i).getName().equals(name)) {
                newList.add(snackList.get(i));
            }
        }
        return newList;
        //
    } //findSnacksByName

    /**
     * Attempts to find snacks with the specified price.
     *
     * @param price the price of the snack to be searched for
     * @return a {@code List} containing all of the found snacks
     */
    private List<Snack> findSnacksByPrice(double price) {
        List<Snack> newList = new ArrayList<>();
        for (int i = 0; i < snackList.size(); i++) {
            if (snackList.get(i).getPrice() == price) {
                newList.add(snackList.get(i));
            }
        }
        return newList;
        //
    } //findSnacksByPrice

    /**
     * Attempts to find snacks with the specified number of calories.
     *
     * @param price the number of calories in the snack to be searched for
     * @return a {@code List} containing all of the found snacks
     */
    private List<Snack> findSnacksByNumCalories(double price) {
        List<Snack> newList = new ArrayList<>();
        for (int i = 0; i < snackList.size(); i++) {
            if (snackList.get(i).getNumCalories() == price) {
                newList.add(snackList.get(i));
            }
        }
        return newList;
        //
    } //findSnacksByNumCalories

    /**
     * The add case of the snack inventory program.
     *
     */
    private void addCase() {
        int id = SnackInventory.nextId++;
        String name;
        double price;
        int numCalories;
        Snack newSnack;

        System.out.println();

        name = this.getSnackName();

        price = this.getSnackPrice();

        numCalories = this.getSnackNumCalories();

        newSnack = new Snack(id, name, price, numCalories);

        this.snackList.add(newSnack);
    } //addCase

    /**
     * The edit name case of the snack inventory program.
     *
     */
    private void editNameCase() {
        int id;
        String newName;

        System.out.println();

        id = this.getSnackId();

        newName = this.getSnackName();

        if (this.editSnackName(id, newName)) {
            System.out.println("\nThe snack's name was edited!");
        } else {
            System.out.println("\nThe ID of the snack was not found! No edits were made!");
        } //end if
    } //editNameCase

    /**
     * The edit price case of the snack inventory program.
     *
     */
    private void editPriceCase() {
        int id;
        double newPrice;

        System.out.println();

        id = this.getSnackId();

        newPrice = this.getSnackPrice();

        if (this.editSnackPrice(id, newPrice)) {
            System.out.println("\nThe snack's price was edited!");
        } else {
            System.out.println("\nThe ID of the snack was not found! No edits were made!");
        } //end if
    } //editPriceCase

    /**
     * The edit calories case of the snack inventory program.
     *
     */
    private void editCaloriesCase() {
        int id;
        int newCalories;

        System.out.println();

        id = this.getSnackId();

        newCalories = this.getSnackNumCalories();

        if (this.editSnackNumCalories(id, newCalories)) {
            System.out.println("\nThe snack's number of calories was edited!");
        } else {
            System.out.println("\nThe ID of the snack was not found! No edits were made!");
        } //end if
    } //editCaloriesCase

    /**
     * The delete case of the snack inventory program.
     */
    private void deleteCase() {
        int id;

        System.out.println();

        id = this.getSnackId();

        if (this.deleteSnack(id)) {
            System.out.println("\nThe snack was deleted!");
        } else {
            System.out.println("\nThe ID of the snack was not found! No deletions were made!");
        } //end if
    } //deleteCase

    /**
     * The find by ID case of the snack inventory program.
     */
    private void findByIdCase() {
        int id;
        Snack snack;

        System.out.println();

        id = this.getSnackId();

        snack = this.findSnackById(id);

        if (snack != null) {
            System.out.printf("\nID:       %d\n", snack.getId());
            System.out.printf("Name:     %s\n", snack.getName());
            System.out.printf("Price:    $%.2f\n", snack.getPrice());
            System.out.printf("Calories: %d\n", snack.getNumCalories());
        } else {
            System.out.println("\nNo snacks with that ID were found!");
        } //end if
    } //findByIdCase

    /**
     * The find by name case of the snack inventory program.
     */
    private void findByNameCase() {
        String name;
        List<Snack> foundSnacks;

        System.out.println();

        name = this.getSnackName();

        foundSnacks = this.findSnacksByName(name);

        if (foundSnacks.isEmpty()) {
            System.out.println("\nNo snacks with that name were found!");
        } else {
            for (Snack snack : foundSnacks) {
                System.out.printf("\nID:       %d\n", snack.getId());
                System.out.printf("Name:     %s\n", snack.getName());
                System.out.printf("Price:    $%.2f\n", snack.getPrice());
                System.out.printf("Calories: %d\n", snack.getNumCalories());
            } //end for
        } //end if
    } //findByNameCase

    /**
     * The find by price case of the snack inventory program.
     */
    private void findByPriceCase() {
        double price;
        List<Snack> foundSnacks;

        System.out.println();

        price = this.getSnackPrice();

        foundSnacks = this.findSnacksByPrice(price);

        if (foundSnacks.isEmpty()) {
            System.out.println("\nNo snacks with that price were found!");
        } else {
            for (Snack snack : foundSnacks) {
                System.out.printf("\nID:       %d\n", snack.getId());
                System.out.printf("Name:     %s\n", snack.getName());
                System.out.printf("Price:    $%.2f\n", snack.getPrice());
                System.out.printf("Calories: %d\n", snack.getNumCalories());
            } //end for
        } //end if
    } //findByPriceCase

    /**
     * The find by number of calories case of the snack inventory program.
     */
    private void findByNumberOfCaloriesCase() {
        int numCalories;
        List<Snack> foundSnacks;

        System.out.println();

        numCalories = this.getSnackNumCalories();

        foundSnacks = this.findSnacksByNumCalories(numCalories);

        if (foundSnacks.isEmpty()) {
            System.out.println("\nNo snacks with that number of calories were found!");
        } else {
            for (Snack snack : foundSnacks) {
                System.out.printf("\nID:       %d\n", snack.getId());
                System.out.printf("Name:     %s\n", snack.getName());
                System.out.printf("Price:    $%.2f\n", snack.getPrice());
                System.out.printf("Calories: %d\n", snack.getNumCalories());
            } //end for
        } //end if
    } //findByNumberOfCaloriesCase

    /**
     * Runs the program for the user.
     */
    private void runProgram() {
        int menuChoice;

        this.displayMenu();

        menuChoice = this.getMenuChoice();

        while (true) {
            switch (menuChoice) {
                case 1:
                    this.addCase();
                    break;

                case 2:
                    this.editNameCase();
                    break;

                case 3:
                    this.editPriceCase();
                    break;

                case 4:
                    this.editCaloriesCase();
                    break;

                case 5:
                    this.deleteCase();
                    break;

                case 6:
                    this.findByIdCase();
                    break;

                case 7:
                    this.findByNameCase();
                    break;

                case 8:
                    this.findByPriceCase();
                    break;

                case 9:
                    this.findByNumberOfCaloriesCase();
                    break;

                case 10:
                    return;
            } //end switch

            System.out.println();

            this.displayMenu();

            menuChoice = this.getMenuChoice();
        } //end while
    } //runProgram

    /**
     * Writes out the saved snacks to the file.
     */
    private void writeOutSavedSnacks() {
        try {
            //FileWriter writer = new FileWriter(new File("C:\\Users\\Spencer\\IdeaProjects\\CS 180 - Summer\\Lab8_Actual\\src\\savedSnacks.txt"));
			FileWriter writer = new FileWriter(new File("savedSnacks.txt"));
            for (int i = 0; i < snackList.size(); i++) {
                Snack snack = snackList.get(i);
                writer.write(snack.getName() + "#" + snack.getPrice() + "#" + snack.getNumCalories() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
    } //writeOutSavedSnacks

    /**
     * Constructs a newly allocated {@code SnackInventory} object.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new SnackInventory();
    } //main
}