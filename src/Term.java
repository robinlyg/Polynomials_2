import java.util.Objects;
import java.util.Scanner;

public class Term implements Comparable<Term>, Cloneable {

    //2 member variables coefficient and exponent. For class purposes these will be ints
    private int mCoefficient;
    private int mExponent;

    //full constructor take in 2 ints
   public Term(int coefficient, int exponent) {
        mCoefficient = coefficient;
        mExponent = exponent;
    }

    //defualt constructor - set coefficient and exponent to 1
    public Term() {
        mCoefficient = 1;
        mExponent = 1;
    }

    //copy constructor set this object to an existing Term
    public Term(Term t) {
        mCoefficient = t.mCoefficient;
        mExponent = t.mExponent;
    }

    //clone to make a deep copy - different from the copy instructor in that we dont set this = to the existing
    //we set new variables = to the objects member variables then call our full constructor to create a new object
    //TODO: heighlighted b.c it doens not call super constructor...why is that necessary when theres no inheritance here?
    public Term clone()
    {
        int coefficent = this.mCoefficient;
        int exponent = this.mExponent;

        return new Term(coefficent, exponent);
    }

    //constructor that takes in a string - should parse here
    public Term (String s) {
        //from his tests we are taking in one term at a time, so we can parse solely on the ^ and he uses +/- based on its sign can't store +with number but can store -
        // lets not assume there are or are not spaces - replace()
        s = s.replace(" ", "");
        //replace + with empty string becauce that will complicate our parsing of the terms. Integer.parseInts can take a '-' but not '+'
        s = s.replace("+","");

       //now s only consists of neg sign and numbers
        int indexOfCarrot = s.indexOf("x^");

        //extract the numerical values based on our index of 'x^'
        this.mCoefficient = Integer.parseInt(s.substring(0,indexOfCarrot));
        this.mExponent = Integer.parseInt(s.substring(indexOfCarrot +1));

        //create new term using full constructor and the extracted values above
        Term t = new Term(mCoefficient,mExponent);

    }

    // setCoefficient(x)
    public void setCoefficient(int newCoef) {
        mCoefficient = newCoef;
    }

    // setExpnent(x)
    public void setExponent(int newEx) {
        mExponent = newEx;
    }

    public int getExponent() {
        return mExponent;
    }

    public int getCoefficient() {
        return mCoefficient;
    }

    //setAll(x,x)
    public void setAll(int newCoef, int newEx) {
        mCoefficient = newCoef;
        mExponent = newEx;
    }

    // equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Term)) return false;
        Term term = (Term) o;
        return mCoefficient == term.mCoefficient && mExponent == term.mExponent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mCoefficient, mExponent);
    }


    // toString: based on our structure if the ^0 then there is no base aka 'x' if the ^1 theres a base 'x'
    @Override
    public String toString() {
        //if the exponent == 0: check if coefficient is greater than 0, if true: return a '+' and coefficient
        //if false just return that coeffiecient: this will already include the '-' sign (has to be a string so add an empty string)
        if (this.getExponent() == 0) {
            if(this.getCoefficient() > 0)
            {return "+" +mCoefficient;}
            else
            {return mCoefficient + "";}
        //else if the exponent == 1
        } else if (this.getExponent() == 1) {
            //check if the coef == 1 if so just return x no need for 1x
            if(this.mCoefficient ==1)
            {return  "+x";}
            //if coeffiecint is greater than 1 then we need to include the '+'coef and x
            else if(this.mCoefficient > 1)
            {return "+" + mCoefficient + "x";}
            //if the coef == -1 then just return -x same logic as if x ==1 but we need -1
            else if(this.mCoefficient == -1)
            {return "-x"; }
            //if the coef is less than 0 we dont want to print the '+'
            else if(this.mCoefficient < 0)
            {return mCoefficient + "x";}
            //finally if none of the above are true just print the coef
            else
            {return mCoefficient + "";}
        }
        //if coef == 0 print nothing
        if(this.getCoefficient() == 0) {
            return "";
        }
        //if coef ==1 and the exponent is greater than 1 or less than 0 no special cases can print '+x' and the coef
        if(this.getCoefficient() == 1 && (this.getExponent() > 1 || this.getExponent() < 0))
        {
            return "+x^"+this.getExponent();
        }

        else {
            //if coef is greater than 1 print '+' coef 'x^' exponnet
            if(this.getCoefficient() > 1) {
                return "+" + mCoefficient + "x^" + mExponent;
            }
            //if coef is == -1 then print -x^ exponent
            else if(this.getCoefficient() == -1)
            {return "-x^" + mExponent;}
            //lastly if all else fails return coef 'x^' exponent
            else{
                return mCoefficient + "x^" + mExponent;
            }
        }
    }

    //compareTo(): compare one exponent to the other for equality
    @Override
    public int compareTo(Term o) {
        return Integer.compare(mExponent, o.mExponent);
    }
}
