/**
 * This is a strategy game between two players.
 * Players take turns removing one or more counters
 * from one of three piles until all the piles are empty.
 * Whichever player is forced to take the last counter is the LOSER.
 * It checks for user input errors.
 * Displays a message letting the user know the winner.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class Nim {

    static String currentPlayer = "";
    static String waitingPlayer = "";

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int pile1 = 3;
        int pile2 = 4;
        int pile3 = 5;

        String player1 = " ";
        String player2 = " ";

        int currentPile = 0;

        System.out.print("\nPlayer 1, enter your name: ");
        player1 = scan.nextLine();

        System.out.print("Player 2, enter your name: ");
        player2 = scan.nextLine();

        while (pile1 > 0 || pile2 > 0 || pile3 > 0 ) {

            displayAsterisks(pile1, pile2, pile3);

            currentPlayer = changePlayer(player1, player2);

            if ((pile1 + pile2 + pile3) == 1) {
                System.out.println("\n" + currentPlayer + ", you must take the last remaining counter, so you lose. " + waitingPlayer + " wins.\n");
                System.exit(0);
            }

            System.out.print("\n" + currentPlayer + ", choose a pile: ");
            String pile = (scan.nextLine()).toUpperCase();

            while (!pile.equals("A") && !pile.equals("B") && !pile.equals("C")) {
                System.out.print("That pile does not exist. Try again: ");
                pile = (scan.nextLine()).toUpperCase();
            }

            currentPile = getCurrentPile(pile, pile1, pile2, pile3);

            while (currentPile < 1) {

                if (pile.equals("A") || pile.equals("B") || pile.equals("C")) {
                    System.out.print("Nice try, " + currentPlayer + ". Pile " + pile.toUpperCase() + " is empty. Choose again:");
                }
                pile = (scan.nextLine()).toUpperCase();

                currentPile = getCurrentPile(pile, pile1, pile2, pile3);

            }

            System.out.print("How many to remove from pile " + pile.toUpperCase() + "?  ");
            int num = Integer.parseInt(scan.nextLine());

            while (num < 1 || currentPile < num) {
                if (num < 1) {
                    System.out.print("You must choose at least 1. How many? ");
                    num = Integer.parseInt(scan.nextLine());
                } else if (currentPile < num) {
                    System.out.print("Pile " + pile + " doesn't have that many. Try again: ");
                    num = Integer.parseInt(scan.nextLine());
                }
            }

            switch (pile) {
                case "A":
                    pile1 = pile1 - num;
                    break;
                case "B":
                    pile2 = pile2 - num;
                    break;
                case "C":
                    pile3 = pile3 - num;
                    break;
                default:
                    System.out.println();
            }

        }

        currentPlayer = changePlayer(player1, player2);

        System.out.println("\n" + currentPlayer + ", there are no counters left, so you WIN");

    }

    public static String changePlayer(String player1, String player2) {

        if (currentPlayer.equals(player1)) {
            currentPlayer = player2;
            waitingPlayer = player1;
        } else {
            currentPlayer = player1;
            waitingPlayer = player2;
        }

        return currentPlayer;

    }

    public static int getCurrentPile(String pile, int pile1, int pile2, int pile3) {

        int currentPile = 0;

        switch(pile) {
            case "A":
                currentPile = pile1;
                break;
            case "B":
                currentPile = pile2;
                break;
            case "C":
                currentPile = pile3;
                break;
            default:
                currentPile = 0;
                System.out.print("That is not a pile. Try again: ");
        }

        return currentPile;

    }

    public static int getLargest(int pile1, int pile2, int pile3) {

        int largest = 0;

        if ( pile1 > pile2 && pile1 > pile3) {
            largest = pile1;
        } else if (pile2 > pile1 && pile2 > pile3) {
            largest = pile2;
        } else {
            largest = pile3;
        }

        return largest;

    }

    public static void displayAsterisks(int pile1, int pile2, int pile3) {

        int largest = getLargest( pile1, pile2, pile3);

        System.out.println();

        for (int i = largest; i > 0; i-- ) {
            if (pile1 >= i) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }
            for (int j = 0; j < 5; j++ ) {
                System.out.print(" ");
            }
            if (pile2 >= i) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }
            for (int j = 0; j < 5; j++ ) {
                System.out.print(" ");
            }
            if (pile3 >= i) {
                System.out.println("*");
            } else {
                System.out.println(" ");
            }
        }

        System.out.println("A     B     C");

    }

}