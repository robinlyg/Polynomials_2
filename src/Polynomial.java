import java.util.ArrayList;

public class Polynomial implements Cloneable {

    private ArrayList<Term> mTerms;

    public Polynomial()
    {
        mTerms = new ArrayList<>();
    }

    // add copy constructor -
    public Polynomial(Polynomial p)
    {
        this.mTerms = new ArrayList<>();
        for(int i = 0; i < p.mTerms.size(); i++)
        {
            int coefficient = p.mTerms.get(i).getCoefficient();
            int exponent = p.mTerms.get(i).getExponent();

            this.mTerms.add(new Term(coefficient, exponent));
        }
    }

    //addTerm adds a term to the poly
    //dont add dupes
    //add together those with the same exp or plain ole coef
    //choose which constructor to use based on if it has an expon or not
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
                    return;
                }
            }

            //if it doesn't have a match first check for order before adding
            //insert at the of the first index to have a smaller exponent index
            for (int i = 0; i < this.mTerms.size(); i++) {
                if (this.mTerms.get(i).getExponent() < term.getExponent()) {
                    this.mTerms.add(i, term);
                    return;
                }
            }
            this.mTerms.add(term);
        }

    }
   /* public int indexOf(int i)
    {
        if(i > this.getNumTerms() || i < 0)
        {
            return -1;
        }

        else{
            return i;
        }
    }*/

    public Term getTerm(int index)
    {
        //if the index falls in bounds return the index, else
        if(index < this.getNumTerms()) {
            return mTerms.get(index);
        }
        //else exit
        return new Term();

    }

    public void clear()
    {
        this.mTerms.clear();
       // this.mTerms = new ArrayList<>();
    }
    // getNumTerms
    public int getNumTerms()
    {
       return this.mTerms.size();
    }

    public void add(Polynomial p)
    {
        for(int i = 0; i <p.getNumTerms(); i++)
        {
            this.addTerm(p.getTerm(i));
           /*if(this.getTerm(i).getExponent() == p.getTerm(i).getExponent())
           {
               this.getTerm(i).setCoefficient((this.getTerm(i).getCoefficient() + p.getTerm(i).getCoefficient()));
           }
           else
           {
               this.mTerms.add(p.getTerm(i));
           }*/
        }
    }
    //TODO: toString
    //consider numOfTerms
    //do we need to loop through the array instead?so each term gets printed individually

    @Override
    public String toString()
    {
        StringBuilder poly = new StringBuilder();
        if(this.getNumTerms() == 0)
        {
            return "0";
        }
        if(this.getNumTerms() == 1 ){
            return String.valueOf(poly.append(getTerm(0))).replaceAll("\\+", "");
        }

        if(this.getNumTerms() > 1)
        {
            for(int i = 0; i < this.getNumTerms()-1; i++) {

                if(this.getTerm(i+1).getCoefficient() >= 0)
                {
                    poly.append(this.getTerm(i))/*.append("+")*/;

                }
                //if(this.getTerm(i+1).getCoefficient() < 0)
                else
                {
                    poly.append(this.getTerm(i));

                }

            }

        }
        poly.append(getTerm(this.mTerms.size()-1));
        return String.valueOf(poly).replaceFirst("\\+", "");
    }

}
