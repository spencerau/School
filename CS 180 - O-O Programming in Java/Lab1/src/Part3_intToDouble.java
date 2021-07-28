

//Skeleton code for part 3
//
//Question: 
//Modify the below code to print out three doubles 
//(3.0, 2.0 and 1.0) without changing the String used 
//to construct the Scanner object ‘input’. Use the Java
//doc to find the correct method to use. Use the 
//skeleton code on Vocareum. Be sure to upload and submit your 
//final solution.

import java.util.Scanner;

class Part3_intToDouble {
	public static void main(String[] args) {
    	
		/*Do NOT change this line*/
		Scanner input = new Scanner("3, 2, 1").useDelimiter(", ");

		//Modify code below
		int first = input.nextInt();
		System.out.println((double)first);

		int second = input.nextInt();
		System.out.println((double)second);

		int third = input.nextInt();
		System.out.println((double)third);
    }
}