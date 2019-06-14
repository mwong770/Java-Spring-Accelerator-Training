/**
 * This app allows a user to play a single hand of "blackjack".
 * Displays the user's and "dealer's" numbers and total, followed by a message
 * indicating who has the higher number.
 * @params args
 *
 */

package com.company;

import java.util.Random;

public class BabyBlackjack {

    public static void main(String[] args) {

        Random randomGenerator = new Random();

        int userCard1 = randomGenerator.nextInt(10) + 1;
        int userCard2 = randomGenerator.nextInt(10) + 1;
        int dealerCard1 = randomGenerator.nextInt(10) + 1;
        int dealerCard2 = randomGenerator.nextInt(10) + 1;

        int userTotal = userCard1 + userCard2;
        int dealerTotal = dealerCard1 + dealerCard2;

        System.out.println("\nBaby Blackjack!\n");

        System.out.println("You drew " + userCard1 + " and " + userCard2 + ".");
        System.out.println("Your total is " + userTotal + ".\n");

        System.out.println("The dealer has " + dealerCard1 + " and " + dealerCard2 + ".");
        System.out.println("Dealer's total is " + dealerTotal + ".\n");

        if (userTotal > dealerTotal) {
            System.out.println("YOU WIN!");
        } else {
            System.out.println("Dealer wins. Better luck next time.");
        }

    }

}
