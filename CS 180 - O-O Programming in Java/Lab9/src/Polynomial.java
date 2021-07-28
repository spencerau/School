import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Polynomial {

 	// The Linked List of Terms representing the Polynomial
    private LinkedList<Term> terms;
 	// The number of Terms in the Linked List
    private int size;

 
 	// The standard constructor for the Polynomial Class
    public Polynomial() {
        this.terms = new LinkedList<Term>();
        this.size = 0;
    }
 
 	// addTerm() adds the parameter term into the Polynomial. Ensure you follow the guidelines from the handout in order
    // to implement this method correctly.
    public void addTerm(Term term) {

     	if (term == null) return;

     	if (term.getCoefficient() == 0) return;

        for (int i = 0; i < size; i++) {
            if (terms.get(i).getExponent() == term.getExponent()) {
                if (terms.get(i).getCoefficient() + term.getCoefficient() == 0.0) {
                    terms.remove(i);
                    size--;
                    return;
                }
                else {
                    terms.get(i).add(term);
                    return;
                }
            }
        }

        terms.add(term);
        size++;
        terms.sort(Comparator.comparing(Term::getExponent).reversed());
    }
 
 	// addition() adds the parameter poly with this Polynomial. Ensure you follow the guidelines from the handout in
    // order to implement this method correctly.
    public void addition(Polynomial poly) {

     	if (poly == null) return;

        for (int i = 0; i < poly.size; i++) {
            addTerm(poly.terms.get(i));
        }

    }
 
 	// subtraction() subtracts the parameter poly from this Polynomial. Ensure you follow the guidelines from the
    // handout in order to implement this method correctly.
    public void subtraction(Polynomial poly) {

     	if (poly == null) return;

        for (int i = 0; i < poly.size; i++) {
            Term temp = poly.terms.get(i);
            temp.setCoefficient(0-temp.getCoefficient());
            addTerm(temp);
        }
     
    }
 
 	// multiplication() multiplies the parameter poly with this Polynomial. Ensure you follow the guidelines from the
    // handout in order to implement this method correctly.
    public void multiplication(Polynomial poly) {

        if (poly == null) return;

        //LinkedList<Term> tempLL = new LinkedList<>();
        Polynomial p = new Polynomial();

        for (int i = 0; i < size; i++) {
            Term temp = new Term(terms.get(i));
            for (int j = 0; j < poly.size; j++) {
                temp.multiply(poly.terms.get(j));
                p.addTerm(temp);
                temp = new Term(terms.get(i));
            }
        }

        p.terms.sort(Comparator.comparing(Term::getExponent).reversed());
        this.terms = p.terms;
        this.size = p.size;
    }

 
 	// toString() prints the Term as a string, similar to how you would write a polynomial for a math problem. This
    // method should be useful for debugging.

    public String toString() {
        String out = "";
        for (Term term : this.terms) {
            out += term.toString() + " ";
        }
        if (out.equals("")) {
            out = "0.0 ";
        }
        return out;
    }

    public static void main(String[] args) {
        Polynomial p = new Polynomial();
        p.addTerm(new Term(-6, 2));
        p.addTerm(new Term(12, 4));
        p.addTerm(new Term(5, 3));

        System.out.println(p.toString());
        System.out.println();

        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();
        p1.addTerm(new Term(-2, 1));
        p1.addTerm(new Term(1, 0));
        p2.addTerm(new Term(3, 1));
        p2.addTerm(new Term(2, 0));

        p1.multiplication(p2);
        System.out.println(p1.toString());

        p1.addition(p2);
        System.out.println(p1.toString());

        p1.subtraction(p2);
        System.out.println(p1.toString());
    }
}
