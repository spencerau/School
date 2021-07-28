import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

/* abstract class Links<AType> goes here
    contains two abstract methods
        AType getElem();
	    Links<AType> getNext();
    make sure to declare these abstract */
abstract class Links<AType> {
	abstract AType getElem();
	abstract Links<AType> getNext();
	//abstract void setElem(AType elem);
}

/*class Cons<AType> goes here extending Links
    make sure to implement the constructor
    stores an element of AType called elem and a Links<AType> called next
    must overload methods from Links, see handout */
class Cons<AType> extends Links<AType> {

    AType elem;
    Links<AType> next;

	public Cons(AType _elem, Links<AType> _next) {
	    this.elem = _elem;
	    this.next = _next;
    }

	@Override
	AType getElem() {
		return this.elem;
	}

	@Override
	Links<AType> getNext() {
		return this.next;
	}

	void setElem(AType elem) {
		this.elem = elem;
	}
}

/*class Nil<AType> goes here extending Links
    Has an empty constructor
    stores no elements
    must overload methods from Links, see handout */
class Nil<AType> extends Links<AType> {

    @Override
    AType getElem() {
        return null;
    }

    @Override
    Links<AType> getNext() {
        return null;
    }
}

// abstract class Filter<AType> goes here extending LList<AType> {
	/* contains an abstract method
    	boolean apply(AType elem);
    and a constructor (LList<AType> _list) that sets the list equal
    to _lis\
    
    implement a method
        Links<AType> filter() 
    that returns a copy of list contain only those elements for which 
    apply returns true */

abstract class Filter<AType> extends LList<AType> {

    LList<AType> _list;

    Links<AType> filter() {
        LList<AType> LList = new LList<AType>();
        Links<AType> node = list;
        while (node != null) {
            node = list;
            if (apply(node.getElem())) {
                LList.add(node.getElem());
            }
            node = node.getNext();
        }
        return list;
    }

    abstract boolean apply(AType elem);
}



class Main {
	public static void main(String[] args) {
		/* use this to test your code */
	}
}
