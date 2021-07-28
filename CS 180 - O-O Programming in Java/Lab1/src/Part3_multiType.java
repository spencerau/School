
//Skeleton code for part 3 

//Question:
//
//Suppose the following Scanner object 'input' is initialized as follows:

import java.util.Scanner;

//Scanner input = new Scanner("3;4.1;taco;false");

//Write a program that sets the delimiter for the Scanner object
//'input' to the correct character and outputs 
//
// 3
// 4.1
// taco
// false
//
//Where ‘3’ has type int, ‘4.1’ has type double, ‘taco’ has type 
//String and ‘false’ has type boolean. Use the Java doc to find the
//correct methods to use. 

/*Your code goes below:

note: snippits are allowed, it does not have to compile but must be otherwise correct*/

class Part3_multiType {
    public static void main(String[] args) {
        Scanner input = new Scanner("3;4.1;taco;false");
        input.useDelimiter(";");
        while (input.hasNext()) {
            System.out.println(input.next());
        }
    }
}