import java.lang.Math;

public class Term {

 	// The coefficient of the Term
    private double coefficient = 0.0;

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    // The exponent of the Term
    private int exponent = 0;
 	// A boolean used for the toString method
    private boolean hasVariable = false;

 
 	// The standard constructor for the Term class
    public Term(double coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = Math.abs(exponent);
        this.hasVariable = this.exponent > 0;
    }

 
 	// The copy constructor for the Term class.
    public Term(Term term) {
        if (term != null) {
            this.coefficient = term.coefficient;
            this.exponent = term.exponent;
            this.hasVariable = term.hasVariable;
        }
    }

 
 	// Returns the sign of this Term.
    public boolean getSign() {
        return coefficient > 0;
    }
 

 	// Sets the coefficient of this Term to the argument coefficient if it is valid.
    public void setCoefficient(double coefficient) {
        if(coefficient != 0) this.coefficient = coefficient;
    }

 
 	// Returns the coefficient of this Term.
    public double getCoefficient() {
        return this.coefficient;
    }

 	
 	// Returns the exponent of this Term.
    public int getExponent() {
        return this.exponent;
    }

 
 	// add() adds the parameter term with this Term. Ensure you follow the guidelines from the handout in order to
    // implement this method correctly.
    public void add(Term term) {

        if (term == null) return;
        if (term.exponent != this.exponent) return;
     	this.coefficient += term.coefficient;
     
    }

 
 	// subtract() subtracts the parameter term with this Term. Ensure you follow the guidelines from the handout in
    // order to implement this method correctly.
    public void subtract(Term term) {

        if (term == null) return;
        if (term.exponent != this.exponent) return;
     	this.coefficient -= term.coefficient;
    }


 	// multiply() multiplies the parameter term with this Term. Ensure you follow the guidelines from the handout in
    // order to implement this method correctly.
    public void multiply(Term term) {

     	if (term == null) return;
     	this.coefficient *= term.coefficient;
     	this.exponent += term.exponent;
     
    }

 
 	// toString() prints the Term as a string, similar to how you would write a term for a math problem. This method should be useful for debugging.
    public String toString() {
        String out = "";
        if(this.coefficient > 0) out += '+';
        out += this.coefficient;
        if(this.hasVariable) {
            out += "x";
            if (this.exponent > 1)
                out += "^" + this.exponent;
        }
        return out;
    }
}
