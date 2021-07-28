import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * TODO: DESCRIBE YOUR PROJECT HERE
 *
 * @author Spencer CW Au, sau@purdue.edu
 * @version 01/25/96
 */
public class TextFilter {

    private static String censor(String passage, String word) {
        String censored = "";
        for (int i = 0; i < word.length(); i++) {
            censored += "X";
        }
        passage = passage.replaceAll("\\b" + word + "\\b", censored);
        return passage;
    }

    private static String replace(String passage, String word, String replace) {
        return passage.replaceAll("\\b" + word + "\\b", replace);
    }

    private static String censorEmail(String email) {
        int indexWS = email.indexOf(" ") + 1;
        email = email.substring(indexWS);
        int indexAt = email.indexOf("@");
        int indexPer = email.indexOf(".");
        String newEmail = "";
        newEmail += email.charAt(0);
        for (int i = 1; i < indexPer; i++) {
            if (indexAt == i) {
                newEmail += "@";
                i++;
                newEmail += email.charAt(i);
            } else newEmail += "*";
        }
        String end = email.substring(indexPer);
        newEmail += end;
        return newEmail;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Print hello message
        System.out.println("Welcome to TextFilter!");

        // Value to keep track of if the user wants to keep filtering text
        boolean keepFiltering = true;

        do {
            //keepFiltering = true;
            // Print options for the user to select
            System.out.println("Please select one of the following filtering options: ");
            System.out.println("1. Filter Word\n" +
                    "2. Find-And-Replace\n" +
                    "3. Censor Information");

            // Save their choice
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                // PART 1 - Censoring Words


                String passage = "";  // The text to be filtered
                System.out.println("Please enter the passage you would like filtered: ");

                // TODO: PART 1 - Ask the user for a passage to censor
                passage = scanner.nextLine();


                String word;  // The word to be censored from the text phrase
                System.out.println("Please enter the word you would like to censor: ");

                // TODO: PART 1 - Ask the user for a word to censor
                word = scanner.nextLine();


                System.out.println("Uncensored: ");
                System.out.println(passage);

                // TODO: PART 1 - Implement your parsing here
                passage = censor(passage, word);
                System.out.println("Censored: ");
                System.out.println(passage);


            } else if (choice == 2) {

                // PART 2 - Replacing Words


                String passage = "";  // The text to be filtered
                System.out.println("Please enter the passage you would like filtered: ");


                // TODO: PART 2 - Ask the user for a passage to filter
                passage = scanner.nextLine();


                String replace;  // The word to be filtered from the text phrase
                System.out.println("Please enter the word you would like to replace: ");

                //TODO: PART 2 - Ask the user for a word to replace
                replace = scanner.nextLine();


                String insert;  // The word to be inserted in the text phrase
                System.out.println("Please enter word you would like to insert: ");

                //TODO: PART 2 - Ask the user for a word to insert
                insert = scanner.nextLine();


                System.out.println("Uncensored: ");
                System.out.println(passage);


                // TODO: PART 2 - Implement your parsing here
                passage = replace(passage, replace, insert);


                System.out.println("Censored: ");
                System.out.println(passage);


            } else if (choice == 3) {

                // PART 3 - Censoring Personal Information


                /*
                 * DO NOT ALTER THIS CODE! This formatting is imperative to the completion of this task.
                 *
                 * Keep asking for input until the user enters an empty line
                 * If they enter an empty line and the phrase is empty, keep waiting for input
                 */

                String passage = "";  // String for holding text to be filtered

                System.out.println("Please enter the phrase you would like to censor information from: ");
                ArrayList<String> list = new ArrayList<>();

                while (true) {

                    // Obtain a line from the user
                    String temp = scanner.nextLine();

                    if (!passage.isEmpty() && temp.isEmpty()) {
                        break;
                    } else if (passage.isEmpty() && temp.isEmpty()) {
                        continue;
                    }

                    //ArrayList<String> list = new ArrayList<>();
                    // Add the contents of temp into the phrase
                    passage += temp;
                    list.add(temp);


                    // Append a newline character to each line for parsing
                    // This will separate each line the user enters
                    // To understand how input is formatted in Part 3, please refer to the handout.
                    passage += '\n';
                }

                // Print the uncensored passage
                System.out.println("Uncensored: ");
                System.out.println(passage);

                // TODO: PART 3 - Implement your parsing here
                for (String string : list) {
                    if (string.startsWith("Name:")) {
                        String name = "";
                        int start = string.indexOf(" ");
                        name += string.charAt(++start);
                        start++;
                        for (int i = start; i < string.length() - 1; i++) {
                            if (string.charAt(i) == ' ') {
                                name += " ";
                            } else name += "*";
                        }
                        name += string.charAt(string.length() - 1);
                        passage = passage.replaceFirst(string, "Name: " + name);
                    } else if (string.startsWith("Email:")) {
                        String newEmail = censorEmail(string);
                        passage = passage.replaceFirst(string, "Email: " + newEmail);
                    } else if (string.startsWith("Phone:")) {
                        String newPhone = "***-***-";
                        int end = string.lastIndexOf("-");
                        newPhone += string.substring(end + 1);
                        passage = passage.replaceFirst(string, "Phone: " + newPhone);
                    }
                }
                // Print the censored passage
                System.out.println("Censored: ");
                System.out.println(passage);

            } else {

                // They entered a number that was not 1, 2 or 3
                System.out.println("The option you chose was invalid!");

            }


            //System.out.println("Would you like to keep filtering? Yes/No");

            // TODO: PART 4 - Update the value of keepGoing accordingly
            // TODO: PART 4 - Replace the line below with your code
            System.out.println("Would you like to keep filtering? Yes/No");
            //String answer = scanner.nextLine();
            if (scanner.nextLine().equals("Yes")) keepFiltering = true;
            else keepFiltering = false;

        } while (keepFiltering);
        System.out.println("Thank you for using TextFilter!");
    }
}

