
//Skeleton code for part 4

//Question
//
//Without modifying any of the existing code, add code that 
//sets the delimiter to the Scanner object ‘input’ so that 
//the program runs without error. 
//
//Run the program by selecting 'RUN'
//

import java.util.ArrayList;
import java.util.Scanner;
class Part4_scannerDebug {
	public static void main(String[] args) {
		Scanner in = new Scanner("12.3 3.4 4.7");
		//double current_number;
		int current_number;
		/*------------------------------*/
		/* your code here, set delimiter to remove error */
        in.useDelimiter("\\.| ");

        ArrayList list = new ArrayList();
        for (int i = 0; i < 6; i++) {
            list.add(Integer.parseInt(in.next()));
        }

        if (list.size() == 6) {
            int i = 0;
            while (i < 6) {
                System.out.println(list.get(i));
                i++;
            }
        }
        else {
            System.out.println("Wrong Formatting");
        }


		/* ------- code continues below --------
		//current_number = in.nextDouble();
		current_number = Integer.parseInt(in.next());
		System.out.println(current_number);

		//current_number = in.nextDouble();
        current_number =(Integer.parseInt(in.next()));
		System.out.println(current_number);

		//current_number = in.nextDouble();
        current_number =(Integer.parseInt(in.next()));
		System.out.println(current_number);

		//current_number = in.nextDouble();
        current_number =(Integer.parseInt(in.next()));
		System.out.println(current_number);

		//current_number = in.nextDouble();
        current_number =(Integer.parseInt(in.next()));
		System.out.println(current_number);

		//current_number = in.nextDouble();
        current_number =(Integer.parseInt(in.next()));
		System.out.println(current_number);
        */
		return;
	}
}
