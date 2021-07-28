
import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;



public class Eater extends Thread{

    int full;
    int id;
    Utensil left;
    Utensil right;

    Eater(int id, Utensil right, Utensil left) {
        this.id = id;
        full = 0;
        this.right = right;
        this.left = left;
    }
    public void eating() {

        System.out.println("Philosopher" +full+" is eating the food!");
		//eat until full
        full = 100;

    }
    public void thinking() {
		
        System.out.println("Philosopher is thinking about Eating.");

    }
    public void getLeft() {

        //TODO
        left.inUse = true;

    }
    public void getRight() {

        //TODO
        right.inUse = true;
    }
    public void releaseRight() {

        //TODO
        left.inUse = false;
    }
    public void releaseLeft() {

        //TODO
        right.inUse = true;

    }
    @Override
    public void run() {
        if (!left.inUse) getLeft();
        System.out.println("Utensil{" + left.id + "} was picked up!");
        if (!right.inUse) getRight();
        System.out.println("Utensil{" + right.id + "} was picked up!");
        System.out.println("Philosopher{" + id + "} is eating!");
        full += left.add;
        full += right.add;
        while (full % 20 != 0 && full != 0) {
            full += left.add;
            full += right.add;
        }
        System.out.println("Philosopher{" + id + "} is " + full + " points full");
        releaseLeft();
        releaseRight();

        /*Add code here*/


    }
    public static void main(String[] args) throws InterruptedException {


        //sample of how your code will be tested
        Utensil one = new Utensil(1, 1);
        Utensil two = new Utensil(1, 2);
        Utensil three = new Utensil(1, 3);
        Utensil four = new Utensil(1, 4);
        Utensil five = new Utensil(1, 5);

        Eater first = new Eater(100, one, five);
        Eater e0 = new Eater(101, one, two);
        Eater e1 = new Eater(102, two, three);
        Eater e2 = new Eater(103, three, four);
        Eater e3 = new Eater(104, four, five);


        first.start();
        e0.start();
        e1.start();
        e2.start();
        e3.start();

        while (first.full != 1000 && e0.full != 1000 && e1.full != 1000 && e2.full != 1000 && e3.full != 1000) {
            first.run();
            e0.run();
            e1.run();
            e2.run();
            e3.run();
        }


    }



}
class Utensil {
    int add;
    int id;
    boolean inUse;

    Utensil(int incr, int unqiue) {
        add = incr;
        id = unqiue;
        add = 1;
    }
    //TODO
    //Create methods that manage when/what is using Utensil
    

}
