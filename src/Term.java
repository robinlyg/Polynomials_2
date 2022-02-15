import java.util.Objects;
import java.util.Scanner;

public class Term implements Comparable<Term>, Cloneable {

    private int mCoefficient;
    private int mExponent;
    //private char mBase;

    //Still have different consturcotrs and in main we will have to check for chars
    //to see which constructor to use
    /*public Term(int coefficient, int exponent, char base) {
        mCoefficient = coefficient;
        mExponent = exponent;
        //mBase = 'x';
    }*/

    /*public Term(int coefficient) {
        mCoefficient = coefficient;
    }*/

    public Term(int coefficient, int exponent) {
        mCoefficient = coefficient;
        mExponent = exponent;
    }

    //defualt constructor
    public Term() {
        mCoefficient = 1;
        mExponent = 1;
    }

    //copy constructor
    public Term(Term t) {
        mCoefficient = t.mCoefficient;
        mExponent = t.mExponent;
    }

    public Term clone()
    {
        int coefficent = this.mCoefficient;
        int exponent = this.mExponent;

        return new Term(coefficent, exponent);
    }

    //constructor that takes in a string - should be able to parse
    public Term (String s) {
        //from his tests we are taking in one term at a time, so we can parse solely on the ^ and he uses +/- based on its sign can't store +with number but can store -
        // lets not assume there are or are not spaces - replace()
        s = s.replace(" ", "");
        s = s.replace("+","");

       //now s only consists of neg sign and numbers
        int indexOfCarrot = s.indexOf("x^");

        this.mCoefficient = Integer.parseInt(s.substring(0,indexOfCarrot));
        this.mExponent = Integer.parseInt(s.substring(indexOfCarrot +1));

        Term t = new Term(mCoefficient,mExponent);

       // Term t = new Term(mCoefficient,mExponent);
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


    // toString
    //need to account for empty and for non base, non expnent
    //what does the term include and how does that translate into how to be printed
    //if exponenet == 0 -> coef == 1
    //if exponent == 1  -> coef == itself
    @Override
    public String toString() {
        //if length
        //if exponenet == 0 -> coef == 1
        if (this.getExponent() == 0) {
            if(this.getCoefficient() > 0)
            {return "+" +mCoefficient;}
            else
            {return mCoefficient + "";}

        } else if (this.getExponent() == 1) {
            if(this.mCoefficient ==1)
            {return  "+x";}
            else if(this.mCoefficient > 1)
            {return "+" + mCoefficient + "x";}
            else if(this.mCoefficient == -1)
            {return "-x"; }
            else if(this.mCoefficient < 0)
            {return mCoefficient + "x";}
            else
            {return mCoefficient + "";}
        }
        //if coef == 0
        if(this.getCoefficient() == 0) {
            return "";
        }
        if(this.getCoefficient() == 1 && (this.getExponent() > 1 || this.getExponent() < 0))
        {
            return "+x^"+this.getExponent();
        }
        //if coef is negative
        //if exponent == 1  -> coef == itself
        else {
            if(this.getCoefficient() > 1) {
                return "+" + mCoefficient + "x^" + mExponent;
            }
            else if(this.getCoefficient() == -1)
            {return "-x^" + mExponent;}
            else{
                return mCoefficient + "x^" + mExponent;
            }
        }
    }


    @Override
    public int compareTo(Term o) {
        return Integer.compare(mExponent, o.mExponent);
    }
}
