import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Main {
    //static variables that we will use throughout main and in methods
    public static Scanner keyboard = new Scanner(System.in);
    public static int coefficient, exponent;

    public static void main(String[] args) {

        //need variables that represent the classes member variables
        int choice = 0;

        Polynomial poly = null;
        Polynomial polyTwo = null;

        do {

            System.out.println("~~~~Please choose from the menu below~~~~");
            System.out.println("To create a new Polynomial and build please enter 1\n" +
                    "To modify and existing Polynomial please enter 2\n" +
                    "To add two polynomials together please enter 3\n" +
                    "To clear the polynomials please enter 4\n"
                    + "and to exit please enter 5");
            choice = keyboard.nextInt();

            //build menu that allows user to modify, clear, build and add polys until they choose to exit

            //TODO: IDE suggested enhanced switch statement
            switch (choice) {
                case 1 -> {
                    //case 1 - build - add terms to the poly
                    int choice2 = 0;
                    System.out.println("~~~~Please choose from the menu below~~~~");
                    System.out.println("To create and build the first polynomial please enter 1.");
                    System.out.println("To create and build the first polynomial please enter 2.");
                    //may not need this third option
                    System.out.println("To exit please enter 3.");
                    choice2 = keyboard.nextInt();
                    //could add loop for any incorrect input
                    switch (choice2) {
                        case 1:
                            poly = new Polynomial();
                            parseTerm(poly);
                            break;
                        case 2:
                            polyTwo = new Polynomial();
                            parseTerm(polyTwo);
                            break;
                        case 3:
                            break;
                    }
                }

                //case 2 - modify
                case 2 -> {
                    //insert, remove, edit an index of existing poly
                    //which to mdoify
                    if (poly.getNumTerms() == 0 || polyTwo.getNumTerms() == 0) {
                        System.out.println("Error, must first create and build both poly");
                        break;
                    }
                    System.out.println("Which polynomial would you like to modify (1 or 2)");
                    int num = keyboard.nextInt();
                    if (num == 1) {
                        modify(poly);
                    }
                    if (num == 2) {
                        modify(polyTwo);
                    }
                }

                //case 3 add
                case 3 -> {
                    poly.add(polyTwo);
                    System.out.println(poly);
                }
                //case 4 clear
                case 4 -> {
                    System.out.println("Which Polynomial would you like to clear (1 or 2)");
                    int i = keyboard.nextInt();
                    //clear each poly to an empty ArrayList
                    if (i == 1) {
                        poly.clear();
                    }
                    if (i == 2) {
                        polyTwo.clear();
                    }
                }


                //case 5 - exit
            }

        } while (choice < 5 && choice > 0);

    }//main method

    public static void modify(Polynomial p) {
        System.out.println("Please enter the index you need to insert at");
        int i = keyboard.nextInt();
        System.out.println("Please enter the new coefficient");
        coefficient = keyboard.nextInt();
        System.out.println("Please enter the new exponent");
        exponent = keyboard.nextInt();
        p.getTerm(i).setCoefficient(coefficient);
        p.getTerm(i).setExponent(exponent);
    }

    public static void parseTerm(Polynomial p) {
        keyboard.nextLine();
        do {

            System.out.println("Enter the term to be added (coefficient first followed by a comma then exponent");
            System.out.println("To exit this polynomial when complete enter \"Quit\"");
            String termString = keyboard.nextLine();
            if (termString.equalsIgnoreCase("Quit")) {
                return;
            }
            //need to parse
            termString = termString.replaceAll(" ", "");
            int comma = termString.indexOf(',');
            coefficient = Integer.parseInt(termString.substring(0, comma));
            exponent = Integer.parseInt(termString.substring(comma + 1));
            Term t = new Term(coefficient, exponent);
            p.addTerm(t);
        } while (true);
    }


}//main class
