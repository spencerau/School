
/*****************************/
// links implementation 

import java.util.ArrayList;
import java.util.List;

/*****************************/
abstract class Links<AType> {
	abstract AType getElem();
	abstract Links<AType> getNext();
}

class Cons<AType> extends Links<AType> {
	AType elem;
	Links<AType> next;
	Cons(AType _elem, Links<AType> _next) {
		elem = _elem;
		next = _next;
	}
	AType getElem(){ return elem; }
	Links<AType> getNext(){ return next; }
}

class Nil<AType> extends Links<AType> {
	Nil(){}
	AType getElem() { return null; }
	Links<AType> getNext() { return null; }
}
/*****************************/

class Main {

    int length(Links<Integer> list) {
        if (list.getNext() == null) {
            return 0;
        }
        else {
            return (1+length(list.getNext()));
        }
    }

    Links<Integer> append(Links<Integer> listA, Links<Integer> listB) {
        if (listA.getNext() == null) {
            return listB;
        }
        else {
            return new Cons(listA.getElem(), append(listA.getNext(), listB));
        }
    }

    Links<Integer> take(int n, Links<Integer> list) {
        if (n == 0) return new Nil<Integer>();
        else {
            if (list.getNext() == null) return new Nil<Integer>();
            else return (new Cons(list.getElem(), take(n-1, list.getNext())));
        }
    }

    // return the last element of a list
    // if list is Nil return -1
    Integer last(Links<Integer> list) {
        if (list.getElem() == null) return -1;
        if (list.getNext() == null) return list.getElem();
        else return last(list.getNext());
    }

    // return all but the last element of list
    // if list is nil return -1
    Links<Integer> initial(Links<Integer> list) {
        return take(length(list)-1, list);
    }

    // reverse a list
    Links<Integer> reverse(Links<Integer> list) {
        /*
        Links<Integer> next = list.getNext();
        Links<Integer> prev = null;

        while (list != null) {
            next = list.getNext();
            list.getNext() = prev;

        }
        */
        ArrayList<Links<Integer>> tempList = new ArrayList<>();
        while (list != null) {
            tempList.add(list);
            list = list.getNext();
        }
        for (int i = tempList.size()-1; i > -1; i--) {
            if (i == 0) {
                new Cons<Integer>(tempList.get(i).getElem(), new Nil<Integer>());
            }
            else {
                new Cons<Integer>(tempList.get(i).getElem(), tempList.get(i - 1));
            }
        }
        return list;
    }
 
    // return all elements of a list less than n
    Links<Integer> lt(int n, Links<Integer> list) {
        if (list == null) return new Nil<>();
        if (list.getElem() <= n) {
            Links<Integer> node = new Cons<>(list.getElem(), list.getNext());
            return  lt(n, node.getNext());
        }
        return new Nil<Integer>();
    }

    // return all elements of a list greater than or equal to n
    Links<Integer> gteq(int n, Links<Integer> list) {
        if (list == null) return new Nil<Integer>();
        if (list.getElem() >= n) {
            Links<Integer> node = new Cons<>(list.getElem(), list.getNext());
            return  gteq(n, node.getNext());
        }
        return new Nil<Integer>();
    }

    // feel free to use this function to test lt and gteq
    // it should sort given list
    Links<Integer> quicksort(Links<Integer> list) {
        if (list.getNext() == null) {
            return new Nil<Integer>();
        } else {
            return append( quicksort ( lt(list.getElem(),list.getNext())),
                       new Cons ( list.getElem(), 
                       quicksort( gteq(list.getElem(), list.getNext()))));
        }
    }

    // multiply the elements of a list
    int mult(Links<Integer> list) {
        int product = 1;
        for (int i = 0; i < length(list); i++) {
            product *= list.getElem();
            list = list.getNext();
        }
        return product;
    }

    // return list of numbers from 1 to n, inclusive
    // if n < 1 return Nil
    Links<Integer> seq(int n) {
        if (n < 1) return new Nil<Integer>();

        else return append(new Cons<>(n, null), seq(n-1));
    }


    // easy definition of factorial function using mult and seq
    int factorial(int n) { return mult (seq(n)); }

    // return the nth element of a list
    // if n is not valid, return default
    Integer get(int n, int def, Links<Integer> list) {
        if (n < 0 || n >= length(list)) return def;
        for (int i = 0; i <= length(list); i++) {
            if (i == n) return list.getElem();
            else list = list.getNext();
        }
        return def;
    }

    // return true if n is an element in the list, false otherwise
    boolean elem(int n, Links<Integer> list) {
        if (list == null) return false;
        else {
            if (list.getElem() == n) return true;
            else {
                return elem(n, list.getNext());
            }
        }
    }

    // print a list with values separated by '->'
    // for the Nil case print 'nil'
    // ex: print([1,2,3]) should print " 1 -> 2 -> 3 -> nil "
    void print(Links<Integer> list) {
        if (list == null) {
            System.out.println("nil");
        }
        else {
            System.out.println(list.getElem() + " -> ");
            print(list.getNext());
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        Links<Integer> l = m.append(m.seq(12), m.quicksort(m.seq(12)));
        m.print(l);
        m.print(m.quicksort(l));
    }

}
