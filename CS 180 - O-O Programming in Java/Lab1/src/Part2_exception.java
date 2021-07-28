
//Your code for part 2 goes here

//Question:
//
//Write a program that throws the exceptinon (error) called by a Scanner object when it runs out of tokens

/* your code below 
note: code snippits are allowed, this will be graded by hand */

import java.util.Scanner;

class Part2_exception {
    public static void main(String[] args) {
        Scanner s = new Scanner("Hello World!");
        while (true) {
            System.out.println(s.next());
        }
    }
}

