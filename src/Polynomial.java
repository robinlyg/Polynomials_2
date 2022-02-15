import java.util.ArrayList;

public class Polynomial {

    //member bariable is an empty arraylist of Terms - want the functionality of an ArrayList so we can add and grow our polynomial as our user enter input
    //TODO: why does IDE suggest that this should be " final"
    private ArrayList<Term> mTerms;

    //default constructor
    public Polynomial()
    {
        mTerms = new ArrayList<>();
    }

    // add copy constructor
    public Polynomial(Polynomial p)
    {
        //need to loop through the arraylist to copy the terms from one object to the new one and create new term objects
        this.mTerms = new ArrayList<>();
        for(int i = 0; i < p.mTerms.size(); i++)
        {
            int coefficient = p.mTerms.get(i).getCoefficient();
            int exponent = p.mTerms.get(i).getExponent();

            this.mTerms.add(new Term(coefficient, exponent));
        }
    }

    //addTerm adds a term to the poly: dupes (matching exponents should have coef added together)
    //also keep the order of the polynomial in order - largest exponent to the smallest
    public void addTerm(Term term)
    {

        //if arrayList.size == 0 just add term
        if(mTerms.size() ==0) {
            mTerms.add(term);
        }
        else {
            //does it match another exponent then we can add together
            //if they match we can assume the term is already in poly in its correct order so we can return to main
            for (int i = 0; i < this.getNumTerms(); i++) {
                if (term.getExponent() == this.getTerm(i).getExponent()) {
                    //if they match add coef and set as new coef
                    this.getTerm(i).setCoefficient(term.getCoefficient() + this.getTerm(i).getCoefficient());
                    if(this.getTerm(i).getCoefficient() == 0)
                    {
                        this.mTerms.remove(i);
                    }
                    return;
                }
            }

            //if it doesn't have a match first check for order before adding
            //insert at the first index to have a smaller exponent index
            for (int i = 0; i < this.mTerms.size(); i++) {
                if (this.mTerms.get(i).getExponent() < term.getExponent()) {
                    this.mTerms.add(i, term);
                    return;
                }
            }
            //if nothing else just add the term
            //TODO: a little confused why this worked and fixed an issue i was experiencing of a contiuous loop and or when i added line 44 -47 it fixed and issue wiht test when tyring to get an index but that index being empty
            this.mTerms.add(term);
        }

    }


    public Term getTerm(int index)
    {
        //if the index falls in bounds return the index, else
        if(index < this.getNumTerms()) {
            return mTerms.get(index);
        }
        //call default constructor - have to return a term
        return new Term();

    }

    //using the clear() method on the underlying arraylist to clear mTerms which is effectively the polynomial
    public void clear()
    {
        this.mTerms.clear();
    }
    // getNumTerms - use the size function from ArrayLists
    public int getNumTerms()
    {
       return this.mTerms.size();
    }

    //add two polynomials together directions were conflicting, the test calls for this to manipulate our calling object while the writen instructions asked that we dont manipulate either poly
    //used logic consistent with test - manipulating the calling object (this)
    public void add(Polynomial p)
    {
        //need to loop throught the poly in the argument to compare to this
        for(int i = 0; i <p.getNumTerms(); i++)
        {
            //effectively we are adding terms to an existing poly so we can all our addTerm method which will check for matching coef and put the terms in order
            this.addTerm(p.getTerm(i));
        }
    }
    // toString
  //TODO: is it inefficient to have so many return statements? Or is it okay because it cuts down how many posibilites you need to check before returning
    @Override
    public String toString()
    {
        //use stringBuilder to build our return string
        StringBuilder poly = new StringBuilder();
        //if size is 0 of the poly just return "0" (decision to return "0" based on test script)
        if(this.getNumTerms() == 0)
        {
            return "0";
        }
        //if size is 1 remove any '+' from the way we print a single term and replace with an empty string (according to test, dont want it)
        if(this.getNumTerms() == 1 ){
            return String.valueOf(poly.append(getTerm(0))).replaceAll("\\+", "");
        }
        //if our size is larger than 1
        if(this.getNumTerms() > 1)
        {
            //we loop through the object and print adding each term to the stringBuilding and  adding a space for readability
            //originally i had it check if the next number was pos or neg to add in a '+' if necessary but how we set up term that was not needed those signs are already there
                //this original iteration also meant i had to do one less than size to not include our last index because otherwise it was adding the sign to the very end of the string
                //again how we set up term prevents this issue
            for(int i = 0; i < this.getNumTerms(); i++) {

                    poly.append(this.getTerm(i)).append(" ");
            }
        }

        return String.valueOf(poly).replaceFirst("\\+", "");
    }

}
