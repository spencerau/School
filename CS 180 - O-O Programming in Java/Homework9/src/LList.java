/**
* Created by Spencer on 7/23/2017.
*/

class LList<AType> {
    Links<AType> list;

    LList() { /* initalize list to Nil<AType> by calling the nil constructor*/ }

    void add(AType elem) {
        list = new Cons<AType>(elem, list);
        //list.setElem(elem);
    }
    /* use the Cons<AType> constructor to add an element to the list */


    AType get(int n, AType a) {
        int i = 0;
        Links<AType> node = list;
        while (node != null) {
            if (i == n) {
                return node.getElem();
            }
            else {
                node = node.getNext();
            }
            i++;
        }
        return a;
    }
    /* return the nth element if n is valid, otherwise return a */


    // code for remove is given
    Links<AType> remove_helper(int n, Links<AType> _list) {
        if (_list.getNext() == null) return new Nil<AType>();
        else if (n == 0) return _list.getNext();
        else return new Cons<AType>(_list.getElem(), remove_helper(n - 1, _list.getNext()));
    }

    void remove(int n) {
        list = remove_helper(n, list);
    }

    // getList() goes here
   /* return the list */

    /* print() is given, notice the structure of the for look
       use this style of programming to loop through the list! */
    void print() {
        Links<AType> temp = list;
        for (; temp.getNext() != null; temp = temp.getNext()) {
            System.out.print(temp.getElem() + " -> ");
            System.out.println("Nil");
        }
    }
}
