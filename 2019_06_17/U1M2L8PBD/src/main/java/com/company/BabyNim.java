/**
 * This app allows a user to remove one or more counters
 * from one of three piles until all the piles are empty.
 * It does not check for user input errors.
 * Displays a message letting the user know when all the piles are empty.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class BabyNim {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int pile1 = 3;
        int pile2 = 3;
        int pile3 = 3;

        while ( pile1 > 0 || pile2 > 0 || pile3 > 0 ) {

            System.out.println("A: " + getAsterisks(pile1) );
            System.out.println("B: " + getAsterisks(pile2) );
            System.out.println("C: " + getAsterisks(pile3) );


            System.out.print("\nChoose a pile: ");
            String pile = scan.nextLine();

            System.out.print("\nHow many to remove from pile " + pile + "?  ");
            int num = Integer.parseInt(scan.nextLine());

            switch (pile.toUpperCase()) {
                case "A":
                    if (pile1 > 0) {
                        pile1 = pile1 - num;
                    } else {
                        System.out.println("\nPile A does not have any items.");
                    }
                    break;
                case "B":
                    if (pile2 > 0) {
                        pile2 = pile2 - num;
                    } else {
                        System.out.println("\nPile B does not have any items.");
                    }
                    break;
                case "C":
                    if (pile3 > 0) {
                        pile3 = pile3 - num;
                    } else {
                        System.out.println("\nPile C does not have any items.");
                    }
                    break;
                default:
                    System.out.println("\nThat pile does not exist.\n");
            }

        }

        System.out.println("\nGreat job! You emptied all the bins.");

    }

    public static String getAsterisks(int pile) {
        String asterisks = "";
        for (int i = 0; i < pile; i++) {
            asterisks += "*";
        }
        return asterisks;
    }

}

